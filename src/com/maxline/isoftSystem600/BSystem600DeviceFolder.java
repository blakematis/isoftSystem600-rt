package com.maxline.isoftSystem600;

import javax.baja.driver.BDeviceFolder;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.sys.Sys;
import javax.baja.sys.Type;

@NiagaraType
public class BSystem600DeviceFolder
        extends BDeviceFolder
{
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.maxline.isoftSystem600.BSystem600DeviceFolder(2979906276)1.0$ @*/
/* Generated Mon Oct 15 14:56:28 PDT 2018 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////
  
  @Override
  public Type getType() { return TYPE; }

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/

    public static final Type TYPE = Sys.loadType(BSystem600DeviceFolder.class);

    public final BSystem600Network getSystem600Network()
    {
        return (BSystem600Network)getNetwork();
    }
}
