package com.maxline.isoftSystem600.comm;


import com.tridium.basicdriver.message.Message;
import com.tridium.basicdriver.serial.BSerialNetwork;

import javax.baja.sys.BRelTime;

public class EnerDaptSystem600Comm extends EnerDaptSerialComm {

    private System600CommReceiver commReceiver = null;

    public EnerDaptSystem600Comm(BSerialNetwork serialNetwork, System600CommReceiver rDriver) {
        super(serialNetwork, rDriver);
        this.commReceiver = rDriver;
    }


    public Message transmit(Message msg, BRelTime responseTimeout, int retryCount)
    {
        this.commReceiver.resetReceiveBuffer();
        return super.transmit(msg, responseTimeout, retryCount);
    }


}
