package com.isoft.system600.messages;

import java.io.ByteArrayOutputStream;

public class System600OutputStream
        extends ByteArrayOutputStream
{
    public static System600OutputStream make()
    {
        return new System600OutputStream();
    }

    public static System600OutputStream make(int size)
    {
        return new System600OutputStream(size);
    }

    public System600OutputStream() {}

    public System600OutputStream(int size)
    {
        super(size);
    }

    public void writeCRC()
    {
        int crc = System600Message.computeCrc(toByteArray());
        write(crc & 0xFF);
        write(crc >> 8 & 0xFF);
    }
}