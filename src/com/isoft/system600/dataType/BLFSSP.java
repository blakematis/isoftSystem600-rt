package com.isoft.system600.dataType;

import javax.baja.control.BEnumWritable;
import javax.baja.sys.BEnumRange;
import javax.baja.sys.BFacets;
import javax.baja.sys.Sys;
import javax.baja.sys.Type;

public class BLFSSP
        extends BEnumWritable
{
    public Type getType()
    {
        return TYPE;
    }

    public static final Type TYPE = Sys.loadType(BLFSSP.class);

    public BLFSSP()
    {
        setFacets(localFacets);
    }

    static final String[] enumTag = { "OFF", "SLOW", "FAST" };
    static BFacets localFacets = BFacets.makeEnum(BEnumRange.make(enumTag));
}