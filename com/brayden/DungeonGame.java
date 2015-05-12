package com.brayden;

import static org.junit.Assert.*;

import org.junit.Test;

public class DungeonGame {
    
    private int getLoss(int loss){
        return loss > 0 ? 0 : loss;
    }
    
    public int calculateMinimumHP(int[][] dungeon) {
        if(dungeon == null || dungeon.length == 0 || dungeon[0].length == 0)
            return 0;
        
        int m = dungeon.length;
        int n = dungeon[0].length;
        
        int lossDown, lossRight;
        int[] hpLoss   = new int[n];
        hpLoss[n - 1]  = getLoss(dungeon[m - 1][n - 1]);
        for(int i = n - 2; i >= 0; i--){
            lossRight = getLoss(hpLoss[i + 1] + dungeon[m - 1][i]);
            hpLoss[i] = lossRight;
        }
            
        for(int i = m - 2; i >= 0; i--){
            lossDown = getLoss(hpLoss[n - 1] + dungeon[i][n - 1]);
            hpLoss[n - 1] = lossDown;
            for(int j = n - 2; j >= 0; j--){
                lossDown = getLoss(hpLoss[j] + dungeon[i][j]);
                lossRight = getLoss(hpLoss[j + 1] + dungeon[i][j]);
                hpLoss[j] = Math.max(lossDown, lossRight);
            }
        }
        
        return -hpLoss[0] + 1;
    }
    
    @Test
    public void test(){
        assertEquals(1, calculateMinimumHP(new int[][]{{0}}));
    }
    
    @Test
    public void test1(){
        assertEquals(4, calculateMinimumHP(new int[][]{{-3, 5}}));
    }
    
    @Test
    public void test2(){
        assertEquals(3, calculateMinimumHP(new int[][]{{0, -1, -10}, {-2, 0, -10}, {10, 0, -2}}));
    }
    
    @Test
    public void test3(){
        assertEquals(2, calculateMinimumHP(new int[][]{{0, -1, -10}, {-2, 0, -10}, {10, 0, 0}}));
    }
}
