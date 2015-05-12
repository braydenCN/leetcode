package com.brayden;

import static org.junit.Assert.*;

import org.junit.Test;

public class Candy {
    public int candy(int[] ratings) {
        if(ratings == null || ratings.length == 0)
            return 0;
        
        int N = ratings.length;
        int sum = 1;
        int pre = 1; // how many the previous boy gets
        int toCompensate = 1; // how many previous boys need to be compensated 
        
        /** 
         * when reached, add one compensation
         * for case 1,3,4,3,2,1, when do compensation with the final 1, 
         * the one at 4 need also to be compensated
         */
        int compensationThreshold = Integer.MAX_VALUE;
        for(int i = 1; i < N; i++){
            int cur;
            if(ratings[i] < ratings[i - 1]){// rate gets down
                cur = 1;
                if(pre == 1){ 
                    if(toCompensate + 1 == compensationThreshold){
                        toCompensate++;
                        compensationThreshold = Integer.MAX_VALUE;
                    }
                    sum += toCompensate++;
                }else{
                    toCompensate = 1;
                    compensationThreshold = pre;
                }
            }else if(ratings[i] > ratings[i - 1]){ // rate gets up
                cur = pre + 1;
                toCompensate = 0;
            }else{ // rate is the same
                cur = 1;
                toCompensate = 1;
                compensationThreshold = Integer.MAX_VALUE;
            }
            sum += cur;
            pre = cur;
        }
        return sum;
    }
    
    @Test
    public void test(){
        assertEquals(11, candy(new int[]{5, 3, 4, 6, 5, 1}));
        assertEquals(1, candy(new int[]{0}));
        assertEquals(4, candy(new int[]{1, 2, 2}));
        assertEquals(2, candy(new int[]{2, 2}));
        assertEquals(4, candy(new int[]{2, 2, 2, 2}));
        assertEquals(4, candy(new int[]{2, 2, 1}));
        assertEquals(13, candy(new int[]{1, 2, 4, 3, 2, 1}));
        assertEquals(18, candy(new int[]{1, 2, 5, 4, 3, 2, 1}));
    }
    
    @Test
    public void test1(){
        /**
Submission Result: Wrong Answer
Input:  [1,2,2]
Output:     5
Expected:   4
         */
        
        /**
Submission Result: Wrong Answer
Input:  [2,2]
Output:     3
Expected:   2
         */
        
        /**
Submission Result: Wrong Answer
Input:  [2,2,1]
Output:     3
Expected:   4
         */
        
        /**
Submission Result: Wrong Answer
Input:  [1,3,4,3,2,1]
Output:     12
Expected:   13
         */
    }
}
