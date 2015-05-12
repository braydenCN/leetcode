package com.brayden;

import static org.junit.Assert.*;

import org.junit.Test;

public class Sqrt_x {
    public int sqrt(int x) {
        if(x < 0)
            return -1;
        
        long low = 0, high = x;
        while(low <= high){
            long mid = ((high - low) >> 1) + low;
            long square  = mid * mid;
            long square1 = (mid + 1) * (mid + 1);
            if(square == x || square < x && square1 > x)
                return (int)mid;
            if(square < x)
                low = mid + 1;
            if(square > x)
                high = mid - 1;
        }
        return (int)low;
    }
    
    @Test
    public void test(){
        assertEquals(25, sqrt(625));
        assertEquals(25, sqrt(626));
        assertEquals(25, sqrt(675));
        assertEquals(0,  sqrt(0));
        assertEquals(1,  sqrt(1));
    }
    
    @Test
    public void tes1(){
        /**
Submission Result: Time Limit Exceeded
Last executed input:    2147483647
         */
        assertEquals(46340, sqrt(2147483647));//yes, overflowed, :)
    }
}
