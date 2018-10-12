package com.maxline.isoftSystem600.utils;

import com.maxline.isoftSystem600.point.BSystem600ProxyExt;
import javax.baja.util.ICoalesceable;

public class ApogeeActionRequest
        implements Runnable, ICoalesceable
{
    public ApogeeActionRequest(BSystem600ProxyExt ext)
    {
        this.hashCode = ext.hashCode();
        this.ext = ext;
    }

    public void run()
    {
        try
        {
            this.ext.releaseValue();
        }
        catch (Throwable t)
        {
            t.printStackTrace();
        }
    }

    public int hashCode()
    {
        return this.hashCode;
    }

    public boolean equals(Object obj)
    {
        if ((obj instanceof ApogeeActionRequest))
        {
            ApogeeActionRequest o = (ApogeeActionRequest)obj;
            return this.ext == o.ext;
        }
        return false;
    }

    public Object getCoalesceKey()
    {
        return this;
    }

    public ICoalesceable coalesce(ICoalesceable c)
    {
        return c;
    }

    private BSystem600ProxyExt ext = null;
    protected int hashCode;
}
