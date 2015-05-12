package com.brayden;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * https://oj.leetcode.com/problems/find-minimum-in-rotated-sorted-array/
 * <p>
 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 * Find the minimum element.
 * You may assume no duplicate exists in the array.
 * @author pengczha
 *
 */
public class FindMinimumInRotatedSortedArray {

    public int findMin(int[] num) {
    	if(num == null || num.length == 0)
    		return 0;
    	
    	if(num.length == 1)
    		return num[0];
    	
        int low = 0, high = num.length - 1;
        if(num[low] < num[high]) // in case no rotation happens. This is valid!
        	return num[low];
        
        int mid = -1;
        while(low <= high){
        	mid = ((high - low) >> 1) + low;
            if(num[mid] > num[mid + 1])
            	break;
            /** so num[mid] < num[mid + 1] now. (we know no duplicate) **/
            if(num[mid] >= num[0])
            	low = mid + 1;
            else// if(num[mid] < num[num.length - 1])
            	high = mid - 1;
        }
        return num[mid + 1];
    }
    
    @Test
    public void test(){
    	assertEquals(1,  findMin(new int[]{4, 5, 6, 7, 1, 2}));
    	assertEquals(1,  findMin(new int[]{6, 7, 1, 2, 3, 4, 5}));
    	assertEquals(0,  findMin(new int[]{}));
    	assertEquals(1,  findMin(new int[]{1}));
    	assertEquals(0,  findMin(null));
    	assertEquals(1,  findMin(new int[]{1,2}));
    }
    
    @Test
    public void test1(){
        assertEquals(1,  findMin(new int[]{6, 7, 1, 2, 3, 4}));
    }
}
