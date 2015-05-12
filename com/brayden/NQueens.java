package com.brayden;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

public class NQueens {

    /** updated solution **/
    public List<String[]> solveNQueens(int n) {
        if(n == 1)
            return Collections.singletonList(new String[]{"Q"});
        if(n < 4)
            return Collections.emptyList();
        
        List<String[]> list = new ArrayList<>(); 
        solveNQueensInt(new boolean[n][n], 0, list);
        return list;
    }
    
    private void solveNQueensInt(boolean[][] board, int row, List<String[]> l){
        int n = board.length;
        if(row == n){
            addResult(board, l);
            return;
        }
        for(int col = 0; col < n; col++){
            board[row][col] = true;
            if(valid(board, row, col))
                solveNQueensInt(board, row + 1, l);
            board[row][col] = false;
        } 
    }

    private void addResult(boolean[][] board, List<String[]> l) {
        int n = board.length;
        char[] chars = new char[n];
        String[] strs = new String[n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++)
                chars[j] = board[i][j] ? 'Q' : '.';
            strs[i] = new String(chars);
        }
        l.add(strs);
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
    private List<String[]> resultList = new ArrayList<>();
    
    private void solveNQueensInternal(boolean[][] board, int row, String[] result) {        
        int len = board.length;
        
        for(int j = 0; j < len; j++)
            if(!board[row][j]){
                if(row == len - 1){
                    result[row] = getResultString(len, j);
                    resultList.add(Arrays.copyOf(result, len));
                    return;
                }
                boolean[][] newBoard = copy(board);
                addQueen(newBoard, row, j);
                result[row] = getResultString(len, j);
                solveNQueensInternal(newBoard, row + 1, result);
             }
    }

    private String getResultString(int len, int j) {
        StringBuilder sb = new StringBuilder();
        for(int a = 0; a < j; a++)
            sb.append(".");
        sb.append("Q");
        for(int a = j + 1; a < len; a++)
            sb.append(".");
        return sb.toString();
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

    public List<String[]> solveNQueens1(int n) {

        if(n == 1){
            resultList.add(new String[]{"Q"});
            return resultList;
        }
        
        if(n < 4)
            return Collections.emptyList();
        
        solveNQueensInternal(new boolean[n][n], 0, new String[n]);
        return resultList;
    }
    
    @Test
    public void test(){
        solveNQueens(4);
        resultList.clear();
        solveNQueens(8);
    }
}
