package com.isoft.system600.job;

import javax.baja.nre.annotations.NiagaraType;
import javax.baja.sys.BFacets;
import javax.baja.sys.BStruct;
import javax.baja.sys.Property;
import javax.baja.sys.Sys;
import javax.baja.sys.Type;

@NiagaraType
public class BApogeeLearnDevicesConfig
        extends BStruct
{
    public static final Property startAddress = newProperty(0, 0, BFacets.makeInt(0, 98));

    public int getStartAddress()
    {
        return getInt(startAddress);
    }

    public void setStartAddress(int v)
    {
        setInt(startAddress, v, null);
    }

    public static final Property endAddress = newProperty(0, 98, BFacets.makeInt(0, 98));
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.maxline.isoftSystem600.job.BApogeeLearnDevicesConfig(2979906276)1.0$ @*/
/* Generated Mon Oct 15 14:56:40 PDT 2018 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////



/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/

    public int getEndAddress()
    {
        return getInt(endAddress);
    }

    public void setEndAddress(int v)
    {
        setInt(endAddress, v, null);
    }

    public Type getType()
    {
        return TYPE;
    }

    public static final Type TYPE = Sys.loadType(BApogeeLearnDevicesConfig.class);
}
