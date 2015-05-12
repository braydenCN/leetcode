package com.brayden;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import static org.junit.Assert.*;

public class TwoSum {
    public int[] twoSum1(int[] numbers, int target) {
        int[] notFound = new int[]{};
        if(numbers == null || numbers.length == 0)
            return notFound;
        
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < numbers.length; i++)
            map.put(numbers[i], i);
        
        for(int i = 0; i < numbers.length; i++){
            Integer another = map.get(target - numbers[i]);
            if(another != null && i != another)
                return i < another ? new int[]{i + 1, another + 1} : 
                                     new int[]{another + 1, i + 1};
        }
        return notFound;
    }
    
    public int[] twoSum(int[] numbers, int target) {
        if(numbers == null || numbers.length < 2)
            return null;
        
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < numbers.length; i++){
            Integer other = map.get(target - numbers[i]);
            if(other != null)
                return new int[]{other + 1, i + 1};
            map.put(numbers[i], i);
        }
        return null;
    }
    
    @Test
    public void test(){
        /**
Submission Result: Wrong Answer
Input:  [3,2,4], 6
Output:     0, 0
Expected:   2, 3
         */
        assertArrayEquals(new int[]{2, 3}, twoSum(new int[]{3, 2, 4}, 6));
    }
}
