package com.brayden;

/**
 * https://oj.leetcode.com/problems/plus-one/
 * <p>
 * Given a non-negative number represented as an array of digits, plus one to 
 * the number.
 * <p>
 * The digits are stored such that the most significant digit is at the head of
 * the list.
 * 
 * @author pengczha
 *
 */
public class PlusOne {
	
    public int[] plusOne(int[] digits) {
    	boolean overflowed = false;
    	for(int i = digits.length - 1; i >= 0; i--){
        	if(digits[i] >= 0 && digits[i] < 9){// digits 0-8
        		digits[i]++;
        		break;
        	}
        	digits[i] = 0; // digit 9; we will not consider for other values
        	if(i == 0)
        		overflowed = true;
        }
    	if(overflowed){
    		digits = new int[digits.length + 1];
    		digits[0] = 1; // other digits are defaultly inited to 0
    	}	 
    	return digits;
    }
    
    /** 
     * To improve, replace the break by return digits; overflowed flag/if(i==0)
     * is not really needed.
     */
    
    public int[] plusOne_updated(int[] digits) {
    	for(int i = digits.length - 1; i >= 0; i--){
        	if(digits[i] >= 0 && digits[i] < 9){// digits 0-8
        		digits[i]++;
        		return digits;
        	}
        	digits[i] = 0; // digit 9; we will not consider for other values
        }
    	digits = new int[digits.length + 1];
    	digits[0] = 1; // other digits are defaultly inited to 0 
    	return digits;
    }
}
