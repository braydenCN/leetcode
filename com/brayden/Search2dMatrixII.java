package com.brayden;

public class Search2dMatrixII {
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return false;
        
        int m = matrix.length, n = matrix[0].length;
        for(int i = 0, j = n - 1; i < m && j >= 0; i++, j--){
            if(matrix[i][j] == target)
                return true;
            if(matrix[i][j] < target)
                i++;
            else 
                j--;
        }
        return false;
    }
}
