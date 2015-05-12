package com.brayden;

import static org.junit.Assert.*;

import org.junit.Test;

public class FactorialTrailingZeroes {
    
    public int trailingZeroes(int n) {
        if(n <= 0)
            return 0;
        
        int result = 0;
        while((n /= 5) > 0)
            result += n;
            
        return result;
    }
    
    private final static int[] fivePowers= new int[]{5, 25, 125, 625, 3125,
        15625, 78125, 390625, 1953125, 9765625, 48828125, 244140625};
 
    public int trailingZeroes2(int n) {
        if(n <= 0)
            return 0;
        
        int result = 0;
        for(int i = 0; i < fivePowers.length && n >= fivePowers[i]; i++)
            result += n / fivePowers[i];
            
        return result;
    }
    
    public int trailingZeroes1(int n) {
        if(n <= 0)
            return 0;
        
        int last5Powser = 0;
        while(last5Powser < fivePowers.length && fivePowers[last5Powser] <= n)
            last5Powser++;
        
        int result = 0;
        for(int i = 1; i < last5Powser; i++)
            result += n / fivePowers[i];
            
        return result;
    }
    
    @Test
    public void test(){
        assertEquals(2, trailingZeroes(12));
        assertEquals(6, trailingZeroes(26));
    }
    
    @Test
    public void test1(){
        /**
Submission Result: Wrong Answer
Input:  1808548329
Output:     452137068
Expected:   452137076
         */
        assertEquals(452137076, trailingZeroes(1808548329));
    }
}
