package com.maxline.isoftSystem600.comm;


import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;

import javax.baja.data.BIDataValue;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.serial.*;
import javax.baja.sys.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@NiagaraType
public class BEnerDaptSerialPort extends BObject implements BISerialPort{
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.maxline.isoftSystem600.comm.BEnerDaptSerialPort(2979906276)1.0$ @*/
/* Generated Mon Oct 15 14:56:28 PDT 2018 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////
  
  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BEnerDaptSerialPort.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/

    private SerialPort serialPort;
    private int newBaudRate, newDataBits, newStopBits, newPrarity;
    private int newTimeoutMode, newReadTimeout, newWriteTimeout;


    public SerialPort getPort(){
        return serialPort;
    }

    public BEnerDaptSerialPort() {
    }

    @Override
    public void close() {
        serialPort.closePort();
    }


    public void open(String portName) {
        serialPort = SerialPort.getCommPort(portName);
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
}