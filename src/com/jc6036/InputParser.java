package com.jc6036;

import com.jc6036.PortScanner;
import com.jc6036.ScanType.*;

import static com.jc6036.ScanType.*;

public class InputParser
{
    /**
     * This class is purely used to parse user input from the commandline.
     * It will take a series of parameters as one string, and configure a set of variables
     * to be used with the PortScanner class. It will output a configured PortScanner object.
     */

    ////////////////////
    // Primary Method //
    ////////////////////
    public static PortScanner ParseInput (String [] sParams)
    {
        /*
        *   This method takes the parameters String array and parses each parameter,
        *   configuring a PortScanner object in the process. If there are any problems
        *   with the parameters, this will shoot out an error message and terminate
        *   the program.
        */

        PortScanner Scanner = new PortScanner(555);

        // For each param string given
        for(int i = 0; i < sParams.length; i++)
        {
            switch(sParams[i])
            {
                /* TODO: Will need to add input parsing for multiple ports eventually */
                // Mode
                case "-m":
                    Scanner.SetScanType(GetScanModeFromString(sParams[i + 1]));
                    i++;
                    break;
                case "-tp":
                    Scanner.SetTargetPort(GetPortFromString(sParams[i + 1]));
                    i++;
                    break;
                case "-ta":
                    Scanner.SetTargetAddress(sParams[i + 1]);
                    i++;
                    break;
                case "-rs":
                    Scanner.SetStartPort(GetPortFromString(sParams[i + 1]));
                    i++;
                    break;
                case "-rn":
                    Scanner.SetEndPort(GetPortFromString(sParams[i + 1]));
                    i++;
                    break;
                default:
                    System.out.println("Command Not Recognized: " + sParams[i]);
                    System.exit(0);
            }
        }

        return Scanner;
    }


    ////////////////////////
    // Supporting Methods //
    ////////////////////////
    private static ScanType GetScanModeFromString(String sParam)
    {
        ScanType Mode = null;
        sParam.toLowerCase();

        switch (sParam)
        {
            case "single":
                Mode = SINGLE_SCAN;
            case "range":
                Mode = RANGE_SCAN;
            case "all":
                Mode = ALL_SCAN;
            case "multi":
                Mode =  MULTI_SCAN;
            default:
                System.out.println("Mode Parameter Not Recognized: " + sParam);
                System.exit(0);
        }

        return Mode;
    }

    private static int GetPortFromString(String sParam)
    {
        int nRet = 0;

        try
        {
            nRet = Integer.parseInt(sParam);
        }
        catch (NumberFormatException e)
        {
            System.out.println("Target Port Parameter is Not a Number: " + sParam);
            System.exit(0);
        }

        return nRet;
    }
}