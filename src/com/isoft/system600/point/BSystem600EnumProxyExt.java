package com.isoft.system600.point;

import com.isoft.system600.messages.ReadPointResponse;
import com.isoft.system600.messages.System600WriteRequest;
import com.isoft.system600.messages.System600WriteResponse;

import javax.baja.status.BStatusEnum;
import javax.baja.status.BStatusValue;
import javax.baja.sys.BDynamicEnum;
import javax.baja.sys.Sys;
import javax.baja.sys.Type;

public class BSystem600EnumProxyExt
        extends BSystem600ProxyExt
{
    public Type getType()
    {
        return TYPE;
    }

    public static final Type TYPE = Sys.loadType(BSystem600EnumProxyExt.class);

    public BSystem600EnumProxyExt()
    {
        setFlags(slope, 1);
        setFlags(intercept, 1);
    }

    public boolean updateOutput(BStatusValue paramBStatusValue)
    {
        BDynamicEnum i = ((BStatusEnum)paramBStatusValue).getValue();

        int deviceAdd = getSystem600Device().getAddress();
        int pointNum = getPointNumber();

        byte[] outValue = new byte[2];
        if (i.getOrdinal() == 0)
        {
            outValue[0] = 0;
            outValue[1] = 0;
        }
        else if (i.getOrdinal() == 1)
        {
            outValue[0] = 1;
            outValue[1] = 0;
        }
        else if (i.getOrdinal() == 1)
        {
            outValue[0] = 2;
            outValue[1] = 0;
        }
        else
        {
            outValue[0] = 0;
            outValue[1] = 0;
        }
        try
        {
            System600WriteRequest request = System600WriteRequest.make(deviceAdd, outValue, pointNum);
            System600WriteResponse response = (System600WriteResponse)getSystem600Network().sendSync(request);
            if (!response.isError()) {
                return true;
            }
        }
        catch (Exception ex)
        {
            getSystem600Network().getLog().error("write a boolean point is failure.", ex);
        }
        writeFail("write a boolean point is failure.");
        getSystem600Network().getLog().trace("write a boolean point is failure.");

        return false;
    }

    public void readData(ReadPointResponse response)
    {
        BStatusEnum newVal = (BStatusEnum)getReadValue().newCopy();
        newVal.setValue(BDynamicEnum.make(response.getIntValue()));
        readOk(newVal);
    }
}