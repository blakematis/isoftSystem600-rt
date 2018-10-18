package com.isoft.system600.messages;

import com.tridium.basicdriver.message.Message;
import com.tridium.basicdriver.message.ReceivedMessage;

public class ReadPointRequest
        extends System600Message
{
    public static ReadPointRequest make(int address, int queryType, int pointNumber)
    {
        return new ReadPointRequest(address, queryType, pointNumber);
    }

    public ReadPointRequest(int address, int queryType, int pointNumber)
    {
        super(address);
        this.cmdType = 6;
        this.command = 10;
        this.pntNumber = ((byte)pointNumber);
        this.queryType = ((byte)queryType);
    }

    public Message toResponse(ReceivedMessage resp)
    {
        System600ReceivedMessage respMsg = (System600ReceivedMessage)resp;
        ReadPointResponse respMessage = ReadPointResponse.make();
        respMessage.readResponse(respMsg.getBytes(), respMsg.getLength());
        respMsg = null;
        return respMessage;
    }

    public int getResponseSize()
    {
        return 9;
    }

    public byte[] getBytes()
    {
        System600OutputStream outStream = System600OutputStream.make();
        outStream.write(22);
        outStream.write(this.cmdType);
        outStream.write(this.deviceAddress);
        outStream.write(this.command);
        outStream.write(this.pntNumber);
        outStream.write(this.queryType);

        outStream.writeCRC();

        return outStream.toByteArray();
    }

    private byte pntNumber = 0;
    private byte queryType = 0;
}
