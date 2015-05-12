package com.brayden.other;

import static org.junit.Assert.*;

import org.junit.Test;

import com.brayden.LeetcodeCommon;

public class SortArrayInWaveForm extends LeetcodeCommon {
    
    public void sortInWave1(int[] arr){
        if(arr == null || arr.length <= 1)
            return;
        
        if(arr[0] < arr[1])
            swap(arr, 0, 1);
        
        int len = arr.length;
        int i = 2; 
        for(; i + 1 < len; i += 2){
            if(arr[i] < arr[i - 1])
                swap(arr, i, i - 1);
            if(arr[i] < arr[i + 1])
                swap(arr, i, i + 1);
        }
        
        if(i == len - 1 && arr[i - 1] > arr[i])
            swap(arr, i - 1, i);
    }
    
    public void sortInWave(int[] arr){
        if(arr == null || arr.length <= 1)
            return;
        
        int len = arr.length;
        for(int i = 0; i < len; i += 2){
            if(i != 0 && arr[i] < arr[i - 1])
                swap(arr, i, i - 1);
            if(i != len - 1 && arr[i] < arr[i + 1])
                swap(arr, i, i + 1);
        }
    }
    
    @Test
    public void test(){
        testWaveSort(new int[]{10, 5, 6, 3, 2, 20, 100, 80});
    }

    @Test
    public void test1(){
        testWaveSort(new int[]{20, 10, 8, 6, 4, 2});
    }

    @Test
    public void test2(){
        testWaveSort(new int[]{2, 4, 6, 8, 10, 20});
    }
    
    @Test
    public void test3(){
        testWaveSort(new int[]{3, 6, 5, 10, 7, 20});
    }
    
    private void testWaveSort(int[] input) {
        sortInWave(input);
        for(int i = 0; i < input.length; i += 2){
            if(i != 0)
                assertTrue(input[i] >= input[i - 1]);
            if(i != input.length - 1)
                assertTrue(input[i] >= input[i + 1]);
        }
    }
}
