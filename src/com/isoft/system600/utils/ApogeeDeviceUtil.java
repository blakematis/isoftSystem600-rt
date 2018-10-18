package com.isoft.system600.utils;

public class ApogeeDeviceUtil
{
    public static String getDeviceType(int devType)
    {
        switch (devType)
        {
            case 630:
            case 703:
                return "LAB";
        }
        return "TEC";
    }

    public static String getDeviceDesc(int devType)
    {
        switch (devType)
        {
            case 630:
                return "Fume Hood - 2 Pos. Controller";
            case 703:
                return "DPM - Single Sensor Operation, Lab Application";
        }
        return null;
    }
}
