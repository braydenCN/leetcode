package com.brayden;

import java.util.Deque;
import java.util.LinkedList;

import static org.junit.Assert.*;

import org.junit.Test;

public class SlidingWindowMaximum {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if(nums == null || nums.length == 0 || nums.length < k)
            return nums;
        
        int len = nums.length, res[] = new int[len - k + 1];
        Deque<Integer> deque = new LinkedList<>();
        for(int i = 0; i < len; i++){
            if(!deque.isEmpty() && deque.peekLast() == i - k)
                deque.pollLast();
            while(!deque.isEmpty() && nums[deque.peekFirst()] <= nums[i])
                deque.pollFirst();
            deque.offerFirst(i);
            if(i >= k - 1)
                res[i - k + 1] = nums[deque.peekLast()];
        }
        return res;
    }
    
    @Test
    public void test(){
        assertArrayEquals(new int[]{1}, maxSlidingWindow(new int[]{1}, 1));
    }
    
    @Test
    public void test2(){
        assertArrayEquals(new int[]{3,3,5,5,6,7}, maxSlidingWindow(new int[]{1,3,-1,-3,5,3,6,7}, 3));
    }
}
