package com.brayden.other;

import static org.junit.Assert.assertArrayEquals;

import java.lang.reflect.Array;
import java.util.Arrays;

import org.junit.Test;

public class MergeSort {

    public <T extends Comparable<T>> void mergeSort(T[] arr){
        int len = arr.length;
        @SuppressWarnings("unchecked")
        T[] arr1 = (T[]) Array.newInstance(arr[0].getClass(), len);
        mergeSortInt(arr, 0, len - 1, arr1);
    }
    
    private <T extends Comparable<T>> void mergeSortInt(T[] arr, 
            int low, int high, T[] arr1){
        if(low >= high)
            return;
        int mid = ((high - low) >> 1) + low;
        mergeSortInt(arr, low, mid, arr1);
        mergeSortInt(arr, mid + 1, high, arr1);
        merge(arr, low, mid, high, arr1);
    }
    
    public <T extends Comparable<T>> void merge(T[] arr, 
            int low, int mid, int high, T[] arr1){
        int ind = low, ind1 = low, ind2 = mid + 1;
        while(ind1 <= mid && ind2 <= high)
            if(arr[ind1].compareTo(arr[ind2]) < 0)
                arr1[ind++] = arr[ind1++];
            else
                arr1[ind++] = arr[ind2++];
        while(ind1 <= mid)
            arr1[ind++] = arr[ind1++];
        while(ind2 <= high)
            arr1[ind++] = arr[ind2++];
        System.arraycopy(arr1, low, arr, low, high - low + 1);
    }
    
    @Test
    public void test(){
        Integer[] input = new Integer[]{5, 3, 3, 3, -1, Integer.MAX_VALUE, Integer.MIN_VALUE, 0};
        Integer[] expected = Arrays.copyOf(input, input.length);
        Arrays.sort(expected);
        mergeSort(input);
        assertArrayEquals(expected, input);
    }
}
