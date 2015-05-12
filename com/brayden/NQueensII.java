package com.brayden;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class NQueensII {
    
    /** updated solution **/
    public int totalNQueens(int n) {
        if(n == 1)
            return 1;
        if(n < 4)
            return 0;
        return totalNQueensInt(new boolean[n][n], 0);
    }
    
    private int totalNQueensInt(boolean[][] board, int row) {
        int result = 0, n = board.length;
        if(row == n)
            return 1;
        for(int col = 0; col < n; col++){
            board[row][col] = true;
            if(valid(board, row, col))
                result += totalNQueensInt(board, row + 1);
            board[row][col] = false;
        }
        return result; 
    }

    private boolean valid(boolean[][] board, int row, int col) {
        int n = board.length;
        for(int i = 0; i < row; i++)
            if(board[i][col])
                return false;
        for(int i = 1; row - i >= 0 && col - i >= 0; i++)
            if(board[row - i][col - i])
                return false;
        for(int i = 1; row - i >= 0 && col + i < n; i++)
            if(board[row - i][col + i])
                return false;
        return true;
    }

    /** original solution **/
    public int totalNQueens1(int n) {
        if(n == 1)
            return 1;
        if(n < 4)
            return 0;
        return totalNQueensInternal1(new boolean[n][n], 0);
    }
    
    private int totalNQueensInternal1(boolean[][] board, int row) {        
        int len = board.length;
        int result = 0;
        
        for(int j = 0; j < len; j++)
            if(!board[row][j]){
                if(row == len - 1)
                    return 1;
                boolean[][] newBoard = copy(board);
                addQueen(newBoard, row, j);
                result += totalNQueensInternal1(newBoard, row + 1);
             }
        return result;
    }
    
    private void addQueen(boolean[][] board, int i, int j) {
        int len = board.length;
        
        for(int a = 0; a < len; a++){
            board[i][a] = true;
            board[a][j] = true;
        }
        
        for(int a = 1; i - a >= 0 && j - a >= 0; a++)
            board[i - a][j - a] = true;
        for(int a = 1; i + a < len && j + a < len; a++)
            board[i + a][j + a] = true;
        for(int a = 1; i + a < len && j - a >= 0; a++)
            board[i + a][j - a] = true;
        for(int a = 1; i - a >= 0 && j + a < len; a++)
            board[i - a][j + a] = true;
    }
    
    private boolean[][] copy(boolean[][] board) {
        int len = board.length;
        boolean[][] newBoard = new boolean[len][len];
        for(int i = 0; i < len; i++)
            System.arraycopy(board[i], 0, newBoard[i], 0, len);
        return newBoard;
    }
   
    @Test
    public void test(){
        assertEquals(2, totalNQueens(4));
        assertEquals(92, totalNQueens(8));
        
        totalNQueens(14); // it takes 35 s
        totalNQueens(15);  // it takes 240 s
    }
}
