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
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.maxline.isoftSystem600.point.BSystem600StringProxyExt(2979906276)1.0$ @*/
/* Generated Mon Oct 15 14:56:42 PDT 2018 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////
  

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/
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
