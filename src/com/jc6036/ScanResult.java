package com.jc6036;

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
}
