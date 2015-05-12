package com.brayden;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * https://oj.leetcode.com/problems/reverse-words-in-a-string/
 * <p>
 * Given an input string, reverse the string word by word. 
 * 
 * @author pengczha
 *
 */
public class ReverseWordsInString {
    public String reverseWords(String s) {
        if(s == null || s.isEmpty())
            return "";
        
        String[] strArr = s.trim().split(" +");
        StringBuffer sb = new StringBuffer();
        for(int i = strArr.length - 1; i > 0; i--)
            sb.append(strArr[i]).append(" ");
        sb.append(strArr[0]);
        return sb.toString();
    }
    
    @Test
    public void test(){
        assertEquals("blue is sky the", reverseWords("the sky  is  blue"));
    }
}
