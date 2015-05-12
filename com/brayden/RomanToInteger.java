package com.brayden;

import static org.junit.Assert.*;

import org.junit.Test;

public class RomanToInteger {
    public int romanToInt(String s) {
        if(s == null || s.length() == 0)
        	return 0;
        
        int result = 0, pre = 10000, prefix = 0;
        for(char c: s.toCharArray()){
            int val = val(c);
            if(pre == val)
                prefix += val;
            else if(pre < val){
                result += (val - prefix);
                prefix = 0;
            }else{ // pre > val
                result += prefix;
                prefix = val;
            }
            pre = val;
        }
        
        result += prefix;
        return result;
    }
    
    private int val(char c){
        switch(c){
        case 'I': return 1;
        case 'V': return 5;
        case 'X': return 10;
        case 'L': return 50;
        case 'C': return 100;
        case 'D': return 500;
        case 'M': return 1000;
        }
        return -1;
    }
    
    @Test
    public void test(){
        assertEquals(1800, romanToInt("MDCCC"));
    }
    
    @Test
    public void test1(){
        assertEquals(890, romanToInt("DCCCXC"));
    }
    
    @Test
    public void test2(){
        assertEquals(74, romanToInt("LXXIV"));
    }
    
    @Test
    public void test3(){
        assertEquals(88, romanToInt("LXXXVIII"));
    }
    
    @Test
    public void test4(){
        assertEquals(19, romanToInt("XIX"));
    }
    
    @Test
    public void test5(){
        assertEquals(15, romanToInt("XV"));
    }
    
    @Test
    public void test6(){
        assertEquals(14, romanToInt("XIV"));
    }
    
    @Test
    public void test7(){
        assertEquals(952, romanToInt("CMLII"));
    }
}
