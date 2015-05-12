package com.brayden;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

public class CombinationSum {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        if(candidates == null || candidates.length == 0)
            return Collections.emptyList();
        
        @SuppressWarnings("unchecked")
        Set<List<Integer>>[] dp = new Set[target + 1];
        dp[0] = new HashSet<>();
        dp[0].add(new ArrayList<Integer>());
        
        for(int i = 1; i <= target; i++)
            for(int candidate: candidates)
                if(i - candidate >= 0 && dp[i - candidate] != null){
                    if(dp[i] == null)
                        dp[i] = new HashSet<>();
                    for(List<Integer> list: dp[i - candidate]){
                        List<Integer> tmpList = new ArrayList<>(list);
                        tmpList.add(candidate);
                        Collections.sort(tmpList);
                        dp[i].add(tmpList);
                    }
                }
        
        if(dp[target] == null)
            return Collections.emptyList();
        else
            return new ArrayList<List<Integer>>(dp[target]);
    }
    
    @Test
    public void test(){
        combinationSum(new int[]{2,3,6,7}, 7);
    }
    
    @Test
    public void test1(){
        /**
Submission Result: Runtime Error
Runtime Error Message:  Line 24: java.lang.NullPointerException
Last executed input:    [2], 1
         */
        // dp[target] could be null
    }
}
