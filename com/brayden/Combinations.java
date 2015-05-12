package com.brayden;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

public class Combinations {
    public List<List<Integer>> combine(int n, int k) {
        if(n < 1 || k < 1 || k > n)
            return Collections.emptyList();
        
        /** c(n, k) = c(n-1, k) + f(n, c(n-1, k-1)) **/
        @SuppressWarnings("unchecked")
        List<List<Integer>>[][] cache = new List[n + 1][k + 1];
        
        /** init cache of k == 0 **/
        for(int i = 0; i <= n; i++){
            cache[i][0] = new ArrayList<>();
            cache[i][0].add(new ArrayList<Integer>());
        }
        
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= i && j <= k; j++){
                /** combine(i, j) **/
                cache[i][j] = new ArrayList<>();
                if(i - 1 >= j)
                    cache[i][j].addAll(cache[i - 1][j]);
                for(List<Integer> list: cache[i - 1][j - 1]){
                    List<Integer> tmpList = new LinkedList<>(list);
                    tmpList.add(i);
                    cache[i][j].add(tmpList);
                }
            }
        }
        
        return cache[n][k];
    }
    
    @Test
    public void test(){
        combine(4, 2);
    }
}
