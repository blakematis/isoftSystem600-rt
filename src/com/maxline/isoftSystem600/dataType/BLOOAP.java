package com.maxline.isoftSystem600.dataType;


import javax.baja.control.BEnumWritable;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.sys.BEnumRange;
import javax.baja.sys.BFacets;
import javax.baja.sys.Sys;
import javax.baja.sys.Type;

@NiagaraType
public class BLOOAP
        extends BEnumWritable
{
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.maxline.isoftSystem600.dataType.BLOOAP(2979906276)1.0$ @*/
/* Generated Mon Oct 15 14:56:30 PDT 2018 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////
  


/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/


    public BLOOAP()
    {
        setFacets(localFacets);
    }

    static final String[] enumTag = { "OFF", "ON", "AUTO" };
    static BFacets localFacets = BFacets.makeEnum(BEnumRange.make(enumTag));
    @Override
    public Type getType() { return TYPE; }
    public static final Type TYPE = Sys.loadType(BLOOAP.class);
}
