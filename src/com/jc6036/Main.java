package com.jc6036;

import com.jc6036.PortScanner;
import com.jc6036.ScanResult;
import com.jc6036.PortScannerTests;
import com.jc6036.InputParser;
import java.util.Map;

public class Main {

    public static void main(String[] args)
    {
        /*
        *   Entry point for code. Parses command line arguments and configures a port scanner object.
        *
        *   The port scanner object will do the scanning of ports and output results.
        * */
        PortScanner Scanner = InputParser.ParseInput(args);

        Scanner.Scan();
    }

    private static void RunTests()
    {
        /* Configure Test Parameters Here */
        /* You will need to manually confirm these results for the test to work right. */
        int nSingleTarget = 6942;
        Boolean bSingleResult = true;


        PortScannerTests tests = new PortScannerTests();

        if(!tests.BaseConstructorTest())
        {
            // Method prints its own message so we should be fine just exiting here
            System.exit(0);
        }

        if(!tests.GettersAndSettersTest())
        {
            System.exit(0);
        }

        if(!tests.SingleLocalPortScanTest(nSingleTarget, bSingleResult))
        {
            System.exit(0);
        }

        // BLASTOFF
        System.out.println("All tests ran correctly!");
    }
}
