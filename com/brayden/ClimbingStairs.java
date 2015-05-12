package com.brayden;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * https://oj.leetcode.com/problems/climbing-stairs/
 * <p>
 * You are climbing a stair case. It takes n steps to reach to the top.
 * <p>
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can 
 * you climb to the top?
 *  
 * @author pengczha
 *
 */
public class ClimbingStairs {
    
    public int climbStairs(int n) {
        if(n < 2)
            return n;
        
        int n1 = 1, n2 = 1;
        for(int i = 2; i <= n; i++){
            n2 = n1 + n2;
            n1 = n2 - n1;
        }
            
        return n2;
    }
    
	/** just another Fibonacci. We just need to ensure the efficiency. **/
	private int[] csCounter;
	
	private int climbStairsInternal(int n){
		if(csCounter[n] != 0)
		    return csCounter[n];
		csCounter[n] = climbStairsInternal(n - 1) + climbStairsInternal(n - 2);
		return csCounter[n];
	}
	
    public int climbStairs1(int n) {
    	if(n >= 0 && n <= 2)
    		return n;
    	
    	csCounter = new int[n + 1];
        /**
         * note: we use csCount[n] to store result of climbStairs[n] 
         * note: csCounter array items will be defaultly init to 0 
         */
    	csCounter[1] = 1;
    	csCounter[2] = 2;
    	
        return climbStairsInternal(n);
    }
    
    @Test
    public void test(){
    	assertEquals(0,  climbStairs(0));
    	assertEquals(1,  climbStairs(1));
    	assertEquals(2,  climbStairs(2));
    	assertEquals(3,  climbStairs(3));
    	assertEquals(5,  climbStairs(4));
    	assertEquals(8,  climbStairs(5));
    	assertEquals(13, climbStairs(6));
    }
    
    /**
     * To improve: no need for extra memory. Do it with iteration. 
     */
}
