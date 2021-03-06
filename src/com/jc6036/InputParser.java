package com.jc6036;

import java.util.ArrayList;
import java.util.List;

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

        PortScanner Scanner = new PortScanner(0);

        // For each param string given
        for(int i = 0; i < sParams.length; i++)
        {
            switch(sParams[i])
            {
                // Mode
                case "-m":
                    Scanner.SetScanType(GetScanModeFromString(sParams[i + 1]));
                    i++;
                    break;
                case "-tp":
                    Scanner.SetTargetPort(GetPortFromString(sParams[i + 1]));
                    i++;
                    break;
                case "-tps":
                    Scanner.SetPortList(GetPortListFromString(sParams[i+1]));
                    i++;
                    break;
                case "-ta":
                    Scanner.SetTargetAddress(sParams[i + 1]);
                    i++;
                    break;
                case "-r":
                    Scanner.SetStartPort(GetPortFromString(sParams[i + 1]));
                    Scanner.SetEndPort(GetPortFromString(sParams[i + 2]));
                    i += 2;
                    break;
                case "?":
                case "-?":
                case "-help":
                case "help":
                    System.out.println("Parameters");
                    System.out.println("Mode: -m [single, range, multi, all]");
                    System.out.println("Target Port: -tp [port number]");
                    System.out.println("Target Address: -ta [address]");
                    System.out.println("Port Range: -r [port number, port number]");
                    System.out.println("Port List: -tps \"[port number, number, number, number]\"");
                    System.out.println("Example, Scan Range of Ports at Target Address");
                    System.out.println("java PortScanner -m range -ta 192.0.1.17 -r 4000 5000");
                    System.out.println("java PortScanner -m multi -ta 192.0.1.1 -tps \"500 234 1222 4353\"");
                    System.out.println("-----------------------------------------------------------------");
                    System.out.println("Single mode scans a single port. Range scans a range of ports, from start to end.");
                    System.out.println("Multi mode scans a given list of ports. All scans ALL PORTS. USE WITH CARE.");
                    System.exit(0);
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
                break;
            case "range":
                Mode = RANGE_SCAN;
                break;
            case "all":
                Mode = ALL_SCAN;
                break;
            case "multi":
                Mode =  MULTI_SCAN;
                break;
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

    private static List<Integer> GetPortListFromString(String sParams)
    {
        List<Integer> nRet = new ArrayList<Integer>();

        sParams.replace("\"", "");
        String sSplitParams[] = sParams.split(" "); // REGEX: Matches of 1 or more digits.

        for(int i = 0; i < sSplitParams.length; i++)
        {
            try
            {
                sSplitParams[i].trim();
                nRet.add(Integer.parseInt(sSplitParams[i]));
            }
            catch (NumberFormatException e)
            {
                System.out.println("Port in Port List Parameter is Not a Number: " + sSplitParams[i]);
                System.exit(0);
            }
        }

        return nRet;
    }
}
