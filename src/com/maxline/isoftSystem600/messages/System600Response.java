package com.maxline.isoftSystem600.messages;


import com.tridium.basicdriver.message.ReceivedMessage;

public abstract class System600Response
        extends ReceivedMessage
        implements System600MessageConst
{
    public abstract void readResponse(byte[] paramArrayOfByte, int paramInt);

    public String toDebugString()
    {
        StringBuffer sb = new StringBuffer();

        return sb.toString();
    }

    public boolean isError()
    {
        return this.error;
    }

    protected System600InputStream in = null;
    protected boolean error = false;
}