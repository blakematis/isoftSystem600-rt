package com.maxline.isoftSystem600.comm;

import com.fazecast.jSerialComm.SerialPort;
import com.tridium.basicdriver.comm.Comm;
import com.tridium.basicdriver.comm.CommReceiver;
import com.tridium.basicdriver.comm.CommTransactionManager;
import com.tridium.basicdriver.comm.CommTransmitter;
import com.tridium.basicdriver.serial.BSerialNetwork;


import javax.baja.log.Log;
import javax.baja.serial.BISerialPort;
import javax.baja.serial.BSerialHelper;
import javax.baja.sys.Property;

import java.io.*;
import java.util.Enumeration;
import java.util.TooManyListenersException;
import java.util.logging.Logger;

import static com.tridium.basicdriver.serial.BSerialNetwork.serialPortConfig;


public class EnerDaptSerialComm extends Comm implements Runnable{


    private Logger log = Logger.getLogger(Logger.class.getName());
    private static final long MIN_SLEEP_TIME = 10L;
    private int timeout = 2000;
    private int threadTime = 0;
    private String portName;

    private BEnerDaptSerialPort serialPort;
    private InputStream inputStream;
    private OutputStream outputStream;
    private Thread rxThread;
    private long lastRecvMessageTicks = 0L;

    public EnerDaptSerialComm(BSerialNetwork serialNetwork, CommReceiver rDriver) {
        super(serialNetwork, rDriver);
    }

    public EnerDaptSerialComm(BSerialNetwork serialNetwork, CommReceiver rDriver, CommTransmitter tDriver) {
        super(serialNetwork, rDriver, tDriver);
    }

    public EnerDaptSerialComm(BSerialNetwork serialNetwork, CommReceiver rDriver, CommTransmitter tDriver, CommTransactionManager manager) {
        super(serialNetwork, rDriver, tDriver, manager);
    }
    protected boolean started() throws Exception {
        try{
            this.serialPort = (BEnerDaptSerialPort)((BSerialNetwork)this.getNetwork()).getSerialPortConfig().open(((BSerialNetwork) this.getNetwork()).getSerialPortConfig().getPortName());

            this.serialPort.enableReceiveTimeout(timeout);
            inputStream = this.serialPort.getInputStream();
            outputStream = this.serialPort.getOutputStream();
        } catch (Exception var6) {
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

            if (this.serialPort != null) {
                this.serialPort.close();
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

        if (this.serialPort != null) {
            this.serialPort.disableReceiveTimeout();
            this.serialPort.close();
        }

        this.inputStream = null;
        this.outputStream = null;
    }

    /**
     * listport()
     *
     * Lists all of the available CommPorts on the system
     * that the method is ran on.
     */
    @SuppressWarnings("rawtypes")
    public void listPort(){
        SerialPort[] commPortIdentifiers = SerialPort.getCommPorts();

        log.info("Available com " + commPortIdentifiers);

        for(SerialPort port: commPortIdentifiers){
            log.info("port" + port.toString());
            log.info(port.getDescriptivePortName() + " : " + port.getSystemPortName());
        }

    }

    /**
     * selectPort(String portName)
     *
     * selects the port by its port name.
     * @param portName - String value for the portName to select.
     */
    @SuppressWarnings("rawtypes")
    public void selectPort(String portName){
        SerialPort[] commPortIdentifiers = SerialPort.getCommPorts();
        for(SerialPort port: commPortIdentifiers){
            if(port.getSystemPortName().equals(portName)){
                this.portName = portName;
            }
        }

        openPort();
    }



    private void openPort(){
        if(portName == "" || portName == null){
            log.warning(portName + " commPort is opened");
        }else{
            log.info(portName + " trying to connect");
            try{
                serialPort.open(portName);
            }catch (Exception e){
                String msg = portName + " is running" + e.getMessage();
                throw new RuntimeException(msg);
            }
        }
    }

    private void checkPort(){
        if(portName == null || portName == ""){
            throw new RuntimeException("selectPort(string portName) commPort is null");
        }

        if(serialPort == null){
            throw new RuntimeException("SerialPort is null");
        }
    }

    /**
     * write(String message)
     *
     * This is for writing out of the comm port.
     * @param message - The message to write out.
     */
    public void write(String message){
        checkPort();

        try{
            outputStream = new BufferedOutputStream(serialPort.getOutputStream());
        }catch (IOException e){
            throw new RuntimeException("OutputStream:" + e.getMessage());
        }

        try{
            outputStream.write(message.getBytes());
        }catch (IOException e){
            throw new RuntimeException(" : " + e.getMessage());
        }finally {
            try{
                outputStream.close();
            }catch (Exception e){
            }
        }
    }

    /**
     * startRead(int time)
     *
     * @param time the time to read for.
     */
    public void startRead(int time){
        checkPort();

        try{
            inputStream = new BufferedInputStream(serialPort.getInputStream());
        }catch(IOException e){
            throw new RuntimeException("InputStream " + e.getMessage());
        }

        /*try{
            //
        } catch (TooManyListenersException e){
            throw new RuntimeException(e.getMessage());
        }
        */

        //serialPort
        if(time > 0){
            this.threadTime = time*1000;
            Thread t = new Thread(this);
            t.start();
            log.info("reading..." + threadTime);
        }
    }

    public void close(){
        serialPort.close();
        serialPort = null;
        portName = null;
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        try{
            Thread.sleep(threadTime);
            serialPort.close();
            log.info("port close");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

   /* @Override
    public void serialEvent(SerialPortEvent serialPortEvent) {
        switch (serialPortEvent.getEventType()){
            case SerialPortEvent.BI: //Break Interrupt
            case SerialPortEvent.OE: //Overrun error
            case SerialPortEvent.FE: //Framing error
            case SerialPortEvent.PE: //Parity error
            case SerialPortEvent.CD: //Carrier Detect
            case SerialPortEvent.CTS: //Clear to send
            case SerialPortEvent.DSR: //Data set ready
            case SerialPortEvent.RI: //Ring indicator
            case SerialPortEvent.OUTPUT_BUFFER_EMPTY: //Output buffer is empty
                break;
                case SerialPortEvent.DATA_AVAILABLE: //Data available at the serial port
                    byte[] readBuffer = new byte[1024];
                    String readStr= "";
                    String s2 = "";

                    try{
                        while(inputStream.available() > 0){
                            inputStream.read(readBuffer);
                            readStr += new String(readBuffer).trim();
                        }

                        s2 = new String(readBuffer).trim();
                        log.info(readStr.length() + " : " + readStr);
                        log.info(s2);
                    } catch (IOException e){
                        log.warning("InputStream: " + e.getMessage());
                    }
        }
    }
    */
    public BISerialPort getSerialPort(){
        return serialPort;
    }

}
