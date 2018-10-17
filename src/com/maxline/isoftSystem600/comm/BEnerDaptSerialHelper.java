package com.maxline.isoftSystem600.comm;

import javax.baja.nre.annotations.NiagaraType;
import javax.baja.serial.BISerialPort;
import javax.baja.serial.BSerialHelper;
import javax.baja.sys.Sys;
import javax.baja.sys.Type;

@NiagaraType
public class BEnerDaptSerialHelper extends BSerialHelper {
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.maxline.isoftSystem600.comm.BEnerDaptSerialHelper(2979906276)1.0$ @*/
/* Generated Mon Oct 15 14:56:28 PDT 2018 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////


/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/
    private BEnerDaptSerialPort bEnerDaptSerialPort;

    public BEnerDaptSerialHelper(){

    }

    @Override
    public BISerialPort open(String owner) throws Exception {
        try {
            bEnerDaptSerialPort = new BEnerDaptSerialPort();
            bEnerDaptSerialPort.open(owner);
        } catch (Exception e){
            log.warning("unable to open port " + e.getMessage());
        }
        return bEnerDaptSerialPort;
    }

    public static final Type TYPE = Sys.loadType(BEnerDaptSerialHelper.class);

    public Type getType(){
        return TYPE;
    }
}
