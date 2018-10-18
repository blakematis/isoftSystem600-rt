package com.isoft.system600.comm;


import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;

import javax.baja.data.BIDataValue;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.serial.*;
import javax.baja.sys.*;
import java.io.*;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.logging.Logger;

@NiagaraType
public class BEnerDaptSerialPort extends BObject implements BISerialPort{
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.isoft.system600.comm.BEnerDaptSerialPort(2979906276)1.0$ @*/
/* Generated Mon Oct 15 14:56:28 PDT 2018 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////
  
  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BEnerDaptSerialPort.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/
    public static final Logger log = Logger.getLogger(Logger.class.getName());
    private SerialPort[] commPortIdentifiers;
    private SerialPort serialPort;
    private OutputStream outputStream;
    private InputStream inputStream;
    private int threadTime = 0;
    private String portName = "";
    private int newBaudRate, newDataBits, newStopBits, newPrarity;
    private int newTimeoutMode, newReadTimeout, newWriteTimeout;


    public SerialPort getPort() {
        if (serialPort == null) {
            return null;
        }else{
            return serialPort;
        }
    }

    public BEnerDaptSerialPort() {
    }


    public BISerialPort open(String portName) {
        try {
            listPort();
            selectPortToOpen(portName);
            return this;

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public void isOpen(){
        try {
            log.info(String.valueOf(serialPort.isOpen()));
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @Override
    public String getOwner() {
        return serialPort.getDescriptivePortName();
    }

    @Override
    public String getOsPortName() {
        return serialPort.getSystemPortName();
    }

    @Override
    public void sendBreak(int i) {
        serialPort.setComPortTimeouts(i,i,i);
    }

    @Override
    public void setFlowControlMode(BSerialFlowControlMode bSerialFlowControlMode) throws UnsupportedOperationException {
        serialPort.setFlowControl(bSerialFlowControlMode.getBits());
    }

    @Override
    public BSerialFlowControlMode getFlowControlMode() {
        return BSerialFlowControlMode.make(serialPort.getFlowControlSettings());
    }

    @Override
    public BBaudRate getBaudRate() {
        return BSerialBaudRate.make(serialPort.getBaudRate());
    }

    @Override
    public BSerialDataBits getDataBits() {
        return BSerialDataBits.make(serialPort.getNumDataBits());
    }

    @Override
    public BSerialStopBits getStopBits() {
        return BSerialStopBits.make(serialPort.getNumStopBits());
    }

    @Override
    public BSerialParity getParity() {
        return BSerialParity.make(serialPort.getParity());
    }

    @Override
    public void setSerialPortParams(BBaudRate bBaudRate, BSerialDataBits bSerialDataBits, BSerialStopBits bSerialStopBits, BSerialParity bSerialParity) throws UnsupportedOperationException {
        serialPort.setComPortParameters(bBaudRate.getOrdinal(), bSerialDataBits.getOrdinal(), bSerialStopBits.getOrdinal(), bSerialParity.getOrdinal());
    }

    @Override
    public void setDTR(boolean b) {

    }

    @Override
    public boolean isDTR() {
        return false;
    }

    @Override
    public void setRTS(boolean b) {

    }

    @Override
    public boolean isRTS() {
        return false;
    }
    @Override
    public boolean isCTS() {
        return false;
    }

    @Override
    public boolean isDSR() {
        return false;
    }

    @Override
    public boolean isRI() {
        return false;
    }

    @Override
    public boolean isCD() {
        return false;
    }

    @Override
    public void enableReceiveThreshold(int i) throws UnsupportedOperationException {

    }

    @Override
    public void disableReceiveThreshold() {
    }

    @Override
    public boolean isReceiveThresholdEnabled() {
        return false;
    }

    @Override
    public int getReceiveThreshold() throws UnsupportedOperationException {
        return serialPort.getReadTimeout();
    }

    @Override
    public void enableReceiveTimeout(int i) throws UnsupportedOperationException {
       serialPort.setComPortTimeouts(i,i,i);
    }

    @Override
    public void disableReceiveTimeout() {

    }

    @Override
    public boolean isReceiveTimeoutEnabled() {
        return false;
    }

    @Override
    public int getReceiveTimeout() throws UnsupportedOperationException {
        return serialPort.getReadTimeout();
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return serialPort.getInputStream();
    }

    @Override
    public OutputStream getOutputStream() throws IOException {
        return serialPort.getOutputStream();
    }


    @Override
    public BIDataValue toDataValue() {
        return BString.make("SerialPort");
    }

    @Override
    public String toString(Context context) {
        return serialPort.toString();
    }

    @Override
    public boolean equivalent(Object o) {
        return false;
    }



    private class SerialEvent implements SerialPortDataListener{

        @Override
        public int getListeningEvents() {
            return 0;
        }

        @Override
        public void serialEvent(SerialPortEvent serialPortEvent) {

        }
    }

    /**
     * listport()
     *
     * Lists all of the available CommPorts on the system
     * that the method is ran on.
     */
    @SuppressWarnings("rawtypes")
    public void listPort(){
        try{
            commPortIdentifiers = AccessController.doPrivileged(new PrivilegedAction<SerialPort[]>() {
                @Override
                public SerialPort[] run() {
                    return SerialPort.getCommPorts();
                }
            });
        } catch (Exception e){
            e.printStackTrace();
        }

        for(SerialPort port: commPortIdentifiers){
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
    public void selectPortToOpen(String portName){
        for(SerialPort port: commPortIdentifiers){
            if(port.getSystemPortName().contains(portName)){
                this.portName = portName;
                this.serialPort = port;
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
                try{
                    this.serialPort.closePort();
                }catch (Exception e){
                    e.printStackTrace();
                }
                if(checkPort()) {


                    if (serialPort.isOpen()) {
                        log.info(serialPort.getDescriptivePortName() + " : " + serialPort.getSystemPortName() + " is open");

                    } else {
                        serialPort.openPort();
                        log.info(portName + ": is Connected!");
                    }
                }else{
                    log.warning("unable to open port : " + portName + " not found.");
                }

            }catch (Exception e){
                log.warning("unable to open port : " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    private boolean checkPort(){
        if(portName == null || portName == "" || portName == "none"){
            return false;
        }

        if(serialPort == null){
            return false;
        }
        return true;
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
        }catch (Exception e){
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
        }catch(Exception e){
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
            //Thread t = new Thread(this);
            //t.start();
            log.info("reading..." + threadTime);
        }
    }

    public void close(){
        serialPort.closePort();
        serialPort = null;
        portName = "";
        outputStream = null;
        inputStream = null;
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
    /*
    @Override
    public void run() {
        try{
            Thread.sleep(threadTime);
            serialPort.closePort();
            log.info("port close");
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    */
}