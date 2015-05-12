package com.brayden;

import static org.junit.Assert.*;

import org.junit.Test;

public class ZigZagConversion {
    public String convert1(String s, int nRows) {
        if(s == null || s.isEmpty() || nRows <= 1)
            return s;
        
        int len = s.length();
        StringBuffer sb = new StringBuffer();
        int base = 2 * (nRows - 1);
        /** compute row 0 **/
        for(int j = 0; j < len; j += base)
            sb.append(s.charAt(j));
        
        for(int i = 1; i < nRows - 1; i++){
            boolean zigzag = true;
            for(int j = i; j < len; ){
                sb.append(s.charAt(j));
                j += (zigzag ? base - 2 * i : 2 * i);
                zigzag = !zigzag;
            }
        }

        /** compute row nRows-1 **/
        for(int j = nRows - 1; j < len; j += base)
            sb.append(s.charAt(j));
        return sb.toString();
    }
    
    public String convert(String s, int nRows) {
        if(s == null || s.isEmpty() || nRows <= 1)
            return s;
        
        int len = s.length();
        StringBuffer sb = new StringBuffer();
        int base = 2 * (nRows - 1);
        
        for(int i = 0; i < nRows; i++){
            boolean zigzag = true;
            for(int j = i; j < len; ){
                sb.append(s.charAt(j));
                j += (zigzag ? base - 2 * i : 2 * i);
                zigzag = !zigzag;
            }
        }

        return sb.toString();
    }
    
    @Test
    public void test(){
        assertEquals("PAHNAPLSIIGYIR", convert("PAYPALISHIRING", 3));
        assertEquals("PAYPALISHIRING", convert("PAYPALISHIRING", 1));
        assertEquals("PYAIHRNAPLSIIG", convert("PAYPALISHIRING", 2));
        assertEquals("AB", convert("AB", 2));
    }
}
