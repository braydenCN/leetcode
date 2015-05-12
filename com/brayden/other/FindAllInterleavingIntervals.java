package com.brayden.other;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.brayden.LeetcodeCommon;

public class FindAllInterleavingIntervals extends LeetcodeCommon {
    public List<Interval> findAllInterleavingIntervals(List<Interval> intervals) {
        if(intervals == null || intervals.isEmpty())
            return Collections.emptyList();
        
        intervals.sort((i1, i2) -> i1.start - i2.start);
        
        int len = intervals.size();
        List<Interval> resultList = new LinkedList<>();
        for(int i = 0; l < len - 1; l++){
            Interval cur = intervals.get(l);
            int end = cur.end;
            int lastIndex = findHigher(intervals, l + 1, len - 1, end) - 1;
            for(int j = )
        }
        
        return resultList;
    }

    private int findHigher(List<Interval> list, int low, int high, int target) {
        int mid;
        while(low <= high){
            mid = ((high - low) >> 1) + low;
            if(list.get(mid).start > target && 
                    (mid == 0 || list.get(mid - 1).start <= target))
                return mid;
            if(list.get(mid).start > target)
                high = mid - 1;
            else 
                low = mid + 1;
        }
        return list.size();
    }
}
