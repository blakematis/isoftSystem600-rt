package com.isoft.system600.messages;

public class DiscoveryPointsResponse
        extends System600Response
{
    DiscoveryPointsRequest request;
    private System600InputStream in;

    public static DiscoveryPointsResponse make(DiscoveryPointsRequest request)
    {
        return new DiscoveryPointsResponse(request);
    }

    public DiscoveryPointsResponse(DiscoveryPointsRequest request)
    {
        this.request = request;
    }

    public void readResponse(byte[] response, int len)
    {
        this.in = new System600InputStream(response, 0, len);
    }

    public System600InputStream getIn()
    {
        return this.in;
    }
}
