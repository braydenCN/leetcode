package com.brayden;

import static org.junit.Assert.*;

import org.junit.Test;

public class SearchInsertPosition {
    
    public int searchInsert(int[] A, int target) {
        if(A == null || A.length == 0)
            return -1;
        
        int len = A.length;
        if(A[0] >= target)
            return 0;
        if(A[len - 1] < target)
            return len;

        int low = 1, high = len - 1;
        while(low <= high){
            int mid = ((high - low) >> 1) + low;
            if(A[mid] == target ||
                   A[mid - 1] < target && A[mid] > target)  
                return mid;
            if(A[mid] < target)
                low = mid + 1;
            else
                high = mid - 1;
        }
        return -1;
    }
    
    public int searchInsert1(int[] A, int target) {
        if(A == null || A.length == 0)
            return -1;
        
        int len = A.length;
        int low = 0, high = len - 1;
        while(low <= high){
            int mid = ((high - low) >> 1) + low;
            if(A[mid] == target)
                return mid;
            if(A[mid] < target){
                if(mid < len - 1 && A[mid + 1] > target)
                    return mid + 1;
                low = mid + 1;
            }
            if(A[mid] > target){
                if(mid > 0 && A[mid - 1] < target)
                    return mid;
                high = mid - 1;
            }
        }
        return low;
    }
    
    @Test
    public void test(){
        assertEquals(2, searchInsert(new int[]{1, 3, 5, 6}, 5));
        assertEquals(1, searchInsert(new int[]{1, 3, 5, 6}, 2));
        assertEquals(4, searchInsert(new int[]{1, 3, 5, 6}, 7));
        assertEquals(0, searchInsert(new int[]{1, 3, 5, 6}, 0));
    }
    
    @Test
    public void test1(){
        /**
Submission Result: Runtime Error
Runtime Error Message:  Line 15: java.lang.ArrayIndexOutOfBoundsException: -1
Last executed input:    [1,3], 2
         */
        assertEquals(1, searchInsert(new int[]{1, 3}, 2));
    }
    
    @Test
    public void test2(){
        /**
Submission Result: Wrong Answer
Input:  [1,3], 3
Output:     2
Expected:   1
         */
        assertEquals(1, searchInsert(new int[]{1, 3}, 3));
    }
    
    public int fromLC(int[] A, int target) {
        int low = 0, high = A.length-1;
        while(low<=high){
            int mid = (low+high)/2;
            if(A[mid] == target) return mid;
            else if(A[mid] > target) high = mid-1;
            else low = mid+1;
        }
        return low;
    }
}
