package com.brayden.other;

import java.util.Arrays;

import org.junit.Test;

import static org.junit.Assert.*;

import com.brayden.LeetcodeCommon;

public class QuickSort extends LeetcodeCommon {

    public <T extends Comparable<T>> void quickSort(T[] arr){
        quickSortInt(arr, 0, arr.length - 1);
    }

    
    private <T extends Comparable<T>> void quickSortInt(T[] arr, int low, int high){
        if(low >= high)
            return;
        int ind = partition(arr, low, high);
        quickSortInt(arr, low, ind - 1);
        quickSortInt(arr, ind + 1, high);
    }
    
    private <T extends Comparable<T>> int partition(T[] arr, int low, int high){
        int ind = low;
        T pivot = arr[low];
        for(int i = low + 1; i <= high; i++)
            if(arr[i].compareTo(pivot) < 0)
                swap(arr, i, ++ind);
        swap(arr, low, ind);
        return ind;
    }
    
    @Test
    public void test(){
        Integer[] input = new Integer[]{5, 3, 3, 3, -1, Integer.MAX_VALUE, Integer.MIN_VALUE, 0};
        Integer[] expected = Arrays.copyOf(input, input.length);
        Arrays.sort(expected);
        quickSort(input);
        assertArrayEquals(expected, input);
    }
}
