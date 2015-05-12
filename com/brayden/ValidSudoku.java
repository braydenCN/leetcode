package com.brayden;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class ValidSudoku extends LeetcodeCommon {
	
    private char[] charArr = new char[9];
    private boolean[] validationArrs = new boolean[10];

    public boolean isValidSudoku(char[][] board) {
        if(board.length != 9 || board[0].length != 9)
        	return false;
        
        for(int i = 0; i < 9; i++)
        	if(!validOneRow(board, i))
        		return false;
        
        for(int i = 0; i < 9; i++)
        	if(!validOneColumn(board, i))
        		return false;
        
        for(int i = 0; i < 9; i += 3)
        	for(int j = 0; j < 9; j += 3)
        		if(!validOneSubBox(board, i, j))
        			return false;
        
        return true;
    }

	private boolean validOneSubBox(char[][] board, int i, int j) {
		for(int i1 = 0;  i1 < 3; i1++)
			for(int j1 = 0; j1 < 3; j1++)
				charArr[i1 * 3 + j1] = board[i + i1][j + j1];
		return validCharArr(charArr);
	}

	private boolean validOneColumn(char[][] board, int i) {
		for(int j = 0; j < 9; j++)
			charArr[j] = board[i][j];
		return validCharArr(charArr);
	}

	private boolean validOneRow(char[][] board, int i) {
		for(int j = 0; j < 9; j++)
			charArr[j] = board[j][i];
		return validCharArr(charArr);
	}

	private boolean validCharArr(char[] charArr) {
		Arrays.fill(validationArrs, false);
		for(char c: charArr){
			if(c < '1' || c > '9')
				continue;
			if(validationArrs[c - '0'])
				return false;
			validationArrs[c - '0'] = true;
		}
		return true;
	}
	
	@Test
	public void test(){
		ValidSudoku vs = new ValidSudoku();
		char[][] sudoku = get2DimCharArrayFromStringArr(
				new String[]{"..5.....6",
						     "....14...",
						     ".........",
						     ".....92..",
						     "5....2...",
						     ".......3.",
						     "...54....",
						     "3.....42.",
						     "...27.6.."}
				);
		assertTrue(vs.isValidSudoku(sudoku));
	}
    
}
