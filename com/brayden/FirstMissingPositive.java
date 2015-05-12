package com.brayden;

import static org.junit.Assert.*;

import org.junit.Test;

public class FirstMissingPositive extends LeetcodeCommon {
    public int firstMissingPositive(int[] A) {
        /** got the idea from Leetcode discussion **/
        if(A == null || A.length == 0)
            return 1;
        
        int len = A.length;
        for(int i = 0; i < len; i++){
            int j = i;
            while(j != A[j] - 1 && A[j] > 0 && A[j] <= len){
                if(A[j] == A[A[j] - 1]){
                    A[j] = 0;
                    break;
                }
                swap(A, j, A[j] - 1);
            }
        }
        
        for(int i = 0; i < len; i++)
            if(A[i] > len || A[i] <= 0)
                return i + 1;
        return len + 1;
    }
    
    @Test
    public void test(){
        assertEquals(3, firstMissingPositive(new int[]{1, 2, 0}));
        assertEquals(2, firstMissingPositive(new int[]{3, 4, -1, 1}));
    }
    
    @Test
    public void test1(){
        /**
Submission Result: Time Limit Exceeded
Last executed input:    [1,1]
         */
        assertEquals(2, firstMissingPositive(new int[]{1, 1}));
    }
}
