package com.brayden;

import static org.junit.Assert.*;

import org.junit.Test;

public class SingleNumberII {
    
    /** 
     * from Leetcode discussion
     * 00 -> 01 -> 10 -> 00
     * ones for the first bit;(from right to left)
     * twos for the second bit
     * this is an FSM
     */
    public int singleNumber(int[] A) {
        int ones = 0, twos = 0;
        for(int i = 0; i < A.length; i++){
            ones = (ones ^ A[i]) & ~twos;
            twos = (twos ^ A[i]) & ~ones;
        }
        return ones;
    }
    
    public int singleNumber1(int[] A) {
        int ones = 0, twos = 0;
        int mask, firstBit, secondBit;
        for(int i = 0; i < A.length; i++)
            for(int j = 0; j < 32; j++){
                mask = 1 << j;
                if((A[i] & mask) == 0)
                    continue;
                firstBit = ones & mask;
                secondBit = twos & mask;
                if(firstBit == 0 && secondBit == 0) // 00 -> 01
                    ones |= mask;
                else if(firstBit != 0 && secondBit == 0){ // 01 - > 10
                    ones &= ~mask;
                    twos |= mask;
                }else if(firstBit == 0 && secondBit != 0) // 10 -> 00
                    twos &= ~mask;
            }
        return ones;
    }
    
    @Test
    public void test(){
        assertEquals(1, singleNumber1(new int[]{1}));
    }
    
    @Test
    public void test2(){
        assertEquals(2, singleNumber1(new int[]{1, 1, 1, 2}));
    }
    
    @Test
    public void test4(){
        assertEquals(2147483647, singleNumber1(new int[]{-2147483648, -2147483648, -2147483648, 2147483647}));
    }
    
    @Test
    public void test1(){
/**
Submission Result: Wrong Answer
Input:  [-2,-2,1,1,-3,1,-3,-3,-4,-2]
Output:     -1
Expected:   -4
**/
        assertEquals(-4, singleNumber1(new int[]{-2,-2,1,1,-3,1,-3,-3,-4,-2}));
    }
    
    @Test
    public void test3(){
        /**
Input:  [43,16,45,89,45,-2147483648,45,2147483646,-2147483647,-2147483648,43,2147483647,-2147483646,-2147483648,89,-2147483646,89,-2147483646,-2147483647,2147483646,-2147483647,16,16,2147483646,43]
Output:     -1
Expected:   2147483647
         */
        assertEquals(2147483647, singleNumber1(new int[]{43,16,45,89,45,-2147483648,45,2147483646,-2147483647,-2147483648,43,2147483647,-2147483646,-2147483648,89,-2147483646,89,-2147483646,-2147483647,2147483646,-2147483647,16,16,2147483646,43}));
    }
}
