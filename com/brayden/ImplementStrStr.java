package com.brayden;

public class ImplementStrStr {
    public int strStr(String haystack, String needle) {
    	if(haystack == null || haystack == null)
    		return -1;
        return haystack.indexOf(needle);
    }
}
