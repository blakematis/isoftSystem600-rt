package com.isoft.system600.comm;

import javax.baja.nre.annotations.NiagaraType;
import javax.baja.serial.*;
import javax.baja.status.BStatus;
import javax.baja.sys.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


@NiagaraType
public class BEnerDaptSerialHelper extends BComponent {

    public static final String noPort = "none";
    public static final Property status;
    public static final Property portName;
    public static final Property baudRate;
    public static final Property dataBits;
    public static final Property stopBits;
    public static final Property parity;
    public static final Property flowControlMode;
    private static final BIcon icon;
    public static Logger log;
    private BISerialHelperParent host;
    private BEnerDaptSerialPort port = null;
    private boolean openPortFailure = false;
    private boolean portParamFailure = false;
    private boolean flowControlFailure = false;

    public BStatus getStatus() {
        return (BStatus)this.get(status);
    }

    public void setStatus(BStatus v) {
        this.set(status, v, (Context)null);
    }

    public String getPortName() {
        return this.getString(portName);
    }

    public void setPortName(String v) {
        this.setString(portName, v, (Context)null);
    }

    public BBaudRate getBaudRate() {
        return (BBaudRate)this.get(baudRate);
    }

    public void setBaudRate(BBaudRate v) {
        this.set(baudRate, v, (Context)null);
    }

    public BSerialDataBits getDataBits() {
        return (BSerialDataBits)this.get(dataBits);
    }

    public void setDataBits(BSerialDataBits v) {
        this.set(dataBits, v, (Context)null);
    }

    public BSerialStopBits getStopBits() {
        return (BSerialStopBits)this.get(stopBits);
    }

    public void setStopBits(BSerialStopBits v) {
        this.set(stopBits, v, (Context)null);
    }

    public BSerialParity getParity() {
        return (BSerialParity)this.get(parity);
    }

    public void setParity(BSerialParity v) {
        this.set(parity, v, (Context)null);
    }

    public BSerialFlowControlMode getFlowControlMode() {
        return (BSerialFlowControlMode)this.get(flowControlMode);
    }

    public void setFlowControlMode(BSerialFlowControlMode v) {
        this.set(flowControlMode, v, (Context)null);
    }


    public void doOpenPort(){
        try {
            open(getPortName());
            log.info("port opened");

        } catch (Exception e) {
            log.warning("unable to open port");
            e.printStackTrace();
        }
    }


    public void doListPorts(){
        port.listPort();
    }

    public void doIsPortOpen(){ port.isOpen(); }

    public static final Action openPort = newAction(Flags.SUMMARY, null, null);

    public void openPort(){ invoke(openPort, null, null);}

    public static final Action isPortOpen = newAction(Flags.SUMMARY, null, null);

    public void isPortOpen(){ invoke(isPortOpen, null, null);}

    public static final Action listPorts = newAction(Flags.SUMMARY, null, null);

    public void listPorts(){ invoke(listPorts, null, null);}

    public BEnerDaptSerialHelper() {
    }

    public void setSerialHelperParent(BISerialHelperParent host) {
        this.host = host;
    }

    public BISerialHelperParent getSerialHelperParent() {
        return this.host != null ? this.host : (BISerialHelperParent)this.getParent();
    }

    public boolean isParentLegal(BComponent parent) {
        return parent instanceof BISerialHelperParent;
    }

    public BISerialPort open(String owner) throws Exception {

        String errMsg;
        try{
            log.info("Opening SerialPort");
            port = new BEnerDaptSerialPort();
            port.open(getPortName());
        } catch (Exception e) {
            errMsg = "Error opening the port " + this.getPortName();
            log.log(Level.SEVERE, errMsg, e);
            this.openPortFailure = true;
            this.computeStatus();
            if (this.port != null) {
                this.port.close();
            }
        }

        this.openPortFailure = false;

        try {
            this.port.setSerialPortParams(this.getBaudRate(), this.getDataBits(), this.getStopBits(), this.getParity());
        } catch (UnsupportedOperationException var8) {
            errMsg = "Unsupported comm parameter for " + this.getPortName();
            log.log(Level.SEVERE, errMsg, var8);
            this.portParamFailure = true;
            this.computeStatus();
            if (this.port != null) {
                this.port.close();
            }

            throw var8;
        } catch (Exception var9) {
            errMsg = "Exception setting comm parameters for " + this.getPortName();
            log.log(Level.SEVERE, errMsg, var9);
            this.portParamFailure = true;
            this.computeStatus();
            if (this.port != null) {
                this.port.close();
            }

            throw var9;
        }

        this.portParamFailure = false;

        try {
            this.port.setFlowControlMode(this.getFlowControlMode());
        } catch (UnsupportedOperationException var10) {
            errMsg = "Unsupported comm parameter (flow control mode) for " + this.getPortName();
            log.log(Level.SEVERE, errMsg, var10);
            this.flowControlFailure = true;
            this.computeStatus();
            if (this.port != null) {
                this.port.close();
            }

            throw var10;
        } catch (Exception var11) {
            errMsg = "Exception setting flow control mode for " + this.getPortName();
            log.log(Level.SEVERE, errMsg, var11);
            this.flowControlFailure = true;
            this.computeStatus();
            if (this.port != null) {
                this.port.close();
            }

            throw var11;
        }

        this.flowControlFailure = false;
        this.computeStatus();
        return this.port;
    }

