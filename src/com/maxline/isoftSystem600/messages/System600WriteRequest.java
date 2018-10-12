package com.maxline.isoftSystem600.messages;


import com.tridium.basicdriver.message.Message;
import com.tridium.basicdriver.message.ReceivedMessage;
import java.io.IOException;

public class System600WriteRequest
        extends System600Message
{
    private byte pntNumber = 0;
    private byte[] vBytes;

    public static System600WriteRequest make(int address, byte[] value, int pointNumber)
    {
        return new System600WriteRequest(address, value, pointNumber);
    }

    public System600WriteRequest(int address, byte[] value, int pointNumber)
    {
        super(address);
        this.cmdType = 7;
        this.command = 117;
        this.pntNumber = ((byte)pointNumber);
        this.vBytes = value;
    }

    public Message toResponse(ReceivedMessage resp)
    {
        System600ReceivedMessage respMsg = (System600ReceivedMessage)resp;
        System600WriteResponse respMessage = System600WriteResponse.make();
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
        try
        {
            outStream.write(22);
            outStream.write(this.cmdType);
            outStream.write(this.deviceAddress);
            outStream.write(this.command);
            outStream.write(this.pntNumber);

            outStream.write(this.vBytes);

            outStream.writeCRC();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return outStream.toByteArray();
    }
}
