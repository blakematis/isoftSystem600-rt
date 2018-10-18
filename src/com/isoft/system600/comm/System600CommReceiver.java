package com.isoft.system600.comm;

import com.isoft.system600.messages.System600Message;
import com.isoft.system600.messages.System600MessageConst;
import com.isoft.system600.messages.System600ReceivedMessage;
import com.tridium.basicdriver.comm.CommReceiver;
import com.tridium.basicdriver.message.Message;
import com.tridium.basicdriver.message.ReceivedMessage;

import javax.baja.nre.util.ByteBuffer;

public class System600CommReceiver
        extends CommReceiver
        implements System600MessageConst
{
    private static final int IDLE = 0;
    private static final int READ_SIZE = 1;
    private static final int READ_DATA = 2;

    public void initReceiveState(Message msg)
    {
        super.initReceiveState(msg);
        this.state = 0;
        this.resLength = 0;
    }

    protected ReceivedMessage receive()
            throws Exception
    {
        boolean done = false;
        this.state = 0;
        this.resLength = 0;
        while (!done)
        {

            int charIn = -1;
            try {
                charIn = getInputStream().read();
            }catch (Exception e){

            }
            boolean newChar = charIn != -1;
            charIn &= 0xFF;
            if (newChar) {
                switch (this.state)
                {
                    case 0:
                        if (charIn == 22)
                        {
                            this.rcvBuffer.write((byte)charIn);
                            this.state = 1;
                        }
                        break;
                    case 1:
                        this.rcvBuffer.write((byte)charIn);
                        this.resLength = (charIn + 2);
                        this.state = 2;
                        break;
                    case 2:
                        this.rcvBuffer.write((byte)charIn);
                        if (this.rcvBuffer.getLength() >= this.resLength)
                        {
                            done = System600Message.verifyCRC(this.rcvBuffer.getBytes(), this.rcvBuffer.getLength());
                            if (!done)
                            {
                                resetReceiveBuffer();
                                this.state = 0;
                            }
                        }
                        break;
                }
            }
        }
        if (this.msg == null)
        {
            this.msg = new System600ReceivedMessage(this.rcvBuffer.getBytes(), this.rcvBuffer.getLength());
        }
        else
        {
            this.msg.setBytes(this.rcvBuffer.getBytes());
            this.msg.setLength(this.rcvBuffer.getLength());
        }
        resetReceiveBuffer();
        return this.msg;
    }

    public synchronized void resetReceiveBuffer()
    {
        this.rcvBuffer.reset();
    }

    private int state = 0;
    private ByteBuffer rcvBuffer = new ByteBuffer();
    private System600ReceivedMessage msg = null;
    private int resLength = 0;
}


