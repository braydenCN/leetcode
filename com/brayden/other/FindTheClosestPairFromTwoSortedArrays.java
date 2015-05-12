package com.brayden.other;

import static org.junit.Assert.*;

import org.junit.Test;

import com.brayden.LeetcodeCommon;

public class FindTheClosestPairFromTwoSortedArrays extends LeetcodeCommon {
    public Pair findClosestPair(int[] arr1, int[] arr2, int target){
        int len1 = arr1.length, len2 = arr2.length;
        int closest = Integer.MAX_VALUE;
        int resInd1 = 0, resInd2 = len2 - 1;
        for(int ind1 = 0, ind2 = len2 -1; ind1 < len1 && ind2 >= 0;){
            int item1 = arr1[ind1], item2 = arr2[ind2];
            int diff = Math.abs(item1 + item2 - target);
            if(diff == 0)
                return new Pair(arr1[ind1], arr2[ind2]);
            if(closest > diff){
                closest = diff;
                resInd1 = ind1;
                resInd2 = ind2;
            }
            if(item1 + item2 > target)
                ind2--;
            else
                ind1++;
        }
        return new Pair(arr1[resInd1], arr2[resInd2]);
    }
    
    @Test
    public void test(){
        assertEquals(new Pair(1, 30), 
                findClosestPair(new int[]{1, 4, 5, 7}, new int[]{10, 20, 30, 40}, 32));
    }
    
    @Test
    public void test1(){
        assertEquals(new Pair(7, 40), 
                findClosestPair(new int[]{1, 4, 5, 7}, new int[]{10, 20, 30, 40}, 50));
    }
}
