package com.maxline.isoftSystem600.utils;

import com.maxline.isoftSystem600.messages.System600MessageConst;

public class Sys600Util
        implements System600MessageConst
{
    public static void main(String[] args) {}

    public static String padZeros(String s, int width)
    {
        if (s.length() >= width) {
            return s;
        }
        StringBuffer sb = new StringBuffer(width);
        sb.append(getZeros(width - s.length())).append(s);
        return sb.toString();
    }

    public static String getZeros(int num)
    {
        try
        {
            return ZEROS[num];
        }
        catch (ArrayIndexOutOfBoundsException e)
        {
            if (num < 0) {
                return "";
            }
            int len = ZEROS.length;
            StringBuffer sb = new StringBuffer(num);
            int rem = num;
            for (;;)
            {
                if (rem < len)
                {
                    sb.append(ZEROS[rem]);
                    break;
                }
                sb.append(ZEROS[(len - 1)]);rem -= len - 1;
            }
            return sb.toString();
        }
    }

    private static String[] ZEROS = new String[16];

    static
    {
        ZEROS[0] = "";
        for (int i = 1; i < 16; i++) {
            ZEROS[i] = (ZEROS[(i - 1)] + "0");
        }
    }
}

