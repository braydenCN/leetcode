package com.brayden;

import org.junit.Test;

public class JumpGame {
    public boolean canJump(int[] A) {
        if(A == null || A.length <= 1)
            return true;
        
        int len = A.length;
        boolean can[] = new boolean[len];
        can[0] = true;
        for(int i = 1; i < len; i++)
            for(int j = 0; j < i; j++)
                if(can[j] && j + A[j] >= i){
                    can[i] = true;
                    break;
                }

        return can[len - 1];
    }
    
    @Test
    public void test(){
        /**
Submission Result: Wrong Answer
Input:      [0,2,3]
Output:     true
Expected:   false
         */
        // miss can[j] judge? stupid coding 
    }
    
    @Test
    public void test1(){
        /**
Submission Result: Time Limit Exceeded
Last executed input:    [0]
        */
        // can[0] not initialized. stupid coding
    }
    
    /**
     * https://oj.leetcode.com/discuss/11810/this-might-be-the-simplest-interview-question-ever
     * Yes, it's enough to just judge the MAX POSITION IT CAN REACH
     */
}
