package com.brayden;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

public class SubsetsII extends LeetcodeCommon {
    
    public List<List<Integer>> subsetsWithDup(int[] num) {
        List<List<Integer>> result = new ArrayList<>();
        if(num == null || num.length == 0)
            return result;
        
        Arrays.sort(num);
        result.add(new ArrayList<Integer>());
        result.add(Arrays.asList(num[0]));
        int preSize = 1;
        for(int i = 1; i < num.length; i++){
            int size = result.size();
            int start = num[i] == num[i - 1] ? preSize : 0;
            for(int j = start; j < size; j++){
                List<Integer> l = new ArrayList<>(result.get(j));
                l.add(num[i]);
                result.add(l);
            }
            preSize = size;
        }
        
        return result;
    }
    
    @Test
    public void test(){
        subsetsWithDup(new int[]{1, 1});
    }
    
    public List<List<Integer>> subsetsWithDup1(int[] num) {
        Set<List<Integer>> set = new HashSet<>();
        set.add(new ArrayList<Integer>());
        if(num == null || num.length == 0)
            return new ArrayList<List<Integer>>(set);
        
        Arrays.sort(num);
        
        for(int n: num){
            List<List<Integer>> tmpList = new ArrayList<>();
            for(List<Integer> l: set){
                List<Integer> list = cloneList(l);
                list.add(n);
                tmpList.add(list);
            }
            set.addAll(tmpList);
        }
        
        return new ArrayList<List<Integer>>(set);
    }
    
}
