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
