package com.maxline.isoftSystem600.messages;

import com.tridium.basicdriver.message.Message;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.baja.nre.util.ByteArrayUtil;

public abstract class System600Message
        extends Message
        implements System600MessageConst
{
    public static final byte startCode = 22;
    protected byte cmdType;
    protected byte deviceAddress;
    protected byte command;

    public System600Message(int address)
    {
        this.deviceAddress = ((byte)address);
    }

    public void write(OutputStream out)
    {
        try
        {
            byte[] abytes = getBytes();
            if (abytes != null) {
                out.write(abytes);
            }
        }
        catch (Exception localException) {}
    }

    public abstract byte[] getBytes();

    public abstract int getResponseSize();

    public static int computeCrc(byte[] bytes)
    {
        int crc = 0;
        for (int i = 0; i < bytes.length; i++) {
            crc = crc >>> 8 ^ CRC_TABLE[((crc ^ bytes[i]) & 0xFF)];
        }
        return crc;
    }

    public static boolean verifyCRC(byte[] b, int len)
    {
        int crc = 0;
        int byteValue = 0;
        for (int i = 0; i < len - 2; i++)
        {
            byteValue = b[i] & 0xFF;
            crc = crc >> 8 ^ CRC_TABLE[((crc ^ byteValue) & 0xFF)];
        }
        int first = crc & 0xFF;
        int second = crc >> 8 & 0xFF;

        return ((b[(len - 2)] & 0xFF) == first) && ((b[(len - 1)] & 0xFF) == second);
    }

    public String toDebugString()
    {
        StringBuffer sb = new StringBuffer("FLN_TX:");
        byte[] abyte0 = getBytes();
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ByteArrayUtil.hexDump(pw, abyte0, 0, abyte0.length);
        sb.append("\n").append(sw.toString());
        return sb.toString();
    }

    protected byte[] crc16Chksum = null;
    public static final int[] CRC_TABLE = { 0, 49345, 49537, 320, 49921, 960, 640, 49729, 50689, 1728, 1920, 51009, 1280, 50625, 50305, 1088, 52225, 3264, 3456, 52545, 3840, 53185, 52865, 3648, 2560, 51905, 52097, 2880, 51457, 2496, 2176, 51265, 55297, 6336, 6528, 55617, 6912, 56257, 55937, 6720, 7680, 57025, 57217, 8000, 56577, 7616, 7296, 56385, 5120, 54465, 54657, 5440, 55041, 6080, 5760, 54849, 53761, 4800, 4992, 54081, 4352, 53697, 53377, 4160, 61441, 12480, 12672, 61761, 13056, 62401, 62081, 12864, 13824, 63169, 63361, 14144, 62721, 13760, 13440, 62529, 15360, 64705, 64897, 15680, 65281, 16320, 16000, 65089, 64001, 15040, 15232, 64321, 14592, 63937, 63617, 14400, 10240, 59585, 59777, 10560, 60161, 11200, 10880, 59969, 60929, 11968, 12160, 61249, 11520, 60865, 60545, 11328, 58369, 9408, 9600, 58689, 9984, 59329, 59009, 9792, 8704, 58049, 58241, 9024, 57601, 8640, 8320, 57409, 40961, 24768, 24960, 41281, 25344, 41921, 41601, 25152, 26112, 42689, 42881, 26432, 42241, 26048, 25728, 42049, 27648, 44225, 44417, 27968, 44801, 28608, 28288, 44609, 43521, 27328, 27520, 43841, 26880, 43457, 43137, 26688, 30720, 47297, 47489, 31040, 47873, 31680, 31360, 47681, 48641, 32448, 32640, 48961, 32000, 48577, 48257, 31808, 46081, 29888, 30080, 46401, 30464, 47041, 46721, 30272, 29184, 45761, 45953, 29504, 45313, 29120, 28800, 45121, 20480, 37057, 37249, 20800, 37633, 21440, 21120, 37441, 38401, 22208, 22400, 38721, 21760, 38337, 38017, 21568, 39937, 23744, 23936, 40257, 24320, 40897, 40577, 24128, 23040, 39617, 39809, 23360, 39169, 22976, 22656, 38977, 34817, 18624, 18816, 35137, 19200, 35777, 35457, 19008, 19968, 36545, 36737, 20288, 36097, 19904, 19584, 35905, 17408, 33985, 34177, 17728, 34561, 18368, 18048, 34369, 33281, 17088, 17280, 33601, 16640, 33217, 32897, 16448 };
}
