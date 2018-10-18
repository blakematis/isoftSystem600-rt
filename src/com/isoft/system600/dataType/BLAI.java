package com.isoft.system600.dataType;

import javax.baja.control.BNumericPoint;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.sys.Sys;
import javax.baja.sys.Type;

@NiagaraType
public class BLAI
        extends BNumericPoint
{

  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BLAI.class);

}
