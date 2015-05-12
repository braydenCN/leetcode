package com.brayden;

import static org.junit.Assert.*;

import org.junit.Test;

public class SingleNumber {
    public int singleNumber(int[] A) {
    	int result = 0;
    	for(int a: A)
    		result ^= a;
    	return result;
    }
    
    @Test
    public void test(){
    	assertEquals(2, singleNumber(new int[]{1, 2, Integer.MAX_VALUE, 1, 
    			Integer.MAX_VALUE}));
    }
}
