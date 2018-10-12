package com.maxline.isoftSystem600;

import javax.baja.driver.BDeviceFolder;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.sys.Sys;
import javax.baja.sys.Type;

@NiagaraType
public class BSystem600DeviceFolder
        extends BDeviceFolder
{
    public Type getType()
    {
        return TYPE;
    }

    public static final Type TYPE = Sys.loadType(BSystem600DeviceFolder.class);

    public final BSystem600Network getSystem600Network()
    {
        return (BSystem600Network)getNetwork();
    }
}
