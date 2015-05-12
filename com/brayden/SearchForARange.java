package com.brayden;

import org.junit.Test;
import static org.junit.Assert.*;

public class SearchForARange extends LeetcodeCommon {
    public int[] searchRange(int[] A, int target) {
        int[] notFound = new int[]{-1, -1};
        if(A == null || A.length == 0)
            return notFound;
        
        int first = findFirst(A, target);
        if(first == -1)
            return notFound;
        return new int[]{first, findLast(A, target, first, A.length - 1)};
    }
    
    @Test
    public void test(){
        assertArrayEquals(new int[]{3, 4}, 
                searchRange(new int[]{5, 7, 7, 8, 8, 10}, 8));
    }
    
    @Test
    public void test1(){
        assertArrayEquals(new int[]{0, 0}, 
                searchRange(new int[]{1}, 1));
    }
    
    @Test
    public void test2(){
        assertArrayEquals(new int[]{0, 0}, 
                searchRange(new int[]{1, 2, 3}, 1));
    }
}
