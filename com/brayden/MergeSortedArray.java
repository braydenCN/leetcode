package com.brayden;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * https://oj.leetcode.com/problems/merge-sorted-array/
 * <p>
 * Given two sorted integer arrays A and B, merge B into A as one sorted array.
 * 
 * @author pengczha
 *
 */
public class MergeSortedArray {
	
    public void merge(int A[], int m, int B[], int n) {
        if(n <= 0)
            return;
        int index = m + n - 1;
        m--; n--;
        while(m >= 0 && n >= 0)
            A[index--] = A[m] > B[n] ? A[m--] : B[n--];
        while(n >= 0)
            A[index--] = B[n--];
    }
    
    public void merge1(int A[], int m, int B[], int n) {
    	if(n <= 0)
    		return;
    	
        int index = m + n - 1;
        m--; n--;
        
        while(m >= 0 && n >= 0){
        	if(A[m] > B[n])
        		A[index--] = A[m--];
        	else
        		A[index--] = B[n--];
        }
        
        while(m >= 0)
        	A[index--] = A[m--];
        
        while(n >= 0)
        	A[index--] = B[n--];
    }
    
    @Test
    public void test(){
    	int[] arr1 = {1, 2, 2, 5, 0, 0, 0, 0};
    	int[] arr2 = {2, 4, 5, 5};
    	int[] arr  = {1, 2, 2, 2, 4, 5, 5, 5};
    	
    	merge(arr1, 4, arr2, 4);
    	assertArrayEquals(arr, arr1);
    }
}
