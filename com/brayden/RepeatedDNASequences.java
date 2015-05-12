package com.brayden;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

public class RepeatedDNASequences extends LeetcodeCommon {
    
    public List<String> findRepeatedDnaSequences(String s) {
        if(s == null || s.length() <= 10)
            return Collections.emptyList();
        
        BitSet seen = new BitSet();
        Set<String> res   = new HashSet<>();
        for(int i = 0; i <= s.length() - 10; i++){
            String key = s.substring(i, i + 10);
            int intKey = 0;
            for(char c: key.toCharArray())
                intKey = (intKey << 2) + map(c);
            if(seen.get(intKey))
                res.add(key);
            else
                seen.set(intKey);
        }
        
        return new ArrayList<>(res);
    }
    
    public List<String> findRepeatedDnaSequences1(String s) {
        if(s == null || s.length() <= 10)
            return Collections.emptyList();
        
        char[] seen = new char[140000];
        Set<String> res   = new HashSet<>();
        for(int i = 0; i <= s.length() - 10; i++){
            String key = s.substring(i, i + 10);
            int intKey = intKey(key);
            if(seen(seen, intKey))
                res.add(key);
            else
                addSeen(seen, intKey);
        }
        
        return new ArrayList<>(res);
    }
    
    private boolean seen(char[] seen, int key) {
        return (seen[key / 8] & (1 << (key % 8))) > 0;
    }

    private void addSeen(char[] seen, int key) {
        seen[key / 8] |= (1 << (key % 8));
    }

    private int map(char c){
        switch(c){
        case 'A': return 0;
        case 'C': return 1;
        case 'G': return 2;
        case 'T': return 3;
        default: return -1;
        }
    }
    
    private int intKey(String s){
        int result = 0;
        for(char c: s.toCharArray())
            result = (result * 4) + map(c);
        return result;
    } 
    
    @Test
    public void test(){
        assertListEquals(Arrays.asList("AAAAACCCCC", "CCCCCAAAAA"), 
                findRepeatedDnaSequences("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"));
    }
    
    @Test
    public void test1(){
        /**
Submission Result: Runtime Error
Runtime Error Message:  Line 21: java.lang.ArrayIndexOutOfBoundsException: -400
Last executed input:    "AAGATCCGTCCCCCCAAGATCCGTC"
         */
        assertListEquals(Arrays.asList("AAGATCCGTC"), 
                findRepeatedDnaSequences("AAGATCCGTCCCCCCAAGATCCGTC"));
    }
    
    @Test
    public void test2(){
        /**
Submission Result: Wrong Answer
Input:  "AAAAAAAAAAA"
Output:     []
Expected:   ["AAAAAAAAAA"]
         */
        assertListEquals(Arrays.asList("AAAAAAAAAA"), 
                findRepeatedDnaSequences("AAAAAAAAAAA")); 
    }
}
