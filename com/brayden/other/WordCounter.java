package com.brayden.other;

import static org.junit.Assert.*;

import org.junit.Test;

public class WordCounter {

    public int countWords1(String str){
        if(str == null || str.length() == 0)
            return 0;
        boolean preNotWB = false;
        int count = 0;
        for(char c: str.toCharArray()){
            if(preNotWB && c == ' ')
                preNotWB = false;
            if(!preNotWB && c != ' '){
                count++; // only count if pre is WB, cur is not WB
                preNotWB = true;
            }
        }
        return count;
    }
    
    public int countWords(String str){
        if(str == null || str.length() == 0)
            return 0;

        int count = 0, len = str.length();
        if(str.charAt(0) != ' ')
            count++;
        for(int i = 1; i < len; i++)
            if(str.charAt(i - 1) == ' ' && str.charAt(i) != ' ')
                count++;
            
        return count;
    }
    
    @Test
    public void test(){
        assertEquals(0, countWords(" "));
        assertEquals(1, countWords("a"));
        assertEquals(1, countWords("abc"));
        assertEquals(2, countWords("a b"));
        assertEquals(2, countWords(" a b"));
        assertEquals(2, countWords("a b "));
        assertEquals(2, countWords(" a  b "));
        assertEquals(2, countWords("aaa  bbb"));
    }
}
