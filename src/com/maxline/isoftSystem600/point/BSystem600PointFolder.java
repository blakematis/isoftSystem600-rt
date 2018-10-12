package com.maxline.isoftSystem600.point;

import com.maxline.isoftSystem600.BSystem600Device;
import com.maxline.isoftSystem600.BSystem600Network;
import javax.baja.driver.point.BPointFolder;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.sys.Sys;
import javax.baja.sys.Type;

@NiagaraType
public class BSystem600PointFolder
        extends BPointFolder
{
    public Type getType()
    {
        return TYPE;
    }

    public static final Type TYPE = Sys.loadType(BSystem600PointFolder.class);

    public final BSystem600Network getSystem600Network()
    {
        return (BSystem600Network)getNetwork();
    }

    public final BSystem600Device getSystem600Device()
    {
        return (BSystem600Device)getDevice();
    }
}

