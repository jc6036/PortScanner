package com.jc6036;

import com.jc6036.PortScanner;
import com.jc6036.ScanResult;

import static com.jc6036.ScanType.*;

import java.util.Map;

public class PortScannerTests {
    /*
    *   Calls to the methods in this class may be made to test the various functions of PortScanner.
    *
    *   Anything publicly accessible in PortScanner should be covered by a test here.
    * */

    /* Constructor Tests */

    public Boolean BaseConstructorTest()
    {
        /*
        * Tests the base PortScanner constructor, and attempts to make sure passed variables were set properly.
        * Relies on class getters to test.
        * */

        PortScanner scanner = new PortScanner(RANGE_SCAN, 8080, 100, 300, "127.0.0.1");

        if(scanner.GetScanType() != RANGE_SCAN)
        {
            System.out.println("Test Failed on Scan Type Variable.");
            return false;
        }

        if(scanner.GetTargetPort() != 8080)
        {
            System.out.println("Test Failed on Target Port Variable");
            return false;
        }

        if(scanner.GetStartPort() != 100)
        {
            System.out.println("Test Failed on Range Start Variable");
            return false;
        }

        if(scanner.GetEndPort() != 300)
        {
            System.out.println("Test Failed on Range End Variable");
            return false;
        }

        if(scanner.GetTargetAddress() != "127.0.0.1")
        {
            System.out.println("Test Failed on Target Address Variable");
            return false;
        }

        return true;
    }

    public Boolean GettersAndSettersTest()
    {
        /*
        * Tests the getters and setters in PortScanner.
        * */

        PortScanner scanner = new PortScanner(8080);

        scanner.SetScanType(SINGLE_SCAN);
        if(scanner.GetScanType() != SINGLE_SCAN)
        {
            System.out.println("Failure in scan type getter/setter");
            return false;
        }

        scanner.SetTargetPort(1000);
        if(scanner.GetTargetPort() != 1000)
        {
            System.out.println("Failure in target port getter/setter");
            return false;
        }

        scanner.SetStartPort(500);
        if(scanner.GetStartPort() != 500)
        {
            System.out.println("Failure in start port getter/setter");
            return false;
        }

        scanner.SetEndPort(3000);
        if(scanner.GetEndPort() != 3000)
        {
            System.out.println("Failure in end port getter/setter");
            return false;
        }

        scanner.SetTargetAddress("127.0.0.1");
        if(scanner.GetTargetAddress() != "127.0.0.1")
        {
            System.out.println("Failure in target address getter/setter");
            return false;
        }

        return true;
    }

    /* Scan Testing
    *
    * Note that in order to run these tests, one must confirm beforehand that a port is open or closed,
    * and then input the desired port to test on in the method parameters.
    * */

    public Boolean SingleLocalPortScanTest(int nTarget, Boolean bResult)
    {
        PortScanner scanner = new PortScanner(nTarget);

        /* We assume right here that the constructor has fired properly and set parameters correctly. */

        ScanResult result = scanner.Scan();

        Map<Integer, Boolean> ports = result.GetPorts(); // We are assuming that GetPorts() is working

        if(!ports.containsKey(nTarget)) // Make sure there's actually data
        {
            System.out.println("ScanResult failure. Map does not contain data for target port " + nTarget + ".");
            return false;
        }

        if(ports.get(nTarget) != bResult) // And then confirm the result
        {
            System.out.println("Scan Result failure. Port was not in its expected state as passed by method parameter.");
            return false;
        }

        return true;
    }
}
