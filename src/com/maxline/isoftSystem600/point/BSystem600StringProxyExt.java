package com.maxline.isoftSystem600.point;

import com.maxline.isoftSystem600.messages.ReadPointResponse;

import javax.baja.nre.annotations.NiagaraType;
import javax.baja.status.BStatusString;
import javax.baja.status.BStatusValue;
import javax.baja.sys.Sys;
import javax.baja.sys.Type;

@NiagaraType
public class BSystem600StringProxyExt
        extends BSystem600ProxyExt
{
    public Type getType()
    {
        return TYPE;
    }

    public static final Type TYPE = Sys.loadType(BSystem600StringProxyExt.class);

    public void readData(ReadPointResponse response)
    {
        BStatusString newVal = (BStatusString)getReadValue().newCopy();
        newVal.setValue(response.getStringValue());
        readOk(newVal);
    }

    public boolean updateOutput(BStatusValue value)
    {
        return false;
    }
}
