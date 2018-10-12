package com.maxline.isoftSystem600.job;

import com.maxline.isoftSystem600.BSystem600Device;
import com.maxline.isoftSystem600.enums.BApogeeUnit;
import com.maxline.isoftSystem600.messages.System600MessageConst;
import com.maxline.isoftSystem600.utils.ApogeePointUtil;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import javax.baja.file.BIFile;
import javax.baja.job.BSimpleJob;
import javax.baja.naming.BOrd;
import javax.baja.naming.SlotPath;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.sys.Context;
import javax.baja.sys.Property;
import javax.baja.sys.Sys;
import javax.baja.sys.Type;
import javax.baja.util.BFolder;

@NiagaraType
public class BApogeeLearnPointsJob
        extends BSimpleJob
        implements System600MessageConst
{
    public static final Property learnedPoints = newProperty(7, new BFolder(), null);

    public BFolder getLearnedPoints()
    {
        return (BFolder)get(learnedPoints);
    }

    public void setLearnedPoints(BFolder v)
    {
        set(learnedPoints, v, null);
    }

    public Type getType()
    {
        return TYPE;
    }

    public static final Type TYPE = Sys.loadType(BApogeeLearnPointsJob.class);
    final BSystem600Device device;
    BApogeeUnit unit;

    public BApogeeLearnPointsJob()
    {
        this.device = null;
    }

    public BApogeeLearnPointsJob(BSystem600Device device, BApogeeUnit unit)
    {
        this.device = device;
        this.unit = unit;
    }

    void addLearnedPoint(String[] as)
    {
        String learnName = as[1];
        learnName = SlotPath.escape(learnName.trim());
        try
        {
            if (getLearnedPoints().get(learnName) == null)
            {
                getLearnedPoints().add(learnName, new BApogeeLearnPointEntry(as));
                logMessage("found point : " + learnName);
            }
        }
        catch (Exception localException) {}
    }

    public void run(Context cx)
            throws Exception
    {
        logMessage("starting");
        try
        {
            BOrd ord = ApogeePointUtil.getDevicePoint(this.device, getUnit());
            if (ord == null)
            {
                logMessage("deviceUpload not support");

                return;
            }
            BIFile bifile = (BIFile)ord.resolve().get();
            BufferedReader f = new BufferedReader(new InputStreamReader(bifile.getInputStream()));
            String s = f.readLine();
            int percent = 0;
            while (s != null)
            {
                String[] as = getPoint(s);
                addLearnedPoint(as);
                progress(percent);
                if (percent > 99) {
                    percent = 0;
                } else {
                    percent++;
                }
                s = f.readLine();
            }
            f.close();
            progress(100);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public String[] getUploadPoint(int pAddr, boolean unit)
    {
        String[] as = new String[5];
        as = ApogeePointUtil.getTablePoint(pAddr, unit);
        return as;
    }

    public String[] getPoint(String s)
    {
        StringTokenizer stk = null;
        String[] as = null;
        try
        {
            stk = new StringTokenizer(s.trim(), ",");
            as = new String[stk.countTokens()];
            int i = 0;
            while (stk.hasMoreTokens())
            {
                as[i] = stk.nextToken(",").trim();
                i++;
            }
        }
        catch (Exception localException) {}
        return as;
    }

    public boolean getUnit()
    {
        if (this.unit.getOrdinal() == 0) {
            return true;
        }
        return false;
    }

    private final void logMessage(String s)
    {
        log().message(s);
        if (this.device == null) {
            return;
        }
        int i = this.device.getSystem600Network().getLog().getSeverity();
        this.device.getSystem600Network().getLog().setSeverity(1);
        this.device.getSystem600Network().getLog().message("Learn Apogee Points Job:" + s);
        this.device.getSystem600Network().getLog().setSeverity(i);
    }
}
