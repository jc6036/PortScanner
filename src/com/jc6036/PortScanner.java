package com.jc6036;

import static com.jc6036.ScanType.*;
import com.jc6036.ScanResult;

public class PortScanner {
    /* Class Vars */
    private int nRangeStart = 0;
    private int nRangeEnd = 0;
    private int nTargetPort = 0;
    private String sTargetAddress = "127.0.0.1";

    // Primary scan type to run with
    private ScanType ScanMode = RANGE_SCAN;

    // Literal min and max ports
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

    // No default constructor for ALL_SCAN option. User must run that explicitly.

    /*
    * Getters and Setters ahead. Beware traveler
    * */
    public ScanType GetScanType()
    {
        return this.ScanMode;
    }
    public void SetScanType(ScanType ScanMode)
    {
        this.ScanMode = ScanMode;
    }

    public int GetTargetPort()
    {
        return this.nTargetPort;
    }
    public void SetTargetPort(int nTargetPort)
    {
        if(nTargetPort <= MAXIMUM_PORT && nTargetPort >= MINIMUM_PORT)
        {
            this.nTargetPort = nTargetPort;
        }
        else
        {
            System.out.println("Error: SetTargetPort called with a port number too high or too low.");
        }
    }

    public int GetStartPort()
    {
        return this.nRangeStart;
    }
    public void SetStartPort(int nStart)
    {
        if(nStart <= MAXIMUM_PORT && nStart >= MINIMUM_PORT)
        {
            this.nRangeStart = nStart;
        }
        else
        {
            System.out.println("Error: SetStartPort called with a port number too high or too low.");
        }
    }

    public int GetEndPort()
    {
        return this.nRangeEnd;
    }
    public void SetEndPort(int nEnd)
    {
        if(nEnd <= MAXIMUM_PORT && nEnd >= MINIMUM_PORT)
        {
            this.nRangeEnd = nEnd;
        }
        else
        {
            System.out.println("Error: SetEndPort called with a port number too high or too low.");
        }
    }

    public String GetTargetAddress()
    {
        return this.sTargetAddress;
    }
    public void SetTargetAddress(String sAddress)
    {
        // We don't error check this here. When we try to turn this string into an IAddress, we will see if it fails.
        this.sTargetAddress = sAddress;
    }


    /*
    *   Scanning Operations Below.
    * */

    public ScanResult Scan()
    {
        /*
        *   The primary scan method. Takes the configured class state and scans ports. This class returns the
        *   result as a ScanResult class. I've chosen to return another class as I believe that it can hold
        *   a more informative set of data than if I were to return an array or simple data type. Using
        *   ScanResult will allow me to expand the dataset I can return in the future, even if ScanResult
        *   starts out with only one or two types in it.
        *
        *   Takes: Nothing
        *
        *   Returns: a configured ScanResult object
        *
        *   State Used:
        *   ScanMode
        *   nTargetPort
        *   sTargetAddress
        *   nRangeStart
        *   nRangeEnd
        * */


        ScanResult Result = new ScanResult();
        return Result;
    }

    public boolean ScanSinglePort()
    {
        /*
        *   Prototype/Proof of Concept Method
        *   This method will be removed once I get the base scanner logic working.
        *
        *   This method scans a singular port.
        *
        *   Returns false if the port is closed, and return true if the port is open
        *
        *   Uses sTargetAddress, nTargetPort, and ScanMode to determine the port details.
        * */



        return false;
    }
}