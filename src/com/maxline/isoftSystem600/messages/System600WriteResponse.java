package com.maxline.isoftSystem600.messages;

public class System600WriteResponse
        extends System600Response
{
    public static System600WriteResponse make()
    {
        return new System600WriteResponse();
    }

    public void readResponse(byte[] response, int len)
    {
        this.in = new System600InputStream(response, 0, len);
        this.in.skip(1L);
        this.error = (this.in.read() != 4);
    }
}
