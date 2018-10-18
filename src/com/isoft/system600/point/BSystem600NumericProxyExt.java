package com.isoft.system600.point;


import com.isoft.system600.utils.ApogeeActionRequest;
import com.isoft.system600.utils.Sys600Util;
import com.isoft.system600.messages.ReadPointResponse;
import com.isoft.system600.messages.System600WriteRequest;
import com.isoft.system600.messages.System600WriteResponse;

import javax.baja.nre.annotations.NiagaraType;
import javax.baja.nre.util.TextUtil;
import javax.baja.status.BStatusNumeric;
import javax.baja.status.BStatusValue;
import javax.baja.sys.Context;
import javax.baja.sys.Property;
import javax.baja.sys.Sys;
import javax.baja.sys.Type;

@NiagaraType
public class BSystem600NumericProxyExt
        extends BSystem600ProxyExt
{
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.maxline.isoftSystem600.point.BSystem600NumericProxyExt(2979906276)1.0$ @*/
/* Generated Mon Oct 15 14:56:41 PDT 2018 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////
  
  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BSystem600NumericProxyExt.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/


    public void started()
            throws Exception
    {
        super.started();
        doCheckChangeValueAction();
    }

    public void readData(ReadPointResponse response)
    {
        BStatusNumeric newVal = (BStatusNumeric)getReadValue().newCopy();
        double slope = getSlope();
        double intercept = getIntercept();
        double result = 0.0D;
        if (slope != 0.0D)
        {
            double x = response.getFloatValue();
            result = slope * x + intercept;
            newVal.setValue(result);
        }
        else
        {
            newVal.setValue(response.getFloatValue());
        }
        readOk(newVal);
    }

    public boolean updateOutput(BStatusValue paramBStatusValue)
    {
        byte[] outValue = new byte[2];
        try
        {
            double y = ((BStatusNumeric)paramBStatusValue).getValue();

            int deviceAdd = getSystem600Device().getAddress();
            int pointNum = getPointNumber();

            int result = 0;
            double slope = getSlope();
            double intercept = getIntercept();
            if (slope != 0.0D) {
                result = (int)((y - intercept) / slope);
            } else {
                result = (int)y;
            }
            String str = Sys600Util.padZeros(Integer.toHexString(result & 0xFFFF), 4);
            if (TextUtil.isHex(str))
            {
                byte[] temp = new byte[str.length()];
                for (int i = 0; i < str.length(); i++) {
                    temp[i] = ((byte)(Byte.parseByte(str.substring(i, i + 1), 16) & 0xFF));
                }
                outValue[0] = ((byte)(((temp[2] & 0xFF) << 4) + (temp[3] & 0xFF)));
                outValue[1] = ((byte)(((temp[0] & 0xFF) << 4) + (temp[1] & 0xFF)));
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
                getSystem600Network().getLog().trace("write a numeric point is failure.", ex);
            }
        }
        catch (Exception ex)
        {
            getSystem600Network().getLog().error("convert a numeric point is failure.", ex);
        }
        writeFail("write a numeric point is failure.");
        getSystem600Network().getLog().trace("write a numeric point is failure.");

        return false;
    }

    public void changed(Property property, Context context)
    {
        super.changed(property, context);
        if (!isRunning()) {
            return;
        }
        if ((property.equals(slope)) || (property.equals(intercept))) {
            poll();
        }
    }

    public void doReleasePoint()
    {
        if (getParentPoint().isWritablePoint()) {
            getSystem600Network().postWrite(new ApogeeActionRequest(this));
        }
    }
}
