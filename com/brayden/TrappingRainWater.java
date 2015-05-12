package com.brayden;

import static org.junit.Assert.*;

import org.junit.Test;

public class TrappingRainWater {
    
    private int trapInternal(int[] A, int low, int high){
        if(A == null || high - low < 2)
            return 0;
        
        int result = 0;
        int max = -1, secondMax = -1;
        int maxInd = 0, secondMaxInd = 0;
        
        for(int i = low; i <= high; i++)
            if(A[i] > max){
                secondMax = max;
                secondMaxInd = maxInd;
                max = A[i];
                maxInd = i;
            }else if(A[i] > secondMax){
                secondMax = A[i];
                secondMaxInd = i;
            }
        
        int ind1 = Math.min(maxInd, secondMaxInd);
        int ind2 = Math.max(maxInd, secondMaxInd);
        if(ind1 + 1 != ind2)
            result += sum(A, ind1, ind2, A[secondMaxInd]);
        
        result += trapInternal(A, low, ind1);
        result += trapInternal(A, ind2, high);
        return result;
    }
    
    public int trap(int[] A) {
        return trapInternal(A, 0, A.length - 1);    
    }
    
    
    public int trap1(int[] A) {
        if(A == null || A.length < 3)
            return 0;
        
        int len = A.length;
        int result = 0;
        int ind;
        
        /** bypass to begin the first downward **/
        for(ind = 0; ind < len - 1 && A[ind] <= A[ind + 1]; ind++);
        int preHighInd = ind;
        boolean downwarding = true;
        
        for(; ind < len; ind++){
            if(ind != 0 &&  A[ind - 1] <= A[ind])
                downwarding = false;
            
            if(ind != 0 &&  A[ind - 1] <= A[ind] && 
                  (ind == len - 1 || A[ind] < A[ind + 1]) && 
                A[ind] == A[preHighInd]){ // upward, need sum
                result += sum(A, preHighInd, ind, A[preHighInd]);
                    
                /** bypass to begin the next downward **/
                for(; ind < len - 1 && A[ind] <= A[ind + 1]; ind++);
                preHighInd = ind;
                downwarding = true;
                continue;
            }
            
            if(!downwarding && (ind == 0 || A[ind - 1] <= A[ind]) && 
                    (ind == len - 1 || A[ind + 1] < A[ind])){ // downward, need sum
                
                int baseline = A[ind] < A[preHighInd] ? A[ind] : A[preHighInd];
                result += sum(A, preHighInd, ind, baseline);
                preHighInd = ind;
                downwarding = true;
                continue;
            }
        }
        return result;
    }
    
    private int sum(int[] A, int ind1, int ind2, int baseline) {
        int result = 0;
        for(int i = ind1; i <= ind2; i++)
            if(A[i] < baseline)
                result += baseline - A[i];
        return result;
    }

    @Test
    public void test(){
        assertEquals(6, trap(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}));
    }
    
    @Test
    public void test1(){
        /**
Input:  [5,4,1,2]
Output:     -1
Expected:   1
         */
        assertEquals(1, trap(new int[]{5,4,1,2}));
    }
 
    @Test
    public void test2(){
        /**
Submission Result: Wrong Answer
Input:  [5,2,1,2,1,5]
Output:     2
Expected:   14
         */
        assertEquals(14, trap(new int[]{5,2,1,2,1,5}));
    }
    
}
