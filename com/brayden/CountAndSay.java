package com.brayden;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class CountAndSay {
    
    private static List<String> casList = new ArrayList<>();
    static{
        casList.add("1");
    }
    
    public String countAndSay(int n) {
        return countAndSayInternal(n);
    }
    
    private String countAndSayInternal(int n) {
        if(casList.size() >= n)
            return casList.get(n - 1);
        
        String lastStr = countAndSayInternal(n - 1);
        char curChar = lastStr.charAt(0), tmpChar;
        int count = 1;
        StringBuffer sb = new StringBuffer();
        for(int i = 1; i < lastStr.length(); i++){
            tmpChar = lastStr.charAt(i);
            if(tmpChar == curChar){
                count++;
            }else{
                sb.append(count).append(curChar);
                count = 1;
            }
            curChar = tmpChar;
        }
        sb.append(count).append(curChar);
        casList.add(sb.toString());
        return casList.get(n - 1);
    }

    @Test
    public void test(){
        CountAndSay cas = new CountAndSay();
        assertEquals("1",      cas.countAndSay(1));
        assertEquals("11",     cas.countAndSay(2));
        assertEquals("21",     cas.countAndSay(3));
        assertEquals("1211",   cas.countAndSay(4));
        assertEquals("111221", cas.countAndSay(5));
        assertEquals("312211", cas.countAndSay(6));
    }
    
    /**
     * To improve, 1. casList not needed; 2. remove recursion
     */
}
