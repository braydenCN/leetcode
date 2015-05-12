package com.brayden;

public class RemoveDuplicatesFromSortedArray {
    public int removeDuplicates(int[] A) {
    	if(A == null || A.length == 0)
    		return 0;
    	
        int ind = 1;
        for(int i = 1; i < A.length; i++)
        	if(A[i] != A[i - 1])
        		A[ind++] =  A[i];
        
        return ind;
    }
}
