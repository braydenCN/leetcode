package com.brayden;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

public class SpiralMatrix extends LeetcodeCommon {
    
    private int[][] matrix;
    private int m;
    private int n;
    private List<Integer> resultList;
    
    public List<Integer> spiralOrder(int[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return Collections.emptyList();
        
        this.matrix = matrix;
        this.m = matrix.length;
        this.n = matrix[0].length;
        resultList = new ArrayList<>();
        spiralOrderInternal(0);
        return resultList;
    }
    
    private void spiralOrderInternal(int begin){
        if(begin > (m - 1) / 2 || begin > (n - 1) / 2)
            return;
        
        for(int i = begin; i <= n - begin - 1; i++)
            resultList.add(matrix[begin][i]);
        for(int i = begin + 1; i <= m - begin - 1; i++)
            resultList.add(matrix[i][n - begin - 1]);

        if(begin * 2 + 1 == m || begin * 2 + 1 == n)
            return;
        
        for(int i = n - begin - 2; i >= begin; i--)
            resultList.add(matrix[m - begin - 1][i]);
        for(int i = m - begin - 2; i > begin; i--)
            resultList.add(matrix[i][begin]);
        
        spiralOrderInternal(begin + 1);
    }
    
    @Test
    public void test(){
        int[][] input = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        List<Integer> expected = Arrays.asList(new Integer[]{1,2,3,6,9,8,7,4,5});
        assertListEquals(expected, spiralOrder(input));
    }
    
    @Test
    public void test4(){
        int[][] input = new int[][]{{1, 2}, {3, 4}, {5, 6}};
        List<Integer> expected = Arrays.asList(new Integer[]{1,2,4, 6, 5, 3});
        assertListEquals(expected, spiralOrder(input));
    }
    
    @Test
    public void test1(){
        int[][] input = new int[][]{{1}};
        List<Integer> expected = Arrays.asList(new Integer[]{1});
        assertListEquals(expected, spiralOrder(input));
    }
    
    @Test
    public void test2(){
        int[][] input = new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        List<Integer> expected = Arrays.asList(new Integer[]{1,2,3,4,8, 12, 11, 10, 9, 5, 6, 7});
        assertListEquals(expected, spiralOrder(input));
    }
    
    @Test
    public void test3(){
        int[][] input = new int[][]{{3}, {2}};
        List<Integer> expected = Arrays.asList(new Integer[]{3, 2});
        assertListEquals(expected, spiralOrder(input));
    }
    
    @Test
    public void test5(){
        int[][] input = new int[][]{{2,5,8},{4,0,-1}};
        List<Integer> expected = Arrays.asList(new Integer[]{2,5,8,-1,0,4});
        assertListEquals(expected, spiralOrder(input));
    }
}
