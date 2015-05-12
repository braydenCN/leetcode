package com.brayden.other;

import static org.junit.Assert.*;

import org.junit.Test;

public class ZipString {
    public String zipString(String str){
        if(str == null || str.length() <= 1)
            return str;
        
        int len = str.length();
        char pre = str.charAt(0);
        int count = 1;
        StringBuilder sb = new StringBuilder();
        
        for(int i = 1; i < len; i++){
            char cur = str.charAt(i);
            if(pre == cur)
                count++;
            else{
                sb.append(count == 1 ? pre : "" + count + pre);
                pre = cur; 
                count = 1;
            }
        }
        sb.append(count == 1 ? pre : "" + count + pre);
        return sb.toString();
    }
    
    @Test
    public void test(){
        assertEquals("3ab",   zipString("aaab"));
        assertEquals("a3b",   zipString("abbb"));
        assertEquals("3ab3a", zipString("aaabaaa"));
        assertEquals("a3ba",  zipString("abbba"));
        assertEquals("",      zipString(""));
        assertEquals("a",     zipString("a"));
        assertEquals("ab",    zipString("ab"));
    }
}
