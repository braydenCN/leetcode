package com.brayden;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class Anagrams extends LeetcodeCommon {

    public List<String> anagrams(String[] strs) {
        if(strs == null || strs.length <= 1)
            return Collections.emptyList();
        
        Map<String, List<String>> map = new HashMap<>();
        for(String str: strs){
            String orderedStr = orderStr(str);
            if(map.containsKey(orderedStr))
                map.get(orderedStr).add(str);
            else
                map.put(orderedStr, 
                      new ArrayList<>(Arrays.asList(new String[]{str})));
        }
        List<String> resultList = new ArrayList<>();
        for(List<String> list: map.values())
            if(list.size() > 1)
                resultList.addAll(list);

        return resultList;
    }

    private String orderStr(String str) {
        char[] charArr = str.toCharArray();
        Arrays.sort(charArr);
        return new String(charArr);
    }
    
    @Test
    public void test(){
        /**
Submission Result: Runtime Error
Runtime Error Message:  Line 10: java.lang.UnsupportedOperationException
Last executed input:    [""]
         */
        /**
         * this is because I used Arrays.asList(new String[]{str})) as new list
         * it is immutable
         */
        assertListEquals(Arrays.asList(new String[]{"", ""}), 
                anagrams(new String[]{"", ""}));
    }
}
