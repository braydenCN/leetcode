package com.brayden;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

import org.junit.Test;

public class Permutations extends LeetcodeCommon {
    
    
    public List<List<Integer>> permute(int[] num) {
        if(num == null || num.length == 0)
            return Collections.emptyList();
        
        List<List<Integer>> resList = new ArrayList<>();
        permuteInt(num, 0, resList);
        return resList;
    }
    
    private void permuteInt(int[] num, int i, List<List<Integer>> resList) {
        if(i == num.length - 1){
            resList.add(Arrays.stream(num).boxed().collect(Collectors.toList()));
            return;
        }
        for(int j = i; j < num.length; j++){
            swap(num, i, j);
            permuteInt(num, i + 1, resList);
            swap(num, i, j);
        }
    }

    public void permuteInt2(Queue<Integer> q, LinkedList<Integer> curList, 
            List<List<Integer>> resList) {
        if(q.isEmpty()){
            resList.add(new ArrayList<>(curList));
            return;
        }
        int size = q.size();
        while(size-- > 0){
            int num = q.poll();
            curList.addLast(num);
            permuteInt2(q, curList, resList);
            curList.removeLast();
            q.offer(num);
        }
    }
    
    public List<List<Integer>> permute2(int[] num) {
        if(num == null || num.length == 0)
            return Collections.emptyList();
        
        Queue<Integer> q = new LinkedList<>();
        Arrays.stream(num).forEach(n -> q.add(n));
        List<List<Integer>> resList = new ArrayList<>();
        permuteInt2(q, new LinkedList<>(), resList);
        return resList;
    }
    
    public List<List<Integer>> permute1(int[] num) {
        if(num == null || num.length == 0)
            return Collections.emptyList();
        
        List<List<Integer>> resultList = new ArrayList<>();
        resultList.add(Arrays.asList(new Integer[]{num[0]}));
        for(int i = 1; i < num.length; i++){
            int curNum = num[i];
            List<List<Integer>> workingList = new ArrayList<>();
            for(List<Integer> list: resultList)
                for(int j = 0; j <= i; j++){
                    List<Integer> tmpList = new ArrayList<>(list);
                    tmpList.add(j, curNum);
                    workingList.add(tmpList);
                }
            resultList = workingList;
        }
        
        return resultList;
    }
    
    @Test
    public void test(){
        permute(new int[]{1});
        permute(new int[]{1, 2});
        permute(new int[]{1, 2, 3});
        permute(new int[]{1, 2, 3, 4});
    }
}
