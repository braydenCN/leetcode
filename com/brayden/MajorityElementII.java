package com.brayden;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

public class MajorityElementII {
    public List<Integer> majorityElement(int[] nums) {
        if(nums == null || nums.length == 0)
            return Collections.emptyList();
        
        int len = nums.length;
        int a = nums[0], aCount = 0, b = nums[0], bCount = 0;
         
        for(int cur: nums){
            if(cur != a)
                aCount--;
            if(cur != b)
                bCount--;
            if(aCount <= 0){
                a = cur;
                aCount = 0;
            }
            if(bCount <= 0){
                b = cur;
                bCount = 0;
            }
            if(cur == a)
                aCount++;
            if(cur == b)
                bCount++;
        }
        aCount = bCount = 0;
        for(int cur: nums){
            if(a == cur)
                aCount++;
            if(b == cur)
                bCount++;
        }
        List<Integer> list = new ArrayList<>();
        if(aCount > len / 3)
            list.add(a);
        if(bCount > len / 3 && a != b)
            list.add(b);
        
        return list;
    }
    
    @Test
    public void test(){
        List<Integer> list = majorityElement(new int[]{1, 1, 2});
        assertEquals(1, list.size());
        assertTrue(list.contains(1));
    }
    
    @Test
    public void test1(){
        List<Integer> list = majorityElement(new int[]{1, 2, 3});
        assertEquals(0, list.size());
    }
    
    @Test
    public void test2(){
        List<Integer> list = majorityElement(new int[]{1, 1, 3, 2, 2});
        assertEquals(2, list.size());
        assertTrue(list.contains(1));
        assertTrue(list.contains(2));
    }
    
    @Test
    public void test3(){
        List<Integer> list = majorityElement(new int[]{3, 2, 2, 1, 1, 1});
        assertEquals(1, list.size());
        assertTrue(list.contains(1));
    }
    
    @Test
    public void test4(){
        List<Integer> list = majorityElement(new int[]{6, 6, 6, 7, 7});
        assertEquals(2, list.size());
        assertTrue(list.contains(6));
        assertTrue(list.contains(7));
    }
    
    @Test
    public void test5(){
        List<Integer> list = majorityElement(new int[]{8, 9, 8, 9, 8});
        assertEquals(2, list.size());
        assertTrue(list.contains(8));
        assertTrue(list.contains(9));
    }
}
