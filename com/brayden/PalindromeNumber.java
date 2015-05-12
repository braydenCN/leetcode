package com.brayden;

import static org.junit.Assert.*;

import org.junit.Test;

public class PalindromeNumber extends LeetcodeCommon {
	
    public boolean isPalindrome(int x) {
        if(x < 0)
            return false;
        
        int y = x, z = 1, len = 0;
        while(y != 0){
            y /= 10;
            z *= 10;
            len++;
        }
        int c = 10;
        for(int i = 0; i < len / 2; i++){
            int a = x 
        }
            if(getDigitAtIndex(x, i) != getDigitAtIndex(x, len - i -1))
                return false;
        return true;
    }
    
	public boolean isPalindrome1(int x) {
        if(x < 0)
        	return false;
        
        int len = digitLength(x);
        for(int i = 0; i < len / 2; i++)
        	if(getDigitAtIndex(x, i) != getDigitAtIndex(x, len - i -1))
        		return false;
        return true;
    }
    
    @Test
    public void test(){
    	assertTrue(isPalindrome(12321));
    	assertTrue(isPalindrome(1221));
    	assertTrue(isPalindrome(1));
    	assertTrue(isPalindrome(0));
    	assertFalse(isPalindrome(-1));
    }
}
