package com.maxline.isoftSystem600.comm;

import com.maxline.isoftSystem600.BSystem600Network;
import com.tridium.basicdriver.message.Message;
import com.tridium.basicdriver.serial.SerialComm;
import javax.baja.sys.BRelTime;

public class System600Comm
        extends SerialComm
{
    public System600Comm(BSystem600Network network, System600CommReceiver commReceiver)
    {
        super(network, commReceiver);
        this.commReceiver = commReceiver;
    }

    public Message transmit(Message msg, BRelTime responseTimeout, int retryCount)
    {
        this.commReceiver.resetReceiveBuffer();
        return super.transmit(msg, responseTimeout, retryCount);
    }

    private System600CommReceiver commReceiver = null;
}