package com.brayden;

import static org.junit.Assert.*;

import org.junit.Test;

public class MultiplyStrings {
    public String multiply(String num1, String num2) {
        if(num1 == null || num2 == null ||
                num1.length() == 0 || num2.length() == 0)
            return "";
        
        num1 = reverse(num1);
        num2 = reverse(num2);
        String result = "0";
        for(int i = 0; i < num1.length(); i++){
            String tmp = multiply(num2, num1.charAt(i));
            for(int j = 0; j < i; j++)
                tmp = multiply10(tmp);
            result = add(result, tmp);
        }
        
        result = removeLeadingZero(result);
        
        return reverse(result);
    }
    
    private String multiply10(String num) {
        return "0" + num;
    }
    
    private String multiply(String num1, char c) {
        int carry = 0;
        int toMul = num(c);
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < num1.length(); i++){
            int n1 = num(num1.charAt(i));
            int tmp = n1 * toMul + carry;
            carry = tmp / 10;
            sb.append(tmp % 10);
        }
        if(carry > 0)
            sb.append(carry);
        return sb.toString();
    }
    
    private String removeLeadingZero(String num){
        return num.replaceAll("^0+(?<=\\d)$", "");
    } 
    
    private String add(String num1, String num2){
        int carry = 0;
        int len1 = num1.length();
        int len2 = num2.length();
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < Math.max(len1, len2); i++){
            int n1 = i < len1 ? num(num1.charAt(i)) : 0;
            int n2 = i < len2 ? num(num2.charAt(i)) : 0;
            int tmp = n1 + n2 + carry;
            carry = tmp / 10;
            sb.append(tmp % 10);
        }
        if(carry == 1)
            sb.append(1);
        return sb.toString();
    }
    
    private String reverse(String s) {
        return new StringBuffer(s).reverse().toString();
    }
    
    private int num(char c){
        return Character.getNumericValue(c);
    }
    
    @Test
    public void test(){
        assertEquals("1024", multiply("64", "16"));
        assertEquals("0",    multiply("64", "0"));
    }

    @Test
    public void test1(){
        /**
Submission Result: Time Limit Exceeded
Last executed input:    "6964594125027226699998128707627435007621143975736747759751", "333791918659904899647584436187733004125181273682766434"
        */
        assertEquals("2324725235680339589575434145174345450376468286967007130548581359508676923461769510883584014763890133705678997934",    
                multiply("6964594125027226699998128707627435007621143975736747759751", 
                "333791918659904899647584436187733004125181273682766434"));
    }
}
