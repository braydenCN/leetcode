package com.brayden;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class SudokuSolver extends LeetcodeCommon {
    public void solveSudoku(char[][] board) {
        if(board == null || board.length != 9 || board[0].length != 9)
            return;
        
        solveSudokuInt(board, 0);
    }

    private boolean solveSudokuInt(char[][] board, int row) {
        for(int i = row; i < 9; i++)
            for(int j = 0; j < 9; j++)
                if(board[i][j] == '.'){
                    for(char val: possibleVal(board, i, j)){
                        board[i][j] = val;
                        if(solveSudokuInt(board, 
                                j == 8 ? row + 1 : row))
                            return true;
                    }
                    /** backtracking **/
                    board[i][j] = '.';
                    return false;
                }
        return true; // all settled
    }
    
    private final static Set<Character> template = new HashSet<>();
    static{
        for(char c = '1'; c <= '9'; c++)
            template.add(c);
    }
    private Set<Character> possibleVal(char[][] board, int row, int col) {
        Set<Character> set = new HashSet<>(template);
        for(int i = 0; i < 9; i++){
            if(board[row][i] != '.')
                set.remove(board[row][i]);
            if(board[i][col] != '.')
                set.remove(board[i][col]);
        }
        
        int startRow = 3 * (row / 3);
        int startCol = 3 * (col / 3);
        for(int i = 0; i < 3; i++)
            for(int j = 0; j < 3; j++){
                char c = board[startRow + i][startCol + j];
                if(c != '.')
                    set.remove(c);
            }
        return set;
    }
    
    @Test
    public void test(){
        char[][] input = 
                new char[][]{
                {'5','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}};
        solveSudoku(input);
        char[][] expected =
                new char[][]{
                {'5','3','4','6','7','8','9','1','2'},
                {'6','7','2','1','9','5','3','4','8'},
                {'1','9','8','3','4','2','5','6','7'},
                {'8','5','9','7','6','1','4','2','3'},
                {'4','2','6','8','5','3','7','9','1'},
                {'7','1','3','9','2','4','8','5','6'},
                {'9','6','1','5','3','7','2','8','4'},
                {'2','8','7','4','1','9','6','3','5'},
                {'3','4','5','2','8','6','1','7','9'}};

        assert2DimArrayEquals(expected, input);
    }
}
