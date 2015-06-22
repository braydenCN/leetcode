package com.brayden;

import static org.junit.Assert.*;

import java.util.TreeSet;

import org.junit.Test;

public class ContainsDuplicateIII {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if(nums == null || k < 0 || t < 0)
            return false;
        TreeSet<Integer> set = new TreeSet<>();
        for(int i = 0; i < nums.length; i++){
            if(i - k - 1 >= 0)
                set.remove(nums[i - k - 1]);
            int n = nums[i];
            if(set.floor(n) != null && n <= t + set.floor(n) || 
                    set.ceiling(n) != null && set.ceiling(n) <= t + n)
                return true;
            set.add(n);
        }
        return false;
    }
    
    @Test
    public void test(){
        /**
Submission Result: Wrong Answer
Input:  [-1,-1], 1, 0
Output:     false
Expected:   true
         */
        assertTrue(containsNearbyAlmostDuplicate(new int[]{-1, -1}, 1, 0));
    }
    
    @Test
    public void test1(){
        assertFalse(containsNearbyAlmostDuplicate(new int[]{-1}, 1, 0));
    }
    
    @Test
    public void test2(){
        /**
Submission Result: Wrong Answer
Input:  [-1,2147483647], 1, 2147483647
Output:     true
Expected:   false
         */
        assertFalse(containsNearbyAlmostDuplicate(new int[]{-1,2147483647}, 1, 2147483647));
    }
}
