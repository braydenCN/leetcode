package com.brayden;

import static org.junit.Assert.*;

import org.junit.Test;


public class FindMinimumInRotatedSortedArrayII {
    public int findMin(int[] num) {
        if(num == null || num.length == 0)
            return -1;
        
        int len = num.length;
        int low = 0, high = len - 1;
        
        if(num[low] < num[high])
            return num[0];
        
        if(num[low] == num[high]){
            do{
                low++;
             } while(low <= high && num[low] == num[0]);
            if(low > high)
                return num[0];
            if(num[low] < num[0])
                return num[low];
        }
        
        /** num[low] > num[high] now **/
        int mid = -1;
        int lowBound = low;
        int highBound = high;
        while(low <= high){
            mid = ((high - low) >> 1) + low;
//            if(mid - 1 >= 0 && num[mid - 1] > num[mid])
            if(num[mid] > num[mid + 1])
                break;
            if(num[mid] >= num[lowBound])
                low = mid + 1;
            else// if(num[mid] <= num[highBound])
                high = mid - 1;
        }
        return num[mid + 1];
    }
    
    @Test
    public void test(){
        assertEquals(1, findMin(new int[]{3, 1}));
    }
    
    @Test
    public void test1(){
        assertEquals(1, findMin(new int[]{1}));
    }
    
    @Test
    public void test2(){
        assertEquals(1, findMin(new int[]{1, 2, 3}));
    }
    
    @Test
    public void test3(){
        assertEquals(0, findMin(new int[]{1, 1, 0, 1, 1}));
    }
    
    @Test
    public void test4(){
        assertEquals(0, findMin(new int[]{1, 2, 0, 1, 1}));
    }
    
    @Test
    public void test5(){
        
        assertEquals(1, findMin(new int[]{3, 1, 1}));
    }
}
