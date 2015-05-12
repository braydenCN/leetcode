package com.brayden;

public class RemoveElement {
    public int removeElement(int[] A, int elem) {
        int newLen = 0;
        int ind = 0;
        for(int i = 0; i < A.length; i++)
        	if(A[i] != elem){
        		newLen++;
        		A[ind++] = A[i];
        	}
        return newLen;
    }
    
    /**
     * To improve, ind is the same thing with newLen, so could be removed
     */
    public int removeElement_update(int[] A, int elem) {
        int newLen = 0;
        for(int i = 0; i < A.length; i++)
        	if(A[i] != elem)
        		A[newLen++] = A[i];
        return newLen;
    }
}
