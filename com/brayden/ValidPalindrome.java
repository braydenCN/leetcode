package com.brayden;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * https://oj.leetcode.com/problems/valid-palindrome/
 * <p>
 *  Given a string, determine if it is a palindrome, considering only 
 *  alphanumeric characters and ignoring cases.<p>
 *  For example,
 *  <code>"A man, a plan, a canal: Panama"</code> is a palindrome.
 *  <code>"race a car"</code> is not a palindrome. 
 * @author brayden.zhang
 *
 */
public class ValidPalindrome extends LeetcodeCommon {
	
    public boolean isPalindrome(String s) {
        int low = -1, high = s.length();
        while(++low < --high){
            while(low < high && !isAlphaNum(s.charAt(low))) low++;
            while(low < high && !isAlphaNum(s.charAt(high))) high--;
            if(low < high && 
                    lowercase(s.charAt(low)) != lowercase(s.charAt(high)))
                return false;
        }
        return true;
    }
    
    private char lowercase(char c){
        return c >= 'A' && c <= 'Z' ? (char)(c - 'A' + 'a') : c;
    }
    
    private boolean isAlphaNum(char c){
        return c >= 'a' && c <= 'z' || 
               c >= 'A' && c <= 'Z' || 
               c >= '0' && c <= '9';
    }
    
	public boolean isPalindrome1(String s) {
        s = s.replaceAll("\\W", "").toLowerCase();
        int len = s.length();
        if(len == 0 || len == 1)
        	return true;
        /** 
         * if length is 2n, then index = n - 1;
         * if length is 2n + 1, then index = n - 1
         */
        return super.isPalindrome(s);
	}
	
	@Test
	public void test(){
		ValidPalindrome validator = new ValidPalindrome();
		assertTrue(validator.isPalindrome("a1bcdcb1a"));
		assertTrue(validator.isPalindrome("a1bccb1a"));
		assertTrue(validator.isPalindrome(" a#1@     \t\nb`cdc>\"b1a\t"));
		assertTrue(!validator.isPalindrome("a1b2cdcb1a"));
		assertTrue(!validator.isPalindrome(" a1b2#?.,cdc***b1a  "));
	}
}
