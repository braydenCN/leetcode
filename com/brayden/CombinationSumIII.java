package com.brayden;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.junit.Test;

public class CombinationSumIII extends LeetcodeCommon {

    public List<List<Integer>> combinationSum3(int k, int n) {
        return combinationSum3Int(k, n, 9, new ArrayList<>(), 
                new LinkedList<Integer>());
    }

    private List<List<Integer>> combinationSum3Int(int k, int n, int max, 
            List<List<Integer>> result, LinkedList<Integer> list){
        if(k <= 0 || n <= 0){
            if(k == 0 && n == 0)
                result.add(new ArrayList<>(list));
            return result;
        }
        for(int i = max; i >= 1; i--){
            list.addFirst(i);
            combinationSum3Int(k - 1, n - i, i - 1, result, list);
            list.removeFirst();
        }
        return result;
    }
    
    @Test
    public void test(){
        List<List<Integer>> expected = Collections.singletonList(Arrays.asList(1, 2, 4));
        assertListOfListEquals(expected, combinationSum3(3, 7));
    }
    
    @Test
    public void test1(){
        Set<List<Integer>> expected = new HashSet<>(Arrays.asList(Arrays.asList(1,2,6), Arrays.asList(1,3,5), Arrays.asList(2,3,4)));
        Set<List<Integer>> result = new HashSet<List<Integer>>(combinationSum3(3, 9));
        assertTrue(expected.containsAll(result) && result.containsAll(expected));
    }
}