    public void changed(Property property, Context context) {
        if (this.getSerialHelperParent() != null) {
            this.getSerialHelperParent().changed(property, context);
        }

        if (this.isRunning()) {
            if (property.equals(portName)) {
                if (this.getPortName().equals("none")) {
                    this.computeStatus();
                }

                this.getSerialHelperParent().reopenPort();
            } else {
                if (!property.equals(dataBits) && !property.equals(stopBits) && !property.equals(parity) && !property.equals(baudRate)) {
                    if (property.equals(flowControlMode)) {
                        try {
                            if (this.port != null) {
                                this.port.setFlowControlMode(this.getFlowControlMode());
                            }
                        } catch (UnsupportedOperationException var4) {
                            log.log(Level.SEVERE, "Unsupported comm parameter", var4);
                            this.flowControlFailure = true;
                            this.computeStatus();
                            throw new RuntimeException("Unsupported comm parameter");
                        }

                        this.flowControlFailure = false;
                        this.computeStatus();
                    } else {
                        super.changed(property, context);
                    }
                } else if (this.port != null) {
                    this.updatePortParms(this.getBaudRate(), this.getDataBits(), this.getStopBits(), this.getParity());
                }

            }
        }
    }

    private void updatePortParms(BBaudRate baud, BSerialDataBits dBits, BSerialStopBits sBits, BSerialParity parity) {
        try {
            this.port.setSerialPortParams(baud, dBits, sBits, parity);
        } catch (UnsupportedOperationException var6) {
            log.log(Level.SEVERE, "Unsupported comm parameter", var6);
            this.portParamFailure = true;
            this.computeStatus();
            throw new RuntimeException("Unsupported comm parameter");
        }

        this.portParamFailure = false;
        this.computeStatus();
    }

    private void computeStatus() {
        if (this.getPortName().equals("none")) {
            this.setStatus(BStatus.down);
        } else {
            if (!this.openPortFailure && !this.portParamFailure && !this.flowControlFailure) {
                this.setStatus(BStatus.ok);
            } else {
                this.setStatus(BStatus.fault);
            }

        }
    }

    public String toString(Context context) {
        StringBuffer sb = new StringBuffer();
        sb.append(this.getPortName());
        sb.append(", ");
        sb.append(this.getBaudRate().getOrdinal());
        sb.append(", ");
        sb.append(this.getDataBits().getOrdinal());
        sb.append(", ");
        sb.append(this.getStopBits().getOrdinal());
        sb.append(", ");
        sb.append(this.getParity());
        return sb.toString();
    }



    static {
        status = newProperty(3, BStatus.down, (BFacets)null);
        portName = newProperty(64, "none", (BFacets)null);
        baudRate = newProperty(0, BSerialBaudRate.baud9600, (BFacets)null);
        dataBits = newProperty(0, BSerialDataBits.dataBits8, (BFacets)null);
        stopBits = newProperty(0, BSerialStopBits.stopBit1, (BFacets)null);
        parity = newProperty(0, BSerialParity.none, (BFacets)null);
        flowControlMode = newProperty(0, BSerialFlowControlMode.none, (BFacets)null);
        icon = BIcon.std("connection.png");
        log = Logger.getLogger("SerialHelper");
    }

    public BIcon getIcon() {
        return icon;
    }

    public static final Type TYPE = Sys.loadType(BEnerDaptSerialHelper.class);

    public Type getType(){
        return TYPE;
    }
}
