package com.brayden;

import static org.junit.Assert.*;

import org.junit.Test;

public class KthLargestElementInAnArray extends LeetcodeCommon {
    
    private int partition(int[] nums, int low, int high){
        int ind = low;
        for(int i = low + 1; i <= high; i++)
            if(nums[low] < nums[i])
                swap(nums, ++ind, i);
        swap(nums, low, ind);
        return ind;
    }
    
    public int findKthLargest(int[] nums, int k) {
        if(nums == null || nums.length == 0)
            throw new IllegalArgumentException();
        int ind = -1, low = 0, high = nums.length - 1;
        do{
            ind = partition(nums, low, high);
            if(ind + 1 == k)
                return nums[ind];
            if(ind + 1 < k)
                low = ind + 1;
            else
                high = ind - 1;
        }while(low <= high);
        return -1;
    }
    
    @Test
    public void test(){
        assertEquals(5, findKthLargest(new int[]{3,2,1,5,6,4}, 2));
    }
    
    @Test
    public void test1(){
        assertEquals(2, findKthLargest(new int[]{1, 2, 2, 2}, 3));
    }
}
