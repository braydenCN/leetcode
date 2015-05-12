package com.brayden;

import static org.junit.Assert.*;

import org.junit.Test;

public class DecodeWays {
    
    public int numDecodings(String s) {
        if(s == null || s.isEmpty())
            return 0;
        
        int prePre = 1, cur = 0, len = s.length();
        int pre = valid(s.charAt(0)) ? 1 : 0;
        if(len == 1)
            return pre;
        for(int i = 1; i < s.length(); i++){
            cur = 0;
            if(valid(s.substring(i - 1, i + 1)))
                cur += prePre;
            if(valid(s.charAt(i)))
                cur += pre;
            prePre = pre;
            pre = cur;
        }
        return cur;
    }
    
    public int numDecodings1(String s) {
        if(s == null || s.isEmpty() || s.matches("^.*\\D.*&"))
            return 0;
        
        int len = s.length();
        int[] cache = new int[len + 1];
        cache[0] = 1;
        if(valid(s.charAt(0)))
            cache[1] = 1;
        for(int i = 2; i <= len; i++){
            if(valid(s.substring(i - 2, i)))
                cache[i] += cache[i - 2];
            if(valid(s.charAt(i - 1)))
                cache[i] += cache[i - 1];   
        }
        return cache[len];
    }

    private boolean valid(char c) {
        return c >= '1' && c <= '9';
    }

    private boolean valid(String str) {
        return str.charAt(0) != '0' && Integer.valueOf(str) <= 26;
    }
    
    @Test
    public void test(){
        assertEquals(2, numDecodings("12"));
        assertEquals(1, numDecodings("120"));
    }
    
    @Test
    public void test1(){
        /**
Submission Result: Wrong Answer
Input:  "0"
Output:     1
Expected:   0
         */
        assertEquals(0, numDecodings("0"));
    }
}
