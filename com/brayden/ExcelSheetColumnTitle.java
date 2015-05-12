package com.brayden;

import static org.junit.Assert.*;

import org.junit.Test;

public class ExcelSheetColumnTitle {
    public String convertToTitle(int n) {
        StringBuffer sb = new StringBuffer();
        while(n > 0){
            int reminder = n % 26;
            n /= 26;
            if(reminder == 0){
                sb.insert(0, 'Z');
                n--;
            }else
                sb.insert(0, (char)('A' + reminder - 1));
        }
        return sb.toString();
    }
    
    @Test
    public void test(){
        assertEquals("A",   convertToTitle(1));
        assertEquals("Z",   convertToTitle(26));
        assertEquals("AA",  convertToTitle(26 + 1));
        assertEquals("AZ",  convertToTitle(26 * 2));
        assertEquals("BA",  convertToTitle(26 * 2 + 1));
        assertEquals("ZZ",  convertToTitle((26 + 1) * 26));
        assertEquals("AAA", convertToTitle((26 + 1) * 26 + 1));
    }
    
}
