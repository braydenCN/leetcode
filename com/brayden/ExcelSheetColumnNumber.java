package com.brayden;

import static org.junit.Assert.*;

import org.junit.Test;

public class ExcelSheetColumnNumber {
    public int titleToNumber(String s) {
        if(s == null || s.length() == 0)
            return 0;
        
        int result = 0;
        for(char c: s.toCharArray())
            result = result * 26 + (c - 'A' + 1);
        return result;
    }
    
    @Test
    public void test(){
        assertEquals(26, titleToNumber("Z"));
        assertEquals(27, titleToNumber("AA"));
        assertEquals(52, titleToNumber("AZ"));
        assertEquals(26 * 26 + 26, titleToNumber("ZZ"));
    }
}
