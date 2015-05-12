package com.brayden;

import static org.junit.Assert.*;

import org.junit.Test;

public class MaximumSubarray {
    public int maxSubArray(int[] A) {
        if(A == null || A.length == 0)
            return 0;
        int max = A[0];
        int sum = A[0];
        for(int i = 1; i < A.length; i++){
            if(sum < 0)
                sum = 0;
            sum += A[i];
            if(sum > max)
                max = sum;
        }
        return max;
    }
    
    @Test
    public void test(){
        assertEquals(6, maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
    }
    
    @Test
    public void test1(){
        /**
Submission Result: Wrong Answer
Input:  [-2,1]
Output:     -1
Expected:   1
        */
        assertEquals(1, maxSubArray(new int[]{-2, 1}));
    }
}
