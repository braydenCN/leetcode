package com.brayden;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

public class GenerateParentheses {
    public List<String> generateParenthesis(int n) {
        if(n <= 0)
            return Collections.emptyList();
        
        @SuppressWarnings("unchecked")
        List<String>[] dp = new List[n + 1];
        dp[0] = Collections.singletonList("");
        for(int i = 1; i <= n; i++){
            dp[i] = new ArrayList<>();
            for(int j = 0; j < i; j++)
                for(String s1: dp[j])
                    for(String s2: dp[i - j - 1])
                        dp[i].add("(" + s1 + ")" + s2);
        }
        
        return dp[n];
    }
    
    @Test
    public void test(){
        List<String> expected = Arrays.asList("((()))", "(()())", "(())()", "()(())", "()()()");
        List<String> result   = generateParenthesis(3);
        assertTrue(expected.containsAll(result) && result.containsAll(expected));
    }
}
