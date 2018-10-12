package com.maxline.isoftSystem600.messages;

import com.tridium.basicdriver.message.Message;
import com.tridium.basicdriver.message.ReceivedMessage;

public class ApogeeUploadPointRequest
        extends System600Message
{
    public ApogeeUploadPointRequest(int address, int pointAddr)
    {
        super(address);
        this.cmdType = 6;
        this.command = 10;
        this.pointAddr = pointAddr;
    }

    public byte[] getBytes()
    {
        System600OutputStream outStream = System600OutputStream.make();
        outStream.write(22);
        outStream.write(this.cmdType);
        outStream.write(this.deviceAddress);
        outStream.write(this.command);
        outStream.write(this.pointAddr);
        outStream.write(99);

        outStream.writeCRC();
        return outStream.toByteArray();
    }

    public int getResponseSize()
    {
        return 49;
    }

    public Message toResponse(ReceivedMessage resp)
    {
        System600ReceivedMessage respMsg = (System600ReceivedMessage)resp;
        ApogeeUploadPointResponse respMessage = new ApogeeUploadPointResponse();
        respMessage.readResponse(respMsg.getBytes(), respMsg.getLength());
        respMsg = null;
        return respMessage;
    }

    int pointAddr = 0;
}
