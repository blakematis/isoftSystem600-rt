package com.maxline.isoftSystem600.messages;

public abstract interface System600MessageConst
{
    public static final byte readSubPointsCmdType = 6;
    public static final byte readSubPointsCommand = 10;
    public static final byte[] readAllSubPoints = { 1, 9 };
    public static final byte readPointValueCmdType = 6;
    public static final byte readPointValueCommand = 10;
    public static final byte readPointValue = 1;
    public static final byte readPointStatus = 32;
    public static final byte READ_SINGLE_POINT_OK = 7;
    public static final byte operatorPointCmdType = 7;
    public static final byte operatorPointCommand = 117;
    public static final byte WRITE_SINGLE_POINT_OK = 4;
    public static final byte operatorPointFault = 5;
    public static final byte operatorReleaseCmdType = 5;
    public static final byte operatorReleaseCommand = 15;
    public static final byte readDeviceCmdType = 4;
    public static final byte readDeviceCommand = -128;
    public static final byte READ_DEVICE_OK = 11;
    public static final int NO_RESPONSE = 255;
    public static final int IS_ERROR = 5;
    public static final int CRC_ERROR = 256;
    public static final int NO_MATCH = 257;
}
