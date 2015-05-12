package com.brayden;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

public class FourSum {
    
    private static class Pair{
        int l, h;

        public Pair(int i, int j) {
            this.l = i;
            this.h = j;
        }
        
    }
    
    public List<List<Integer>> fourSum(int[] num, int target) {
        if(num == null || num.length < 4)
            return Collections.emptyList();
        
        Arrays.sort(num);
        int len = num.length;
        
        Set<List<Integer>> resultSet = new HashSet<>();
        Map<Integer, List<Pair>> twoSumMap = new HashMap<>();
        for(int i = 0; i < len - 1; i++)
            for(int j = i + 1; j < len; j++){
                int key = num[i] + num[j];
                if(!twoSumMap.containsKey(key))
                    twoSumMap.put(key, new ArrayList<Pair>());
                twoSumMap.get(key).add(new Pair(i, j));
            }
        for(int i = 0; i < len - 1; i++)
            for(int j = i + 1; j < len; j++){
                int key = target - num[i] - num[j];
                if(twoSumMap.containsKey(key)){
                    for(Pair p: twoSumMap.get(key)){
                        if(p.l == i || p.l == j || p.h == i || p.h == j)
                            continue;
                        List<Integer> list = getList(num, i, j, p.l, p.h);
                        resultSet.add(list);
                    }
                }
            }
                
        return new ArrayList<List<Integer>>(resultSet);
    }

    public List<List<Integer>> fourSum1(int[] num, int target) {
        if(num == null || num.length < 4)
            return Collections.emptyList();
        
        Arrays.sort(num);
        int len = num.length;
        
        Set<List<Integer>> set = new HashSet<>();
        Map<Integer, List<Pair>> map = new HashMap<>();
        for(int i = 0; i < len - 1; i++)
            for(int j = i + 1; j < len; j++){
                int key = num[i] + num[j];
                if(map.containsKey(target - key))
                    for(Pair p: map.get(target - key))
                        if(p.l != i && p.h != j && p.l != j && p.h != i)
                            set.add(getList(num, p.l, p.h, i, j));
                map.computeIfAbsent(key, k -> new ArrayList<Pair>());
                map.get(key).add(new Pair(i, j));
            }
                
        return new ArrayList<>(set);
    }
    
    private List<Integer> getList(int[] num, int i1, int i2, int i3, int i4) {
        List<Integer> list = new ArrayList<>();
        list.add(num[i1]);
        list.add(num[i2]);
        list.add(num[i3]);
        list.add(num[i4]);
        Collections.sort(list);
        return list;
    }
    
    @Test
    public void test(){
        fourSum1(new int[]{1, 0, -1, 0, -2, 2}, 0);
    }
}
