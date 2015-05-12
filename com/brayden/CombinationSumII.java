package com.brayden;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

public class CombinationSumII {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        if(candidates == null || candidates.length == 0)
            return Collections.emptyList();
        
        @SuppressWarnings("unchecked")
        Set<List<Integer>>[] dp = new Set[target + 1];
        dp[0] = new HashSet<>();
        dp[0].add(new ArrayList<Integer>());
        
        for(int candidate: candidates)
            for(int i = target; i >= 1; i--)
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
        combinationSum2(new int[]{10,1,2,7,6,1,5}, 8);
    }
    
    
}
