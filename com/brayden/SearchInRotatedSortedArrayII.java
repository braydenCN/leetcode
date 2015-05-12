package com.brayden;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class SearchInRotatedSortedArrayII {
    
    private int findMaxInd(int[] num, int low, int high) {
        int mid = -1;
        int lowBound = low;
        int highBound = high;
        while(low <= high){
            mid = ((high - low) >> 1) + low;
            if(num[mid] > num[mid + 1])
                break;
            if(num[mid] >= num[lowBound]){
                low = mid + 1;
            }else if(num[mid] <= num[highBound]){
                high = mid - 1;
            }
        }
        return mid;
    }
    
    public boolean search(int[] A, int target) {
        if(A == null || A.length == 0)
            return false;
        
        int len = A.length;
        int low = 0, high = len - 1;
        if(low == high && A[low] != target)
            return false;
        if(A[low] == target || A[high] == target)
            return true;
        
        if(A[low] < A[high])
            return Arrays.binarySearch(A, target) >= 0;
        
        if(A[low] == A[high]){
            do{
                low++;
             } while(low <= high && A[low] == A[0]);
            if(low > high)
                return false;
            if(A[low] < A[0])
                return Arrays.binarySearch(A, low, len, target) >= 0;
        }

        /** so A[low] > A[high] **/
        int maxInd = findMaxInd(A, low, high);
        if(target >= A[0])
            return Arrays.binarySearch(A, 0, maxInd + 1, target) >= 0;
        if(target <= A[len - 1])
            return Arrays.binarySearch(A, maxInd + 1, len, target) >= 0;
            
        return false;
    }
    
    @Test
    public void test(){
        assertTrue(search(new int[]{1, 2, 3, 4}, 3));
    }
    
    @Test
    public void test1(){
        assertFalse(search(new int[]{1, 1, 1, 1}, 2));
    }
    
    @Test
    public void test2(){
        assertTrue(search(new int[]{1, 1, 2, 1, 1}, 2));
    }
    
    @Test
    public void test3(){
        assertTrue(search(new int[]{2, 3, 4, 1, 2}, 1));
    }
    
    @Test
    public void test4(){
        assertTrue(search(new int[]{2,2,2,0,2,2}, 0));
    }
    
    @Test
    public void test5(){
        /**
Submission Result: Time Limit Exceeded
Last executed input:    [0,0,1,1,2,0], 2
         */
        assertTrue(search(new int[]{0,0,1,1,2,0}, 2));
    }
}
