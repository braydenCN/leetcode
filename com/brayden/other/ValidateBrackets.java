package com.brayden.other;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ValidateBrackets {
    public boolean isValid(String s) {
        if(s == null)
            return true;
        
        int leftBracketCount = 0;
        for(char c: s.toCharArray())
            switch(c){
            case '(':
                leftBracketCount++; break;
            case ')':
                if(leftBracketCount <= 0)
                    return false;
                leftBracketCount--;
            }
        return leftBracketCount == 0;
    }
    
    @Test
    public void test(){
        assertTrue(isValid("abcde"));
        assertTrue(isValid("(a)"));
        assertTrue(isValid("$(x())"));
        assertTrue(isValid("((()))"));
        assertTrue(isValid("(()())"));
        assertFalse(isValid("("));
        assertFalse(isValid(")"));
        assertFalse(isValid("())"));
        assertFalse(isValid("(()"));
    }
}
