package com.brayden;

import static org.junit.Assert.assertEquals;

import java.util.Stack;

import org.junit.Test;

public class MaximalRectangle {
    
    public int maximalRectangle(char[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return 0;
        
        int m = matrix.length, n = matrix[0].length;
        int[] height = new int[n];
        int max = 0, ind, tp, area;
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++)
                height[j] = (matrix[i][j] == '1') ? height[j] + 1 : 0;
            ind = 0;
            while (ind < n)
                if(stack.isEmpty() || height[stack.peek()] <= height[ind])
                    stack.push(ind++);
                else{
                    tp = stack.pop();
                    area = height[tp] * 
                            (stack.isEmpty() ? ind : ind - stack.peek() - 1);
                    if (max < area)
                        max = area;
                }
     
            while(!stack.isEmpty()){
                tp = stack.pop();
                area = height[tp] * 
                        (stack.isEmpty() ? n : n - stack.peek() - 1);
                if (max < area)
                    max = area;
            }
        }
        return max;
    }
    
    private static class Point{
        int i, j;

        public Point(int i, int j) {
            this.i = i;
            this.j = j;
        }

        @Override
        public String toString() {
            return "Point [i=" + i + ", j=" + j + "]";
        }
        
    }
    
    private char[][] matrix;
    private int m, n;
    private boolean isOne(int i, int j){
        return matrix[i][j] == '1';
    }
    
    public int maximalRectangle1(char[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return 0;
        
        this.matrix = matrix;
        this.m = matrix.length;
        this.n = matrix[0].length;
        Point[] dp = new Point[n];
        int max = 0;
        for(int i = 0; i < m; i++){            
            for(int j = 0; j < n; j++)
                if(isOne(i, j)){
                    if(j == 0 || dp[j - 1] == null){
                        dp[j] = 
                            new Point((dp[j] == null) ? i : dp[j].i, j);
                        while(dp[j].i > 0 && isOne(dp[j].i - 1, j))
                            dp[j].i--;
                    }else if(dp[j] == null){
                        dp[j] = 
                            new Point(i, (dp[j - 1] == null) ? j : dp[j - 1].j);
                        while(dp[j].j > 0 && isOne(i, dp[j].j - 1))
                            dp[j].j--;
                    }else{
                        dp[j].i = Math.max(dp[j].i, dp[j - 1].i);
                        dp[j].j = Math.max(dp[j].j, dp[j - 1].j);
outter1:                while(dp[j].i > 0)
                            for(int k = dp[j].j; k <= j; k++){
                                if(!isOne(dp[j].i - 1, k))
                                    break outter1;
                                dp[j].i--;
                            }
outter2:                while(dp[j].j > 0)
                            for(int k = dp[j].i; k <= i; k++){
                                if(!isOne(k, dp[j].j - 1))
                                    break outter2;
                                dp[j].j--;
                            }
                    }
                    max = Math.max(max, (i - dp[j].i + 1) * (j - dp[j].j + 1));
                }else
                    dp[j] = null;
            }
        return max;
    }
    
    @Test
    public void test(){
        assertEquals(1, maximalRectangle(new char[][]{{'1'}}));
    }
    
    @Test
    public void test1(){
        assertEquals(2, maximalRectangle(new char[][]{{'1', '1'}}));
    }
    
    @Test
    public void test2(){
        assertEquals(2, maximalRectangle(new char[][]{{'0', '1', '1', '0'}}));
    }
    
    @Test
    public void test3(){
        assertEquals(2, maximalRectangle(new char[][]{
                {'0', '0', '0', '0'},
                {'0', '1', '1', '0'},
                {'0', '0', '0', '0'}}));
    }
    
    @Test
    public void test4(){
        assertEquals(9, maximalRectangle(new char[][]{
                {'0', '0', '0', '0', '0'},
                {'0', '0', '1', '1', '1'},
                {'0', '1', '1', '1', '1'},
                {'0', '1', '1', '1', '1'},
                {'0', '1', '1', '1', '0'}}));
    }
    
    @Test
    public void test5(){
        assertEquals(8, maximalRectangle(new char[][]{
                {'0', '0', '0', '0', '0'},
                {'0', '0', '1', '1', '0'},
                {'0', '1', '1', '1', '1'},
                {'0', '1', '1', '1', '1'},
                {'0', '0', '1', '1', '0'}}));
    }
}
