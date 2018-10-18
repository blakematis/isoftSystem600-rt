package com.isoft.system600.utils;

import com.isoft.system600.BSystem600Device;

import javax.baja.naming.BOrd;

public class ApogeePointUtil
{
    private static final String DB_FOLDER = "module://isoftSystem600/com/isoft/System600/db/";

    public static BOrd getDevicePoint(BSystem600Device device, boolean flag)
    {
        int deviceCode = device.getApplication();
        switch (deviceCode)
        {
            case 10:
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/SA102722" + (flag ? "M" : "E") + ".csv");
            case 23:
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/VV0423" + (flag ? "M" : "E") + ".csv");
            case 30:
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/CV0330" + (flag ? "M" : "E") + ".csv");
            case 90:
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/FC0290" + (flag ? "M" : "E") + ".csv");
            case 630:
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/FHS3630" + (flag ? "M" : "E") + ".csv");
            case 701:
            case 703:
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/RM01703" + (flag ? "M" : "E") + ".csv");
            case 1002:
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/1002" + (flag ? "E" : "E") + ".csv");
            case 1003:
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/1003" + (flag ? "E" : "E") + ".csv");
            case 1005:
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/UC061005" + (flag ? "E" : "E") + ".csv");
            case 1006:
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/UC061006" + (flag ? "E" : "E") + ".csv");
            case 1007:
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/1007" + (flag ? "E" : "E") + ".csv");
            case 1008:
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/1008" + (flag ? "E" : "E") + ".csv");
            case 1009:
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/1009" + (flag ? "E" : "E") + ".csv");
            case 1010:
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/1010" + (flag ? "E" : "E") + ".csv");
            case 1012:
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/1012" + (flag ? "E" : "E") + ".csv");
            case 1013:
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/10013" + (flag ? "E" : "E") + ".csv");
            case 1101:
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/1101" + (flag ? "E" : "E") + ".csv");
            case 2020:
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/VV112020" + (flag ? "M" : "E") + ".csv");
            case 2021:
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/VV112021" + (flag ? "M" : "E") + ".csv");
            case 2022:
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/VV112022" + (flag ? "M" : "E") + ".csv");
            case 2023:
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/VV112023" + (flag ? "M" : "E") + ".csv");
            case 2024:
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/VV112024" + (flag ? "M" : "E") + ".csv");
            case 2025:
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/VV112025" + (flag ? "M" : "E") + ".csv");
            case 2026:
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/VV112026" + (flag ? "M" : "E") + ".csv");
            case 2027:
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/VV112027" + (flag ? "M" : "E") + ".csv");
            case 2030:
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/CV102030" + (flag ? "M" : "E") + ".csv");
            case 2032:
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/CV102032" + (flag ? "M" : "E") + ".csv");
            case 2033:
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/CV102033" + (flag ? "M" : "E") + ".csv");
            case 2035:
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/SD102035" + (flag ? "M" : "E") + ".csv");
            case 2036:
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/SD102036" + (flag ? "M" : "E") + ".csv");
            case 2040:
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/FC122040" + (flag ? "M" : "E") + ".csv");
            case 2041:
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/FC122041" + (flag ? "M" : "E") + ".csv");
            case 2050:
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/FC122050" + (flag ? "M" : "E") + ".csv");
            case 2051:
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/FC122051" + (flag ? "M" : "E") + ".csv");
            case 2052:
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/FC122052" + (flag ? "M" : "E") + ".csv");
            case 2053:
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/FC122053" + (flag ? "M" : "E") + ".csv");
            case 2054:
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/FC122054" + (flag ? "M" : "E") + ".csv");
            case 2064:
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/SD102064" + (flag ? "M" : "E") + ".csv");
            case 2065:
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/SD102065" + (flag ? "M" : "E") + ".csv");
            case 2066:
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/SD102066" + (flag ? "M" : "E") + ".csv");
            case 2090:
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/FC122090" + (flag ? "M" : "E") + ".csv");
            case 2070:
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/HP102070" + (flag ? "M" : "E") + ".csv");
            case 2071:
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/HP102071" + (flag ? "M" : "E") + ".csv");
            case 2072:
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/HP102072" + (flag ? "M" : "E") + ".csv");
            case 2091:
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/VV112091" + (flag ? "M" : "E") + ".csv");
            case 2092:
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/CV112092" + (flag ? "M" : "E") + ".csv");
            case 2215:
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/RP202215" + (flag ? "M" : "E") + ".csv");
            case 2216:
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/RE202216" + (flag ? "M" : "E") + ".csv");
            case 2217:
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/RP202217" + (flag ? "M" : "E") + ".csv");
            case 2218:
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/RE202218" + (flag ? "M" : "E") + ".csv");
            case 2219:
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/RP202219" + (flag ? "M" : "E") + ".csv");
            case 2237:
                if (device.getRevision().equals("DD10")) {
                    return BOrd.make("module://isoftSystem600/com/isoft/System600/db/DD102237" + (flag ? "M" : "E") + ".csv");
                }
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/DD2S2237" + (flag ? "M" : "E") + ".csv");
            case 2238:
                if (device.getRevision().equals("DD10")) {
                    return BOrd.make("module://isoftSystem600/com/isoft/System600/db/DD102238" + (flag ? "M" : "E") + ".csv");
                }
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/DD2S2238" + (flag ? "M" : "E") + ".csv");
            case 2267:
                if (device.getRevision().equals("DD10")) {
                    return BOrd.make("module://isoftSystem600/com/isoft/System600/db/DD102267" + (flag ? "M" : "E") + ".csv");
                }
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/DD2S2267" + (flag ? "M" : "E") + ".csv");
            case 2268:
                if (device.getRevision().equals("DD10")) {
                    return BOrd.make("module://isoftSystem600/com/isoft/System600/db/DD102268" + (flag ? "M" : "E") + ".csv");
                }
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/DD2S2268" + (flag ? "M" : "E") + ".csv");
            case 2269:
                if (device.getRevision().equals("DD10")) {
                    return BOrd.make("module://isoftSystem600/com/isoft/System600/db/DD102269" + (flag ? "M" : "E") + ".csv");
                }
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/DD2S2269" + (flag ? "M" : "E") + ".csv");
            case 2270:
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/HP102270" + (flag ? "M" : "E") + ".csv");
            case 2271:
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/HP102271" + (flag ? "M" : "E") + ".csv");
            case 2272:
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/HP102272" + (flag ? "M" : "E") + ".csv");
            case 2273:
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/HM102273" + (flag ? "M" : "E") + ".csv");
            case 2274:
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/HM102274" + (flag ? "M" : "E") + ".csv");
            case 2276:
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/UH102276" + (flag ? "M" : "E") + ".csv");
            case 2277:
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/UH102277" + (flag ? "M" : "E") + ".csv");
            case 2278:
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/UH102278" + (flag ? "M" : "E") + ".csv");
            case 2279:
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/UH102279" + (flag ? "M" : "E") + ".csv");
            case 2281:
            case 2299:
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/UE112281" + (flag ? "M" : "E") + ".csv");
            case 2284:
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/UE112284" + (flag ? "M" : "E") + ".csv");
            case 2300:
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/HC112300" + (flag ? "M" : "E") + ".csv");
            case 2301:
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/HV102301" + (flag ? "M" : "E") + ".csv");
            case 2302:
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/HS102302" + (flag ? "M" : "E") + ".csv");
            case 2303:
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/HS102303" + (flag ? "M" : "E") + ".csv");
            case 2304:
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/DV202304" + (flag ? "M" : "E") + ".csv");
            case 2305:
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/DV202305" + (flag ? "M" : "E") + ".csv");
            case 2334:
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/UB102334" + (flag ? "M" : "E") + ".csv");
            case 2341:
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/UM102341" + (flag ? "M" : "E") + ".csv");
            case 2342:
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/UM102342" + (flag ? "M" : "E") + ".csv");
            case 2384:
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/UM102384" + (flag ? "M" : "E") + ".csv");
            case 2523:
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/VY112523" + (flag ? "M" : "E") + ".csv");
            case 2393:
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/UA122393E.csv");
            case 2404:
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/FF102404" + (flag ? "M" : "E") + ".csv");
            case 2408:
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/LA102408" + (flag ? "M" : "E") + ".csv");
            case 2409:
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/FI102409" + (flag ? "M" : "E") + ".csv");
            case 2410:
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/LD102410" + (flag ? "M" : "E") + ".csv");
            case 2411:
                if (device.getRevision().equals("FJ10")) {
                    return BOrd.make("module://isoftSystem600/com/isoft/System600/db/FJ102411" + (flag ? "M" : "E") + ".csv");
                }
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/FJ112411" + (flag ? "M" : "E") + ".csv");
            case 2417:
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/CB102417" + (flag ? "M" : "E") + ".csv");
            case 2419:
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/FK102419" + (flag ? "M" : "E") + ".csv");
            case 2428:
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/FK102428" + (flag ? "M" : "E") + ".csv");
            case 2430:
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/CA102430" + (flag ? "M" : "E") + ".csv");
            case 2432:
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/CA102432" + (flag ? "M" : "E") + ".csv");
            case 2433:
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/CA102433" + (flag ? "M" : "E") + ".csv");
            case 2434:
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/FL102434" + (flag ? "M" : "E") + ".csv");
            case 2437:
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/EA102437" + (flag ? "M" : "E") + ".csv");
            case 2438:
                if (device.getRevision().equals("EB10")) {
                    return BOrd.make("module://isoftSystem600/com/isoft/System600/db/EB102438" + (flag ? "M" : "E") + ".csv");
                }
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/EB112438" + (flag ? "M" : "E") + ".csv");
            case 2439:
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/EC102439" + (flag ? "M" : "E") + ".csv");
            case 2449:
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/LG102449" + (flag ? "M" : "E") + ".csv");
            case 2451:
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/LH102451" + (flag ? "M" : "E") + ".csv");
            case 2452:
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/MV102452" + (flag ? "M" : "E") + ".csv");
            case 2474:
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/LH102474" + (flag ? "M" : "E") + ".csv");
            case 2475:
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/LG102475" + (flag ? "M" : "E") + ".csv");
            case 2477:
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/EA102477" + (flag ? "M" : "E") + ".csv");
            case 2478:
                if (device.getRevision().equals("EB10")) {
                    return BOrd.make("module://isoftSystem600/com/isoft/System600/db/EB102478" + (flag ? "M" : "E") + ".csv");
                }
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/EB112478" + (flag ? "M" : "E") + ".csv");
            case 2479:
                if (device.getRevision().equals("EC10")) {
                    return BOrd.make("module://isoftSystem600/com/isoft/System600/db/EC102479" + (flag ? "M" : "E") + ".csv");
                }
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/EC112479" + (flag ? "M" : "E") + ".csv");
            case 2482:
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/CB102482" + (flag ? "M" : "E") + ".csv");
            case 2483:
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/FK102483" + (flag ? "M" : "E") + ".csv");
            case 2485:
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/FL102485" + (flag ? "M" : "E") + ".csv");
            case 2600:
                if (device.getRevision().equals("LB10")) {
                    return BOrd.make("module://isoftSystem600/com/isoft/System600/db/LB102600" + (flag ? "M" : "E") + ".csv");
                }
                if (device.getRevision().equals("LC10")) {
                    return BOrd.make("module://isoftSystem600/com/isoft/System600/db/LC102600" + (flag ? "M" : "E") + ".csv");
                }
                if (device.getRevision().equals("LD10")) {
                    return BOrd.make("module://isoftSystem600/com/isoft/System600/db/LD102600" + (flag ? "M" : "E") + ".csv");
                }
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/LX102600" + (flag ? "M" : "E") + ".csv");
            case 2601:
                if (device.getRevision().equals("LC10")) {
                    return BOrd.make("module://isoftSystem600/com/isoft/System600/db/LC102601" + (flag ? "M" : "E") + ".csv");
                }
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/LX102601" + (flag ? "M" : "E") + ".csv");
            case 2602:
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/LC102602" + (flag ? "M" : "E") + ".csv");
            case 2603:
                if (device.getRevision().equals("LC10")) {
                    return BOrd.make("module://isoftSystem600/com/isoft/System600/db/LC102603" + (flag ? "M" : "E") + ".csv");
                }
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/LX102603" + (flag ? "M" : "E") + ".csv");
            case 2611:
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/LX102611" + (flag ? "M" : "E") + ".csv");
            case 2612:
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/LX102612" + (flag ? "M" : "E") + ".csv");
            case 2613:
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/LX102613" + (flag ? "M" : "E") + ".csv");
            case 2700:
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/C13H2700" + (flag ? "M" : "E") + ".csv");
            case 2701:
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/MG102701" + (flag ? "M" : "E") + ".csv");
            case 2703:
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/GD102703" + (flag ? "M" : "E") + ".csv");
            case 2707:
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/AS102707" + (flag ? "M" : "E") + ".csv");
            case 2708:
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/AH102708" + (flag ? "M" : "E") + ".csv");
            case 2709:
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/GD2F2709" + (flag ? "M" : "E") + ".csv");
            case 2711:
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/GF1F2711" + (flag ? "M" : "E") + ".csv");
            case 2712:
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/MG302712" + (flag ? "M" : "E") + ".csv");
            case 2713:
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/CH102713" + (flag ? "M" : "E") + ".csv");
            case 2719:
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/AA102719" + (flag ? "M" : "E") + ".csv");
            case 2720:
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/GF112720" + (flag ? "M" : "E") + ".csv");
            case 2723:
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/AB102723" + (flag ? "M" : "E") + ".csv");
            case 2724:
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/AC102724" + (flag ? "M" : "E") + ".csv");
            case 2726:
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/AD102726" + (flag ? "M" : "E") + ".csv");
            case 2728:
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/HI102728" + (flag ? "M" : "E") + ".csv");
            case 2729:
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/HI202729" + (flag ? "M" : "E") + ".csv");
            case 2730:
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/HI302730" + (flag ? "M" : "E") + ".csv");
            case 2731:
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/ED102731" + (flag ? "M" : "E") + ".csv");
            case 2733:
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/GG112733" + (flag ? "M" : "E") + ".csv");
            case 2734:
            case 2737:
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/AB102734" + (flag ? "M" : "E") + ".csv");
            case 2780:
            case 2781:
            case 2782:
            case 2783:
            case 2784:
            case 2785:
            case 2786:
            case 2787:
            case 2788:
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/ML30" + deviceCode + ".csv");
            case 2817:
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/LPS22817" + (flag ? "M" : "E") + ".csv");
            case 2822:
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/BC102822" + (flag ? "M" : "E") + ".csv");
            case 2830:
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/BC102830" + (flag ? "M" : "E") + ".csv");
            case 2833:
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/BC102833" + (flag ? "M" : "E") + ".csv");
            case 2855:
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/FCS12855" + (flag ? "M" : "E") + ".csv");
            case 2891:
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/BC102891" + (flag ? "M" : "E") + ".csv");
            case 2917:
                return BOrd.make("module://isoftSystem600/com/isoft/System600/db/LPS22917" + (flag ? "M" : "E") + ".csv");
        }
        return null;
    }

    public static String[] getTablePoint(int pAddr, boolean unit)
    {
        return null;
    }
}
