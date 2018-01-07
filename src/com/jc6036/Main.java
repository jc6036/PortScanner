package com.jc6036;

import com.jc6036.PortScanner;
import com.jc6036.PortScannerTests;

public class Main {

    public static void main(String[] args)
    {
        /*
        *   Entry point for code. Parses command line arguments and configures a port scanner object.
        *
        *   The port scanner object will do the scanning of ports and output results.
        * */

        PortScanner Scanner = new PortScanner(6942); // Placeholder
        boolean bPortOpen = Scanner.ScanSinglePort();
        if(bPortOpen)
        {
            System.out.println("Port is open!");
        }
        else
        {
            System.out.println("Port is closed!");
        }
    }
}
