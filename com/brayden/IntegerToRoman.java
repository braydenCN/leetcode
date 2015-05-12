package com.brayden;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class IntegerToRoman {
    public String intToRoman(int num) {
        StringBuilder sb = new StringBuilder();
        while(num >= 1000){
            sb.append("M");
            num -= 1000;
        }
        if(num >= 900){
            sb.append("CM");
            num -= 900;
        }
        if(num >= 500){
            sb.append("D");
            num -= 500;
        }
        if(num >= 400){
            sb.append("CD");
            num -= 400;
        }
        while(num >= 100){
            sb.append("C");
            num -= 100;
        }
        if(num >= 90){
            sb.append("XC");
            num -= 90;
        }
        if(num >= 50){
            sb.append("L");
            num -= 50;
        }
        if(num >= 40){
            sb.append("XL");
            num -= 40;
        }
        while(num >= 10){
            sb.append("X");
            num -= 10;
        }
        if(num >= 9){
            sb.append("IX");
            num -= 9;
        }
        if(num >= 5){
            sb.append("V");
            num -= 5;
        }
        if(num >= 4){
            sb.append("IV");
            num -= 4;
        }
        while(num >= 1){
            sb.append("I");
            num -= 1;
        }
        return sb.toString();
    }
    
    @Test
    public void test(){
        assertEquals("MDCCC", intToRoman(1800));
    }
    
    @Test
    public void test1(){
        assertEquals("DCCCXC", intToRoman(890));
    }
    
    @Test
    public void test2(){
        assertEquals("LXXIV", intToRoman(74));
    }
    
    @Test
    public void test3(){
        assertEquals("LXXXVIII", intToRoman(88));
    }
    
    @Test
    public void test4(){
        assertEquals("XIX", intToRoman(19));
    }
    
    @Test
    public void test5(){
        assertEquals("XV", intToRoman(15));
    }
    
    @Test
    public void test6(){
        assertEquals("XIV", intToRoman(14));
    }
    
    @Test
    public void test7(){
        assertEquals("CMLII", intToRoman(952));
    }
    
    @Test
    public void test8(){
        assertEquals("CD", intToRoman(400));
    }
}
