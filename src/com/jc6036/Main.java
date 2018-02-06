package com.jc6036;

import com.jc6036.PortScanner;
import com.jc6036.ScanResult;
import com.jc6036.PortScannerTests;
import java.util.Map;

public class Main {

    public static void main(String[] args)
    {
        /*
        *   Entry point for code. Parses command line arguments and configures a port scanner object.
        *
        *   The port scanner object will do the scanning of ports and output results.
        * */

        PortScanner Scanner = new PortScanner(6942);
        ScanResult result = Scanner.Scan();

        System.out.println(result.GetAddress());

        Map<Integer, Boolean> results = result.GetPorts();

        String sResult = results.toString();

        System.out.println(sResult);
    }
}
