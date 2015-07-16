package com.brayden;

import static org.junit.Assert.*;

import org.junit.Test;

public class ProductOfArrayExceptSelf {
    public int[] productExceptSelf(int[] nums) {
        if(nums == null || nums.length == 0)
            return nums;
        
        int len = nums.length, right = 1;
        int[] res = new int[len];
        res[0] = 1;
        for(int i = 1; i < len; i++)
            res[i] = res[i - 1] * nums[i - 1];
        
        for(int i = len - 1; i >= 0; i--){
            res[i] *= right;
            right *= nums[i];
        }
        return res;
    }
    
    @Test
    public void test(){
        assertArrayEquals(new int[]{24,12,8,6}, productExceptSelf(new int[]{1,2,3,4})); 
    }
}
