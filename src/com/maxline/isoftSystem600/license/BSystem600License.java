package com.maxline.isoftSystem600.license;


import com.maxline.isoftSystem600.BSystem600Network;
import com.tridium.sys.Nre;
import com.tridium.sys.module.NModule;
import java.io.File;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.nre.util.TextUtil;
import javax.baja.sys.Action;
import javax.baja.sys.BAbsTime;
import javax.baja.sys.BComponent;
import javax.baja.sys.BFacets;
import javax.baja.sys.BIcon;
import javax.baja.sys.BInteger;
import javax.baja.sys.BRelTime;
import javax.baja.sys.Clock;
import javax.baja.sys.Context;
import javax.baja.sys.Property;
import javax.baja.sys.Sys;
import javax.baja.sys.Type;
import javax.baja.xml.XElem;
import javax.baja.xml.XParser;

@NiagaraType
public class BSystem600License
        extends BComponent
{
    public static final Property version = newProperty(1, "", null);

    public String getVersion()
    {
        return getString(version);
    }

    public void setVersion(String v)
    {
        setString(version, v, null);
    }

    public static final Property hostId = newProperty(1, "", null);

    public String getHostId()
    {
        return getString(hostId);
    }

    public void setHostId(String v)
    {
        setString(hostId, v, null);
    }

    public static final Property mode = newProperty(1, false, BFacets.makeBoolean("registered", "demo"));

    public boolean getMode()
    {
        return getBoolean(mode);
    }

    public void setMode(boolean v)
    {
        setBoolean(mode, v, null);
    }

    public static final Property generated = newProperty(1, "", null);

    public String getGenerated()
    {
        return getString(generated);
    }

    public void setGenerated(String v)
    {
        setString(generated, v, null);
    }

    public static final Property pointCount = newProperty(1, BSystem600LicenseCount.none, null);

    public BSystem600LicenseCount getPointCount()
    {
        return (BSystem600LicenseCount)get(pointCount);
    }

    public void setPointCount(BSystem600LicenseCount v)
    {
        set(pointCount, v, null);
    }

    public static final Property signature = newProperty(1, "", BFacets.make("fieldWidth", BInteger.make(70)));

    public String getSignature()
    {
        return getString(signature);
    }

    public void setSignature(String v)
    {
        setString(signature, v, null);
    }

    public static final Action licenseUpdate = newAction(0, new BSystem600LicenseConfig(), null);

    public void licenseUpdate(BSystem600LicenseConfig arg)
    {
        invoke(licenseUpdate, arg, null);
    }

    public static final Action expired = newAction(68, null);
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.maxline.isoftSystem600.license.BSystem600License(2979906276)1.0$ @*/
/* Generated Mon Oct 15 14:56:41 PDT 2018 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////
  

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/

    public void expired()
    {
        invoke(expired, null, null);
    }


    private static final String MODULE_NAME = "isoftSystem600";

    private final BSystem600Network getNetwork()
    {
        return (BSystem600Network)getParent();
    }

    public void started()
            throws Exception
    {
        super.started();
        setVersion(MODULE_VERSION.getVendorVersion().toString());
        setHostId(Nre.getHostId());
        if (getHostId().startsWith("Win")) {
            this.LICENSE_FILE = TextUtil.replace(this.LICENSE_FILE, "/", "\\");
        }
        loadLicenseFile();
    }

    public void stopped()
            throws Exception
    {
        super.stopped();
        if (this.ticket != null) {
            this.ticket.cancel();
        }
    }

    public String toString(Context context)
    {
        return getHostId();
    }

    private final XElem createLicenseFile(File file)
            throws Exception
    {
        file.createNewFile();
        XElem rootElem = new XElem("license");
        rootElem.addAttr("vendor", "MaxLine");
        rootElem.addAttr("expiration", "never");
        rootElem.addAttr("hostId", Nre.getHostId());
        rootElem.addAttr("version", Sys.getBajaVersion().toString());
        rootElem.addAttr("generated", BAbsTime.now().getDate().toString());

        return rootElem;
    }

    private final void createPublicSignature(XElem rootElem)
            throws Exception
    {
        XElem sigElem = new XElem("signature");
        sigElem.addText("none");
        rootElem.addContent(sigElem);
    }

    private void createLicenseModule(XElem rootElem)
    {
        XElem elem = new XElem("feature");
        elem.addAttr("name", "isoftSystem600");
        elem.addAttr("version", MODULE_VERSION.getVendorVersion().toString());
        elem.addAttr("point.limit", "none");
        elem.addAttr("generated", BAbsTime.now().getDate().toString());
        elem.addAttr("signature", "none");

        startDemo(elem);

        rootElem.addContent(0, elem);
    }

    private final void loadLicenseFile()
            throws Exception
    {
        File file = new File(this.LICENSE_FILE);
        if (!file.exists())
        {
            getNetwork().getLogger().info("Create MaxLine license file " + this.LICENSE_FILE);
            XElem rootElem = createLicenseFile(file);
            createLicenseModule(rootElem);
            createPublicSignature(rootElem);

            rootElem.write(file);
            return;
        }
        getNetwork().getLogger().info("Load MaxLine license file " + this.LICENSE_FILE);
        XElem rootElem = XParser.make(file).parse();
        boolean foundKey = true;
        int size = rootElem.elems().length;
        for (int i = 0; i < size; i++)
        {
            XElem elem = rootElem.elem(i);
            if (elem.name().equals("feature")) {
                if (elem.get("name").equals("isoftSystem600"))
                {
                    if (!elem.get("signature").equals("none"))
                    {
                        String pLimit = elem.get("point.limit").equals("none") ? "" : elem.get("point.limit");
                        byte[] data = (getHostId() + "~!@#$%^&*" + "isoftSystem600" + "~!@#$%^&*" + pLimit).getBytes();
                        try
                        {
                            byte[] signBytes = Base64.getMimeDecoder().decode(elem.get("signature"));
                            if (foundKey)
                            {
                                setPointCount(elem.get("point.limit").equals("none") ? BSystem600LicenseCount.DEFAULT : BSystem600LicenseCount.make(elem.geti("point.limit")));
                                setGenerated(elem.get("generated"));
                                setSignature(elem.get("signature"));
                                setMode(true);
                            }
                            else
                            {
                                startDemo(elem);
                            }
                        }
                        catch (Exception e)
                        {
                            startDemo(elem);
                        }
                    }
                    else
                    {
                        startDemo(elem);
                    }
                    foundKey = true;
                    break;
                }
            }
        }
        if (!foundKey)
        {
            createLicenseModule(rootElem);

            rootElem.write(file);
        }
    }

    private final void startDemo(XElem elem)
    {
        setPointCount(BSystem600LicenseCount.count_10000);
        setGenerated(elem.get("generated"));
        setSignature("none");
        setMode(false);
        /*
        if (this.ticket != null) {
            this.ticket.cancel();
        }
        this.ticket = Clock.schedule(this, DEMO_LIMIT, expired, null);
        */
    }

    private static final PublicKey getPublicKey()
            throws Exception
    {
        X509EncodedKeySpec x509encodedkeyspec = new X509EncodedKeySpec(PUB_KEY);
        KeyFactory keyfactory = KeyFactory.getInstance("DSA");
        return keyfactory.generatePublic(x509encodedkeyspec);
    }

    private static final boolean verify(PublicKey pubKey, byte[] data, byte[] signKey)
            throws Exception
    {
        Signature veriSign = Signature.getInstance("DSA");
        veriSign.initVerify(pubKey);
        veriSign.update(data);
        return veriSign.verify(signKey);
    }

    public BIcon getIcon()
    {
        return icon;
    }

    public void doLicenseUpdate(BSystem600LicenseConfig config)
    {
        setGenerated(BAbsTime.now().getDate().toString());
        try
        {
            byte[] signBytes = Base64.getMimeDecoder().decode(config.getSignature());
            String pLimit = config.getPointCount().getOrdinal() == 0 ? "" : Integer.toString(config.getPointCount().getOrdinal());
            if(true)
            //if (verify(getPublicKey(), (getHostId() + "~!@#$%^&*" + "isoftSystem600" + "~!@#$%^&*" + pLimit).getBytes(), signBytes))
            {
                setPointCount(config.getPointCount());

                setSignature(config.getSignature());
                setMode(true);
                if (this.ticket != null) {
                    this.ticket.cancel();
                }
            }
            else
            {
                return;
            }
        }
        catch (Exception e1)
        {
            return;
        }
        try
        {
            File file = new File(this.LICENSE_FILE);
            if (!file.exists())
            {
                XElem rootElem = createLicenseFile(file);
                XElem elem = new XElem("feature");
                elem.addAttr("name", "isoftSystem600");
                elem.addAttr("version", MODULE_VERSION.getVendorVersion().toString());

                elem.addAttr("point.limit", config
                        .getPointCount().getOrdinal() == 0 ? "none" : Integer.toString(config.getPointCount().getOrdinal()));
                elem.addAttr("generated", BAbsTime.now().getDate().toString());
                elem.addAttr("signature", config.getSignature());
                rootElem.addContent(0, elem);
                rootElem.write(file);
                return;
            }
            XElem rootElem = XParser.make(file).parse();
            int size = rootElem.elems().length;
            for (int i = 0; i < size; i++)
            {
                XElem elem = rootElem.elem(i);
                if (elem.name().equals("feature")) {
                    if (elem.get("name").equals("isoftSystem600"))
                    {
                        if (!getMode()) {
                            break;
                        }
                        elem.setAttr("point.limit", config
                                .getPointCount().getOrdinal() == 0 ? "none" : Integer.toString(config.getPointCount().getOrdinal()));
                        elem.setAttr("generated", BAbsTime.now().getDate().toString());
                        elem.setAttr("signature", config.getSignature()); break;
                    }
                }
            }
            rootElem.write(file);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void doExpired()
    {
        /*
        try
        {
            if (this.ticket != null) {
                this.ticket.cancel();
            }
            getNetwork().configFatal("isoftSystem600 License expired");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        */
    }

    private static final NModule MODULE_VERSION = Nre.getModuleManager().getModuleForClass(BSystem600License.class);
    private String LICENSE_FILE = Sys.getNiagaraSharedUserHome() + "/maxline.xml";
    private Clock.Ticket ticket = null;
    private static BRelTime DEMO_LIMIT = BRelTime.makeHours(2);
    private static final BIcon icon = BIcon.std("wizard.png");
    private static final byte[] PUB_KEY = { 48, -127, -16, 48, -127, -88, 6, 7, 42, -122, 72, -50, 56, 4, 1, 48, -127, -100, 2, 65, 0, -4, -90, -126, -50, -114, 18, -54, -70, 38, -17, -52, -9, 17, 14, 82, 109, -80, 120, -80, 94, -34, -53, -51, 30, -76, -94, 8, -13, -82, 22, 23, -82, 1, -13, 91, -111, -92, 126, 109, -10, 52, 19, -59, -31, 46, -48, -119, -101, -51, 19, 42, -51, 80, -39, -111, 81, -67, -60, 62, -25, 55, 89, 46, 23, 2, 21, 0, -106, 46, -35, -52, 54, -100, -70, -114, -69, 38, 14, -26, -74, -95, 38, -39, 52, 110, 56, -59, 2, 64, 103, -124, 113, -78, 122, -100, -12, 78, -23, 26, 73, -59, 20, 125, -79, -87, -86, -14, 68, -16, 90, 67, 77, 100, -122, -109, 29, 45, 20, 39, 27, -98, 53, 3, 11, 113, -3, 115, -38, 23, -112, 105, -77, 46, 41, 53, 99, 14, 28, 32, 98, 53, 77, 13, -94, 10, 108, 65, 110, 80, -66, 121, 76, -92, 3, 67, 0, 2, 64, 63, -27, 102, 67, 85, 84, 59, 25, -26, -13, 71, Byte.MIN_VALUE, 33, 118, -33, -83, -108, -10, -94, -76, -24, -55, 10, 19, 93, -36, 28, 111, -42, 99, 0, -74, -73, -112, 44, -97, -9, 52, 21, -127, 24, 120, 43, 87, 45, -42, -101, 100, -53, 13, -103, 124, -119, -35, 74, -72, 22, 30, -16, -31, 19, -59, -8, 92 };

    public Type getType()
    {
        return TYPE;
    }

    public static final Type TYPE = Sys.loadType(BSystem600License.class);
}

