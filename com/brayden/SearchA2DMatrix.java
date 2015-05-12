package com.brayden;

import static org.junit.Assert.*;

import org.junit.Test;

public class SearchA2DMatrix {

    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return false;
        
        int m = matrix.length;
        int n = matrix[0].length;
        
        int low = 0, high = m * n - 1;
        while(low <= high){
            int mid = ((high - low) >> 1) + low;
            int num = matrix[mid / n][mid % n];
            if(num == target)
                return true;
            else if(num < target)
                low = mid + 1;
            else 
                high = mid - 1;
        }
        return false;
    }
    
    public boolean searchMatrix2(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return false;
        
        int low = 0, high = matrix.length - 1, mid = 0;
        while(low <= high){
            mid = ((high - low) >> 1) + low;
            int tmp = matrix[mid][0];
            if(tmp == target)
                return true;
            if(tmp < target && 
                    (mid == matrix.length - 1 || matrix[mid + 1][0] > target))
                break;
            else if(tmp > target)
                high = mid - 1;
            else 
                low = mid + 1;
        } // b-search for the row
        
        int row = mid;
        low = 0;
        high = matrix[row].length - 1;
        while(low <= high){
            mid = ((high - low) >> 1) + low;
            int tmp = matrix[row][mid];
            if(tmp == target)
                return true;
            else if(tmp < target)
                low = mid + 1;
            else //if(tmp > target)
                high = mid - 1;
        }
        return false;
    }
    
    public boolean searchMatrix1(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return false;
        
        int low = 0, high = matrix.length - 1, mid = 0;
        while(low < high){
            mid = ((high - low) >> 1) + low;
            int tmp = matrix[mid][0];
            if(tmp == target)
                return true;
            if(tmp < target){
                if(matrix[mid + 1][0] == target)
                    return true;
                else if(matrix[mid + 1][0] > target){
                    low = mid;
                    break;
                }else low = mid + 1;
            }
            if(tmp > target)
                high = mid - 1;
        } // b-search for the row
        
        int row = low;
        low = 0;
        high = matrix[row].length - 1;
        while(low <= high){
            mid = ((high - low) >> 1) + low;
            int tmp = matrix[row][mid];
            if(tmp == target)
                return true;
            if(tmp < target)
                low = mid + 1;
            if(tmp > target)
                high = mid - 1;
        }
        return false;
    }
    
    @Test
    public void test(){
        assertTrue(searchMatrix(new int[][]{{1,   3,  5,  7},
                                            {10, 11, 16, 20},
                                            {23, 30, 34, 50}}, 3));
    }
    
    @Test
    public void test1(){
        /**
Submission Result: Time Limit Exceeded
Last executed input:    [[1],[3]], 2
         */
        assertFalse(searchMatrix(new int[][]{{1}, {3}}, 2));
    }
    
    @Test
    public void test2(){
        /**
Submission Result: Wrong Answer
Input:  [[1,3,5,7],[10,11,16,20],[23,30,34,50]], 11
Output:     false
Expected:   true
         */
        assertTrue(searchMatrix(new int[][]{{1,   3,  5,  7},
                {10, 11, 16, 20},
                {23, 30, 34, 50}}, 11));
    }
}
