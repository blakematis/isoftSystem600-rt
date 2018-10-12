package com.maxline.isoftSystem600.point;

import com.maxline.isoftSystem600.BSystem600Device;
import com.maxline.isoftSystem600.BSystem600Network;
import com.maxline.isoftSystem600.dataType.BLAO;
import com.maxline.isoftSystem600.dataType.BLDO;
import com.maxline.isoftSystem600.messages.ApogeeReleaseRequest;
import com.maxline.isoftSystem600.messages.ReadPointRequest;
import com.maxline.isoftSystem600.messages.ReadPointResponse;
import com.maxline.isoftSystem600.messages.System600MessageConst;
import com.maxline.isoftSystem600.utils.ApogeeActionRequest;
import com.tridium.basicdriver.BBasicNetwork;
import com.tridium.basicdriver.point.BBasicProxyExt;
import com.tridium.basicdriver.util.BIBasicPollable;
import com.tridium.basicdriver.util.BasicWriteAsyncRequest;

import javax.baja.driver.point.BReadWriteMode;
import javax.baja.driver.util.BPollFrequency;
import javax.baja.status.BStatusBoolean;
import javax.baja.status.BStatusEnum;
import javax.baja.status.BStatusNumeric;
import javax.baja.status.BStatusString;
import javax.baja.status.BStatusValue;
import javax.baja.sys.BFacets;
import javax.baja.sys.Context;
import javax.baja.sys.Property;
import javax.baja.sys.Sys;
import javax.baja.sys.Type;
import javax.baja.util.Lexicon;

