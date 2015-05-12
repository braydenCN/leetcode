package com.brayden.other;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class FindAllSubsets {

    public Set<Set<Integer>> findAllSubsets(Set<Integer> set){
        Set<Set<Integer>> subsets = new HashSet<>();
        Set<Set<Integer>> subsets1 = new HashSet<>();
        for(Integer i: set){
            subsets1.add(new HashSet<Integer>(Arrays.asList(i)));
            for(Set<Integer> s: subsets){
                Set<Integer> tmpSet = new HashSet<>(s);
                tmpSet.add(i);
                subsets1.add(tmpSet);
            }
            subsets.addAll(subsets1);
            subsets1.clear();
        }
        return subsets;
    }
    
    @Test
    public void test(){
        Set<Set<Integer>> subsets = 
                findAllSubsets(new HashSet<Integer>(Arrays.asList(1, 2, 3)));
        return;
    }
}
