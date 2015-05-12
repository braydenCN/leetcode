package com.brayden;

import org.junit.Test;

public class SetMatrixZeroes extends LeetcodeCommon {
    public void setZeroes(int[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return;
        
        int m = matrix.length;
        int n = matrix[0].length;
        boolean firstRowSetToZero = false;
        boolean firstColumnSetToZero = false;
        
        for(int i = 0; i < m; i++)
            if(matrix[i][0] == 0)
                firstColumnSetToZero = true;
        for(int i = 0; i < n; i++)
            if(matrix[0][i] == 0)
                firstRowSetToZero = true;
        
        for(int i = 1; i < m; i++)
            for(int j = 1; j < n; j++)
                if(matrix[i][j] == 0){
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
        
        for(int i = 1; i < m; i++)
            if(matrix[i][0] == 0)
                for(int j = 1; j < n; j++)
                    matrix[i][j] = 0;
        for(int i = 1; i < n; i++)
            if(matrix[0][i] == 0)
                for(int j = 1; j < m; j++)
                    matrix[j][i] = 0;
        
        if(firstRowSetToZero)
            for(int i = 0; i < n; i++)
                matrix[0][i] = 0;
        if(firstColumnSetToZero)
            for(int i = 0; i < m; i++)
                matrix[i][0] = 0;
    }
    
    @Test
    public void test(){
        /**
Submission Result: Wrong Answer
Input:  [[0,0,0,5],[4,3,1,4],[0,1,1,4],[1,2,1,3],[0,0,1,1]]
Output:     [[0,0,0,0],[0,0,0,0],[0,0,0,0],[0,0,0,0],[0,0,0,0]]
Expected:   [[0,0,0,0],[0,0,0,4],[0,0,0,0],[0,0,0,3],[0,0,0,0]]

[0,0,0,5]
[4,3,1,4]
[0,1,1,4]
[1,2,1,3]
[0,0,1,1]
         */
        int[][] arr      = new int[][]{{0,0,0,5},{4,3,1,4},{0,1,1,4},{1,2,1,3},{0,0,1,1}};
        int[][] expected = new int[][]{{0,0,0,0},{0,0,0,4},{0,0,0,0},{0,0,0,3},{0,0,0,0}};
        setZeroes(arr);
        assert2DimArrayEquals(expected, arr);
    }
}
