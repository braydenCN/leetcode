package com.brayden;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class IsomorphicStrings {
    public boolean isIsomorphic(String s, String t) {
        if(s == null)
            return t == null;
        if(s.isEmpty())
            return t.isEmpty();
        
        Map<Character, Character> map = new HashMap<>(), 
                mapR = new HashMap<>();
        for(int i = 0; i < s.length(); i++){
            char cS = s.charAt(i), cT = t.charAt(i);
            map.putIfAbsent(cS, cT);
            mapR.putIfAbsent(cT, cS);
            if(map.get(cS) != cT || mapR.get(cT) != cS)
                return false;
        }
        return true;
    }
    
    @Test
    public void test(){
       assertTrue(isIsomorphic("", ""));
       assertTrue(isIsomorphic("egg", "add"));
       assertFalse(isIsomorphic("foo", "bar"));
       assertFalse(isIsomorphic("ab", "aa"));
       assertTrue(isIsomorphic("paper", "title"));
    }
}
