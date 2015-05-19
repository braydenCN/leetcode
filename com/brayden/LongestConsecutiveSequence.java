package com.brayden;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class LongestConsecutiveSequence extends LeetcodeCommon {
    
    public int longestConsecutive(int[] num) {
        if(num == null)
            return 0;
        
        int longest = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for(int n: num){
            if(map.containsKey(n))
                continue;
            map.put(n, 1);
            int start = map.containsKey(n - 1) ? n - map.get(n - 1) : n;
            int end = map.containsKey(n + 1) ? n + map.get(n + 1) : n;
            int len = end - start + 1;
            map.put(start, len);
            map.put(end, len);
            if(len > longest)
                longest = len;
        }
        return longest;
    }
    
    public int longestConsecutive1(int[] num) {
        if(num == null || num.length == 0)
            return 0;
        
        int longest = 0;
        Map<Integer, Interval> map = new HashMap<>();
        for(int n: num)
            if(!map.containsKey(n)){
                int start = map.containsKey(n - 1) ? map.get(n - 1).start : n;
                int end = map.containsKey(n + 1) ? map.get(n + 1).end : n;
                Interval p = new Interval(start, end);
                map.put(n, p);
                map.put(start, p);
                map.put(end, p);
                int length = end - start + 1;
                if(length > longest)
                    longest = length;
            }
        return longest;
    }
    
    @Test
    public void test2(){
        /**
        Input:  [0,0,-1]
                Output:     1
                Expected:   2
        **/
        assertEquals(2, longestConsecutive(new int[]{0, 0 , -1}));
    }
    
    @Test
    public void test(){
        assertEquals(4, longestConsecutive(new int[]{100, 4, 200, 1, 3, 2}));
        assertEquals(1, longestConsecutive(new int[]{1}));
        assertEquals(3, longestConsecutive(new int[]{1, 2, 0, 1}));
    }
    
    @Test
    public void test1(){
        /**
Submission Result: Wrong Answer
Input:  [1,2,0,1]
Output:     2
Expected:   3
         */
        /**
         * missing map.put(lowBound/highBound..) when implementing, 
         * blame to work too early in morning...
         */
        
    }
}
