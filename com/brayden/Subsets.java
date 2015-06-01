package com.brayden;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Subsets extends LeetcodeCommon {
    
    public List<List<Integer>> subsets(int[] S) {
        Arrays.sort(S);
        int size = 1 << S.length;
        List<List<Integer>> list = new ArrayList<>();
        for(int i = 0; i < size; i++){
            List<Integer> tmp = new ArrayList<>();
            for(int j = 0; j < S.length; j++)
                if((i & (1 << j)) != 0)
                    tmp.add(S[j]);
            list.add(tmp);
        }
        return list;
    }
    
    public List<List<Integer>> subsets2(int[] S) {
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<Integer>());
        if(S == null || S.length == 0)
            return result;
        
        Arrays.sort(S);
        
        for(int s: S){
            int size = result.size();
            for(int i = 0; i < size; i++){
                List<Integer> l = new ArrayList<>(result.get(i));
                l.add(s);
                result.add(l);
            }
        }
        
        return result;
    }
    
    public List<List<Integer>> subsets1(int[] S) {
        List<List<Integer>> resultList = new ArrayList<>();
        resultList.add(new ArrayList<Integer>());
        if(S == null || S.length == 0)
            return resultList;
        
        Arrays.sort(S);
        
        for(int s: S){
            List<List<Integer>> tmpList = new ArrayList<>();
            for(List<Integer> l: resultList){
                List<Integer> list = new ArrayList<>(l);
                list.add(s);
                tmpList.add(list);
            }
            resultList.addAll(tmpList);
        }
        
        return resultList;
    }

}
