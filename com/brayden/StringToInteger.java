package com.brayden;

import static org.junit.Assert.*;

import org.junit.Test;

public class StringToInteger {
	
    public int atoi(String str) {
        if(str == null || str.isEmpty())
        	return 0;
        
        str = str.trim();
        
        /** handle optional initial plus or minus sign **/
        int sign = 1;
        if(str.startsWith("+"))
        	str = str.substring(1);
        else if(str.startsWith("-")){
        	sign = -1;
        	str = str.substring(1);
        }

        if(str.isEmpty() || str.charAt(0) < '0' || str.charAt(0) > '9')
        	return 0; // not do conversion
        
        long result = 0;
        for(char c: str.toCharArray()){
        	if(c < '0' || c > '9') // ignore additional non-digit characters
        		break;
        	result = (result * 10 + c -'0');
        	if(sign * result > Integer.MAX_VALUE)
        		return sign * Integer.MAX_VALUE;
        	if(sign * result < Integer.MIN_VALUE)
        		return sign * Integer.MIN_VALUE;
        }
        
        return sign * (int)result;
    }
    
    @Test
    public void test(){
    	assertEquals(123,                atoi("123"));
    	assertEquals(0,                  atoi("+-123"));
    	assertEquals(10,                 atoi("010"));
    	assertEquals(0,                  atoi(" +0 123"));
    	assertEquals(-123,               atoi("-123DECE"));
    	assertEquals(123,                atoi("+123DECE"));
    	assertEquals(Integer.MAX_VALUE,  atoi("12343412341234123"));
    	assertEquals(Integer.MIN_VALUE,  atoi("-12343412341234123"));
    }
}
