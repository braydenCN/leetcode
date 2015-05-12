package com.brayden;

import static org.junit.Assert.*;

import org.junit.Test;

public class LongestCommonPrefix {
    public String longestCommonPrefix1(String[] strs) {
        if(strs == null || strs.length == 0)
        	return "";
        
        if(strs.length == 1)
        	return strs[0];

        StringBuffer resultSb = new StringBuffer();
        String firstStr       = strs[0];
        int shortestLen       = firstStr.length();
        for(String s: strs)
        	if(s.length() < shortestLen)
        		shortestLen = s.length();
        
outter: for(int i = 0; i < shortestLen; i++){
        	char curChar = firstStr.charAt(i);
        	for(int j = 1; j < strs.length; j++)
        		if(strs[j].charAt(i) != curChar)
        			break outter;
        	resultSb.append(curChar);
        }
        return resultSb.toString();
    }
    
    public String longestCommonPrefix(String[] strs) {
        if(strs == null || strs.length == 0)
            return "";

        int len = Integer.MAX_VALUE;
        for(String s: strs)
            len = Math.min(s.length(), len);
        StringBuffer sb = new StringBuffer();
        
        for(int i = 0; i < len; i++){
            for(int j = 1; j < strs.length; j++)
                if(strs[j].charAt(i) != strs[j - 1].charAt(i))
                    return sb.toString();
            sb.append(strs[0].charAt(i));
        }
        return sb.toString();
    }
    
    @Test
    public void test(){
    	String[] arr = {"aca", "cba"};
    	assertEquals("", longestCommonPrefix(arr));
    	arr = new String[]{};
    	assertEquals("", longestCommonPrefix(arr));
    	arr = new String[]{"aaaa", "aabb", "aacc"};
    	assertEquals("aa", longestCommonPrefix(arr));
    }
    
    /**
     * To improve, no need to compute shortestLen; no need for a SB
     */
}
