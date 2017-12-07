package com.jc6036;

import static com.jc6036.ScanType.*;

public class PortScanner {
    /* Class Vars */
    private int nRangeStart = 0;
    private int nRangeEnd = 0;
    private int nTargetPort = 0;
    private String sTargetAddress = "127.0.0.1";

    // Primary scan type to run with
    private ScanType ScanMode = RANGE_SCAN;

    private static final int MINIMUM_PORT = 0;
    private static final int MAXIMUM_PORT = 65535;

    // I think I should refactor this into a static factory method at some point if I have to add any more params
    public PortScanner(ScanType ScanMode, int nTargetPort, int nRangeStart, int nRangeEnd, String sTargetAddress)
    {
        /*
        * The Base Constructor.
        * */

        this.ScanMode = ScanMode;
        this.nTargetPort = nTargetPort;
        this.nRangeStart = nRangeStart;
        this.nRangeEnd = nRangeEnd;
        this.sTargetAddress = sTargetAddress;
    }

    public PortScanner(int nTargetPort)
    {
        /*
        * Default mode of operation. Single scan mode on local ports.
        * */
        this(SINGLE_SCAN, nTargetPort, 0, 0, "127.0.0.1");
    }

    public PortScanner(int nRangeStart, int nRangeEnd)
    {
        /*
        * Second default mode of operation: range scan mode on local ports.
        * */
        this(RANGE_SCAN, 0, nRangeStart, nRangeEnd, "127.0.0.1");
    }

    public PortScanner(int nRangeStart, int nRangeEnd, String sTargetAddress)
    {
        /*
        * Default range scan with a target address option.
        * */
        this(RANGE_SCAN, 0, nRangeStart, nRangeEnd, sTargetAddress);
    }

    public PortScanner(int nTargetPort, String sTargetAddress)
    {
        /*
        * Default single port scan with a target address option.
        * */
        this(SINGLE_SCAN, nTargetPort, 0, 0, sTargetAddress);
    }

    // No constructor for ALL_SCAN option. User must run that explicitly.
}
