package com.brayden;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PermutationsII {
    public List<List<Integer>> permuteUnique(int[] num) {
        if(num == null || num.length == 0)
            return Collections.emptyList();
        
        Set<List<Integer>> set = new HashSet<>();
        set.add(Arrays.asList(new Integer[]{num[0]}));
        for(int i = 1; i < num.length; i++){
            int curNum = num[i];
            Set<List<Integer>> workingSet = new HashSet<>();
            for(List<Integer> list: set)
                for(int j = 0; j <= i; j++){
                    List<Integer> tmpList = new ArrayList<>(list);
                    tmpList.add(j, curNum);
                    workingSet.add(tmpList);
                }
            set = workingSet;
        }
        
        return new ArrayList<List<Integer>>(set);
    }
}
