package com.brayden;

import static org.junit.Assert.*;

import org.junit.Test;

public class LengthOfLastWord {
	
    public int lengthOfLastWord(String s) {
    	if(s == null || s.equals(""))
    		return 0;
    	
    	s = s.trim(); // "a " should be 1, sigh...
    	
        int indOfLastWB = s.lastIndexOf(" ");
        if(indOfLastWB == -1)
        	return s.length();
        return s.substring(indOfLastWB + 1).length();
    }
    
    @Test
    public void test(){
    	LengthOfLastWord lolw = new LengthOfLastWord();
    	assertEquals(3, lolw.lengthOfLastWord("aaa bbb"));
    	assertEquals(6, lolw.lengthOfLastWord("aaabbb "));
    	assertEquals(6, lolw.lengthOfLastWord("aaabbb"));
    	assertEquals(0, lolw.lengthOfLastWord(null));
    	assertEquals(0, lolw.lengthOfLastWord(""));
    }
    
    /**
     * To improve, this works: return s.length() - indOfLastWB - 1
     */
}
