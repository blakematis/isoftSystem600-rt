package com.isoft.system600.comm;

import com.isoft.system600.BSystem600Network;
import com.tridium.basicdriver.BBasicNetwork;
import com.tridium.basicdriver.comm.BCommPlugIn;
import com.tridium.basicdriver.comm.CommReceiver;
import com.tridium.basicdriver.message.Message;
import com.tridium.basicdriver.message.ReceivedMessage;
import com.tridium.basicdriver.util.BasicException;

import javax.baja.serial.BISerialHelperParent;
import javax.baja.serial.BISerialPort;
import javax.baja.serial.BSerialHelper;
import javax.baja.sys.*;
import java.io.InputStream;
import java.io.OutputStream;

public class BEnerDaptSerialComm extends BCommPlugIn implements BIEnerDaptSerialHelperParent {
    public static final Property interMessageDelay = newProperty(0, BRelTime.make(20L), BFacets.make("showMilliseconds", BBoolean.TRUE, "min", BRelTime.make(5L), "max", BRelTime.SECOND));
    public static final Property enerDaptSerialPortConfig = newProperty(Flags.SUMMARY, new BEnerDaptSerialHelper(), null);
    private static final long MIN_SLEEP_TIME = 10L;
    private BISerialPort serialPort;
    private InputStream in;
    private OutputStream out;
    private int timeout = 2000;
    private Thread rxThread;
    private long lastRecvMessageTicks = 0L;

    public BEnerDaptSerialComm() {
    }

    public BRelTime getInterMessageDelay() {
        return (BRelTime)this.get(interMessageDelay);
    }

    public void setInterMessageDelay(BRelTime v) {
        this.set(interMessageDelay, v, (Context)null);
    }

    public BEnerDaptSerialHelper getEnerDaptSerialPortConfig() {
        return (BEnerDaptSerialHelper)this.get(enerDaptSerialPortConfig);
    }

    public void setEnerDaptSerialPortConfig(BEnerDaptSerialHelper v) {
        this.set(enerDaptSerialPortConfig, v, (Context)null);
    }


    public final void reopenPort() {
        try {
            String newPort = this.getEnerDaptSerialPortConfig().getPortName();
            if (newPort.equals("none")) {
                this.getNetwork().configFail("No port selected for serial communication.");
                this.getNetwork().stopComm();
                return;
            }

            this.restartSerialNetwork();
        } catch (Exception var2) {
            this.getNetwork().getLog().error("BSerialNetwork caught exception in reopenPort(): ", var2);
        }

    }

    private void restartSerialNetwork() throws Exception {
        BBasicNetwork net = this.getNetwork();
        if (!net.isDisabled() && !net.isDown() && !net.isFatalFault()) {
            if (net.getLog().isTraceOn()) {
                net.getLog().trace(this.getName() + " *** Restarting serial comm ***");
            }

            net.stopComm();
            net.startComm();
        }

    }

    public boolean commStarted() throws Exception {
        try {

            this.serialPort = this.getEnerDaptSerialPortConfig().open(this.getNetwork().getName());
            this.serialPort.enableReceiveTimeout(2000);
            this.in = this.serialPort.getInputStream();
            this.out = this.serialPort.getOutputStream();
        } catch (Exception var6) {
            String errMsg = "Error opening and configuring the serial port";
            this.getNetwork().getLog().error(errMsg, var6);
            if (this.in != null) {
                try {
                    this.in.close();
                } catch (Exception var5) {
                    this.getNetwork().getLog().error("Unable to close serial input stream.", var5);
                }
            }

            if (this.out != null) {
                try {
                    this.out.close();
                } catch (Exception var4) {
                    this.getNetwork().getLog().error("Unable to close serial output stream.", var4);
                }
            }

            if (this.serialPort != null) {
                this.serialPort.close();
            }

            throw var6;
        }

        CommReceiver commRx = this.getCommReceiver();
        commRx.setInputStream(this.in);
        this.getCommTransmitter().setOutputStream(this.out);
        this.rxThread = new Thread(commRx, "SerialRcv:" + this.getNetwork().getName());
        commRx.setAlive(true);
        this.rxThread.start();
        this.rxThread.setPriority(5);
        return true;
    }

    public void commStopped() throws Exception {
        this.getCommReceiver().setAlive(false);
        if (this.getCommReceiver() != null && this.rxThread != null) {
            this.rxThread.interrupt();
        }

        if (this.in != null) {
            try {
                this.in.close();
            } catch (Exception var3) {
                this.getNetwork().getLog().error("Unable to close serial input stream.", var3);
            }
        }

        if (this.out != null) {
            try {
                this.out.close();
            } catch (Exception var2) {
                this.getNetwork().getLog().error("Unable to close serial output stream.", var2);
            }
        }

        if (this.serialPort != null) {
            this.serialPort.disableReceiveTimeout();
            this.serialPort.close();
        }

        this.in = null;
        this.out = null;
    }

    public Message transmit(Message msg, BRelTime responseTimeout, int retryCount) throws BasicException {
        if (msg == null) {
            return null;
        } else if (!msg.getResponseExpected()) {
            this.transmitNoResponse(msg);
            return null;
        } else {
            this.performInterMessageDelay();
            return this.getComm().transmit(msg, responseTimeout, retryCount);
        }
    }

    public void transmitNoResponse(Message msg) throws BasicException {
        if (msg != null) {
            if (!this.getComm().isCommStarted()) {
                throw new BasicException("Communication handler service not started.");
            } else {
                this.performInterMessageDelay();
                this.getComm().transmitNoResponse(msg);
            }
        }
    }

    public void receive(ReceivedMessage msg) {
        if (msg != null) {
            this.setReceivedMessageTicks(Clock.ticks());
            this.getComm().receive(msg);
        }
    }

    protected void performInterMessageDelay() {
        long minDelay = ((BSystem600Network)this.getNetwork()).getInterMessageDelay().getMillis();
        if (minDelay > 0L) {
            long difference = Clock.ticks() - this.lastRecvMessageTicks;
            if (difference < minDelay) {
                long sleepTime = Math.max(minDelay - difference, 10L);

                try {
                    Thread.sleep(sleepTime);
                } catch (Exception var8) {
                    var8.printStackTrace();
                }

            }
        }
    }

    protected void setReceivedMessageTicks(long ticks) {
        this.lastRecvMessageTicks = ticks;
    }

    public BISerialPort getSerialPort() {
        return this.serialPort;
    }

    public String toString(Context cx) {
        StringBuffer sb = new StringBuffer();
        BEnerDaptSerialHelper sh = this.getEnerDaptSerialPortConfig();
        sb.append(sh.getPortName());
        sb.append(' ');
        sb.append(sh.getBaudRate());
        sb.append(',');
        sb.append(sh.getDataBits());
        sb.append(',');
        sb.append(sh.getStopBits());
        sb.append(',');
        sb.append(sh.getParity());
        return sb.toString();
    }

    public static final Type TYPE = Sys.loadType(BEnerDaptSerialComm.class);
    public Type getType(){
        return TYPE;
    }
}
