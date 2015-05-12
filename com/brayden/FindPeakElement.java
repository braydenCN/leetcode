package com.brayden;

import static org.junit.Assert.*;

import org.junit.Test;

public class FindPeakElement {
    public int findPeakElement1(int[] num) {
        if(num == null || num.length == 0)
            return -1;
        
        int len = num.length;
        int low = 0, high = len - 1;
        while(low <= high){
            int mid = ((high - low) >> 1) + low;
            if((mid == 0 || num[mid - 1] < num[mid]) && 
                    (mid == len - 1 || num[mid] > num[mid + 1]))
                return mid;
            if((mid == 0 || num[mid - 1] < num[mid]) && 
                    (mid == len - 1 || num[mid] < num[mid + 1]))
                low = mid + 1;
            else 
                high = mid - 1;
        }
        return -1;
    }
    
    @Test
    public void test(){
/**
Submission Result: Runtime Error
Runtime Error Message:  Line 10: java.lang.ArrayIndexOutOfBoundsException: -1
Last executed input:    [1,2,1]
*/
        assertEquals(1, findPeakElement(new int[]{1, 2, 1}));
    }
    
    @Test
    public void test1(){
        /**
Submission Result: Wrong Answer
Input:  [1]
Output:     -1
Expected:   0
         */
        assertEquals(0, findPeakElement(new int[]{1}));
    }
    
    /** from leetcode discussion **/
    public int findPeakElement(int[] num) { 
        int low = 0; 
        int high = num.length - 1; 
        while(low < high){ 
            int mid = (low + high) / 2;
            if(num[mid] < num[mid + 1])
                low = mid + 1; 
            else
                high=mid;
        }
        return low; 
    }
}
