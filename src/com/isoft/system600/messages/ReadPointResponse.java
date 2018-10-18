package com.isoft.system600.messages;

public class ReadPointResponse
        extends System600Response
{
    public static ReadPointResponse make()
    {
        return new ReadPointResponse();
    }

    public String getStrValue()
    {
        this.in.reset();
        this.in.skip(5L);
        byte[] b = new byte[2];
        this.in.read(b, 0, b.length);
        return new String(b).toUpperCase();
    }

    public void readResponse(byte[] response, int len)
    {
        this.in = new System600InputStream(response, 0, len);
        this.in.skip(1L);
        this.error = (this.in.read() != 7);
    }

    public float getFloatValue()
    {
        return getIntValue();
    }

    public int getIntValue()
    {
        this.in.reset();
        this.in.skip(5L);

        int val0 = this.in.read();
        int val1 = this.in.read();
        return (val1 & 0xFF) << 8 | val0 & 0xFF;
    }

    public String getStringValue()
    {
        return "NONE";
    }

    public boolean getBooleanValue()
    {
        return getIntValue() == 1;
    }
}

