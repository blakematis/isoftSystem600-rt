package com.isoft.system600.point;

import com.isoft.system600.BSystem600Device;
import com.isoft.system600.BSystem600Network;

import javax.baja.driver.point.BPointDeviceExt;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.sys.Sys;
import javax.baja.sys.Type;

@NiagaraType
public class BSystem600PointDeviceExt
        extends BPointDeviceExt
{
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.maxline.isoftSystem600.point.BSystem600PointDeviceExt(2979906276)1.0$ @*/
/* Generated Mon Oct 15 14:56:41 PDT 2018 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////
  


/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/
    public Type getType()
    {
        return TYPE;
    }

    public static final Type TYPE = Sys.loadType(BSystem600PointDeviceExt.class);

    public final BSystem600Network getSystem600Network()
    {
        return (BSystem600Network)getNetwork();
    }

    public final BSystem600Device getSystem600Device()
    {
        return (BSystem600Device)getDevice();
    }

    public Type getDeviceType()
    {
        return BSystem600Device.TYPE;
    }

    public Type getPointFolderType()
    {
        return BSystem600PointFolder.TYPE;
    }

    public Type getProxyExtType()
    {
        return BSystem600ProxyExt.TYPE;
    }
}