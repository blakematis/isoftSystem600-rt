package com.maxline.isoftSystem600.point;

import javax.baja.control.BControlPoint;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.sys.BAction;
import javax.baja.sys.BBoolean;
import javax.baja.sys.BComponent;
import javax.baja.sys.BValue;
import javax.baja.sys.Sys;
import javax.baja.sys.Type;

@NiagaraType
public class BApogeeReleaseAction
        extends BAction
{
    public Type getType()
    {
        return TYPE;
    }

    public static final Type TYPE = Sys.loadType(BApogeeReleaseAction.class);

    public BValue getParameterDefault()
    {
        return null;
    }

    public Type getParameterType()
    {
        return null;
    }

    public Type getReturnType()
    {
        return null;
    }

    public BValue invoke(BComponent target, BValue arg)
            throws Exception
    {
        if (!(target instanceof BControlPoint)) {
            throw new IllegalArgumentException("Clear Override cannot be invoked on " + target.getType());
        }
        BControlPoint bcontrolpoint = (BControlPoint)target;
        Type type = bcontrolpoint.getProxyExt().getType();
        if (!type.is(BSystem600ProxyExt.TYPE)) {
            throw new IllegalArgumentException("Clear Override cannot be invoked on proxy point type " + type);
        }
        ((BSystem600ProxyExt)bcontrolpoint.getProxyExt()).doReleasePoint();
        return BBoolean.TRUE;
    }
}

