package com.maxline.isoftSystem600.license;

import javax.baja.sys.BFacets;
import javax.baja.sys.BInteger;
import javax.baja.sys.BStruct;
import javax.baja.sys.Property;
import javax.baja.sys.Sys;
import javax.baja.sys.Type;

public class BSystem600LicenseConfig
        extends BStruct
{
    public static final Property signature = newProperty(0, "", BFacets.make("fieldWidth", BInteger.make(70)));

    public String getSignature()
    {
        return getString(signature);
    }

    public void setSignature(String v)
    {
        setString(signature, v, null);
    }

    public static final Property pointCount = newProperty(0, BSystem600LicenseCount.count_10000, null);

    public BSystem600LicenseCount getPointCount()
    {
        return (BSystem600LicenseCount)get(pointCount);
    }

    public void setPointCount(BSystem600LicenseCount v)
    {
        set(pointCount, v, null);
    }

    public Type getType()
    {
        return TYPE;
    }

    public static final Type TYPE = Sys.loadType(BSystem600LicenseConfig.class);
}