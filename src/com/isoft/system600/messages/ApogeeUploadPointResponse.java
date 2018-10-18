package com.isoft.system600.messages;

public class ApogeeUploadPointResponse
        extends System600Response
{
    public void readResponse(byte[] response, int len)
    {
        this.in = new System600InputStream(response, 0, len);
    }
}