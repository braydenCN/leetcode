package com.brayden;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ContainsDuplicate {
    public boolean containsDuplicate1(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for(int n: nums)
            if(set.contains(n))
                return true;
            else
                set.add(n);
        return false;
    }
    
    public boolean containsDuplicate(int[] nums) {
        if(nums == null)
            return false;
        Arrays.sort(nums);
        for(int i = 1; i < nums.length; i++)
            if(nums[i] == nums[i - 1])
                return true;
        return false;
    }
}
