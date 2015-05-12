package com.brayden;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * One Edit Distance  

Given two strings S and T, determine if they are both one edit distance apart.

Hint:
1. If | n ¨C m | is greater than 1, we know immediately both are not one-edit 
distance apart.
2. It might help if you consider these cases separately, m == n and m ¡Ù n.
3. Assume that m is always ¡Ü n, which greatly simplifies the conditional 
statements. If m > n, we could just simply swap S and T.
4. If m == n, it becomes finding if there is exactly one modified operation. If
 m ¡Ù n, you do not have to consider the delete operation. Just consider the 
 insert operation in T.
 
 */
public class OneEditDistance {
    public boolean isOneEditDistance(String s, String t){
        if(s == null || t == null)
            return false;
        
        int lenS = s.length();
        int lenT = t.length();
        if(lenS - lenT > 1 || lenS - lenT < -1)
            return false;
        
        if(lenS == lenT){
            int count = 0;
            for(int i = 0; i < lenS; i++)
                if(s.charAt(i) != t.charAt(i))
                    if(++count == 2)
                        return false;
            return true;
        }
        
        String s1, s2;
        if(lenS < lenT){
            s1 = s; s2 = t;
        }else{
            s1 = t; s2 = s;
        }
        int count = 0, indS1 = 0, indS2 = 0;
        for(; indS1 < s1.length() && indS2 < s2.length(); indS1++, indS2++)
            if(s1.charAt(indS1) != s2.charAt(indS2)){
                if(++count == 2)
                    return false;
                indS1--;
            }
        return true;
    }
    
    @Test
    public void test(){
        assertTrue(isOneEditDistance("a", "b"));
    }
    @Test
    public void test1(){
        assertTrue(isOneEditDistance("a", "ba"));
    }
    @Test
    public void test2(){
        assertTrue(isOneEditDistance("aba", "bba"));
    }
    @Test
    public void test3(){
        assertFalse(isOneEditDistance("a", "bb"));
    }
    @Test
    public void test4(){
        assertFalse(isOneEditDistance("", "bb"));
    }
    @Test
    public void test5(){
        assertTrue(isOneEditDistance("a", ""));
    }
    
}