public abstract class BSystem600ProxyExt
        extends BBasicProxyExt
        implements BIBasicPollable, System600MessageConst
{
    public static final Property pollFrequency = newProperty(0, BPollFrequency.normal, null);

    public BPollFrequency getPollFrequency()
    {
        return (BPollFrequency)get(pollFrequency);
    }

    public void setPollFrequency(BPollFrequency v)
    {
        set(pollFrequency, v, null);
    }

    public static final Property pointNumber = newProperty(0, 1, BFacets.makeInt(1, 99));

    public int getPointNumber()
    {
        return getInt(pointNumber);
    }

    public void setPointNumber(int v)
    {
        setInt(pointNumber, v, null);
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

    public static final Property descriptor = newProperty(1, "", null);

    public String getDescriptor()
    {
        return getString(descriptor);
    }

    public void setDescriptor(String v)
    {
        setString(descriptor, v, null);
    }

    public static final Property state = newProperty(68, 0, null);

    public int getState()
    {
        return getInt(state);
    }

    public void setState(int v)
    {
        setInt(state, v, null);
    }

    public Type getType()
    {
        return TYPE;
    }

    public static final Type TYPE = Sys.loadType(BSystem600ProxyExt.class);

    public final BSystem600Network getSystem600Network()
    {
        return (BSystem600Network)getNetwork();
    }

    public final BSystem600Device getSystem600Device()
    {
        return (BSystem600Device)getDevice();
    }

    public final BSystem600PointDeviceExt getSystem600PointDeviceExt()
    {
        return (BSystem600PointDeviceExt)getDeviceExt();
    }

    public void started()
            throws Exception
    {
        super.started();
    }

    public void changed(Property property, Context context)
    {
        super.changed(property, context);
        if (!isRunning()) {
            return;
        }
        if ((property.equals(pointNumber)) || (property.equals(slope)) || (property.equals(intercept))) {
            poll();
        }
    }

    public void readSubscribed(Context cx)
            throws Exception
    {
        if (getSystem600Network().registerProxyExt(this)) {
            return;
        }
        super.readSubscribed(cx);
    }

    public void readUnsubscribed(Context cx)
            throws Exception
    {
        getSystem600Network().unregisterProxyExt(this);
        super.readUnsubscribed(cx);
    }

    public Type getDeviceExtType()
    {
        return BSystem600PointDeviceExt.TYPE;
    }

    public void poll()
    {
        if (isUnoperational()) {
            return;
        }
        if (!isValid())
        {
            readFail("point address must great then 0 on read data attempt.");
            return;
        }
        if (getSystem600Device().getAddress() < 0)
        {
            readFail("device address must greather then 0 on read data attempt.");
            return;
        }
        pollForData();
    }

    private void pollForData()
    {
        int devAddr = getSystem600Device().getAddress();
        try
        {
            ReadPointRequest req = ReadPointRequest.make(devAddr, 1, getPointNumber());
            ReadPointResponse rsp = (ReadPointResponse)getSystem600Network().sendSync(req);
            if (rsp == null)
            {
                readFail("timeout");
                getSystem600Device().ping();
                return;
            }
            if (rsp.isError())
            {
                readFail("timeout");

                return;
            }
            readData(rsp);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            readFail(e.toString());
        }
    }

    public abstract void readData(ReadPointResponse paramReadPointResponse);

    public boolean isValid()
    {
        return getPointNumber() > 0;
    }

    public void doReleasePoint()
    {
        if (!isRunning()) {
            return;
        }
        if (getParentPoint().isWritablePoint()) {
            getSystem600Network().postWrite(new ApogeeActionRequest(this));
        }
    }

    public void releaseValue()
    {
        if (!isRunning()) {
            return;
        }
        int deviceAdd = getSystem600Device().getAddress();
        try
        {
            ApogeeReleaseRequest request = ApogeeReleaseRequest.make(deviceAdd, getPointNumber());
            getSystem600Network().sendSync(request);

            setState(STATE_NORMAL);
        }
        catch (Exception ex)
        {
            getSystem600Network().getLog().trace("write point is failure.", ex);
        }
    }

    protected final void doCheckChangeValueAction()
    {
        BApogeeReleaseAction[] action = getParentPoint().getChildren(BApogeeReleaseAction.class);
        if (getParentPoint().isWritablePoint())
        {
            if (action.length == 0)
            {
                BApogeeReleaseAction bchangevalueaction = new BApogeeReleaseAction();
                getParentPoint().add("ReleasePoint", bchangevalueaction, 256);
            }
        }
        else {
            for (int i = 0; i < action.length; i++) {
                getParentPoint().remove(action[i]);
            }
        }
    }

    boolean hasReleaseOption()
    {
        if (!isRunning()) {
            return false;
        }
        if (((getParentPoint() instanceof BLAO)) || ((getParentPoint() instanceof BLDO))) {
            if (((isNumeric()) || (isBoolean())) && (getState() != STATE_NORMAL)) {
                return true;
            }
        }
        return false;
    }

    public boolean write(Context cx)
            throws Exception
    {
        if (getMode() != BReadWriteMode.readonly)
        {
            BStatusValue out = getWriteValue();
            if (((out.getStatus().isNull()) && (!hasReleaseOption())) || (!getSystem600Device().isPingSubscribe())) {
                return false;
            }
            BBasicNetwork network = (BBasicNetwork)getNetwork();
            if (network.getLog().isTraceOn()) {
                network.getLog().trace("Write <" + this + "> " + out);
            }
            try
            {
                network.postWrite(new BasicWriteAsyncRequest(this, out));
            }
            catch (Exception localException) {}
        }
        return false;
    }

    public void doWrite(BStatusValue value)
    {
        if (isUnoperational()) {
            return;
        }
        if (value.getStatus().isNull())
        {
            releaseValue();
            writeOk(value);
        }
        else if (updateOutput(value))
        {
            writeOk(value);
            setState(STATE_OVERRIDE);
        }
    }

    public abstract boolean updateOutput(BStatusValue paramBStatusValue);

    protected boolean isBoolean()
    {
        return getParentPoint().getOutStatusValue() instanceof BStatusBoolean;
    }

    protected boolean isNumeric()
    {
        return getParentPoint().getOutStatusValue() instanceof BStatusNumeric;
    }

    protected boolean isString()
    {
        return getParentPoint().getOutStatusValue() instanceof BStatusString;
    }

    protected boolean isEnum()
    {
        return getParentPoint().getOutStatusValue() instanceof BStatusEnum;
    }

    static Lexicon lex = Lexicon.make(BBasicProxyExt.class);
    static int STATE_NORMAL = 0;
    static int STATE_OVERRIDE = 1;
}
