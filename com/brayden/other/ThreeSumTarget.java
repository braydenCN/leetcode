package com.brayden.other;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

public class ThreeSumTarget {

    public List<int[]> threeSum1(int[] nums, int target){
        if(nums == null || nums.length < 3)
            return Collections.emptyList();
        
        Arrays.sort(nums);
        Set<Integer> set = new HashSet<>();
        set.add(nums[0]);
        List<int[]> list = new ArrayList<>();
        int len = nums.length;
        for(int i = 1; i < len - 1; i++){
            for(int j = i + 1; j < len; j++){
                int n = target - nums[i] - nums[j];
                if(set.contains(n))
                    list.add(new int[]{n, nums[i], nums[j]});
            }
            set.add(nums[i]);
        }
        
        return list;
    }
    
    public List<int[]> threeSum(int[] num, int target) {
        if(num == null || num.length < 3)
            return Collections.emptyList();
        
        List<int[]> list = new ArrayList<>();       
        Arrays.sort(num);
        int len = num.length;
        for(int i = 0; i < len - 2; i++){
            int low = i + 1, high = len - 1;
            while(low < high){
                int sum = num[i] + num[low] + num[high];
                if(sum == target){
                    list.add(new int[]{num[i], num[low], num[high]});
                    low++; high--;
                }else if(sum < target)
                    low++;
                else
                    high--;
            }
        }
        return list;
    }
    
    @Test
    public void test(){
        threeSum(new int[]{1, 2, 3, 4, 5, 6}, 9);
    }
    
    @Test
    public void test1(){
        threeSum(new int[]{1, 100, 3, 101, 5, 200}, 202);
    }
}
