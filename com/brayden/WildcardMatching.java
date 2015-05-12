package com.brayden;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class WildcardMatching {
    
    private String  s;
    private String  p;
    private int     sLen;
    private int     pLen;
    Map<Long, Boolean> cache = new HashMap<>();
    
    long key(int sInd, int pInd){
        return sInd * pLen + pInd;
    }
    
    public boolean isMatch(String s, String p) {
        if(s == null || s.isEmpty())
            return false;
        if(p == null || p.isEmpty())
            return true;
        
        this.p       =  p;
        this.s       =  s;
        this.pLen    =  p.length();
        this.sLen    =  s.length();
        
        int ind = p.lastIndexOf("*");
        if(ind != -1){
            int pEnd = pLen - 1, sEnd = sLen - 1;
            while(pEnd > ind && sEnd >= 0){
                char pChar = p.charAt(pEnd);
                char sChar = s.charAt(sEnd);
                if(pChar != '?' && pChar != sChar)
                    return false;
                pEnd--; sEnd--;
            }
            if(pEnd > ind && sEnd < 0)
                return false;
        }

        cache.clear();
        return isMatchInternal(0, 0);
    }

    private boolean isMatchInternal(int sInd, int pInd) {
        if(sInd == sLen && pInd == pLen)
            return true;
        
        if(sInd >= sLen || pInd >= pLen)
            return false;
        
        long key = key(sInd, pInd);
        if(cache.containsKey(key))
            return cache.get(key);
        
        boolean result = false;
        char pChar = p.charAt(pInd);
        char sChar = s.charAt(sInd);
        while(pChar != '*'){
            if(pChar != '?' && pChar != sChar)
                break; // result == false now
            if(sInd == sLen - 1 && pInd < pLen - 1 || 
                    pInd == pLen - 1 && sInd < sLen - 1)
                break;
            if(sInd == sLen - 1 && pInd == pLen - 1){
                result = true;
                break;
            }
            sChar = s.charAt(++sInd); 
            pChar = p.charAt(++pInd);
        }
        if(pChar == '*'){
            if(pInd == pLen - 1)
                result = true;
            else{
outter:         while(++pInd < pLen && sInd < sLen){
                    pChar = p.charAt(pInd);
                    if(pChar == '*'){ // handle "**"
                        if(pInd == pLen - 1){
                            result = true;
                            break;
                        }
                        pInd++;
                        continue;
                    }
                    if(pChar == '?'){ // handle "*?"
                        if(sInd == sLen - 1){
                            result = false;
                            break;
                        }
                        sInd++; pInd++;
                        continue;
                    }
                
                    /** handle "*[^*?]" case **/
                    int ind = sInd - 1;
                    while((ind = s.indexOf(pChar, ind + 1)) != -1)
                        if(isMatchInternal(ind + 1, pInd + 1)){
                            result = true;
                            break outter;
                        }
                }
            }
        }
        cache.put(key, result);
        return result;
    }

    @Test
    public void test(){
        assertFalse(isMatch("aa","a"));
        assertTrue(isMatch("aa","aa"));
        assertFalse(isMatch("aaa","aa"));
        assertTrue(isMatch("aa", "*"));
        assertTrue(isMatch("aa", "a*"));
        assertTrue(isMatch("ab", "?*"));
        assertFalse(isMatch("aab", "c*a*b"));
        assertFalse(isMatch("aab", "c*a*b"));
    }
    
    @Test
    public void test1(){
        /**
Submission Result: Time Limit Exceeded
Last executed input:    "abefcdgiescdfimde", "ab*cd?i*de"
         */
        assertTrue(isMatch("abefcdgiescdfimde", "ab*cd?i*de"));
    }
    
    @Test
    public void test2(){
        /**
Submission Result: Time Limit Exceeded
Last executed input:    "abbaabbbbababaababababbabbbaaaabbbbaaabbbabaabbbbbabbbbabbabbaaabaaaabbbbbbaaabbabbbbababbbaaabbabbabb", "***b**a*a*b***b*a*b*bbb**baa*bba**b**bb***b*a*aab*a**"
         */
        assertTrue(isMatch("abbaabbbbababaababababbabbbaaaabbbbaaabbbabaabbbbbabbbbabbabbaaabaaaabbbbbbaaabbabbbbababbbaaabbabbabb","***b**a*a*b***b*a*b*bbb**baa*bba**b**bb***b*a*aab*a**"));
    }
    
    @Test
    public void test3(){
        assertTrue(isMatch("abbabaaabbabbaababbabbbbbabbbabbbabaaaaababababbbabababaabbababaabbbbbbaaaabababbbaabbbbaabbbbababababbaabbaababaabbbababababbbbaaabbbbbabaaaabbababbbbaababaabbababbbbbababbbabaaaaaaaabbbbbaabaaababaaaabb", "**aa*****ba*a*bb**aa*ab****a*aaaaaa***a*aaaa**bbabb*b*b**aaaaaaaaa*a********ba*bbb***a*ba*bb*bb**a*b*bb"));
    }

    @Test
    public void test4(){
        char[] cArr = new char[69000];
        Arrays.fill(cArr, 'a');
        char[] cArr1 = new char[32000];
        Arrays.fill(cArr1, 'a');
        cArr1[0] = '*';
        cArr1[cArr1.length - 1] = '*';
        assertTrue(isMatch(String.valueOf(cArr), String.valueOf(cArr1)));
    }
    
    @Test
    public void test5(){
        assertFalse(isMatch("abbabbaaabababbaabaabbababbbbbbabaaababbabbaaabbbbbabbbbaaaaababbabaaabaaabaaabbbababbbabbabaaabaabaabbababaaabbbbaaababbabbabbabababaababbaaabbbbbbbabaaabbbabbbabaabbabaabbaaabbaabbaababaaabbbaabababaaab", "*ab**ba*a***ba******a*ba**ba**b***a*aabb*aaaa*a********a**b***abba**ab*ab*a**aba***b*a*b***bb****bba*aa"));
    }
    
}

