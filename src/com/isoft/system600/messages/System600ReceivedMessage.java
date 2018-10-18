package com.isoft.system600.messages;

import com.tridium.basicdriver.message.ReceivedMessage;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.baja.nre.util.ByteArrayUtil;

public class System600ReceivedMessage
        extends ReceivedMessage
        implements System600MessageConst
{
    private byte[] data;
    private int len;

    public System600ReceivedMessage(byte[] data, int len)
    {
        this.data = data;
        this.len = len;
    }

    public byte[] getBytes()
    {
        return this.data;
    }

    public void setBytes(byte[] data)
    {
        this.data = data;
    }

    public int getLength()
    {
        return this.len;
    }

    public void setLength(int len)
    {
        this.len = len;
    }

    public String toDebugString()
    {
        StringBuffer sb = new StringBuffer("FLN_RX:");
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ByteArrayUtil.hexDump(pw, this.data, 0, this.len);
        sb.append("\n").append(sw.toString());
        return sb.toString();
    }
}
