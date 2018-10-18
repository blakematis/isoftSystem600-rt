package com.isoft.system600.enums;

import javax.baja.nre.annotations.NiagaraType;
import javax.baja.sys.BFrozenEnum;
import javax.baja.sys.Sys;
import javax.baja.sys.Type;
import javax.baja.util.Lexicon;

@NiagaraType
public final class BPointType
        extends BFrozenEnum
{
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.maxline.isoftSystem600.enums.BPointType(2979906276)1.0$ @*/
/* Generated Mon Oct 15 14:56:40 PDT 2018 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////
  
  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BPointType.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/
    static Lexicon lex = Lexicon.make("isoftSystem600");
    public static final int NONE = 0;
    public static final int LAI = 1;
    public static final int LAO = 2;
    public static final int LPACI = 3;
    public static final int LDI = 4;
    public static final int LDO = 5;
    public static final int L_2SL = 6;
    public static final int L_2SP = 7;
    public static final int LOOAL = 8;
    public static final int LOOAP = 9;
    public static final int LFSSL = 10;
    public static final int LFSSP = 11;
    public static final int LENUM = 12;
    public static final BPointType none = new BPointType(0);
    public static final BPointType lai = new BPointType(1);
    public static final BPointType lao = new BPointType(2);
    public static final BPointType lpaci = new BPointType(3);
    public static final BPointType ldi = new BPointType(4);
    public static final BPointType ldo = new BPointType(5);
    public static final BPointType l2sl = new BPointType(6);
    public static final BPointType l2sp = new BPointType(7);
    public static final BPointType looal = new BPointType(8);
    public static final BPointType looap = new BPointType(9);
    public static final BPointType lfssl = new BPointType(10);
    public static final BPointType lfssp = new BPointType(11);
    public static final BPointType lenum = new BPointType(12);

    public static BPointType make(int ordinal)
    {
        return (BPointType)none.getRange().get(ordinal, false);
    }

    public static BPointType make(String tag)
    {
        return (BPointType)none.getRange().get(tag);
    }

    private BPointType(int ordinal)
    {
        super(ordinal);
    }
}

