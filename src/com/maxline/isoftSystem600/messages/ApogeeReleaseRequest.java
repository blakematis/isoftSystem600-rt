package com.maxline.isoftSystem600.messages;

import com.tridium.basicdriver.message.Message;
import com.tridium.basicdriver.message.ReceivedMessage;

public class ApogeeReleaseRequest
        extends System600Message
{
    private byte pntNumber = 0;

    public static ApogeeReleaseRequest make(int address, int pointNumber)
    {
        return new ApogeeReleaseRequest(address, pointNumber);
    }

    public ApogeeReleaseRequest(int address, int pointNumber)
    {
        super(address);
        this.cmdType = 5;
        this.command = 15;
        this.pntNumber = ((byte)pointNumber);
    }

    public Message toResponse(ReceivedMessage resp)
    {
        System600ReceivedMessage respMsg = (System600ReceivedMessage)resp;
        ApogeeReleaseResponse respMessage = ApogeeReleaseResponse.make();
        respMessage.readResponse(respMsg.getBytes(), respMsg.getLength());
        respMsg = null;
        return respMessage;
    }

    public int getResponseSize()
    {
        return 6;
    }

    public byte[] getBytes()
    {
        System600OutputStream outStream = System600OutputStream.make();

        outStream.write(22);
        outStream.write(this.cmdType);
        outStream.write(this.deviceAddress);
        outStream.write(this.command);
        outStream.write(this.pntNumber);

        outStream.writeCRC();
        return outStream.toByteArray();
    }
}

