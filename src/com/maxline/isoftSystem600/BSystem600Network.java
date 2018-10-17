package com.maxline.isoftSystem600;

import com.maxline.isoftSystem600.comm.BEnerDaptSerialHelper;
import com.maxline.isoftSystem600.comm.EnerDaptSystem600Comm;
import com.maxline.isoftSystem600.comm.System600Comm;
import com.maxline.isoftSystem600.comm.System600CommReceiver;
import com.maxline.isoftSystem600.job.BApogeeLearnDevicesConfig;
import com.maxline.isoftSystem600.job.BApogeeLearnDevicesJob;
import com.maxline.isoftSystem600.license.BSystem600License;
import com.maxline.isoftSystem600.point.BSystem600ProxyExt;
import com.tridium.basicdriver.comm.Comm;
import com.tridium.basicdriver.serial.BSerialNetwork;
import java.util.Vector;
import javax.baja.naming.BOrd;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.serial.BSerialBaudRate;
import javax.baja.serial.BSerialHelper;
import javax.baja.sys.*;

@NiagaraType
public class BSystem600Network
        extends BSerialNetwork
{
    public static final Property interMessageDelay = newProperty(0, BRelTime.make(20L), BFacets.make("showMilliseconds", BBoolean.TRUE, "min", BRelTime.make(5L), "max", BRelTime.SECOND));

    public BRelTime getInterMessageDelay()
    {
        return (BRelTime)get(interMessageDelay);
    }

    public void setInterMessageDelay(BRelTime v)
    {
        set(interMessageDelay, v, null);
    }

    public static final Property license = newProperty(0, new BSystem600License(), null);

    public BSystem600License getLicense()
    {
        return (BSystem600License)get(license);
    }

    public void setLicense(BSystem600License v)
    {
        set(license, v, null);
    }

    public static final Property defaultDeviceConfig = newProperty(4, new BApogeeLearnDevicesConfig(), null);

    public BApogeeLearnDevicesConfig getDefaultDeviceConfig()
    {
        return (BApogeeLearnDevicesConfig)get(defaultDeviceConfig);
    }

    public void setDefaultDeviceConfig(BApogeeLearnDevicesConfig v)
    {
        set(defaultDeviceConfig, v, null);
    }

    public static final Action submitDiscoveryDevicesJob = newAction(4, new BApogeeLearnDevicesConfig(), null);
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.maxline.isoftSystem600.BSystem600Network(2979906276)1.0$ @*/
/* Generated Mon Oct 15 14:56:28 PDT 2018 by Slot-o-Matic (c) Tridium, Inc. 2012 */



/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/

    public BOrd submitDiscoveryDevicesJob(BApogeeLearnDevicesConfig arg)
    {
        return (BOrd)invoke(submitDiscoveryDevicesJob, arg, null);
    }


    public BSystem600Network()
    {

        //getSerialPortConfig().setBaudRate(BSerialBaudRate.baud4800);
        setSerialPortConfig(new BEnerDaptSerialHelper());
        getSerialPortConfig().setBaudRate(BSerialBaudRate.baud4800);
        setFlags(upload, 4);
        setFlags(download, 4);
    }



    public static final Property serialPortConfig = newProperty(Flags.SUMMARY, new BEnerDaptSerialHelper(), null);


    @Override
    public BEnerDaptSerialHelper getSerialPortConfig() {
        return (BEnerDaptSerialHelper) this.get(serialPortConfig);
    }

    public void setSerialPortConfig(BEnerDaptSerialHelper v){ this.set(serialPortConfig, v, null);}

    public Type getDeviceType()
    {
        return BSystem600Device.TYPE;
    }

    public Type getDeviceFolderType()
    {
        return BSystem600DeviceFolder.TYPE;
    }

    public BOrd doSubmitDiscoveryDevicesJob(BApogeeLearnDevicesConfig config, Context cx)
    {
        return new BApogeeLearnDevicesJob(this, config).submit(cx);
    }

    public void started()
            throws Exception
    {
        removeOldComponent();
        setFlags(upload, 4);
        setFlags(download, 4);
        super.started();
    }

    public void stopped()
            throws Exception
    {
        super.stopped();
    }

    public void changed(Property prop, Context cx)
    {
        super.changed(prop, cx);
        if (!isRunning()) {}
    }

    protected Comm makeComm()
    {
        return new EnerDaptSystem600Comm(this, new System600CommReceiver());
    }

    private final void removeOldComponent()
    {
        try
        {
            if (get("system600License") != null) {
                remove("system600License");
            }
        }
        catch (Exception localException) {}
    }

    public final boolean registerProxyExt(BSystem600ProxyExt proxyExt)
    {
        if (getLicense().getPointCount().getOrdinal() == 0) {
            return false;
        }
        synchronized (this.licenseVector)
        {
            if (!this.licenseVector.contains(proxyExt)) {
                this.licenseVector.addElement(proxyExt);
            }
            if (this.licenseVector.size() > getLicense().getPointCount().getOrdinal()) {
                proxyExt.readFail("License point count exceeded limit");
            }
            return this.licenseVector.size() > getLicense().getPointCount().getOrdinal();
        }
    }

    public final void unregisterProxyExt(BSystem600ProxyExt proxyExt)
    {
        if (getLicense().getPointCount().getOrdinal() == 0) {
            return;
        }
        synchronized (this.licenseVector)
        {
            if (this.licenseVector.contains(proxyExt)) {
                this.licenseVector.removeElement(proxyExt);
            }
        }
    }

    private Vector<BSystem600ProxyExt> licenseVector = new Vector();

    ////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////

    @Override
    public Type getType() { return TYPE; }
    public static final Type TYPE = Sys.loadType(BSystem600Network.class);
}
