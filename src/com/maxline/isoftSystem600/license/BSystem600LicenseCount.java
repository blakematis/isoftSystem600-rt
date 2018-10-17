package com.maxline.isoftSystem600.license;

import javax.baja.nre.annotations.NiagaraType;
import javax.baja.sys.BFrozenEnum;
import javax.baja.sys.Sys;
import javax.baja.sys.Type;
import javax.baja.util.Lexicon;

@NiagaraType
public final class BSystem600LicenseCount
        extends BFrozenEnum
{
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.maxline.isoftSystem600.license.BSystem600LicenseCount(2979906276)1.0$ @*/
/* Generated Mon Oct 15 14:56:41 PDT 2018 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////
  


/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/
    public static Lexicon lex = Lexicon.make("isoftSystem600");
    public static final int NONE = 0;
    public static final int COUNT_500 = 500;
    public static final int COUNT_1000 = 1000;
    public static final int COUNT_1500 = 1500;
    public static final int COUNT_2000 = 2000;
    public static final int COUNT_2500 = 2500;
    public static final int COUNT_3000 = 3000;
    public static final int COUNT_3500 = 3500;
    public static final int COUNT_4000 = 4000;
    public static final int COUNT_4500 = 4500;
    public static final int COUNT_5000 = 5000;
    public static final int COUNT_5500 = 5500;
    public static final int COUNT_6000 = 6000;
    public static final int COUNT_6500 = 6500;
    public static final int COUNT_7000 = 7000;
    public static final int COUNT_7500 = 7500;
    public static final int COUNT_8000 = 8000;
    public static final int COUNT_8500 = 8500;
    public static final int COUNT_9000 = 9000;
    public static final int COUNT_9500 = 9500;
    public static final int COUNT_10000 = 10000;
    public static final BSystem600LicenseCount none = new BSystem600LicenseCount(0);
    public static final BSystem600LicenseCount count_500 = new BSystem600LicenseCount(500);
    public static final BSystem600LicenseCount count_1000 = new BSystem600LicenseCount(1000);
    public static final BSystem600LicenseCount count_1500 = new BSystem600LicenseCount(1500);
    public static final BSystem600LicenseCount count_2000 = new BSystem600LicenseCount(2000);
    public static final BSystem600LicenseCount count_2500 = new BSystem600LicenseCount(2500);
    public static final BSystem600LicenseCount count_3000 = new BSystem600LicenseCount(3000);
    public static final BSystem600LicenseCount count_3500 = new BSystem600LicenseCount(3500);
    public static final BSystem600LicenseCount count_4000 = new BSystem600LicenseCount(4000);
    public static final BSystem600LicenseCount count_4500 = new BSystem600LicenseCount(4500);
    public static final BSystem600LicenseCount count_5000 = new BSystem600LicenseCount(5000);
    public static final BSystem600LicenseCount count_5500 = new BSystem600LicenseCount(5500);
    public static final BSystem600LicenseCount count_6000 = new BSystem600LicenseCount(6000);
    public static final BSystem600LicenseCount count_6500 = new BSystem600LicenseCount(6500);
    public static final BSystem600LicenseCount count_7000 = new BSystem600LicenseCount(7000);
    public static final BSystem600LicenseCount count_7500 = new BSystem600LicenseCount(7500);
    public static final BSystem600LicenseCount count_8000 = new BSystem600LicenseCount(8000);
    public static final BSystem600LicenseCount count_8500 = new BSystem600LicenseCount(8500);
    public static final BSystem600LicenseCount count_9000 = new BSystem600LicenseCount(9000);
    public static final BSystem600LicenseCount count_9500 = new BSystem600LicenseCount(9500);
    public static final BSystem600LicenseCount count_10000 = new BSystem600LicenseCount(10000);



    public static BSystem600LicenseCount make(int ordinal)
    {
        return (BSystem600LicenseCount)none.getRange().get(ordinal, false);
    }

    public static BSystem600LicenseCount make(String tag)
    {
        return (BSystem600LicenseCount)none.getRange().get(tag);
    }

    private BSystem600LicenseCount(int ordinal)
    {
        super(ordinal);
    }

    public static final BSystem600LicenseCount DEFAULT = none;

    @Override
    public Type getType() { return TYPE; }
    public static final Type TYPE = Sys.loadType(BSystem600LicenseCount.class);
}
