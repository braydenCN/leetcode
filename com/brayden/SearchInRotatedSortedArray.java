package com.brayden;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class SearchInRotatedSortedArray {
    
    public int search(int[] A, int target) {
        if(A == null || A.length == 0)
            return -1;
        
        int len = A.length;
        int low = 0, high = len - 1;
        
        if(A[low] < A[high]){
            int ind = Arrays.binarySearch(A, target);
            return ind >= 0 ? ind : -1;
        }

        if(target < A[0] && target > A[len - 1])
            return -1;
        
        while(low <= high){
            int mid = ((high - low) >> 1) + low;
            if(A[mid] == target)
                return mid;
            if(A[mid] > target){
                if(target >= A[0]){
                    high = mid - 1;
                }else{ // target < A[0]
                    if(A[mid] >= A[0])
                        low = mid + 1;
                    else //A[mid] < A[0]
                        high = mid - 1;
                }
            }else{ // A[mid] < target
                if(A[mid] >= A[0]){
                    low = mid + 1;
                }else{ // A[mid] < A[0]
                    if(target >= A[0])
                        high = mid - 1;
                    else //target < A[0]
                        low = mid + 1;
                }
            }
        }
            
        return -1;
    }
    
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
    
    public int search1(int[] A, int target) {
        if(A == null || A.length == 0)
            return -1;
        
        int len = A.length;
        int low = 0, high = len - 1;
        
        if(len == 1)
            return target == A[0] ? 0 : -1;
        
        if(A[low] < A[high]){
            int ind = Arrays.binarySearch(A, target);
            return ind >= 0 ? ind : -1;
        }

        /** so A[low] > A[high] **/
        int maxInd = findMaxInd(A, low, high);
        if(target >= A[0]){
            int ind = Arrays.binarySearch(A, 0, maxInd + 1, target);
            return ind >= 0 ? ind : -1;
        }
        if(target <= A[len - 1]){
            int ind = Arrays.binarySearch(A, maxInd + 1, len, target);
            return ind >= 0 ? ind : -1;
        }
        return -1;
    }
    
    @Test
    public void test(){
        assertEquals(2, search(new int[]{1, 2, 3, 4}, 3));
    }
    
    @Test
    public void test1(){
        assertEquals(1, search(new int[]{2, 3, 1}, 3));
    }
    
    @Test
    public void test2(){
        assertEquals(-1, search(new int[]{2, 3, 1}, 4));
    }
    
    @Test
    public void test3(){
        assertEquals(-1, search(new int[]{3, 4, 1}, 2));
    }
    
    @Test
    public void test4(){
        assertEquals(-1, search(new int[]{3}, 2));
    }
    
    @Test
    public void test5(){
        assertEquals(0, search(new int[]{3}, 3));
    }
}
