package com.maxline.isoftSystem600.messages;


import com.tridium.basicdriver.message.Message;
import com.tridium.basicdriver.message.ReceivedMessage;

public class System600PingRequest
        extends System600Message
{
    public static System600PingRequest make(int address)
    {
        return new System600PingRequest(address);
    }

    public System600PingRequest(int address)
    {
        super(address);
        this.cmdType = 4;
        this.command = Byte.MIN_VALUE;
    }

    public Message toResponse(ReceivedMessage resp)
    {
        System600ReceivedMessage respMsg = (System600ReceivedMessage)resp;
        System600PingResponse respMessage = System600PingResponse.make();
        respMessage.readResponse(respMsg.getBytes(), respMsg.getLength());
        respMsg = null;
        return respMessage;
    }

    public int getResponseSize()
    {
        return 13;
    }

    public byte[] getBytes()
    {
        System600OutputStream outStream = System600OutputStream.make();
        outStream.write(22);
        outStream.write(this.cmdType);
        outStream.write(this.deviceAddress);
        outStream.write(this.command);

        outStream.writeCRC();
        return outStream.toByteArray();
    }
}
