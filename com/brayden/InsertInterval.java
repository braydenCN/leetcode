package com.brayden;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

public class InsertInterval extends LeetcodeCommon {
    public List<Interval> insert(List<Interval> intervals, 
            Interval newInterval) {
        if(newInterval == null)
            return intervals;
        if(intervals == null || intervals.isEmpty())
            return Collections.singletonList(newInterval);
        
        List<Interval> result = new LinkedList<>();
        int len = intervals.size(), i = 0;
        int start = newInterval.start, end = newInterval.end;
        for(; i < len && start > intervals.get(i).end; i++)
            result.add(intervals.get(i));
        if(i < len)
            start = Math.min(start, intervals.get(i).start);
        for(; i < len && intervals.get(i).start <= end; i++);
        if(i < len)
            end = Math.max(end, intervals.get(--i).end);
        result.add(new Interval(start, end));
        for(; i < len; i++)
            result.add(intervals.get(i));
        return intervals;
    }
    
    public List<Interval> insert1(List<Interval> intervals, 
            Interval newInterval) {
        if(newInterval == null)
            return Collections.emptyList();

        if(intervals == null || intervals.size() == 0)
            return Collections.singletonList(newInterval);
        
        int start = newInterval.start;
        int end   = newInterval.end;
        int low = 0, high = intervals.size() - 1, mid = 0;
        intervals = new ArrayList<>(intervals);
        while(low <= high){
            mid = ((high - low) >> 1) + low;
            Interval i = intervals.get(mid);
            if(start <= i.end && (mid == 0 || 
                    start > intervals.get(mid - 1).end))
                break;
            else if(start <= i.end)
                high = mid - 1;
            else 
                low = mid + 1;
        }
        
        intervals = new LinkedList<>(intervals);
        Interval cur = intervals.get(mid);
        if(intersected(newInterval, cur)){
            intervals.set(mid, new Interval(Math.min(start, cur.start), 
                                   Math.max(end, cur.end)));
        }else{
            if(cur.start < start)
                mid++;
            intervals.add(mid, newInterval);
            return intervals;
        }
        
        Interval pre = intervals.get(mid);
        while(mid + 1 < intervals.size()){
            cur = intervals.get(mid + 1);
            if(!intersected(cur, pre))
                break;
            else{
                cur = new Interval(Math.min(pre.start, cur.start), 
                                   Math.max(pre.end, cur.end));
                intervals.remove(mid + 1);
                intervals.set(mid, cur);
                pre = cur;
            }
        }
        return intervals;
    }
    
    @Test
    public void test(){
        List<Interval> input = Arrays.asList(
                new Interval[]{new Interval(1, 3), new Interval(6, 9)}); 
        List<Interval> expected = Arrays.asList(
                new Interval[]{new Interval(1, 5), new Interval(6, 9)}); 
        
        assertListEquals(expected, insert(input, new Interval(2, 5)));
    }
    
    @Test
    public void test1(){
        List<Interval> input = Arrays.asList(
                new Interval[]{new Interval(1, 2), new Interval(3, 5), 
                        new Interval(6, 7), new Interval(8, 10), 
                        new Interval(12, 16)}); 
        List<Interval> expected = Arrays.asList(
                new Interval[]{new Interval(1, 2), new Interval(3, 10), 
                        new Interval(12, 16)}); 
        
        assertListEquals(expected, insert(input, new Interval(4, 9)));
    }
}
