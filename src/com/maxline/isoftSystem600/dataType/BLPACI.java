package com.maxline.isoftSystem600.dataType;

import javax.baja.control.BNumericWritable;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.sys.Sys;
import javax.baja.sys.Type;

@NiagaraType
public class BLPACI
        extends BNumericWritable
{
    public Type getType()
    {
        return TYPE;
    }

    public static final Type TYPE = Sys.loadType(BLPACI.class);
}
