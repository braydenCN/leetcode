package com.brayden;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;
// this needs improve!!!

/**
 * https://oj.leetcode.com/problems/maximum-product-subarray/
 * <p>
 * Find the contiguous subarray within an array (containing at least one 
 * number) which has the largest product. 
 * 
 * @author pengczha
 *
 */
public class MaximumProductSubarray extends LeetcodeCommon {

    public int maxProduct(List<Integer> nums) {
        if(nums == null || nums.isEmpty())
            return 0;
        if(nums.size() == 1)
            return nums.get(0);
        
        int max = 0, maxPos = 0, minNeg = 0;
        for(int n: nums){
            if(n == 0)
                maxPos = minNeg = 0;
            else if(n > 0){
                maxPos = maxPos == 0 ? n : maxPos * n;
                minNeg = minNeg == 0 ? 0 : minNeg * n;
            }else{
                int t = maxPos;
                maxPos = minNeg == 0 ? 0 : minNeg * n;
                minNeg = t == 0 ? n : t * n;
            }
            max = Math.max(max,  maxPos);
        }
        
        return Math.max(maxPos, max);
    }
    
    
	private int findLastNegative(int[] A, int low, int high) {
		int ind = -1;
		for(int i = low; i <= high; i++)
			if(A[i] < 0)
				ind = i;
		return ind;
	}

	private int findFirstNegative(int[] A, int low, int high) {
		for(int i = low; i <= high; i++)
			if(A[i] < 0)
				return i;
		return -1;
	}

	private int getProduct(int[] A, int low, int high) {
		int product = 1;
		for(int i = low; i <= high; i++)
			product *= A[i];
		return product;
	}

	private int findNumOfNegatives(int[] A, int low, int high) {
		int count = 0;
		for(int i = low; i <= high; i++)
			if(A[i] < 0)
				count ++;
		return count;
	}

	private int findZero(int[] A, int begin) {
		for(int i = begin; i < A.length; i++)
			if(A[i] == 0)
				return i;
		return -2;
	}
	
	private int maxProductNoZero(int[] A, int low, int high) {
		if(low == high)
			return A[low];
		
		int numOfNegatives = findNumOfNegatives(A, low, high);
		if(numOfNegatives % 2 == 0)
			return getProduct(A, low, high);
		else{
			int firstNegInd =  findFirstNegative(A, low, high);
			int lastNegInd  =  findLastNegative(A, low, high);
			
			int maxProduct  =  getProduct(A, firstNegInd + 1, high);
			return Math.max(maxProduct, getProduct(A, low, lastNegInd - 1));
		}
	}
	
    public int maxProduct(int[] A) {
        if(A == null || A.length == 0)
        	return 0;
        
        if(A.length == 1)
        	return A[0];

    	int low, high, tmp;
        int aLen        =  A.length;
        int maxProduct  =  0;  // in some case, max product may be zero
        int lastZeroInd =  -1; // should never set to (A.length - 1)
        int nextZeroInd =  -1;
        boolean goOn    =  true;
        
        if(findZero(A, 0) == -2) // so no zero, max product may be negative
        	maxProduct = Integer.MIN_VALUE;
        
        while(goOn){
        	low = lastZeroInd + 1;
        	nextZeroInd = findZero(A, lastZeroInd + 1);
        		
        	if(nextZeroInd == -2){ // no more zero
        		high = aLen - 1;
        		goOn = false;
        	}else if(nextZeroInd == A.length - 1){ // next zero at the end
        		high = aLen - 2;
        		goOn = false; 
        	}else
        		high = nextZeroInd - 1;
        	
        	if(nextZeroInd == lastZeroInd || //this could happen in first round
        			nextZeroInd == lastZeroInd + 1){ // skips continuous zero
        		lastZeroInd = nextZeroInd;
        		continue;
        	}
        	
        	tmp = maxProductNoZero(A, low, high);
        	if(maxProduct < tmp)
        		maxProduct = tmp;
        	lastZeroInd = nextZeroInd;
        }
        return maxProduct;
    }

    
    @Test
    public void test(){
    	assertEquals(0,   maxProduct(new int[]{0, -2, 0}));
    	assertEquals(0,   maxProduct(new int[]{0, 0, -2, 0}));
    	assertEquals(6,   maxProduct(new int[]{2, 3, -2, 4}));
    	assertEquals(0,   maxProduct(new int[]{}));
    	assertEquals(0,   maxProduct(new int[]{-2, 0, -2}));
    	assertEquals(-2,  maxProduct(new int[]{-2}));
    	assertEquals(2,   maxProduct(new int[]{-2, 2}));
    	assertEquals(4,   maxProduct(new int[]{-2, -2}));
    	assertEquals(2,   maxProduct(new int[]{2, -2, 0, 0}));
    	assertEquals(4,   maxProduct(new int[]{-2, -2, 0, -1, -2}));
    	assertEquals(12,  maxProduct(new int[]{-4, -3, -2}));
    }
    
    @Test
    public void test1(){
        assertEquals(0,   maxProduct(Arrays.asList(0, -2, 0)));
        /**
Submission Result: Wrong Answer
Input:  [-4,-3,-2]
Output:     72
Expected:   12
         */
        assertEquals(12,   maxProduct(Arrays.asList(-4, -3, -2)));
//        assertEquals(0,   maxProduct(new int[]{0, 0, -2, 0}));
//        assertEquals(6,   maxProduct(new int[]{2, 3, -2, 4}));
//        assertEquals(0,   maxProduct(new int[]{}));
//        assertEquals(0,   maxProduct(new int[]{-2, 0, -2}));
//        assertEquals(-2,  maxProduct(new int[]{-2}));
//        assertEquals(2,   maxProduct(new int[]{-2, 2}));
//        assertEquals(4,   maxProduct(new int[]{-2, -2}));
//        assertEquals(2,   maxProduct(new int[]{2, -2, 0, 0}));
//        assertEquals(4,   maxProduct(new int[]{-2, -2, 0, -1, -2}));
//        assertEquals(12,  maxProduct(new int[]{-4, -3, -2}));
    }
}
