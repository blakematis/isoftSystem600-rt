package com.isoft.system600.comm;

import javax.baja.nre.annotations.NiagaraType;
import javax.baja.serial.BISerialHelperParent;
import javax.baja.sys.*;

@NiagaraType
public interface BIEnerDaptSerialHelperParent extends BInterface {
    Type TYPE = Sys.loadType(BISerialHelperParent.class);

    void reopenPort();

    void changed(Property var1, Context var2);
}
