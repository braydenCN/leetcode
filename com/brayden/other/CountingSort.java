package com.brayden.other;

import java.util.Arrays;
import java.util.Random;

import org.junit.Test;
import static org.junit.Assert.*;

public class CountingSort {
    
    /** assume arr contains 0 - 99, inclusive **/
    public int[] countingSort(int[] arr){
        if(arr == null || arr.length <= 1)
            return arr;
        
        int[] counting = new int[100];
        for(int n: arr)
            counting[n]++;
        for(int i = 1; i < 100; i++)
            counting[i] += counting[i - 1];
        
        int[] result = new int[arr.length];
        for(int n: arr)
            result[--counting[n]] = n;
        return result;
    }
    
    @Test
    public void test(){
        int LEN = 10000000;
        int[] input = new int[LEN];
        Random r = new Random();
        for(int i = 0; i < input.length; i++)
            input[i] = r.nextInt(100);
        int[] expected = Arrays.copyOf(input, LEN);
        Arrays.sort(expected);
        input = countingSort(input);
        assertArrayEquals(expected, input);
    }
}
