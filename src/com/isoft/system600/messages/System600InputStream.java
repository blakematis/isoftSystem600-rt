package com.isoft.system600.messages;


import java.io.ByteArrayInputStream;

public class System600InputStream
        extends ByteArrayInputStream
{
    public System600InputStream(byte[] buf)
    {
        super(buf);
    }

    public System600InputStream(byte[] buf, int offset, int length)
    {
        super(buf, offset, length);
    }
}
