package com.isoft.system600.comm;

import com.isoft.system600.BSystem600Network;
import com.tridium.basicdriver.comm.Comm;
import com.tridium.basicdriver.comm.CommReceiver;
import com.tridium.basicdriver.comm.CommTransactionManager;
import com.tridium.basicdriver.comm.CommTransmitter;
import com.tridium.basicdriver.message.Message;
import com.tridium.basicdriver.message.ReceivedMessage;
import com.tridium.basicdriver.util.BasicException;

import javax.baja.serial.BISerialPort;
import javax.baja.sys.BRelTime;
import javax.baja.sys.Clock;
import java.io.*;

import java.util.logging.Logger;



public class EnerDaptSerialComm extends Comm{


    private Logger log = Logger.getLogger(Logger.class.getName());
    private static final long MIN_SLEEP_TIME = 10L;
    private int timeout = 2000;

    private String portName;

    private BISerialPort serialPortHandler;
    private InputStream inputStream;
    private OutputStream outputStream;
    private Thread rxThread;
    private long lastRecvMessageTicks = 0L;

    public EnerDaptSerialComm(BSystem600Network system600Network, CommReceiver rDriver) {
        super(system600Network, rDriver);
    }

    public EnerDaptSerialComm(BSystem600Network system600Network, CommReceiver rDriver, CommTransmitter tDriver) {
        super(system600Network, rDriver, tDriver);
    }

    public EnerDaptSerialComm(BSystem600Network system600Network, CommReceiver rDriver, CommTransmitter tDriver, CommTransactionManager manager) {
        super(system600Network, rDriver, tDriver, manager);
    }

    public BSystem600Network getNetwork(){
        return (BSystem600Network) super.getNetwork();
    }
    protected boolean started() throws Exception {
        try{
            this.serialPortHandler = this.getNetwork().getEnerDaptSerialPortConfig().open(this.getNetwork().getEnerDaptSerialPortConfig().getPortName());
            this.serialPortHandler.enableReceiveTimeout(timeout);
            inputStream = this.serialPortHandler.getInputStream();
            outputStream = this.serialPortHandler.getOutputStream();
        } catch (Exception var6) {
            var6.printStackTrace();
            var6.getMessage();
            String errMsg = "Error opening and configuring the serial port";
            this.getNetwork().getLog().error(errMsg, var6);
            if (this.inputStream != null) {
                try {
                    this.inputStream.close();
                } catch (Exception var5) {
                    this.getNetwork().getLog().error("Unable to close serial input stream.", var5);
                }
            }

            if (this.outputStream != null) {
                try {
                    this.outputStream.close();
                } catch (Exception var4) {
                    this.getNetwork().getLog().error("Unable to close serial output stream.", var4);
                }
            }

            if (this.serialPortHandler != null) {
                this.serialPortHandler.close();
            }

            throw var6;
    }

        this.getCommReceiver().setInputStream(this.inputStream);
        this.getCommTransmitter().setOutputStream(this.outputStream);
        this.rxThread = new Thread(this.getCommReceiver(), "SerialRcv:" + this.getNetwork().getName());
        this.getCommReceiver().setAlive(true);
        this.rxThread.start();
        this.rxThread.setPriority(5);
        return true;
    }

    @Override
    protected void stopped() throws Exception {
        this.getCommReceiver().setAlive(false);
        if (this.getCommReceiver() != null && this.rxThread != null) {
            this.rxThread.interrupt();
        }

        if (this.inputStream != null) {
            try {
                this.inputStream.close();
            } catch (Exception var3) {
                this.getNetwork().getLog().error("Unable to close serial input stream.", var3);
            }
        }

        if (this.outputStream != null) {
            try {
                this.outputStream.close();
            } catch (Exception var2) {
                this.getNetwork().getLog().error("Unable to close serial output stream.", var2);
            }
        }

        if (this.serialPortHandler != null) {
            this.serialPortHandler.disableReceiveTimeout();
            this.serialPortHandler.close();
        }

        this.inputStream = null;
        this.outputStream = null;
    }


    public Message transmit(Message msg, BRelTime responseTimeout, int retryCount) throws BasicException {
        if (msg == null) {
            return null;
        } else if (!msg.getResponseExpected()) {
            this.transmitNoResponse(msg);
            return null;
        } else {
            this.performInterMessageDelay();
            return super.transmit(msg, responseTimeout, retryCount);
        }
    }

    public void transmitNoResponse(Message msg) throws BasicException {
        if (msg != null) {
            if (!this.isCommStarted()) {
                throw new BasicException("Communication handler service not started.");
            } else {
                this.performInterMessageDelay();
                super.transmitNoResponse(msg);
            }
        }
    }

    public void receive(ReceivedMessage msg) {
        if (msg != null) {
            this.setReceivedMessageTicks(Clock.ticks());
            super.receive(msg);
        }
    }

    protected void performInterMessageDelay() {
        long minDelay = this.getNetwork().getInterMessageDelay().getMillis();
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
        return this.serialPortHandler;
    }

}
