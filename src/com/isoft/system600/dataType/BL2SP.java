package com.isoft.system600.dataType;

import javax.baja.control.BBooleanWritable;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.sys.BFacets;
import javax.baja.sys.Sys;
import javax.baja.sys.Type;

@NiagaraType
public class BL2SP
        extends BBooleanWritable
{



    public BL2SP()
    {
        setFacets(localFacets);
    }

    static BFacets localFacets = BFacets.makeBoolean("ON", "OFF");

    @Override
    public Type getType() { return TYPE; }
    public static final Type TYPE = Sys.loadType(BL2SP.class);
}
