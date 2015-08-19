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

public class ThreeSum extends LeetcodeCommon {
    
    public List<List<Integer>> threeSum(int[] num) {
        if(num == null || num.length < 3)
            return Collections.emptyList();
        
        List<List<Integer>> list = new ArrayList<>();        
        Arrays.sort(num);
        int len = num.length;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < len - 1; i++) {
            if(i > 1 && num[i] == num[i - 1] && num[i - 1] == num[i - 2])
                continue;
            for (int j = i + 1; j < len; j++) {
                if(j > 2 && num[j] == num[j - 1] && num[j - 1] == num[j - 2])
                    continue;
                int target = -num[i] - num[j];
                if (set.contains(target))
                    list.add(Arrays.asList(target, num[i], num[j]));
            }
            set.add(num[i]);
        }
        return list;
    }
    
    public List<List<Integer>> threeSum1(int[] num) {
        if(num == null || num.length < 3)
            return Collections.emptyList();
        
        Map<Integer, Integer> negativeMap = new HashMap<>(); 
        Map<Integer, Integer> positiveMap = new HashMap<>();
        int zeroCount = 0;
        
        for(int n: num)
            if(n > 0){
                Integer i = positiveMap.get(n);
                positiveMap.put(n, i == null ? 1 : i + 1);
            }else if(n < 0){
                Integer i = negativeMap.get(n);
                negativeMap.put(n, i == null ? 1 : i + 1);
            }else
                zeroCount++;
        
        Set<List<Integer>> resultSet = new HashSet<>();
        if(zeroCount >= 3)
            addResult(resultSet,new int[]{0, 0, 0});        
        
        for(int neg: negativeMap.keySet())
            for(int pos: positiveMap.keySet()){
                int needed = -(neg + pos);
                if(needed == 0 && zeroCount > 0)
                    addResult(resultSet,new int[]{neg, 0, pos});
                else if(needed < 0){
                    Integer anotherNegCount = negativeMap.get(needed);
                    if(anotherNegCount == null)
                        continue;
                    if(needed != neg || anotherNegCount >= 2)
                        addResult(resultSet,new int[]{neg, needed, pos});
                }else if(needed > 0){
                    Integer anotherPosCount = positiveMap.get(needed);
                    if(anotherPosCount == null)
                        continue;
                    if(needed != pos || anotherPosCount >= 2)
                        addResult(resultSet,new int[]{neg, needed, pos});
                }
            }
        
        return new ArrayList<>(resultSet);
    }
    
    public List<List<Integer>> threeSum2(int[] num) {
        if(num == null || num.length < 3)
            return Collections.emptyList();
        
        List<List<Integer>> list = new ArrayList<>();        
        Arrays.sort(num);
        int len = num.length;
        for(int i = 0; i < len - 2; i++){
            if(i > 0 && num[i] == num[i - 1])
                continue;
            int low = i + 1, high = len - 1;
            while(low < high){
                int sum = num[i] + num[low] + num[high];
                if(sum == 0){
                    list.add(Arrays.asList(num[i], num[low], num[high]));
                    while(low < high && num[low] == num[low + 1])
                        low++;
                    while(low < high && num[high] == num[high - 1])
                        high--;
                }
                if(sum < 0)
                    low++;
                else
                    high--;
            }
        }
        return list;
    }
    
    private void addResult(Set<List<Integer>> resultSet, int[] result){
        Arrays.sort(result);
        List<Integer> list = new ArrayList<>();
        for(int r: result)
            list.add(r);
        resultSet.add(list);
    }
    
    @Test
    public void test(){
        List<List<Integer>> expected = new ArrayList<>();
        expected.add(Arrays.asList(new Integer[]{-1, -1, 2}));
        expected.add(Arrays.asList(new Integer[]{-1, 0, 1}));
        assertListOfListEquals(expected, 
                threeSum(new int[]{-1, 0, 1, 2, -1, -4}));
    }
    
    @Test
    public void test1(){
        /**
Input:      [1,1,-2]
Output:     []
Expected:   [[-2,1,1]]
         */
        List<List<Integer>> expected = new ArrayList<>();
        expected.add(Arrays.asList(new Integer[]{-2, 1, 1}));
        assertListOfListEquals(expected, 
                threeSum(new int[]{1, 1, -2}));
    }
    
    @Test
    public void test3(){
        List<List<Integer>> expected = new ArrayList<>();
        expected.add(Arrays.asList(new Integer[]{-1, -1, 2}));
        expected.add(Arrays.asList(new Integer[]{-1, 0, 1}));
        assertListOfListEquals(expected, 
                threeSum(new int[]{-1, 0, 1, 2, -1, -1, -1, -4}));
    }
    
    @Test
    public void test4(){
        List<List<Integer>> expected = new ArrayList<>();
        expected.add(Arrays.asList(new Integer[]{-2, 1, 1}));
        assertListOfListEquals(expected, 
                threeSum(new int[]{-2, 0, 1, 1, 1, 1, 4}));
    }
    
    @Test
    public void test2(){
        /**
Submission Result: Time Limit Exceeded
Last executed input:    [7,-1,14,-12,-8,7,2,-15,8,8,-8,-14,-4,-5,7,9,11,-4,-15,-6,1,-14,4,3,10,-5,2,1,6,11,2,-2,-5,-7,-6,2,-15,11,-6,8,-4,2,1,-1,4,-6,-15,1,5,-15,10,14,9,-8,-6,4,-6,11,12,-15,7,-1,-9,9,-1,0,-4,-1,-12,-2,14,-9,7,0,-3,-4,1,-2,12,14,-10,0,5,14,-1,14,3,8,10,-8,8,-5,-2,6,-11,12,13,-7,-12,8,6,-13,14,-2,-5,-11,1,3,-6]
         */
        threeSum(new int[]{7,-1,14,-12,-8,7,2,-15,8,8,-8,-14,-4,-5,7,9,11,-4,-15,-6,1,-14,4,3,10,-5,2,1,6,11,2,-2,-5,-7,-6,2,-15,11,-6,8,-4,2,1,-1,4,-6,-15,1,5,-15,10,14,9,-8,-6,4,-6,11,12,-15,7,-1,-9,9,-1,0,-4,-1,-12,-2,14,-9,7,0,-3,-4,1,-2,12,14,-10,0,5,14,-1,14,3,8,10,-8,8,-5,-2,6,-11,12,13,-7,-12,8,6,-13,14,-2,-5,-11,1,3,-6});
    }
}
