package com.brayden;

import org.junit.Test;

public class SpiralMatrixII extends LeetcodeCommon {
    public int[][] generateMatrix(int n) {
        if(n <= 0)
            return new int[][]{};
        
        int[][] matrix = new int[n][n];
        int count = 0;
        for(int i = 0; i < (n + 1) / 2; i++){
            for(int j = i; j <= n - i - 1; j++)
                matrix[i][j] = ++count;
            for(int j = i + 1; j <= n - i - 1; j++)
                matrix[j][n - i - 1] = ++count;
            for(int j = n - i - 2; j >= i; j--)
                matrix[n - i - 1][j] = ++count;
            for(int j = n - i - 2; j > i; j--)
                matrix[j][i] = ++count;
        }
            
        return matrix;
    }
    
    @Test
    public void test(){
        int[][] expected = new int[][]{{1, 2, 3}, {8, 9, 4}, {7, 6, 5}};
        assert2DimArrayEquals(expected, generateMatrix(3));
        
        expected = new int[][]{{1, 2}, {4, 3}};
        assert2DimArrayEquals(expected, generateMatrix(2));
        
        expected = new int[][]{{1, 2, 3, 4}, {12, 13, 14, 5}, {11, 16, 15, 6}, {10, 9, 8, 7}};
        assert2DimArrayEquals(expected, generateMatrix(4));
    }
}
