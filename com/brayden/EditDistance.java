package com.brayden;

import static org.junit.Assert.*;

import org.junit.Test;

public class EditDistance {
    public int minDistance(String word1, String word2) {
        if(word1 == null || word1.length() == 0)
            return word2.length();
        
        if(word2 == null || word2.length() == 0)
            return word1.length();
        
        int len1 = word1.length();
        int len2 = word2.length();
        
        int[][] minDist = new int[len1 + 1][len2 + 1];
        
        for(int i = 0; i <= len1; i++)
            minDist[i][0] = i;
        for(int i = 0; i <= len2; i++)
            minDist[0][i] = i;
        
        for(int i = 1; i <= len1; i++)
            for(int j = 1; j <= len2; j++){
                int min = minDist[i - 1][j - 1];
                if(word1.charAt(i - 1) != word2.charAt(j - 1))
                    min++;
                
                min = Math.min(min, minDist[i - 1][j] + 1);
                min = Math.min(min, minDist[i][j - 1] + 1);
                minDist[i][j] = min;
            }
        
        return minDist[len1][len2];
    }
    
    @Test
    public void test(){
        assertEquals(0, minDistance("abcd", "abcd"));
        assertEquals(2, minDistance("abcd", "acbd"));
        assertEquals(1, minDistance("abcd", "abbd"));
        assertEquals(2, minDistance("ad",   "abcd"));
    }
    
    @Test
    public void test1(){
        assertEquals(4, minDistance("aaad",  "bcda"));
    }
}
