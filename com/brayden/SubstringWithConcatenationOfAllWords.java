package com.brayden;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import org.junit.Test;

public class SubstringWithConcatenationOfAllWords extends LeetcodeCommon {
    public List<Integer> findSubstring(String S, String[] L) {
        if(S == null || S.length() == 0 || 
                L == null || L.length == 0 || L[0].length() == 0)
            return Collections.emptyList();
        
        int lenS  = S.length();
        int sizeL = L.length;
        int lenL  = L[0].length();
        if(lenS < sizeL * lenL)
            return Collections.emptyList();

        Map<String, Integer> map = new HashMap<>();
        for(String s: L)
            if(!map.containsKey(s))
                map.put(s, 1);
            else 
                map.put(s, map.get(s) + 1);
        
        List<Integer> resList = new ArrayList<>();
        for(int i = 0; i < lenL; i++){
            Map<String, Integer> workingMap = new HashMap<>(map);
            Queue<String> q = new LinkedList<>();
            int index = i;
            for(int j = i; j + lenL <= lenS ; j += lenL){
                String word = S.substring(j, j + lenL);
                if(!map.containsKey(word)){ // word not in L
                    if(!q.isEmpty()){
                        workingMap = new HashMap<>(map);
                        q.clear();
                    }
                    index = j + lenL;
                    continue;
                }
                if(workingMap.get(word) > 0){ 
                    q.offer(word);
                    workingMap.put(word, workingMap.get(word) - 1);
                    if(q.size() == sizeL){
                        resList.add(index);
                        String str = q.poll();
                        workingMap.put(str, workingMap.get(str) + 1);
                        index += lenL;
                    }
                }else{ // workingMap.get(word) == 0; word in L but already used
                    workingMap.put(q.poll(), 1);
                    index += lenL;
                    j -= lenL;
                }
            }
        }
        
        return resList;
    }
    
    @Test
    public void test(){
        assertListEquals(Arrays.asList(new Integer[]{0, 9}), 
              findSubstring("barfoothefoobarman", new String[]{"foo", "bar"}));
    }
    
    @Test
    public void test1(){
        /**
Submission Result: Wrong Answer
Input:  "aaa", ["a","a"]
Output:     [0]
Expected:   [0,1]
         */
        assertListEquals(Arrays.asList(new Integer[]{0, 1}), 
                findSubstring("aaa", new String[]{"a", "a"}));
    }
}
