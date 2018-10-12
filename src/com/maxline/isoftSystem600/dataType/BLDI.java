package com.maxline.isoftSystem600.dataType;

import javax.baja.control.BBooleanPoint;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.sys.BFacets;
import javax.baja.sys.Sys;
import javax.baja.sys.Type;

@NiagaraType
public class BLDI
        extends BBooleanPoint
{
    public Type getType()
    {
        return TYPE;
    }

    public static final Type TYPE = Sys.loadType(BLDI.class);

    public BLDI()
    {
        setFacets(localFacets);
    }

    static BFacets localFacets = BFacets.makeBoolean("ON", "OFF");
}