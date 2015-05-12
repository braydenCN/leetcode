package com.brayden;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Longest Substring with At Most Two Distinct Characters

Given a string S, find the length of the longest substring T that contains at
 most two distinct characters.
For example,
Given S = ¡°eceba¡±,
T is ¡°ece¡± which its length is 3.
 */
public class LongestSubstringWithAtMostTwoDistinctCharacters {
    public int lengthOfLongestSubstringTwoDistinct(String s){
        if(s == null)
            return 0;
        
        int len = s.length();
        if(len < 2)
            return len;
        
        int maxLen = 0;
        int curLen = 2;
        int char1 = s.charAt(0), char2 = s.charAt(1);
        int lastChar1Ind = 0, lastChar2Ind = 1;
        for(int i = 2; i < len; i++){
            char curChar = s.charAt(i);
            if(curChar == char1){
                curLen++;
                char1 = char2;
                lastChar1Ind = lastChar2Ind;
                char2 = curChar;
                lastChar2Ind = i;
            }else if(curChar == char2){
                curLen++;
                lastChar2Ind++;
            }else{
                if(curLen > maxLen)
                    maxLen = curLen;
                curLen = i - lastChar1Ind;
                
                char1 = char2;
                lastChar1Ind = lastChar2Ind;
                char2 = curChar;
                lastChar2Ind = i;
            }
        }
        
        if(curLen > maxLen)
            maxLen = curLen;
        return maxLen;
    }
    
    @Test
    public void test(){
        assertEquals(3, lengthOfLongestSubstringTwoDistinct("eceab"));
    }
    
    @Test
    public void test1(){
        assertEquals(5, lengthOfLongestSubstringTwoDistinct("abbcaabbac"));
    }
    
    @Test
    public void test2(){
        assertEquals(3, lengthOfLongestSubstringTwoDistinct("abii"));
    }
    
    @Test
    public void test3(){
        assertEquals(1, lengthOfLongestSubstringTwoDistinct("a"));
    }
    
    @Test
    public void test4(){
        assertEquals(2, lengthOfLongestSubstringTwoDistinct("ab"));
    }
    
    /**
     * from http://www.danielbit.com/blog/puzzle/leetcode/leetcode-longest-substring-with-at-most-two-distinct-characters
     */
    public int lengthOfLongestSubstringTwoDistinct1(String s) {
        /**
         * so i is the begin index of current sliding window; 
         * j is the lastChar2Ind;
         * 
         */
        int i = 0, j = -1, maxLen = 0;
        for (int k = 1; k < s.length(); k++) {
            if (s.charAt(k) == s.charAt(k - 1)) continue;
            if (j >= 0 && s.charAt(j) != s.charAt(k)) {
                maxLen = Math.max(k - i, maxLen);
                i = j + 1; 
            }
            j = k - 1;  
        }
        return Math.max(s.length() - i, maxLen);
    }
}
