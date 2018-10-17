package com.maxline.isoftSystem600.enums;

import javax.baja.nre.annotations.NiagaraType;
import javax.baja.sys.BFrozenEnum;
import javax.baja.sys.Sys;
import javax.baja.sys.Type;

@NiagaraType
public final class BCommPortEnums extends BFrozenEnum {

    /*-----------------------------DATA FIELDS-----------------------------*/

    //Default value

    public static final BCommPortEnums DEFAULT = BCommPortEnums.none;

    /** Ordinal value for none. */
    public static final int NONE = 0;

    public static final int COM1 = 1;

    public static final int COM2 = 2;

    public static final int COM3 = 3;

    public static final int COM4 = 4;

    public static final int COM5 = 5;

    public static final int COM6 = 6;


    /** BCommPortEnum constants for none, and others. */
    public static final BCommPortEnums none = new BCommPortEnums(NONE);

    public static final BCommPortEnums com1 = new BCommPortEnums(COM1);

    public static final BCommPortEnums com2 = new BCommPortEnums(COM2);

    public static final BCommPortEnums com3 = new BCommPortEnums(COM3);

    public static final BCommPortEnums com4 = new BCommPortEnums(COM4);

    public static final BCommPortEnums com5 = new BCommPortEnums(COM5);

    public static final BCommPortEnums com6 = new BCommPortEnums(COM6);


    /** Factory Method with ordinal **/
    public static BCommPortEnums make(int ordinal){
        return (BCommPortEnums)none.getRange().get(ordinal,false);
    }

    /** Factory Method with tag */
    public static BCommPortEnums make(String tag){
        return (BCommPortEnums)none.getRange().get(tag);
    }

    /*-----------------------------CONSTRUCTORS-----------------------------*/

    /** Private Constructor */
    private BCommPortEnums(int ordinal){
        super(ordinal);
    }

    public Type getType() { return TYPE; }

    public static final Type TYPE = Sys.loadType(BCommPortEnums.class);

    /*-------------------------GETTERS AND SETTERS--------------------------*/



    /*-------------------------------METHODS-------------------------------*/
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.maxline.isoftSystem600.enums.BCommPortEnums(4188009469)1.0$ @*/
/* Generated Mon Oct 15 14:56:40 PDT 2018 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////
  


/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/

}

