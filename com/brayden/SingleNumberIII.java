package com.brayden;

import org.junit.Test;
import static org.junit.Assert.*;

public class SingleNumberIII {
    public int[] singleNumber(int[] nums) {
        if(nums == null || nums.length < 2)
            return null;
        
        int mask = 0;
        for (int n: nums)
            mask ^= n;
        mask &= -mask;
        
        int[] res = new int[2];
        for (int n: nums)
            if ((n & mask) == 0)
                res[0] ^= n;
            else 
                res[1] ^= n;
        return res;
    }
    
    public int[] singleNumber1(int[] nums) {
        if(nums == null || nums.length < 2)
            return null;
        
        int xor = 0;
        for (int n: nums)
            xor ^= n;
        int mask = 1;
        while (xor % 2 == 0) {
            mask <<= 1;
            xor >>= 1;
        }
        
        int ind = 0;
        for (int i = 0; i < nums.length; i++)
            if((nums[i] & mask) == 0)
                swap(nums, ind++, i);
        
        int res1 = 0, res2 = 0;
        for (int i = 0; i < ind; i++)
            res1 ^= nums[i];
        for (int i = ind; i < nums.length; i++)
            res2 ^= nums[i];
        return new int[]{res1, res2};
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
    
    @Test
    public void test() {
        System.out.println(singleNumber(new int[]{3, 1, 3, 4, 4, 2}));
    }
}
