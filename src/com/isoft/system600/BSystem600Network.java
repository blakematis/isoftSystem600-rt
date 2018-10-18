package com.isoft.system600;


import com.isoft.system600.comm.BEnerDaptSerialHelper;
import com.isoft.system600.comm.EnerDaptSystem600Comm;
import com.isoft.system600.comm.System600CommReceiver;
import com.isoft.system600.job.BApogeeLearnDevicesConfig;
import com.isoft.system600.job.BApogeeLearnDevicesJob;
import com.isoft.system600.license.BSystem600License;
import com.isoft.system600.point.BSystem600ProxyExt;
import com.tridium.basicdriver.BBasicNetwork;
import com.tridium.basicdriver.comm.Comm;
import java.util.Vector;
import javax.baja.log.Log;
import javax.baja.naming.BOrd;
import javax.baja.naming.SlotPath;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.serial.BISerialHelperParent;
import javax.baja.serial.BSerialBaudRate;
import javax.baja.sys.*;

@NiagaraType
public class BSystem600Network
        extends BBasicNetwork implements BISerialHelperParent
{

    private Log log = null;
    private Subscriber subscriber;
    private String serialInitError = null;

    public static final Property interMessageDelay = newProperty(0, BRelTime.make(20L), BFacets.make("showMilliseconds", BBoolean.TRUE, "min", BRelTime.make(5L), "max", BRelTime.SECOND));

    public BRelTime getInterMessageDelay()
    {
        return (BRelTime)get(interMessageDelay);
    }

    public void setInterMessageDelay(BRelTime v)
    {
        set(interMessageDelay, v, null);
    }

    public static final Property license = newProperty(0, new BSystem600License(), null);

    public BSystem600License getLicense()
    {
        return (BSystem600License)get(license);
    }

    public void setLicense(BSystem600License v)
    {
        set(license, v, null);
    }

    public static final Property defaultDeviceConfig = newProperty(0, new BApogeeLearnDevicesConfig(), null);

    public static final Property enerDaptSerialPortConfig = newProperty(0, new BEnerDaptSerialHelper(), null);


    public BEnerDaptSerialHelper getEnerDaptSerialPortConfig() {
        return (BEnerDaptSerialHelper) this.get(enerDaptSerialPortConfig);
    }

    public void setEnerDaptSerialPortConfig(BEnerDaptSerialHelper v){ this.set(enerDaptSerialPortConfig, v, null);}

    public BApogeeLearnDevicesConfig getDefaultDeviceConfig()
    {
        return (BApogeeLearnDevicesConfig)get(defaultDeviceConfig);
    }

    public void setDefaultDeviceConfig(BApogeeLearnDevicesConfig v)
    {
        set(defaultDeviceConfig, v, null);
    }

    public static final Action submitDiscoveryDevicesJob = newAction(Flags.SUMMARY, new BApogeeLearnDevicesConfig(), null);
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.maxline.isoftSystem600.BSystem600Network(2979906276)1.0$ @*/
/* Generated Mon Oct 15 14:56:28 PDT 2018 by Slot-o-Matic (c) Tridium, Inc. 2012 */



/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/

    public BOrd submitDiscoveryDevicesJob(BApogeeLearnDevicesConfig arg)
    {
        return (BOrd)invoke(submitDiscoveryDevicesJob, arg, null);
    }


    public BSystem600Network()
    {

        //getSerialPortConfig().setBaudRate(BSerialBaudRate.baud4800);
        try {
            getEnerDaptSerialPortConfig().setBaudRate(BSerialBaudRate.baud4800);
            //setFlags(upload, 4);
            //setFlags(download, 4);
        }catch (Exception e){
            e.printStackTrace();
            e.getMessage();
        }
    }

    public void serviceStarted() throws Exception {
        this.subscriber = new NameSubscriber(this);
        this.subscriber.subscribe((BComponent)this.getParent());
        this.subscriber.subscribe(this.getEnerDaptSerialPortConfig());
        this.getEnerDaptSerialPortConfig().setSerialHelperParent(this);
        if (this.log == null) {
            this.log = this.getLog();
        }

        Log var1 = this.log;
        synchronized(this.log) {
            this.log = this.getLog();
        }

        super.serviceStarted();
    }

    public void serviceStopped() throws Exception {
        super.serviceStopped();
        this.subscriber.unsubscribeAll();
        this.subscriber = null;
    }

    public void startComm() throws Exception {
        boolean var6 = false;

        try {
            var6 = true;
            super.startComm();
            this.serialInitError = null;
            var6 = false;
        } catch (Exception var7) {
            this.serialInitError = "Could not enable serial communication (" + var7 + ")";
            throw var7;
        } finally {
            if (var6) {
                String error = this.checkSerialConfig();
                if (error == null) {
                    this.configOk();
                } else {
                    this.configFail(error);
                }

            }
        }

        String error = this.checkSerialConfig();
        if (error == null) {
            this.configOk();
        } else {
            this.configFail(error);
        }

    }

    protected String checkSerialConfig() {
        return this.getEnerDaptSerialPortConfig().getPortName().equals("none") ? "No port selected for serial communication." : this.serialInitError;
    }

    public final Log getLog() {
        String serialLogName = this.getName() + "_" + this.getEnerDaptSerialPortConfig().getPortName();
        if (!SlotPath.isValidName(serialLogName)) {
            serialLogName = SlotPath.escape(serialLogName);
        }

        return Log.getLog(serialLogName);
    }

    public final String getLoggerName() {
        String serialLogName = this.getType().getTypeName() + "_" + this.getEnerDaptSerialPortConfig().getPortName();
        if (!SlotPath.isValidName(serialLogName)) {
            serialLogName = SlotPath.escape(serialLogName);
        }

        return serialLogName;
    }

    private void updateLog() {
        String serialLogName = this.getName() + "_" + this.getEnerDaptSerialPortConfig().getPortName();
        if (!SlotPath.isValidName(serialLogName)) {
            serialLogName = SlotPath.escape(serialLogName);
        }

        Log newLog = Log.getLog(serialLogName);
        if (this.log == null) {
            this.log = this.getLog();
        }

        Log var3 = this.log;
        synchronized(this.log) {
            if (this.log != null) {
                newLog.setSeverity(this.log.getSeverity());
                if (!newLog.getLogName().equals(this.log.getLogName())) {
                    Log.deleteLog(this.log.getLogName());
                }
            }

            this.log = newLog;
        }
    }

    public final void reopenPort() {
        try {
            String newPort = this.getEnerDaptSerialPortConfig().getPortName();
            if (newPort.equals("none")) {
                this.configFail("No port selected for serial communication.");
                this.stopComm();
                return;
            }

            this.restartSerialNetwork();
        } catch (Exception var2) {
            this.getLog().error("BSerialNetwork caught exception in reopenPort(): ", var2);
        }

    }

    private void restartSerialNetwork() throws Exception {
        if (!this.isDisabled() && !this.isDown() && !this.isFatalFault()) {
            if (this.log.isTraceOn()) {
                this.log.trace(this.getName() + " *** Restarting serial comm ***");
            }

            this.stopComm();
            this.startComm();
        }

    }

    protected final void serialServiceStarted() throws Exception {
    }

    protected final void serialServiceStopped() throws Exception {
    }

    protected final void serialNetworkStarted() {
    }

    protected final void serialNetworkStopped() {
    }

    protected final void serialNetworkChanged(Property prop, Context context) {
    }

    private void tryReopenPort() {
        try {
            String newPort = this.getEnerDaptSerialPortConfig().getPortName();
            if (newPort.equals("none")) {
                this.configFail("No port selected for serial communication.");
                this.stopComm();
                return;
            }

            this.restartNetwork();
        } catch (Exception var2) {
            this.getLog().error("BSerialNetwork caught exception in reopenPort(): ", var2);
        }

    }

    private void restartNetwork() throws Exception {
        if (!this.isDisabled() && !this.isDown() && !this.isFatalFault()) {
            if (this.getLog().isTraceOn()) {
                this.getLog().trace(this.getName() + " *** Restarting serial comm ***");
            }

            this.stopComm();
            this.startComm();
        }

    }



    public Type getDeviceType()
    {
        return BSystem600Device.TYPE;
    }

    public Type getDeviceFolderType()
    {
        return BSystem600DeviceFolder.TYPE;
    }

    public BOrd doSubmitDiscoveryDevicesJob(BApogeeLearnDevicesConfig config, Context cx)
    {
        return new BApogeeLearnDevicesJob(this, config).submit(cx);
    }

    public void started()
            throws Exception
    {
        removeOldComponent();
        setFlags(upload, 4);
        setFlags(download, 4);
        super.started();
    }

    public void stopped()
            throws Exception
    {
        super.stopped();
    }


    public void changed(Property prop, Context cx)
    {
        super.changed(prop, cx);
        if (!isRunning()) {}
    }

    protected Comm makeComm()
    {
        return new EnerDaptSystem600Comm(this, new System600CommReceiver());
    }

    private final void removeOldComponent()
    {
        try
        {
            if (get("system600License") != null) {
                remove("system600License");
            }
        }
        catch (Exception localException) {}
    }

    public final boolean registerProxyExt(BSystem600ProxyExt proxyExt)
    {
        if (getLicense().getPointCount().getOrdinal() == 0) {
            return false;
        }
        synchronized (this.licenseVector)
        {
            if (!this.licenseVector.contains(proxyExt)) {
                this.licenseVector.addElement(proxyExt);
            }
            if (this.licenseVector.size() > getLicense().getPointCount().getOrdinal()) {
                proxyExt.readFail("License point count exceeded limit");
            }
            return this.licenseVector.size() > getLicense().getPointCount().getOrdinal();
        }
    }

    public final void unregisterProxyExt(BSystem600ProxyExt proxyExt)
    {
        if (getLicense().getPointCount().getOrdinal() == 0) {
            return;
        }
        synchronized (this.licenseVector)
        {
            if (this.licenseVector.contains(proxyExt)) {
                this.licenseVector.removeElement(proxyExt);
            }
        }
    }

    private Vector<BSystem600ProxyExt> licenseVector = new Vector();

    private class NameSubscriber extends Subscriber {
        private BSystem600Network net;

        public NameSubscriber(BSystem600Network net) {
            this.net = net;
        }

        public void event(BComponentEvent event) {
            if (event.getId() == 3) {
                if (event.getSlot().equals(this.net.getPropertyInParent())) {
                    this.net.updateLog();
                }
            } else if (event.getId() == 0 && event.getSlot().equals(BEnerDaptSerialHelper.portName)) {
                this.net.updateLog();
            }

        }
    }

    ////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////

    @Override
    public Type getType() { return TYPE; }
    public static final Type TYPE = Sys.loadType(BSystem600Network.class);
}
