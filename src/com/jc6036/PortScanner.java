package com.jc6036;

import static com.jc6036.ScanType.*;

import java.io.IOException;
import java.net.*;

public class PortScanner
{
    /* Class Vars */
    private int nRangeStart = 0;
    private int nRangeEnd = 0;
    private int nTargetPort = 0;
    private String sTargetAddress = "127.0.0.1"; // Note that this can be set to a URL as well

    // Primary scan type to run with
    private ScanType ScanMode = RANGE_SCAN;

    // Literal min and max ports
    private static final int MINIMUM_PORT = 0;
    private static final int MAXIMUM_PORT = 65535;

    // <editor-fold desc="Constructors">
    // I think I should refactor this into a static factory method at some point if I have to add any more params
    public PortScanner(ScanType ScanMode, int nTargetPort, int nRangeStart, int nRangeEnd, String sTargetAddress)
    {
        /*
        * The Base Constructor.
        * */

        if(nTargetPort < MINIMUM_PORT || nTargetPort > MAXIMUM_PORT)
        {
            nTargetPort = MINIMUM_PORT;
            System.out.println("Target Port was out of bounds during construction. Must be higher than 0 and lower than 65535.");
            System.out.println("Port Given: " + nTargetPort);
            System.exit(0);
        }
        if(nRangeStart < MINIMUM_PORT || nRangeStart > MAXIMUM_PORT)
        {
            nRangeStart = MINIMUM_PORT;
            System.out.println("Port Range Start was out of bounds during construction. Must be higher than 0.");
            System.out.println("Start Port Given: " + nRangeStart);
            System.exit(0);
        }
        if(nRangeEnd < MINIMUM_PORT || nRangeEnd > MAXIMUM_PORT)
        {
            nRangeEnd = MINIMUM_PORT;
            System.out.println("Port Range End was out of bounds during construction. Must be lower than 65535.");
            System.out.println("End Port Given: " + nRangeEnd);
            System.exit(0);
        }

        // Invalid Settings
        if(ScanMode == RANGE_SCAN && nRangeStart > nRangeEnd)
        {
            nRangeStart = MINIMUM_PORT;
            nRangeEnd = MINIMUM_PORT;
            System.out.println("Port Choices for Range Scan invalid. Start must be higher than 0, end must be lower than 65536.");
            System.out.println("Start Port Given: " + nRangeStart);
            System.out.println("End Port Given: " + nRangeEnd);
            System.exit(0);
        }

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

    // Yes, there is no default constructor that does ALL_SCAN, that must be set explicitly.
    // </editor-fold>


    /*
    * Getters and Setters ahead. Beware traveler
    * */
    // <editor-fold desc="Getters And Setters">
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
            System.exit(0);
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
            System.exit(0);
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
            System.exit(0);
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
    //</editor-fold>


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
        *   ScanMode - Determines the vars I use and the logic I run
        *   nTargetPort - Used if ScanMode is SINGLE_SCAN, as target
        *   sTargetAddress - Used for every scan
        *   nRangeStart - Used for RANGE_SCAN
        *   nRangeEnd - Used for RANGE_SCAN
        * */

        ScanResult Result = new ScanResult();

        if(ScanMode == SINGLE_SCAN)
        {
            Boolean bResult = ScanSinglePort(sTargetAddress, nTargetPort);
            Result.AddResult(nTargetPort, bResult);
            Result.SetAddress(sTargetAddress);
        }
        else if(ScanMode == RANGE_SCAN)
        {
        }
        else if(ScanMode == MULTI_SCAN)
        {
        }
        else if(ScanMode == ALL_SCAN) // BEWARE THE FORBIDDEN FRUIT
        {
        }

        return Result;
    }


    private boolean ScanSinglePort(String sTargetAddress, int nTargetPort)
    {
        /*
        *   Prototype/Proof of Concept Method
        *   This method will be removed once I get the base scanner logic working.
        *
        *   This method scans a singular port.
        *
        *   Returns false if the port is closed, and return true if the port is open
        *
        *   Uses sTargetAddress and nTargetPort to determine the port details.
        * */

        ServerSocket socket = null; // Checking TCP
        DatagramSocket datagramSocket = null; // Checking UDP
        InetAddress Address = null; // Target Object

        try
        {
            Address = Inet4Address.getByName(sTargetAddress);

            socket = new ServerSocket(nTargetPort, 2, Address);
            socket.setReuseAddress(true);
            if(socket != null)
            {
                socket.close();
            }
            return true;
        }
        catch (IOException e)
        {
            //System.out.println("There was an error while establishing a TCP connection to a target port.");
            //System.out.println("Target: " + sTargetAddress + " Port: " + nTargetPort);
            //System.out.println("IOException Error Code: " + e.getMessage());
            //System.exit(0);

            // Hitting an IOException should be excusable. Terminating upon one of these kills the flow of the program.
            // Maybe it's a smell that part of the design relies on an skipping an exception in the case of a
            // connection failure?
            // Definitely a smell, but it's the way the Java connection utilities work. A refused connection
            // inidcating a closed port throws an exception, chalk it up to java weirdness.
        }

        try
        {
            datagramSocket = new DatagramSocket(nTargetPort, Address);
            datagramSocket.setReuseAddress(true);
            if(datagramSocket != null)
            {
                datagramSocket.close();
            }
            return true;
        }
        catch (IOException e)
        {
            //System.out.println("There was an error while establishing a UDP connection to a target port.");
            //System.out.println("Target: " + sTargetAddress + " Port: " + nTargetPort);
            //System.out.println("IOException Error Code: " + e.getMessage());
            //System.exit(0);

            // See note above in the TCP exception block.
        }

        return false;
    }
}