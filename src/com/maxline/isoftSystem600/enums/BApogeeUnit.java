package com.maxline.isoftSystem600.enums;

import javax.baja.nre.annotations.NiagaraType;
import javax.baja.sys.BFrozenEnum;
import javax.baja.sys.Sys;
import javax.baja.sys.Type;

@NiagaraType
public final class BApogeeUnit
        extends BFrozenEnum {
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.maxline.isoftSystem600.enums.BApogeeUnit(2979906276)1.0$ @*/
/* Generated Mon Oct 15 14:56:40 PDT 2018 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////
  


/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/
    public static final int METRIC = 0;
    public static final int ENGLISH = 1;
    public static final BApogeeUnit metric = new BApogeeUnit(0);
    public static final BApogeeUnit english = new BApogeeUnit(1);

    public Type getType() {
        return TYPE;
    }

    public static final Type TYPE = Sys.loadType(BApogeeUnit.class);

    public static BApogeeUnit make(int ordinal) {
        return (BApogeeUnit) metric.getRange().get(ordinal, false);
    }

    public static BApogeeUnit make(String tag) {
        return (BApogeeUnit) metric.getRange().get(tag);
    }

    private BApogeeUnit(int ordinal) {
        super(ordinal);
    }
}