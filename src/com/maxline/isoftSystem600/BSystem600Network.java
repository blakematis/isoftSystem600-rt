package com.maxline.isoftSystem600;

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
import javax.baja.sys.Action;
import javax.baja.sys.BBoolean;
import javax.baja.sys.BFacets;
import javax.baja.sys.BRelTime;
import javax.baja.sys.Context;
import javax.baja.sys.Property;
import javax.baja.sys.Sys;
import javax.baja.sys.Type;

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

    public BOrd submitDiscoveryDevicesJob(BApogeeLearnDevicesConfig arg)
    {
        return (BOrd)invoke(submitDiscoveryDevicesJob, arg, null);
    }

    public Type getType()
    {
        return TYPE;
    }

    public static final Type TYPE = Sys.loadType(BSystem600Network.class);

    public BSystem600Network()
    {
        getSerialPortConfig().setBaudRate(BSerialBaudRate.baud4800);
        setFlags(upload, 4);
        setFlags(download, 4);
    }

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
        return new System600Comm(this, new System600CommReceiver());
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
}
