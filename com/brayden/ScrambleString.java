package com.brayden;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class ScrambleString {
    
    public boolean isScramble(String s1, String s2) {
        if(s1 == null || s2 == null || s1.length() != s2.length())
            return false;
        
        if(s1.equals(s2))
            return true;
        
        char[] s1Arr = s1.toCharArray();
        char[] s2Arr = s2.toCharArray();
        Arrays.sort(s1Arr);
        Arrays.sort(s2Arr);
        if(!Arrays.equals(s1Arr, s2Arr))
            return false;
        
        len = s1.length();
        this.s1 = s1; this.s2 = s2;
        return isScrambleInt(0, len - 1, 0, len - 1);
    }

    private boolean isScrambleInt(int low1, int high1, int low2, int high2) {
        long key = key(low1, low2, high1 - low1);
        if(cache.containsKey(key))
            return cache.get(key);
        
        boolean result = false;
        if(low1 == high1)
            result = s1.charAt(low1) == s2.charAt(low2);
        else 
            for(int i = 0; i < high1 - low1; i++)
                if(isScrambleInt(low1, low1 + i, low2, low2 + i) && 
                    isScrambleInt(low1 + i + 1, high1, low2 + i + 1, high2) 
                    ||
                   isScrambleInt(low1, low1 + i, high2 - i, high2) &&
                    isScrambleInt(low1 + i + 1, high1, low2, high2 - i - 1)){
                    result = true;
                    break;
                }
        
        cache.put(key, result);
        return result;
    }

    private Map<Long, Boolean> cache = new HashMap<>();
    private String s1, s2;
    private int len;
    private long key(int i1, int i2, int l){
        return i1 * len * len + i2 * len + l;
    }
    
//    private Set<String> getScrambleSet(int i1, int i2) {
//        long key = key(i1, i2);
//        if(cache.containsKey(key))
//            return cache.get(key);
//        
//        Set<String> resSet = new HashSet<>();
//        if(i1 == i2){
//            resSet.add(s2.substring(i1, i1 + 1));
//            cache.put(key, resSet);
//            return resSet;
//        }
//        
//        for(int i = i1; i < i2; i++){
//            Set<String> leftSet  = getScrambleSet(i1, i);
//            Set<String> rightSet = getScrambleSet(i + 1, i2);
//            for(String sLeft: leftSet)
//                for(String sRight: rightSet){
//                    resSet.add(sLeft + sRight);
//                    resSet.add(sRight + sLeft);
//                }
//        }
//        cache.put(key, resSet);
//        return resSet;
//    }
    
    @Test
    public void test(){
        assertTrue(isScramble("great", "rgeat"));
    }
    
    @Test
    public void test1(){
        assertTrue(isScramble("great", "rgtae"));
    }
    
    @Test
    public void test2(){
        assertTrue(isScramble("ab", "ba"));
    }
    
    @Test
    public void test3(){
        assertFalse(isScramble("abcd", "bdac"));
    }
    
    @Test
    public void test4(){
        assertFalse(isScramble("abcde", "caebd"));
    }
   
//    @Test
//    public void test5(){
//        char[] input1 = new char[32000];
//        char[] input2 = new char[32000];
//        Arrays.fill(input1, 0, 31000, 'a');
//        Arrays.fill(input1, 31000, 32000, 'b');
//        Arrays.fill(input2, 0, 1000, 'b');
//        Arrays.fill(input2, 1000, 32000, 'a');
//        assertTrue(isScramble(String.valueOf(input1), String.valueOf(input2)));
//    }
    
    @Test
    public void test6(){
/**
Submission Result: Time Limit Exceeded
Last executed input:    "abcdefghij", "efghijcadb"
**/
        assertFalse(isScramble("abcdefghij", "efghijcadb"));
    }
    
    @Test
    public void test7(){
        /**
Submission Result: Time Limit Exceeded
Last executed input:    "abcdefghijklmnopq", "efghijklmnopqcadb"
         */
        assertFalse(isScramble("abcdefghijklmnopq", "efghijklmnopqcadb"));
    }
}
