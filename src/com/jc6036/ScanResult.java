package com.jc6036;

import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;

public class ScanResult
{
    /*
    *   This class contains the dataset to be returned by PortScanner, as well as helper functions for the data.
    *
    *   Data is intended to be retrieved and set through the getter and setter methods.
    * */

    private Map<Integer, Boolean> mPorts; // This will be the primary data returned
    private String sTargetAddress; // Nice to include in the set

    public ScanResult()
    {
        mPorts = new HashMap<Integer, Boolean>();
        sTargetAddress = "";
    }


    /* Getters and Setters Below */
    public void SetPorts(Map<Integer, Boolean> mPorts)
    {
        this.mPorts = mPorts;
    }
    public Map<Integer, Boolean> GetPorts()
    {
        return mPorts;
    }

    public void SetAddress(String sAddress)
    {
        this.sTargetAddress = sAddress;
    }
    public String GetAddress()
    {
        return sTargetAddress;
    }


    /* Helpers */

    public void AddResult(Integer nPort, Boolean bOpen)
    {
        mPorts.put(nPort, bOpen);
    }
    public void ClearResults()
    {
        mPorts.clear();
    }


    /* Output Methods Print Contents to Console */
    public void ToConsole()
    {
        System.out.println("Scan Results");

        System.out.println("Target Address: " + sTargetAddress);

        Iterator it = mPorts.entrySet().iterator();

        while(it.hasNext())
        {
            Map.Entry<Integer, Boolean> entry = (Map.Entry<Integer, Boolean>)it.next();

            String status;
            if(entry.getValue())
            {
                status = "OPEN";
            }
            else
            {
                status = "CLOSED";
            }

            System.out.println("Port: " + Integer.toString(entry.getKey()) + " | " + status);
        }
    }
}
