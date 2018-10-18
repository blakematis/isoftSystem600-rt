package com.isoft.system600.comm;


import com.isoft.system600.BSystem600Network;
import com.tridium.basicdriver.message.Message;

import javax.baja.sys.BRelTime;

public class EnerDaptSystem600Comm extends EnerDaptSerialComm {

    private System600CommReceiver commReceiver = null;

    public EnerDaptSystem600Comm(BSystem600Network system600Network, System600CommReceiver rDriver) {
        super(system600Network, rDriver);
        this.commReceiver = rDriver;
    }


    public Message transmit(Message msg, BRelTime responseTimeout, int retryCount)
    {
        this.commReceiver.resetReceiveBuffer();
        return super.transmit(msg, responseTimeout, retryCount);
    }


}
