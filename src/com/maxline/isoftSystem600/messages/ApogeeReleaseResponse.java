package com.maxline.isoftSystem600.messages;

public class ApogeeReleaseResponse
        extends System600Response
{
    public static ApogeeReleaseResponse make()
    {
        return new ApogeeReleaseResponse();
    }

    public void readResponse(byte[] response, int len)
    {
        this.in = new System600InputStream(response, 0, len);
        this.in.skip(1L);
        this.error = (this.in.read() != 4);
    }
}
