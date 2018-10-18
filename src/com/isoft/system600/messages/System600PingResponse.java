package com.isoft.system600.messages;

public class System600PingResponse
        extends System600Response
{
    public static System600PingResponse make()
    {
        return new System600PingResponse();
    }

    public void readResponse(byte[] response, int len)
    {
        this.in = new System600InputStream(response, 0, len);
        this.in.skip(1L);
        this.error = (this.in.read() != 11);
    }

    public String getRevision()
    {
        this.in.reset();
        this.in.skip(4L);
        byte[] b = new byte[4];
        this.in.read(b, 0, b.length);
        return new String(b).toUpperCase();
    }
}
