package com.brayden;

import static org.junit.Assert.*;

import org.junit.Test;

public class ReverseInteger extends LeetcodeCommon {
	
	public int reverse(int x) {
		return super.reverse(x);
	}
	
    @Test
    public void test(){
    	assertEquals(321,  reverse(123));
    	assertEquals(-321, reverse(-123));
    	assertEquals(1,    reverse(100));
    	assertEquals(0,    reverse(1000000003));
    	assertEquals(0,    reverse(-1000000003));
    }
    
    /**
     * To improve, no need to do the overflow check in the loop.
     */
}
