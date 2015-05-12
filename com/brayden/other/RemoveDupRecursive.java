package com.brayden.other;

import static org.junit.Assert.*;

import org.junit.Test;

public class RemoveDupRecursive {

    public int removeDup(int[] arr){
        if(arr == null)
            return 0;
        if(arr.length < 2)
            return arr.length;
        
        return removeDupInt(arr, 1, 1);
    }

    private int removeDupInt(int[] arr, int i, int newLen) {
        if(i == arr.length)
            return newLen;
        
        if(arr[i - 1] == arr[i])
            return removeDupInt(arr, i + 1, newLen);
        
        arr[newLen] = arr[i];
        return removeDupInt(arr, i + 1, newLen + 1);
    }
    
    @Test
    public void test(){
        baseTest(new int[]{1, 1, 2}, new int[]{1, 2});
    }
    
    @Test
    public void test1(){
        baseTest(new int[]{1, 2, 2}, new int[]{1, 2});
    }
    
    @Test
    public void test2(){
        baseTest(new int[]{1, 2, 3}, new int[]{1, 2, 3});
    }
    
    @Test
    public void test3(){
        baseTest(new int[]{1, 1}, new int[]{1});
    }

    private void baseTest(int[] input, int[] expected) {
        int newLen = removeDup(input);
        assertEquals(expected.length, newLen);
        for(int i = 0; i < newLen; i++)
            assertEquals(input[i], expected[i]);
    }
}
