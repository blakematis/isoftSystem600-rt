package com.maxline.isoftSystem600.dataType;

import javax.baja.control.BEnumWritable;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.sys.BEnumRange;
import javax.baja.sys.BFacets;
import javax.baja.sys.Sys;
import javax.baja.sys.Type;

@NiagaraType
public class BLFSSL
        extends BEnumWritable
{
    public Type getType()
    {
        return TYPE;
    }

    public static final Type TYPE = Sys.loadType(BLFSSL.class);

    public BLFSSL()
    {
        setFacets(localFacets);
    }

    static final String[] enumTag = { "OFF", "SLOW", "FAST" };
    static BFacets localFacets = BFacets.makeEnum(BEnumRange.make(enumTag));
}
