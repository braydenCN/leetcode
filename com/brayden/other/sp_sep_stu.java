package com.brayden.other;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

/**
 * 有100名学生的成绩，每个班级50人，要求两个班级的平均分越接近越好，如何分配两个班级？
 * @author pengczha
 *
 */
public class sp_sep_stu {
    
    public Set<Integer> sep(int[] score){
        int N = score.length;
        int half = Arrays.stream(score).sum() / 2;
        @SuppressWarnings("unchecked")
        Set<Integer> dp[] = new HashSet[half + 1];
        for(int i = 0; i <= half; i++)
            dp[i] = new HashSet<>();
        for(int i = 1; i <= N / 2; i++)
            for(int k = 0; k < N; k++)
                for(int j = half; j >= score[k]; j--){
                    if(dp[j - score[k]].contains(k))
                        continue;
                    if(sum(dp[j - score[k]]) + score[k] > sum(dp[j])){
                        dp[j].clear();
                        dp[j].addAll(dp[j - score[k]]);
                        dp[j].add(k);
                    }
                }
        return dp[half];
    }
    
    public Set<Integer> sep1(int[] score){
        int N = score.length;
        int half = Arrays.stream(score).sum() / 2;
        @SuppressWarnings("unchecked")
        Set<Integer> dp[][] = new HashSet[N / 2 + 1][half + 1];
        for(int i = 0; i < half + 1; i++)
            dp[0][i] = new HashSet<>();
        for(int i = 1; i <= N / 2; i++)
            for(int k = 0; k < N; k++)
                for(int j = score[k]; j <= half; j++)
                    if(dp[i - 1][j - score[k]] != null && !dp[i - 1][j - score[k]].contains(k)){
                        if(dp[i][j] == null)
                            dp[i][j] = new HashSet<>();
                        if(sum(dp[i - 1][j - score[k]]) + score[k] > sum(dp[i][j])){
                            dp[i][j].clear();
                            dp[i][j].addAll(dp[i - 1][j - score[k]]);
                            dp[i][j].add(k);
                        }
                    }
        return dp[N / 2][half];
    }
    
    private int sum(Set<Integer> list) {
        int sum = 0;
        for(int n: list)
            sum += n;
        return sum;
    }

    @Test
    public void test(){
        testBase(5, new int[]{1, 3, 4, 4});
    }
    
    @Test
    public void test2(){
        testBase(7, new int[]{1, 1, 2, 3, 4, 4});
    }
    
    @Test
    public void test3(){
        testBase(4, new int[]{1, 0, 2, 1, 3, 2});
    }
    
    private void testBase(int target, int[] score) {
        int sum = 0;
        for(int i: sep(score))
            sum += score[i];
        assertEquals(target, sum);
    }

    public Set<Integer> sep2(int[] score){
        int N = score.length;
        int half = Arrays.stream(score).sum() / 2;
        boolean dp[][] = new boolean[N / 2 + 1][half + 1];
        int t[][] = new int[N / 2 + 1][half + 1];
        dp[0][0] = true;

        for(int k = 0; k < N; k++)
            for(int i = Math.min(N / 2, k + 1); i > 0; i--)
                for(int j = score[k]; j < half + 1; j++)
                    if(dp[i - 1][j - score[k]]){
                        dp[i][j] = true;
                        t[i][j] = k;
                    }
        int aScore = half;
        for(; aScore >= 0 && !dp[N / 2][aScore]; aScore--);
        Set<Integer> set = new HashSet<>();
        for(int i = N / 2; i > 0; i--){
            int k = t[i][aScore];
            set.add(k);
            aScore -= score[k];
        }
        return set;
    }
}
