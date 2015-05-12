package com.brayden;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Two Sum II - Input array is sorted
 * 
Given an array of integers that is already sorted in ascending order, find two 
numbers such that they add up to a specific target number.

The function twoSum should return indices of the two numbers such that they add
 up to the target, where index1 must be less than index2. Please note that your
  returned answers (both index1 and index2) are not zero-based.

You may assume that each input would have exactly one solution.

Input: numbers={2, 7, 11, 15}, target=9
Output: index1=1, index2=2
 */
public class TwoSumIIInputArrayIsSorted {
    public int[] twoSum(int[] numbers, int target) {
        if(numbers == null || numbers.length < 2)
            return null;
        
        int low = 0, high = numbers.length - 1;
        while(low < high){
            int sum = numbers[low] + numbers[high]; 
            if(sum == target)
                return new int[]{low + 1, high + 1};
            if(sum < target)
                low++;
            else high--;
        }
        
        return null;
    }
    
    @Test
    public void test(){
        assertArrayEquals(new int[]{1, 2}, twoSum(new int[]{2, 7, 11, 15}, 9));
    }
    
    @Test
    public void test1(){
        assertNull(twoSum(new int[]{2, 7, 11, 15}, 8));
    }
    
    @Test
    public void test2(){
        assertArrayEquals(new int[]{5, 6}, twoSum(new int[]{2, 2, 2, 2, 7, 11, 15}, 18));
    }
    
    @Test
    public void test3(){
        assertNull(twoSum(new int[]{2}, 8));
    }
    
    @Test
    public void test4(){
        assertNull(twoSum(new int[]{2, 3}, 4));
    }
}
