package com.isoft.system600.comm;

import javax.baja.nre.annotations.NiagaraType;
import javax.baja.serial.BISerialPort;
import javax.baja.serial.BSerialHelper;
import javax.baja.sys.*;


@NiagaraType
public class BEnerDaptSerialHelper extends BSerialHelper {
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.maxline.isoftSystem600.comm.BEnerDaptSerialHelper(2979906276)1.0$ @*/
/* Generated Mon Oct 15 14:56:28 PDT 2018 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////


    /*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/
    private BEnerDaptSerialPort bEnerDaptSerialPort = null;



    @Override
    public BISerialPort open(String owner) throws Exception {
        try {
            log.info("Opening SerialPort");
            bEnerDaptSerialPort = new BEnerDaptSerialPort();
            bEnerDaptSerialPort.open(owner);
        } catch (Exception e){
            e.printStackTrace();
            e.getMessage();
            log.warning("unable to open port " + e.getMessage());
        }
        return bEnerDaptSerialPort;
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
        bEnerDaptSerialPort.listPort();
    }

    public void doIsPortOpen(){
        bEnerDaptSerialPort.isOpen();
    }

    public static final Action openPort = newAction(Flags.SUMMARY, null, null);

    public void openPort(){ invoke(openPort, null, null);}

    public static final Action isPortOpen = newAction(Flags.SUMMARY, null, null);

    public void isPortOpen(){ invoke(isPortOpen, null, null);}

    public static final Action listPorts = newAction(Flags.SUMMARY, null, null);

    public void listPorts(){ invoke(listPorts, null, null);}


    public static final Type TYPE = Sys.loadType(BEnerDaptSerialHelper.class);

    public Type getType(){
        return TYPE;
    }
}
