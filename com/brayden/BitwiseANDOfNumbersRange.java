package com.brayden;

import static org.junit.Assert.*;

import org.junit.Test;

public class BitwiseANDOfNumbersRange {
    public int rangeBitwiseAnd(int m, int n) {
        int result = 0;
        long base = 1;
        while(m >= base){
            if((n & base) > 0)
                result = (m & base) > 0 ? result + (int)base : 0;
            base <<= 1;
        }
        return n >= base ? 0 : result;
    }
    
    @Test
    public void test(){
        assertEquals(4, rangeBitwiseAnd(5, 7));
    }
    
    @Test
    public void test1(){
        assertEquals(0, rangeBitwiseAnd(3, 7));
    }
}
