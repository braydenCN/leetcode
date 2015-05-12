package com.brayden;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.TreeSet;

import org.junit.BeforeClass;
import org.junit.Test;

public class MaximumGap {
    
    public int maximumGap(int[] num) {
        /** get the solution from Leetcode discussion **/
        if(num == null || num.length < 2)
            return 0;
        
        int min = num[0], max = num[0], len = num.length;
        for(int n: num){
            if(n < min)
                min = n;
            if(n > max)
                max = n;
        }
        int gap = (max - min + 1) / (len - 1) + 1;
        int[] bucketMin = new int[len];
        int[] bucketMax = new int[len];
        Arrays.fill(bucketMin, Integer.MAX_VALUE);
        Arrays.fill(bucketMax, -1);
        
        int bucketInd;
        for(int n: num){
            bucketInd = (n - min) / gap;
            if(n < bucketMin[bucketInd])
                bucketMin[bucketInd] = n;
            if(n > bucketMax[bucketInd])
                bucketMax[bucketInd] = n;
        }
        
        int maxGap = 0, preNum = bucketMin[0];
        for(int i = 0; i < len; i++){
            if(bucketMax[i] == -1)
                continue;
            maxGap = Math.max(maxGap, bucketMin[i] - preNum);
            maxGap = Math.max(maxGap, bucketMax[i] - bucketMin[i]);
            preNum = bucketMax[i];
        }
        
        return maxGap;
    }
    
    
    private static int BUCKET_SIZE = 65536;
    
    private static class Bucket implements Comparable<Bucket> {
        private int start;
        private TreeSet<Integer> set = new TreeSet<>();
        
        private Bucket(int start) {
            this.start = start;
        }

        @Override
        public int compareTo(Bucket o) {
            return start - o.start;
        }
        
        static TreeSet<Bucket> bucketSet = new TreeSet<>();
        static void add(int num){
            int base = (num / BUCKET_SIZE) * BUCKET_SIZE;
            Bucket tmpBucket = new Bucket(base);
            if(!bucketSet.contains(tmpBucket)){
                bucketSet.add(tmpBucket);
                tmpBucket.set.add(num);
            }else
                bucketSet.ceiling(tmpBucket).set.add(num);
        }
        
        static int maxGap(){
            int maxGap = 0;
            int preNum = bucketSet.first().set.first();
            for(Bucket b: bucketSet)
                for(int n: b.set){
                    maxGap = Math.max(maxGap, n - preNum);
                    preNum = n;
                }
            return maxGap;
        }
    }
    
    public int maximumGapFail(int[] num) {
        if(num == null || num.length < 2)
            return 0;
        
        for(int n: num)
            Bucket.add(n);
        
        return Bucket.maxGap();
    }
    
    @Test
    public void test(){
        assertEquals(1_000_000_000, maximumGap(new int[]{1, 1_000_000_001}));
    }
    
    @Test
    public void test2(){
        assertEquals(99999997, maximumGap(new int[]{2,99999999}));
    }
    
    
    private static int[] input;
    private static int expected;
    @BeforeClass
    public static void init(){
        int SIZE = 1000_000;
        List<Integer> list = new ArrayList<>(SIZE);
        Random r = new Random();
        for(int i = 0; i < SIZE; i++)
            list.add(Math.abs(r.nextInt()));
        Collections.sort(list);
        expected = 0;
        for(int i = 1; i < SIZE; i++)
            expected = Math.max(expected, list.get(i) - list.get(i - 1));
        
        Collections.shuffle(list);
        
        input = new int[list.size()];
        for(int i = 0; i < SIZE; i++)
            input[i] = list.get(i);
    }
    
    @Test
    public void test1(){
        long time = System.currentTimeMillis();
        assertEquals(expected, maximumGap(input));
        System.out.println(System.currentTimeMillis() - time);
    }    
    
}
