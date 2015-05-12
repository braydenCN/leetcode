package com.brayden;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import org.junit.Test;

public class MinimumWindowSubstring {
    
    public String minWindow(String S, String T) {
        if(S == null || S.isEmpty() || T == null || T.isEmpty())
            return "";
                    
        Queue<Character> seqQ = new LinkedList<>();
        int min   = Integer.MAX_VALUE;
        int resultBegin = 0, resultEnd = -1;
        Map<Character, Integer> frequency = new HashMap<>();
        for(char c: T.toCharArray())
            if(!frequency.containsKey(c))
                frequency.put(c, 1);
            else 
                frequency.put(c, frequency.get(c) + 1);
        
        Map<Character, Queue<Integer>> workingMap = new HashMap<>();
        for(char c: frequency.keySet())
            workingMap.put(c, new LinkedList<>());
        
        Map<Character, Integer> invalidMap = new HashMap<>();
        for(char c: frequency.keySet())
            invalidMap.put(c, 0);
        
        int count = 0;
        for(int i = 0; i < S.length(); i++){
            char c = S.charAt(i);
            if(!frequency.containsKey(c))
                continue;
            
            if(workingMap.get(c).size() < frequency.get(c)){
                seqQ.offer(c);
                workingMap.get(c).offer(i);
                count++;
                if(count == T.length()){
                    char firstC = seqQ.poll();
                    while(invalidMap.get(firstC) > 0){
                        invalidMap.put(firstC, invalidMap.get(firstC) - 1);
                        firstC = seqQ.poll();
                    }
                    int loc = workingMap.get(firstC).poll();
                    count--;
                    int len = i - loc + 1;
                    if(len < min){
                        min = len;
                        resultBegin = loc;
                        resultEnd = i;
                    }
                }
            }else{ // map.get(c).size() == frequency.get(c)
                workingMap.get(c).poll();
                invalidMap.put(c, invalidMap.get(c) + 1);
                count--;
                i--; // do it again
            }
        }
        return S.substring(resultBegin, resultEnd + 1);
    }
    
    @Test
    public void test(){
        assertEquals("BANC", minWindow("ADOBECODEBANC", "ABC"));
    }
    
    @Test
    public void test1(){
        assertEquals("", minWindow("ADODDDDDDDC", "ABC"));
    }
    
    @Test
    public void test2(){
        assertEquals("CBDA", minWindow("ABDDCDDCBDAD", "ABC"));
    }
    
    @Test
    public void test3(){
        /**
Submission Result: Wrong Answer
Input:  "acbbaca", "aba"
Output:     ""
Expected:   "baca"
         */
        assertEquals("baca", minWindow("acbbaca", "aba"));
    }
    
    @Test
    public void test4(){
        /**
Submission Result: Wrong Answer
Input:  "caae", "cae"
Output:     ""
Expected:   "caae"
         */
        assertEquals("caae", minWindow("caae", "cae"));
    }
}
