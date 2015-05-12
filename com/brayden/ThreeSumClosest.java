package com.brayden;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Random;

import org.junit.Test;

public class ThreeSumClosest {
    public int threeSumClosest1(int[] num, int target) {
        if(num == null || num.length < 3)
            return -1;
        
        int len = num.length;
        Arrays.sort(num);
        int n = num[0] + num[1] + num[2];
        if(n >= target)
            return n;
        n = num[len - 1] + num[len - 2] + num[len -3];
        if(n <= target)
            return n;
        
        int result = Integer.MAX_VALUE;
        for(int i = 0; i < len; i++){
            int low = 0, high = len - 1;
            while(low < high){
                if(low == i){
                    low++; continue;
                }
                if(high == i){
                    high--; continue;
                }
                int threeSum = num[low] + num[high] + num[i];
                if(threeSum == target)
                    return target;
                
                if(Math.abs(threeSum - target) < Math.abs(result - target))
                    result = threeSum;
                    
                if(threeSum < target)
                    low++;
                else 
                    high--;    
            }
        }
        return result;
    }
    
    public int threeSumClosest(int[] num, int target) {
        if(num == null || num.length < 3)
            return -1;
        
        int len = num.length;
        Arrays.sort(num);
        
        int result = num[0] + num[1] + num[2];
        for(int i = 0; i < len - 2; i++){
            int low = i + 1, high = len - 1;
            while(low < high){
                int sum = num[low] + num[high] + num[i];
                if(sum == target)
                    return target;
                if(Math.abs(sum - target) < Math.abs(result - target))
                    result = sum;
                if(sum < target)
                    low++;
                else 
                    high--;    
            }
        }
        return result;
    }
    
    @Test
    public void test(){
        assertEquals(2, threeSumClosest(new int[]{-1, 2, 1, -4}, 1));
    }
    
    @Test
    public void test1(){
        int[] input = new int[1000];
        Random r = new Random();
        int len = input.length;
        for(int i = 0; i < len; i++)
            input[i] = r.nextInt();
        
        int target = 1000000000;
        int result = Integer.MAX_VALUE;
        for(int i = 0; i < len; i++)
            for(int j = 0; j < len; j++)
                for(int k = 0; k < len; k++){
                    if(i == j || j == k || i == k)
                        continue;
                    int sum = input[i] + input[j] + input[k];
                    if(Math.abs(sum - target) < Math.abs(result - target))
                        result = sum;
                }
        assertEquals(result, threeSumClosest(input, target));
    }
}
