package com.maxline.isoftSystem600.comm;

import com.tridium.basicdriver.comm.Comm;
import com.tridium.basicdriver.comm.CommReceiver;
import com.tridium.basicdriver.comm.CommTransactionManager;
import com.tridium.basicdriver.comm.CommTransmitter;
import com.tridium.basicdriver.serial.BSerialNetwork;

import javax.baja.serial.BISerialPort;
import java.io.InputStream;
import java.io.OutputStream;

public class EnerDaptSerialComm extends Comm {

    private static final long MIN_SLEEP_TIME = 10L;
    private BISerialPort serialPort;
    private InputStream in;
    private OutputStream out;
    private Thread rxThread;
    private long lastRecvMessageTicks = 0L;

    public EnerDaptSerialComm(BSerialNetwork serialNetwork, CommReceiver rDriver) {
        super(serialNetwork, rDriver);
    }

    public EnerDaptSerialComm(BSerialNetwork serialNetwork, CommReceiver rDriver, CommTransmitter tDriver) {
        super(serialNetwork, rDriver, tDriver);
    }

    public EnerDaptSerialComm(BSerialNetwork serialNetwork, CommReceiver rDriver, CommTransmitter tDriver, CommTransactionManager manager) {
        super(serialNetwork, rDriver, tDriver, manager);
    }
    protected boolean started() throws Exception {
        return false;
    }

    @Override
    protected void stopped() throws Exception {

    }
}
