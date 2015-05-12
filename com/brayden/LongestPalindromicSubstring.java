package com.brayden;

import static org.junit.Assert.*;

import org.junit.Test;

public class LongestPalindromicSubstring {
    public String longestPalindrome(String s) {
        if(s == null || s.length() <= 1)
            return s;
        
        StringBuffer sb = new StringBuffer();
        for(char c: s.toCharArray())
            sb.append('~').append(c);
        sb.append('~');
        s = sb.toString();
        
        int len = s.length();
        String reversedS = new StringBuffer(s).reverse().toString();
        String longestPalindrome = s.substring(0, 1);
        for(int i = 0; i < s.length(); i++){
            String tmp = 
                    findLongestCommonPrefixAt(s, i, reversedS, len - i - 1);
            if(tmp.length() > longestPalindrome.length())
                longestPalindrome = tmp;
        }
        
        sb = new StringBuffer();
        for(int i = 1; i < longestPalindrome.length(); i += 2)
            sb.append(longestPalindrome.charAt(i));
        return sb.toString();
    }

    private String findLongestCommonPrefixAt(String s, int i, String reversedS,
            int j) {
        int k;
        for(k = 1; k < s.length() - i && k < reversedS.length() - j; k++)
            if(s.charAt(i + k) != reversedS.charAt(j + k))
                break;
        
        return k == 1 ? "" : s.substring(i - k + 1, i + k);
    }
    
    @Test
    public void test(){
        assertEquals("aba", longestPalindrome("cabadefg"));
    }
    
    @Test
    public void test1(){
        /**
Submission Result: Wrong Answer
Input:      "bb"
Output:     "b"
Expected:   "bb"
         */
        assertEquals("bb", longestPalindrome("bb"));
        assertEquals("bb", longestPalindrome("cbbg"));
        assertEquals("abba", longestPalindrome("cabbad"));
        assertEquals("bb", longestPalindrome("cbbg"));
    }
}
