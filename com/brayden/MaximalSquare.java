package com.brayden;

import static org.junit.Assert.*;

import org.junit.Test;

public class MaximalSquare {
    
    public int maximalSquare(char[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return 0;
        int m = matrix.length, n = matrix[0].length, max = 0, pre;
        int[] dp = new int[n + 1];
        for(int i = 1; i <= m; i++){
            pre = 0;
            for(int j = 1; j <= n; j++){
                int tmp = dp[j];
                dp[j] = matrix[i - 1][j - 1] == '0' ? 0 
                    : min(dp[j - 1], dp[j], pre) + 1;
                max = Math.max(max, dp[j]);
                pre = tmp;
            }
        }
        return max * max;
    }
    
    public int maximalSquare2(char[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return 0;
        int m = matrix.length, n = matrix[0].length, max = 0;
        int[] pre = new int[n + 1], cur = new int[n + 1];
        for(int i = 1; i <= m; i++){
            for(int j = 1; j <= n; j++){
                cur[j] = matrix[i - 1][j - 1] == '0' ? 0 
                    : min(cur[j - 1], pre[j - 1], pre[j]) + 1;
                max = Math.max(max, cur[j]);
            }
            System.arraycopy(cur, 1, pre, 1, n);
        }
        return max * max;
    }
    
    public int maximalSquare1(char[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return 0;
        int m = matrix.length, n = matrix[0].length, max = 0;
        int[][] dp = new int[m + 1][n + 1];
        for(int i = 1; i <= m; i++)
            for(int j = 1; j <= n; j++){
                dp[i][j] = matrix[i - 1][j - 1] == '0' ? 0 
                    : min(dp[i - 1][j - 1], dp[i][j - 1], dp[i - 1][j]) + 1;
                max = Math.max(max, dp[i][j]);
            }
        return max * max;
    }

    private int min(int i, int j, int k) {
        return Math.min(i, Math.min(j, k));
    }
    
    @Test
    public void test(){
        assertEquals(4, maximalSquare(new char[][]{{'1', '1'}, {'1', '1'}}));
    }
}
