package com.brayden;

import static org.junit.Assert.*;

import org.junit.Test;

public class HouseRobber {
    public int rob1(int[] num) {
        if(num == null || num.length == 0)
            return 0;
        int len = num.length;
        if(len == 1)
            return num[0];

        int[] dp = new int[len];
        dp[0] = num[0];
        dp[1] = Math.max(num[0],  num[1]);
        for(int i = 2; i < len; i++)
            dp[i] = Math.max(dp[i - 2] + num[i], dp[i - 1]);
        
        return dp[len - 1];
    }
    
    public int rob2(int[] num) {
        if(num == null || num.length == 0)
            return 0;
        int len = num.length;
        if(len == 1)
            return num[0];

        int prePre = num[0];
        int pre = Math.max(num[0],  num[1]);
        for(int i = 2; i < len; i++){
            int cur = Math.max(prePre + num[i], pre);
            prePre = pre;
            pre = cur;
        }
        
        return pre;
    }
    
    public int rob(int[] num) {
        if(num == null || num.length == 0)
            return 0;
        int pre = 0, cur = 0;
        for(int n: num){
            int next = Math.max(pre + n, cur);
            pre = cur;
            cur = next;
        }
        return cur;
    }
    
    @Test
    public void test(){
        assertEquals(2, rob(new int[]{1, 2}));
        assertEquals(3, rob(new int[]{1, 2, 2}));
        assertEquals(4, rob(new int[]{1, 2, 1, 2}));
        assertEquals(5, rob(new int[]{1, 2, 4, 2}));
    }
}
