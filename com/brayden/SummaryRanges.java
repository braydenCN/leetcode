package com.brayden;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SummaryRanges {
    public List<String> summaryRanges(int[] nums) {
        if(nums == null || nums.length == 0)
            return Collections.emptyList();
        List<String> list = new ArrayList<>();
        int begin = nums[0], len = nums.length;
        for(int i = 1; i < len; i++)
            if(nums[i] != nums[i - 1] + 1){
                list.add(getString(begin, nums[i - 1]));
                begin = nums[i];
            }
        list.add(getString(begin, nums[len - 1]));
        return list;
    }

    private String getString(int begin, int end) {
        if(begin == end)
            return "" + begin;
        return "" + begin + "->" + end; 
    }
}
