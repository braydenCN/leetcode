package com.brayden.other;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.junit.Test;

public class AllSubsetPermutation {

    public List<List<List<Integer>>> allSubsetPermutation(int[] nums){
        if(nums == null || nums.length == 0)
            return null;
        Queue<List<List<Integer>>> q = new LinkedList<>();
        q.add(new LinkedList<>());
        
        for(int n: nums){
            int size = q.size();
            while(size-- > 0){
                List<List<Integer>> list = q.poll();
                for(int i = 0; i < list.size(); i++){
                    List<List<Integer>> l = deepClone(list);
                    l.get(i).add(n);
                    q.offer(l);
                }
                list.add(new LinkedList<>(Arrays.asList(n)));
                q.offer(list);
            }
        }
        return new LinkedList<>(q);
    }
    
    private List<List<Integer>> deepClone(List<List<Integer>> list){
        List<List<Integer>> l = new LinkedList<>();
        for(List<Integer> li: list)
            l.add(new LinkedList<>(li));
        return l;
    }
    
    @Test
    public void test(){
        List<List<List<Integer>>> l = null;
        l = allSubsetPermutation(new int[]{1});
        l = allSubsetPermutation(new int[]{1, 2});
        l = allSubsetPermutation(new int[]{1, 2, 3});
        l = allSubsetPermutation(new int[]{1, 2, 3, 4});
        return;
    }
}
