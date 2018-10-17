package com.maxline.isoftSystem600.dataType;

import javax.baja.control.BBooleanWritable;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.sys.BFacets;
import javax.baja.sys.Sys;
import javax.baja.sys.Type;

@NiagaraType
public class BLDO
        extends BBooleanWritable
{
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.maxline.isoftSystem600.dataType.BLDO(2979906276)1.0$ @*/
/* Generated Mon Oct 15 14:56:30 PDT 2018 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////
  


/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/


    public BLDO()
    {
        setFacets(localFacets);
    }

    static BFacets localFacets = BFacets.makeBoolean("ON", "OFF");

    public Type getType()
    {
        return TYPE;
    }

    public static final Type TYPE = Sys.loadType(BLDO.class);
}