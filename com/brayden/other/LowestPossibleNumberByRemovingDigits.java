package com.brayden.other;

import static org.junit.Assert.*;

import org.junit.Test;

public class LowestPossibleNumberByRemovingDigits {

    public String removeDigits(String s, int n){
        if(n == 0)
            return s;
        if(n >= s.length())
            return "";
        
        int len = s.length();
        for(int i = 1; i < len; i++)
            if(s.charAt(i) < s.charAt(i - 1))
                return removeDigits(s.substring(0, i - 1) + s.substring(i), 
                        n - 1);
        
        return removeDigits(s.substring(1), n - 1);
    }
    
    @Test
    public void test(){
        assertEquals("2043", removeDigits("4325043", 3));
        assertEquals("0221", removeDigits("765028321", 5));
        assertEquals("1118", removeDigits("121198", 2));
        assertEquals("", removeDigits("121198", 8));
    }
}
