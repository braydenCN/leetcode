package com.brayden.other;

import static org.junit.Assert.assertArrayEquals;

import java.util.Arrays;

import org.junit.Test;

import com.brayden.LeetcodeCommon;

public class HeapSort extends LeetcodeCommon {
    
    public <T extends Comparable<T>> void heapSort(T[] arr){
        int len = arr.length;
        /** create Heap **/
        for(int i = len / 2; i >= 0; i--)
            siftDown(arr, len, i);
        
        for(int i = len - 1; i > 0; i--){
            swap(arr, 0, i);
            siftDown(arr, i, 0);
        }
    }

    private int leftChild(int ind) {
        return ind * 2 + 1;
    }

    private int rightChild(int ind) {
        return ind * 2 + 2;
    }

    private <T extends Comparable<T>> boolean hasLeftChild(int size, int ind) {
        return leftChild(ind) < size;
    }

    private <T extends Comparable<T>> boolean hasRightChild(int size, 
            int ind) {
        return rightChild(ind) < size;
    }

    private <T extends Comparable<T>> void siftDown(T[] arr, 
            int size, int ind) {
        while (hasLeftChild(size, ind)) {
            int child = leftChild(ind);
            if (hasRightChild(size, ind) && 
                    arr[child + 1].compareTo(arr[child]) > 0)
                child++; // compare with right child now
            if (arr[child].compareTo(arr[ind]) > 0)
                swap(arr, ind, child);
            ind = child;
        }
    }
    
    @Test
    public void test(){
        Integer[] input = new Integer[]{5, 3, 3, 3, -1, Integer.MAX_VALUE, Integer.MIN_VALUE, 0};
        Integer[] expected = Arrays.copyOf(input, input.length);
        Arrays.sort(expected);
        heapSort(input);
        assertArrayEquals(expected, input);
    }
}
