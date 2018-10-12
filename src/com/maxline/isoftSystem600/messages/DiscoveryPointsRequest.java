package com.maxline.isoftSystem600.messages;

import com.tridium.basicdriver.message.Message;
import com.tridium.basicdriver.message.ReceivedMessage;
import java.io.IOException;

public class DiscoveryPointsRequest
        extends System600Message
{
    public static DiscoveryPointsRequest make(int address)
    {
        return new DiscoveryPointsRequest(address);
    }

    public DiscoveryPointsRequest(int address)
    {
        super(address);
        this.cmdType = 6;
        this.command = 10;
    }

    public int getResponseSize()
    {
        return 240;
    }

    public Message toResponse(ReceivedMessage resp)
    {
        System600ReceivedMessage respMsg = (System600ReceivedMessage)resp;
        DiscoveryPointsResponse respMessage = DiscoveryPointsResponse.make(this);
        respMessage.readResponse(respMsg.getBytes(), respMsg.getLength());
        respMsg = null;
        return respMessage;
    }

    public byte[] getBytes()
    {
        System600OutputStream outStream = System600OutputStream.make();
        try
        {
            outStream.write(22);
            outStream.write(this.cmdType);
            outStream.write(this.deviceAddress);
            outStream.write(this.command);
            outStream.write(readAllSubPoints);

            outStream.writeCRC();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return outStream.toByteArray();
    }
}

