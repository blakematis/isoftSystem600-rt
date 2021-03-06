package com.isoft.system600.job;

import com.isoft.system600.BSystem600Network;
import com.isoft.system600.messages.*;

import javax.baja.job.BSimpleJob;
import javax.baja.naming.SlotPath;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.sys.BRelTime;
import javax.baja.sys.Context;
import javax.baja.sys.Property;
import javax.baja.sys.Sys;
import javax.baja.sys.Type;
import javax.baja.util.BFolder;

@NiagaraType
public class BApogeeLearnDevicesJob
        extends BSimpleJob
        implements System600MessageConst
{
    public static final Property learnedDevices = newProperty(7, new BFolder(), null);
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.maxline.isoftSystem600.job.BApogeeLearnDevicesJob(2979906276)1.0$ @*/
/* Generated Mon Oct 15 14:56:40 PDT 2018 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////

    @Override
    public Type getType() { return TYPE; }
    public static final Type TYPE = Sys.loadType(BApogeeLearnDevicesJob.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/

    public BFolder getLearnedDevices()
    {
        return (BFolder)get(learnedDevices);
    }

    public void setLearnedDevices(BFolder v)
    {
        set(learnedDevices, v, null);
    }


    final BSystem600Network network;
    int startAddress;
    int endAddress;

    public BApogeeLearnDevicesJob()
    {
        this.network = null;
    }

    public BApogeeLearnDevicesJob(BSystem600Network network, BApogeeLearnDevicesConfig cfg)
    {
        this.network = network;
        this.startAddress = cfg.getStartAddress();
        this.endAddress = cfg.getEndAddress();
    }

    void addLearnedDevice(int addr, String revision, int app)
    {
        String learnName = SlotPath.escape("ApogeeDevice#" + addr);
        if (getLearnedDevices().get(learnName) == null)
        {
            getLearnedDevices().add(learnName, new BApogeeLearnDeviceEntry(addr, revision, app, ""));
            logMessage("found " + learnName);
        }
    }

    public void run(Context cx)
            throws Exception
    {
        logMessage("starting");
        try
        {
            System600PingRequest req = null;
            System600PingResponse rsp = null;
            for (int i = this.startAddress; i <= this.endAddress; i++)
            {
                req = new System600PingRequest(i);
                rsp = (System600PingResponse)this.network.sendSync(req, BRelTime.make(1000L), 0);
                if ((rsp != null) && (!rsp.isError()))
                {
                    String revision = rsp.getRevision();

                    ReadPointRequest rdReq = new ReadPointRequest(i, 1, 2);
                    ReadPointResponse rdRsp = (ReadPointResponse)this.network.sendSync(rdReq, BRelTime.make(1000L), 0);
                    if ((rdRsp != null) && (!rdRsp.isError()))
                    {
                        int application = rdRsp.getIntValue();

                        addLearnedDevice(i, revision, application);
                    }
                }
                int percent = (int)((i - this.startAddress + 1) * 100.0D / (this.endAddress - 1 + 1));
                progress(percent);
                if (!isAlive()) {
                    break;
                }
            }
            logMessage("completed");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void logMessage(String message)
    {
        log().message(message);
        if (this.network != null)
        {
            int severity = this.network.getLog().getSeverity();
            this.network.getLog().setSeverity(1);
            this.network.getLog().message("Learn Apogee Device Job:" + message);
            this.network.getLog().setSeverity(severity);
        }
    }
}
