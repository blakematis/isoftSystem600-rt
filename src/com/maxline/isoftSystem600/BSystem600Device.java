package com.maxline.isoftSystem600;


import com.maxline.isoftSystem600.enums.BApogeeUnit;
import com.maxline.isoftSystem600.job.BApogeeLearnPointsJob;
import com.maxline.isoftSystem600.messages.DiscoveryPointsRequest;
import com.maxline.isoftSystem600.messages.ReadPointRequest;
import com.maxline.isoftSystem600.messages.ReadPointResponse;
import com.maxline.isoftSystem600.messages.System600MessageConst;
import com.maxline.isoftSystem600.messages.System600PingRequest;
import com.maxline.isoftSystem600.messages.System600PingResponse;
import com.maxline.isoftSystem600.point.BSystem600PointDeviceExt;
import com.tridium.basicdriver.BBasicDevice;
import javax.baja.naming.BOrd;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.sys.Action;
import javax.baja.sys.BFacets;
import javax.baja.sys.BRelTime;
import javax.baja.sys.Context;
import javax.baja.sys.Property;
import javax.baja.sys.Sys;
import javax.baja.sys.Type;
import javax.baja.util.IFuture;

@NiagaraType
public class BSystem600Device
        extends BBasicDevice
        implements System600MessageConst
{
    public static final Property address = newProperty(0, 1, BFacets.makeInt(0, 99));

    public int getAddress()
    {
        return getInt(address);
    }

    public void setAddress(int v)
    {
        setInt(address, v, null);
    }

    public static final Property revision = newProperty(1, "", null);

    public String getRevision()
    {
        return getString(revision);
    }

    public void setRevision(String v)
    {
        setString(revision, v, null);
    }

    public static final Property application = newProperty(1, 0, null);

    public int getApplication()
    {
        return getInt(application);
    }

    public void setApplication(int v)
    {
        setInt(application, v, null);
    }

    public static final Property defaultUnit = newProperty(4, BApogeeUnit.metric, null);

    public BApogeeUnit getDefaultUnit()
    {
        return (BApogeeUnit)get(defaultUnit);
    }

    public void setDefaultUnit(BApogeeUnit v)
    {
        set(defaultUnit, v, null);
    }

    public static final Property points = newProperty(0, new BSystem600PointDeviceExt(), null);

    public BSystem600PointDeviceExt getPoints()
    {
        return (BSystem600PointDeviceExt)get(points);
    }

    public void setPoints(BSystem600PointDeviceExt v)
    {
        set(points, v, null);
    }

    public static final Action submitPointsDiscoveryJob = newAction(4, BApogeeUnit.metric, null);

    public BOrd submitPointsDiscoveryJob(BApogeeUnit arg)
    {
        return (BOrd)invoke(submitPointsDiscoveryJob, arg, null);
    }

    public Type getType()
    {
        return TYPE;
    }

    public static final Type TYPE = Sys.loadType(BSystem600Device.class);

    public BSystem600Device()
    {
        setFlags(upload, 4);
        setFlags(download, 4);
    }

    public final BSystem600Network getSystem600Network()
    {
        return (BSystem600Network)getNetwork();
    }

    public Type getNetworkType()
    {
        return BSystem600Network.TYPE;
    }

    public void started()
            throws Exception
    {
        super.started();
    }

    public void stopped()
            throws Exception
    {
        super.stopped();
        this.pingSubscribe = false;
    }

    public void changed(Property prop, Context cx)
    {
        super.changed(prop, cx);
        if (!isRunning()) {
            return;
        }
        if (prop.equals(address)) {
            ping();
        }
    }

    public IFuture postPing()
    {
        doPing();
        return null;
    }

    public void doPing()
    {
        BSystem600Network network = (BSystem600Network)getNetwork();
        System600PingRequest req = null;
        System600PingResponse rsp = null;
        try
        {
            req = System600PingRequest.make(getAddress());
            rsp = (System600PingResponse)network.sendSync(req);
            if (rsp == null)
            {
                pingFail("timeout");
            }
            else if (rsp.isError())
            {
                pingFail("communicator error");
            }
            else
            {
                pingOk();
                setRevision(rsp.getRevision());
                if (getApplication() == 0)
                {
                    ReadPointRequest rdReq = new ReadPointRequest(getAddress(), 1, 2);
                    ReadPointResponse rdRsp = (ReadPointResponse)network.sendSync(rdReq, BRelTime.make(1000L), 0);
                    if ((rdRsp != null) && (!rdRsp.isError())) {
                        setApplication(rdRsp.getIntValue());
                    }
                }
            }
        }
        catch (Exception e)
        {
            pingFail("caught exception");
        }
        finally
        {
            network = null;
        }
    }

    public void pingOk()
    {
        super.pingOk();
        if (!this.pingSubscribe) {
            this.pingSubscribe = true;
        }
    }

    public void pingFail(String cause)
    {
        super.pingFail(cause);
        this.pingSubscribe = false;
    }

    public final boolean isPingSubscribe()
    {
        return this.pingSubscribe;
    }

    public BOrd doSubmitPointsDiscoveryJob(BApogeeUnit unit, Context cx)
    {
        return new BApogeeLearnPointsJob(this, unit).submit(cx);
    }

    public String toString(Context context)
    {
        return "FLN:" + getAddress();
    }

    public void doUploadTest()
    {
        try
        {
            DiscoveryPointsRequest req = new DiscoveryPointsRequest(getAddress());
            getSystem600Network().sendSync(req);
        }
        catch (Exception localException) {}
    }

    private boolean pingSubscribe = false;
}

