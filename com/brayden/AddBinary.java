package com.brayden;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * https://oj.leetcode.com/problems/add-binary/
 * <p>
 * Given two binary strings, return their sum (also a binary string). 
 * 
 * @author pengczha
 *
 */
public class AddBinary {
	
    public String addBinary(String a, String b) {
        if((a == null || a.equals("")) && 
        		(b == null || b.equals("")))
        	return "";
        if(a == null || a.equals(""))
        	return b;
        if(b == null || b.equals(""))
        	return a;
        
        int resCharArrlen  =  Math.max(a.length(), b.length()) + 1;
        char[] resCharArr  =  new char[resCharArrlen];        
        int aInd           =  a.length() - 1;
        int bInd           =  b.length() - 1;
        int resInd         =  resCharArrlen - 1;
        boolean haveCarry  =  false;
        boolean aIs1, bIs1;
        
        while(aInd >= 0 && bInd >= 0){
        	aIs1 = (a.charAt(aInd--) == '1');
        	bIs1 = (b.charAt(bInd--) == '1');

        	/** compute the current digit **/
        	if(threeOfThreeAreTrue(aIs1, bIs1, haveCarry) ||
        			oneOfThreeIsTrue(aIs1, bIs1, haveCarry))
        		resCharArr[resInd--] = '1';
        	else
        		resCharArr[resInd--] = '0';
        	
            /** compute the carry **/
        	if(threeOfThreeAreTrue(aIs1, bIs1, haveCarry) ||
        			twoOfThreeAreTrue(aIs1, bIs1, haveCarry))
        		haveCarry = true;
        	else 
        		haveCarry = false;
        }
        
        while(aInd >= 0){ // some of a not processed
        	aIs1 = (a.charAt(aInd--) == '1');
        	resCharArr[resInd--] = 
        			oneOfTwoIsTrue(aIs1, haveCarry) ? '1' : '0';
        	haveCarry = aIs1 && haveCarry;
        }
        
        while(bInd >= 0){// some of b not processed
        	bIs1 = (b.charAt(bInd--) == '1');
        	resCharArr[resInd--] = 
        			oneOfTwoIsTrue(bIs1, haveCarry) ? '1' : '0';
        	haveCarry = bIs1 && haveCarry;
        }
        
        /** get the final result **/
        if(!haveCarry)
            return new String(resCharArr, 1, resCharArrlen - 1);	
        resCharArr[resInd] = '1';
        return new String(resCharArr);
    }

    private boolean oneOfTwoIsTrue(boolean a, boolean b) {
		return !a && b || a && !b;
	}
    
	private boolean twoOfThreeAreTrue(boolean a, boolean b, boolean c) {
		return !a && b && c || a && !b && c || a && b && !c;
	}

	private boolean oneOfThreeIsTrue(boolean a, boolean b, boolean c) {
		return !a && !b && c || a && !b && !c || !a && b && !c;
	}

	private boolean threeOfThreeAreTrue(boolean a, boolean b, boolean c) {
		return a && b && c;
	}
    
	@Test
	public void test(){
		AddBinary adder = new AddBinary();
		assertEquals("100",      adder.addBinary("1", "11"));
		assertEquals("10",       adder.addBinary("10", ""));
		assertEquals("10",       adder.addBinary(null, "10"));
		assertEquals("100100",   adder.addBinary("10101", "1111"));
		assertEquals("10000000", adder.addBinary("1111111", "1"));
	}
	
	/**
	 * To improve, 1. using int as carry, aChar, bChar is better: xor for 
	 * carry; ++/2 for bit. 2. using 0 for the missing bits of the shorter 
	 * string.
	 */
}
