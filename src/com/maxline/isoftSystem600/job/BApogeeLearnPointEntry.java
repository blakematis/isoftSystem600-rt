package com.maxline.isoftSystem600.job;

import com.maxline.isoftSystem600.enums.BPointType;

import javax.baja.nre.annotations.NiagaraType;
import javax.baja.sys.BComponent;
import javax.baja.sys.Property;
import javax.baja.sys.Sys;
import javax.baja.sys.Type;

@NiagaraType
public class BApogeeLearnPointEntry
        extends BComponent
{
    public static final Property pointAddress = newProperty(0, 0, null);

    public int getPointAddress()
    {
        return getInt(pointAddress);
    }

    public void setPointAddress(int v)
    {
        setInt(pointAddress, v, null);
    }

    public static final Property pointType = newProperty(0, BPointType.none, null);

    public BPointType getPointType()
    {
        return (BPointType)get(pointType);
    }

    public void setPointType(BPointType v)
    {
        set(pointType, v, null);
    }

    public static final Property slope = newProperty(0, 0.0D, null);

    public double getSlope()
    {
        return getDouble(slope);
    }

    public void setSlope(double v)
    {
        setDouble(slope, v, null);
    }

    public static final Property intercept = newProperty(0, 0.0D, null);

    public double getIntercept()
    {
        return getDouble(intercept);
    }

    public void setIntercept(double v)
    {
        setDouble(intercept, v, null);
    }

    public static final Property descriptor = newProperty(0, "", null);
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.maxline.isoftSystem600.job.BApogeeLearnPointEntry(2979906276)1.0$ @*/
/* Generated Mon Oct 15 14:56:41 PDT 2018 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////
  
  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BApogeeLearnPointEntry.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/

    public String getDescriptor()
    {
        return getString(descriptor);
    }

    public void setDescriptor(String v)
    {
        setString(descriptor, v, null);
    }


    public BApogeeLearnPointEntry() {}

    public BApogeeLearnPointEntry(String[] args)
    {
        try
        {
            setPointAddress(Integer.parseInt(args[0].trim()));
            setDescriptor(args[1].trim());
            setSlope(Double.parseDouble(args[2].trim()));
            setIntercept(Double.parseDouble(args[3].trim()));
            setPointType(BPointType.make(args[4].trim().toLowerCase()));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
