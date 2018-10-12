package com.maxline.isoftSystem600.job;

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

