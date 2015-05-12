package com.brayden;

import static org.junit.Assert.*;

import org.junit.Test;

public class CompareVersionNumbers {
    public int compareVersion(String version1, String version2) {
        
        String[] ver1Arr = version1.split("\\.");
        String[] ver2Arr = version2.split("\\.");
        int ver1ArrLen   = ver1Arr.length;
        int ver2ArrLen   = ver2Arr.length;
        for(int i = 0; i < ver1ArrLen || i < ver2ArrLen; i++){
            if(i >= ver1ArrLen){
                if(Integer.valueOf(ver2Arr[i]) != 0)
                    return -1;
                continue;
            }
            if(i >= ver2ArrLen){
                if(Integer.valueOf(ver1Arr[i]) != 0)
                    return 1;
                continue;
            }
            int ver1Part = Integer.valueOf(ver1Arr[i]);
            int ver2Part = Integer.valueOf(ver2Arr[i]);
            if(ver1Part < ver2Part)
                return -1;
            if(ver1Part > ver2Part)
                return 1;
        }
        return 0; 
    }
    
    @Test
    public void test(){
        assertEquals(-1, compareVersion("0", "1"));
        assertEquals(0, compareVersion("01", "1"));
        assertEquals(0, compareVersion("1.0", "1"));
        assertEquals(1, compareVersion("1.1", "1"));
    }
}
