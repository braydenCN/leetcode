package com.brayden.other;

import static org.junit.Assert.*;

import org.junit.Test;

import com.brayden.LeetcodeCommon;

public class FindKthLargestElem extends LeetcodeCommon {
    public int findKthLargestElem(int[] arr, int k){
        if(arr == null || k < 0 || arr.length < k)
            return -1;
        
        findKthLargestElem(arr, 0, arr.length - 1, k);
        return arr[k - 1];
    }

    private void findKthLargestElem(int[] arr, int low, int high, int k) {
        int ind = partition(arr, low, high);
        if(ind + 1 == k)
            return;
        if(ind + 1 < k)
            findKthLargestElem(arr, ind + 1, high, k);
        else
            findKthLargestElem(arr, low, ind - 1, k);
    }

    private int partition(int[] arr, int low, int high) {
        int pivot = arr[low], ind = low;
        for(int i = low; i <= high; i++)
            if(arr[i] > pivot)
                swap(arr, i, ++ind);
        swap(arr, ind, low);
        return ind;
    }
    
    @Test
    public void test(){
        int[] input = new int[]{3, 2, 7, 6, 4, 1, 5};
        assertEquals(6, findKthLargestElem(input, 2));
        assertEquals(2, findKthLargestElem(input, 6));
    }
}
