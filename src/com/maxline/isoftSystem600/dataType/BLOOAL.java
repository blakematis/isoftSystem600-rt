package com.maxline.isoftSystem600.dataType;

import javax.baja.control.BEnumWritable;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.sys.BEnumRange;
import javax.baja.sys.BFacets;
import javax.baja.sys.Sys;
import javax.baja.sys.Type;

@NiagaraType
public class BLOOAL
        extends BEnumWritable
{
    public Type getType()
    {
        return TYPE;
    }

    public static final Type TYPE = Sys.loadType(BLOOAL.class);

    public BLOOAL()
    {
        setFacets(localFacets);
    }

    static final String[] enumTag = { "OFF", "ON", "AUTO" };
    static BFacets localFacets = BFacets.makeEnum(BEnumRange.make(enumTag));
}

