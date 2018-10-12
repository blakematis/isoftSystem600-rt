package com.maxline.isoftSystem600.dataType;

import javax.baja.control.BBooleanWritable;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.sys.BFacets;
import javax.baja.sys.Sys;
import javax.baja.sys.Type;

@NiagaraType
public class BLCTLR
        extends BBooleanWritable
{
    public Type getType()
    {
        return TYPE;
    }

    public static final Type TYPE = Sys.loadType(BLCTLR.class);

    public BLCTLR()
    {
        setFacets(localFacets);
    }

    static BFacets localFacets = BFacets.makeBoolean("DAY", "NIGHT");
}