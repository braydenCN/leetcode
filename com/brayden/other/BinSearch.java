package com.brayden.other;

import static org.junit.Assert.*;

import org.junit.Test;

public class BinSearch {

    public <T extends Comparable<T>> int binSearch(T[] arr, T target){
        int low = 0, high = arr.length - 1, mid;
        while(low <= high){
            mid = ((high - low) >> 1) + low;
            if(arr[mid].compareTo(target) == 0)
                return mid;
            if(arr[mid].compareTo(target) > 0)
                high = mid - 1;
            else 
                low = mid + 1;
        }
        return -1;
    }
    
    @Test
    public void test(){
        Integer[] input = new Integer[]{Integer.MIN_VALUE, -1, 0, 3, 3, 3, Integer.MAX_VALUE};
        assertEquals(0, binSearch(input, Integer.MIN_VALUE));
        assertEquals(1, binSearch(input, -1));
        assertEquals(2, binSearch(input, 0));
        int ind = binSearch(input, 3);
        assertTrue(ind == 3 || ind == 4 || ind == 5);
        assertEquals(6, binSearch(input, Integer.MAX_VALUE));
        assertEquals(-1, binSearch(input, 2));
    }
    
}
