package com.brayden;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class InterleavingString {
  
    public boolean isInterleave(String s1, String s2, String s3) {
        if(s1 == null || s2 == null || s3 == null || 
                s3.length() != s1.length() + s2.length())
            return false;
        
        if(s1.isEmpty())
            return s2.equals(s3);
        if(s2.isEmpty())
            return s1.equals(s3);
        
        int len1 = s1.length(), len2 = s2.length();
        boolean[] dp = new boolean[len2 + 1];
        dp[0] = true;
        for(int i = 1; i <= len2; i++)
            if(dp[i - 1] && s2.charAt(i - 1) == s3.charAt(i - 1))
                dp[i] = true;

        for(int i1 = 1; i1 <= len1; i1++){
            dp[0] = dp[0] && s1.charAt(i1 - 1) == s3.charAt(i1 - 1);
            for(int i2 = 1; i2 <= len2; i2++)
                if(dp[i2] && s1.charAt(i1 - 1) == s3.charAt(i1 + i2 - 1) ||
                        dp[i2 - 1] && s2.charAt(i2 - 1) == s3.charAt(i1 + i2 - 1))
                    dp[i2] = true;
                else
                    dp[i2] = false;
        }
        return dp[len2];
    }
    
    public boolean isInterleave2(String s1, String s2, String s3) {
        if(s1 == null || s2 == null || s3 == null || 
                s3.length() != s1.length() + s2.length())
            return false;
        
        if(s1.isEmpty())
            return s2.equals(s3);
        if(s2.isEmpty())
            return s1.equals(s3);
        
        int len1 = s1.length(), len2 = s2.length();
        boolean[][] dp = new boolean[len1 + 1][len2 + 1];
        dp[0][0] = true;
        for(int i = 1; i <= len1; i++)
            if(dp[i - 1][0] && s1.charAt(i - 1) == s3.charAt(i - 1))
                dp[i][0] = true;
        for(int i = 1; i <= len2; i++)
            if(dp[0][i - 1] && s2.charAt(i - 1) == s3.charAt(i - 1))
                dp[0][i] = true;

        for(int i1 = 1; i1 <= len1; i1++)
            for(int i2 = 1; i2 <= len2; i2++)
                if(dp[i1 - 1][i2] && s1.charAt(i1 - 1) == s3.charAt(i1 + i2 - 1) ||
                        dp[i1][i2 - 1] && s2.charAt(i2 - 1) == s3.charAt(i1 + i2 - 1))
                    dp[i1][i2] = true;
        
        return dp[len1][len2];
    }
    
    
    private String s1;
    private String s2;
    private String s3;
    int len1;
    int len2;
    int len3;
    
    public boolean isInterleave1(String s1, String s2, String s3) {
        if(s1 == null || s2 == null || s3 == null || 
                s3.length() != s1.length() + s2.length())
            return false;
        
        if(s1.isEmpty())
            return s2.equals(s3);
        if(s2.isEmpty())
            return s1.equals(s3);
        
         this.s1 = s1;
         this.s2 = s2;
         this.s3 = s3;
         len1 = s1.length();
         len2 = s2.length();
         len3 = s3.length();
         cache.clear();
         
         return isInterleaveInternal(0, 0, 0);
    }
    
    private long key(int i1, int i2){
        return (long)i2 * len2 + i1;
    }
    
    private Map<Long, Boolean> cache = new HashMap<>();
    
    private boolean isInterleaveInternal(int ind1, int ind2, int ind3) {
        if(ind3 >= len3)
            return true;
        
        long key = key(ind1, ind2);
        if(cache.containsKey(key))
            return cache.get(key);
        
        char curChar = s3.charAt(ind3);
        boolean result = 
               ind1 < len1 && curChar == s1.charAt(ind1) && 
                 isInterleaveInternal(ind1 + 1, ind2, ind3 + 1) 
            || ind2 < len2 && curChar == s2.charAt(ind2) && 
                 isInterleaveInternal(ind1, ind2 + 1, ind3 + 1);
        cache.put(key, result);
        return result;
    }
    
    @Test
    public void test(){
        assertTrue(isInterleave("aabcc", "dbbca", "aadbbcbcac"));
        assertFalse(isInterleave("aabcc", "dbbca", "aadbbbaccc"));
    }
    
    @Test
    public void test2(){
        /**
Submission Result: Wrong Answer
Input:  "aa", "ab", "aaab"
Output:     false
Expected:   true
         */
        assertTrue(isInterleave("aa", "ab", "aaab"));
    }
    
    @Test
    public void test3(){
        /**
Submission Result: Wrong Answer
Input:  "aa", "ab", "abaa"
Output:     false
Expected:   true
         */
        assertTrue(isInterleave("aa", "ab", "abaa"));
    }
    
    @Test
    public void test1(){
        /**
Submission Result: Time Limit Exceeded
Last executed input:    "bbbbbabbbbabaababaaaabbababbaaabbabbaaabaaaaababbbababbbbbabbbbababbabaabababbbaabababababbbaaababaa", "babaaaabbababbbabbbbaabaabbaabbbbaabaaabaababaaaabaaabbaaabaaaabaabaabbbbbbbbbbbabaaabbababbabbabaab", "babbbabbbaaabbababbbbababaabbabaabaaabbbbabbbaaabbbaaaaabbbbaabbaaabababbaaaaaabababbababaababbababbbababbbbaaaabaabbabbaaaaabbabbaaaabbbaabaaabaababaababbaaabbbbbabbbbaabbabaabbbbabaaabbababbabbabbab"
         */
        assertFalse(isInterleave("bbbbbabbbbabaababaaaabbababbaaabbabbaaabaaaaababbbababbbbbabbbbababbabaabababbbaabababababbbaaababaa", "babaaaabbababbbabbbbaabaabbaabbbbaabaaabaababaaaabaaabbaaabaaaabaabaabbbbbbbbbbbabaaabbababbabbabaab", "babbbabbbaaabbababbbbababaabbabaabaaabbbbabbbaaabbbaaaaabbbbaabbaaabababbaaaaaabababbababaababbababbbababbbbaaaabaabbabbaaaaabbabbaaaabbbaabaaabaababaababbaaabbbbbabbbbaabbabaabbbbabaaabbababbabbabbab"));
    }
}
