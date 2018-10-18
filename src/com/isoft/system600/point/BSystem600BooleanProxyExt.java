package com.isoft.system600.point;

import com.isoft.system600.messages.ReadPointResponse;
import com.isoft.system600.messages.System600WriteRequest;
import com.isoft.system600.messages.System600WriteResponse;

import javax.baja.nre.annotations.NiagaraType;
import javax.baja.status.BStatusBoolean;
import javax.baja.status.BStatusValue;
import javax.baja.sys.Sys;
import javax.baja.sys.Type;

@NiagaraType
public class BSystem600BooleanProxyExt
        extends BSystem600ProxyExt
{
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.maxline.isoftSystem600.point.BSystem600BooleanProxyExt(2979906276)1.0$ @*/
/* Generated Mon Oct 15 14:56:41 PDT 2018 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////
  
  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BSystem600BooleanProxyExt.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/


    public void started()
            throws Exception
    {
        super.started();
        doCheckChangeValueAction();
    }

    public boolean updateOutput(BStatusValue paramBStatusValue)
    {
        boolean bool = ((BStatusBoolean)paramBStatusValue).getValue();

        int deviceAdd = getSystem600Device().getAddress();
        int pointNum = getPointNumber();

        byte[] outValue = new byte[2];
        if (bool)
        {
            outValue[0] = 1;
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
        BStatusBoolean newVal = (BStatusBoolean)getReadValue().newCopy();
        newVal.setValue(response.getBooleanValue());
        readOk(newVal);
    }
}

