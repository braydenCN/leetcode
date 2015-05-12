package com.brayden;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.Queue;

import org.junit.Test;

import com.brayden.LeetcodeCommon.Point;

public class NumberOfIslands {
    
    private char[][] g = null;
    private boolean[][] visited = null;
    private int m, n;
    private int count = 0;
   
    public int numIslands(char[][] grid) {
        if((g = grid) == null || (m = grid.length) == 0 || 
                (n = grid[0].length) == 0)
            return 0;
        
        this.count = 0;
        visited = new boolean[m][n];
        
        for(int i = 0; i < m; i++)
            for(int j = 0; j < n; j++)
                if(grid[i][j] == '1' && !visited[i][j]){
                    dsf(i, j);
                    count++;
                }
        
        return count;
    }
    
    
    private void dsf(int i, int j) {
        if(i < 0 || i >= m || j < 0 || j >= n || 
                g[i][j] == '0' || visited[i][j])
            return;
        
        visited[i][j] = true;
        dsf(i - 1, j);
        dsf(i + 1, j);
        dsf(i, j - 1);
        dsf(i, j + 1);
    }


    public int numIslands1(char[][] grid) {
        if((g = grid) == null || (m = grid.length) == 0 || 
                (n = grid[0].length) == 0)
            return 0;
        
        this.count = 0;
        visited = new boolean[m][n];
        
        for(int i = 0; i < m; i++)
            for(int j = 0; j < n; j++){
                if(grid[i][j] == '0' || visited[i][j])
                    continue;
                visited[i][j] = true;
                bsf(i, j);
                count++;
            }
        
        return count;
    }

    private void bsf(int i, int j) {
        Queue<Point> searchQ = new LinkedList<>();
        searchQ.offer(new Point(i, j));
        while(!searchQ.isEmpty()){
            int size = searchQ.size();
            for(int k = 0; k < size; k++){
                Point p = searchQ.poll();
                int x = p.x, y = p.y;
                if(x != 0 && !visited[x - 1][y] && g[x - 1][y] == '1'){
                    visited[x - 1][y] = true;
                    searchQ.offer(new Point(x - 1, y));
                }
                if(y != 0 && !visited[x][y - 1] && g[x][y - 1] == '1'){
                    visited[x][y - 1] = true;
                    searchQ.offer(new Point(x, y - 1));
                }
                if(x != m - 1 && !visited[x + 1][y] && g[x + 1][y] == '1'){
                    visited[x + 1][y] = true;
                    searchQ.offer(new Point(x + 1, y));
                }
                if(y != n - 1 && !visited[x][y + 1] && g[x][y + 1] == '1'){
                    visited[x][y + 1] = true;
                    searchQ.offer(new Point(x, y + 1));
                }
            }
        }
    }
    
    @Test
    public void test(){
        assertEquals(1, 
                numIslands(new char[][]{{'1','1','1','1','0'},
                                        {'1','1','0','1','0'},
                                        {'1','1','0','0','0'},
                                        {'0','0','0','0','0'}}));
    }
    
    @Test
    public void test1(){
        assertEquals(3, 
                numIslands(new char[][]{{'1','1','0','0','0'},
                                        {'1','1','0','0','0'},
                                        {'0','0','1','0','0'},
                                        {'0','0','0','1','1'}}));
    }
    
    @Test
    public void test2(){
        assertEquals(1, 
                numIslands(new char[][]{{'1','1','0','1','1'},
                                        {'1','1','0','0','1'},
                                        {'0','1','0','1','1'},
                                        {'0','1','1','1','1'}}));
    }
}
