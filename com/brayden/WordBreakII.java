package com.brayden;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

public class WordBreakII extends LeetcodeCommon {
    
    public List<String> wordBreak(String s, Set<String> dict) {
        if(s == null || s.length() == 0 || dict.isEmpty())
            return Collections.emptyList();
        
        if(dict.stream().noneMatch(str -> s.startsWith(str)))
            return Collections.emptyList();
        if(dict.stream().noneMatch(str -> s.endsWith(str)))
            return Collections.emptyList();
        
        int len = s.length();
        @SuppressWarnings("unchecked")
        List<String>[] cache = new List[len + 1];
        cache[0] = Collections.emptyList();
        for(int i = 1; i <= len; i++) {
            for(int j = 0; j < i; j++) {
                String word = s.substring(j, i);
                if (cache[j] != null && dict.contains(word)) {
                    if(cache[i] == null)
                        cache[i] = new ArrayList<>();
                    for(String str: cache[j])
                        cache[i].add(str + " " + word);
                    if(cache[j].isEmpty())
                        cache[i].add(word);
                }
            }
        }
        return cache[len] == null ? Collections.emptyList() : cache[len];
    }
    
    @Test
    public void test(){
        /**
Submission Result: Time Limit Exceeded
Last executed input:    "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab", ["a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa"]
         */
                wordBreak("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab", 
                new HashSet<String>(Arrays.asList("a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa")));
    }
    
    @Test
    public void test1(){
    /**
Submission Result: Wrong Answer
Input:  "bb", ["a","b","bbb","bbbb"]
Output:     []
Expected:   ["b b"]
    */
        
    }    
}
