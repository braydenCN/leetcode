package com.brayden;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class FractionToRecurringDecimal {
    public String fractionToDecimal(int numerator, int denominator) {
        if(denominator == 0)
            throw new IllegalArgumentException();
        
        long numeratorL = numerator;
        long denominatorL = denominator;
        boolean negative = numeratorL * denominatorL < 0;
        numeratorL = Math.abs(numeratorL);
        denominatorL = Math.abs(denominatorL);
        long intPart = (int) (numeratorL / denominatorL);
        numeratorL %= denominatorL;
        if(numeratorL == 0)
            return (negative ? "-" : "") + String.valueOf(intPart);
       
        List<Long> list = new ArrayList<>();
        Map<Long, Integer> numIndMap = new HashMap<>();
        int ind = 0;
        while(true){
            if(numIndMap.containsKey(numeratorL))
                return getStr(intPart, list, negative, numIndMap.get(numeratorL));
            numIndMap.put(numeratorL, ind++);
            numeratorL *= 10;
            list.add(numeratorL / denominatorL);
            numeratorL %= denominatorL;
            if(numeratorL == 0)
                return getStr(intPart, list, negative);
        }
    }

    private String getStr(long intPart, List<Long> list, 
            boolean negative, int recurringInd) {
        StringBuilder sb = new StringBuilder();
        if(negative)
            sb.append("-");
        sb.append(intPart).append(".");
        for(int i = 0; i < list.size(); i++){
            if(i == recurringInd)
                sb.append("(");
            sb.append(list.get(i));
        }
        if(recurringInd != -1)
            sb.append(")");
        return sb.toString();
    }

    private String getStr(long intPart, List<Long> list, boolean negative) {
        return getStr(intPart, list, negative, -1);
    }
    
    @Test
    public void test(){
        assertEquals("0.5",    fractionToDecimal(1, 2));
        assertEquals("2",      fractionToDecimal(2, 1));
        assertEquals("0.(6)",  fractionToDecimal(2, 3));
        assertEquals("0.(09)", fractionToDecimal(1, 11));
        assertEquals("1.(09)", fractionToDecimal(12, 11));
    }
    
    @Test
    public void test1(){
        assertEquals("-6.25", fractionToDecimal(-50, 8));
    }
    
    @Test
    public void test2(){
        assertEquals("-0.58(3)", fractionToDecimal(7, -12));
    }
    
    @Test
    public void test3(){
        /**
Submission Result: Wrong Answer
Input:  -1, -2147483648
Output:     "-0.0000000000000000000000000000001"
Expected:   "0.0000000004656612873077392578125"
         */
        
        /**
Input:  -2147483648, 1
Output:     "2147483648"
Expected:   "-2147483648"
         */
        assertEquals("-2147483648", fractionToDecimal(-2147483648, 1));
    }
        
}
