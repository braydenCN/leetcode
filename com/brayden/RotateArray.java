package com.brayden;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class RotateArray extends LeetcodeCommon {
    public void rotate(int[] nums, int k) {
        if(nums == null || nums.length == 0 || k < 1)
            return;
        
        int len = nums.length;
        k %= len;
        int gcd = gcd(len, k);
        for(int i = 0; i < gcd; i++){
            int pre = nums[i], cur, curInd;;
            for(int j = 1; j <= len / gcd; j++){
                curInd = (i + j * k) % len;
                cur = nums[curInd];
                nums[curInd] = pre;
                pre = cur;
            }
        }
    }

    @Test
    public void test1(){
        int[] expected = new int[]{5,6,7,1,2,3,4};
        int[] input = new int[]{1,2,3,4,5,6,7};
        rotate(input, 3);
        assertArrayEquals(expected, input);
    }
    
    private void test(int n, int k){
        int[] input = new int[n];
        for(int i = 0; i < n; i++)
            input[i] = i;
        
        int[] expected = new int[n];
        for(int i = 0; i < n; i++)
            expected[(i + k) % n] = input[i];
        

        rotate(input, k);
        
        assertArrayEquals(expected, input);
    }
    
    @Test
    public void test2(){
        test(15, 6);
        test(15, 100);
    }
    
}
