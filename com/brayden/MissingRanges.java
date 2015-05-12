package com.brayden;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

/*
 Missing Ranges  

Given a sorted integer array where the range of elements are [0, 99] inclusive, return its missing ranges.
For example, given [0, 1, 3, 50, 75], return [¡°2¡±, ¡°4->49¡±, ¡°51->74¡±, ¡°76->99¡±]
 */
public class MissingRanges extends LeetcodeCommon {

    private String getRangeStrInclusive(int start, int end){
        if(end < start)
            throw new IllegalArgumentException();
        
        if(end == start)
            return "" + start;
        
        return start + "->" + end;
    }
    
    public List<String> findMissingRanges(int[] vals, int start, int end) {
        if(vals == null || vals.length == 0)
            return Collections.singletonList(getRangeStrInclusive(start, end));
        
        int len = vals.length;
        List<String> resultList = new ArrayList<>();
        if(vals[0] > start)
            resultList.add(getRangeStrInclusive(start, vals[0] - 1));
        
        for(int i = 1; i < len; i++)
            if(vals[i] >= vals[i - 1] + 2)
                resultList.add(getRangeStrInclusive(vals[i - 1] + 1, vals[i] - 1));
        
        if(vals[len - 1] < end)
            resultList.add(getRangeStrInclusive(vals[len - 1] + 1, end));
        
        return resultList;
    }
    
    @Test
    public void test(){
        assertListEquals(new ArrayList<String>(), findMissingRanges(new int[]{1, 2, 3, 4}, 1, 4));
    }
    
    @Test
    public void test1(){
        List<String> expected = Collections.singletonList("2->3");
        assertListEquals(expected, findMissingRanges(new int[]{1, 4}, 1, 4));
    }
    
    @Test
    public void test2(){
        List<String> expected = Collections.singletonList("1");
        assertListEquals(expected, findMissingRanges(new int[]{2, 3, 4, 5}, 1, 4));
    }
}
