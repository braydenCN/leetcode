package com.brayden;

import org.junit.Test;

public class RotateImage {
    public void rotate(int[][] matrix) {
        if(matrix == null || matrix.length == 0 || 
                matrix[0].length != matrix.length)
            return;
        
        int n = matrix.length;
        for(int i = 0; i < n / 2; i++){ // start node; from outside to inside
            for(int j = i; j < n - i - 1; j++){  // end node
                int tmp                      =   matrix[i][j];
                matrix[i][j]                 =   matrix[n - j - 1][i];
                matrix[n - j - 1][i]         =   matrix[n - i - 1][n - j - 1];
                matrix[n - i - 1][n - i - 1] =   matrix[j][n - i - 1];
                matrix[i][j]                 =   tmp;
            }
        }
    }
    
    @Test
    public void test(){
        /**
Submission Result: Wrong Answer
Input:  [[1,2],[3,4]]
Output:     [[1,2],[4,2]]
Expected:   [[3,1],[4,2]]
         */
    }
}
