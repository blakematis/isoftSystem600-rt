package com.isoft.system600.job;


import javax.baja.nre.annotations.NiagaraType;
import javax.baja.sys.BComponent;
import javax.baja.sys.BFacets;
import javax.baja.sys.Property;
import javax.baja.sys.Sys;
import javax.baja.sys.Type;

@NiagaraType
public class BApogeeLearnDeviceEntry
        extends BComponent
{
    public static final Property address = newProperty(0, 0, BFacets.makeInt(0, 98));

    public int getAddress()
    {
        return getInt(address);
    }

    public void setAddress(int v)
    {
        setInt(address, v, null);
    }

    public static final Property revision = newProperty(0, "", null);

    public String getRevision()
    {
        return getString(revision);
    }

    public void setRevision(String v)
    {
        setString(revision, v, null);
    }

    public static final Property application = newProperty(0, 0, null);

    public int getApplication()
    {
        return getInt(application);
    }

    public void setApplication(int v)
    {
        setInt(application, v, null);
    }

    public static final Property description = newProperty(0, "", null);
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.maxline.isoftSystem600.job.BApogeeLearnDeviceEntry(2979906276)1.0$ @*/
/* Generated Mon Oct 15 14:56:40 PDT 2018 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////
  
  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BApogeeLearnDeviceEntry.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/

    public String getDescription()
    {
        return getString(description);
    }

    public void setDescription(String v)
    {
        setString(description, v, null);
    }



    public BApogeeLearnDeviceEntry(int address, String rev, int app, String desc)
    {
        setAddress(address);
        setRevision(rev);
        setApplication(app);
        setDescription(desc);
    }

    public BApogeeLearnDeviceEntry() {}
}

