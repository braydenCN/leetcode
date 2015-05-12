package com.brayden;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import org.junit.Test;

public class CourseSchedule {
    
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        @SuppressWarnings("unchecked")
        Set<Integer>[] preSets  = new Set[numCourses], 
                       postSets = new Set[numCourses];
        Set<Integer> untaken = new HashSet<>();
        for(int i = 0; i < numCourses; i++){
            preSets[i] = new HashSet<>();
            postSets[i] = new HashSet<>();
            untaken.add(i);
        }
        for(int[] p: prerequisites){
            preSets[p[1]].add(p[0]);
            postSets[p[0]].add(p[1]);
        }
        
        Set<Integer> taking = new HashSet<>();
        while(true){
            for(int i: untaken)
                if(preSets[i].isEmpty()){
                    taking.add(i);
                    for(int cur: postSets[i])
                        preSets[cur].remove(i);
                }
            if(taking.isEmpty())
                return untaken.isEmpty();
            untaken.removeAll(taking);
            taking.clear();
        }
    }
    
    public boolean canFinish1(int numCourses, int[][] prerequisites) {
        @SuppressWarnings("unchecked")
        Set<Integer>[] preSets  = new Set[numCourses], 
                       postSets = new Set[numCourses];
        Set<Integer> untaken = new HashSet<>();
        for(int i = 0; i < numCourses; i++){
            preSets[i] = new HashSet<>();
            postSets[i] = new HashSet<>();
            untaken.add(i);
        }
        for(int[] p: prerequisites){
            preSets[p[1]].add(p[0]);
            postSets[p[0]].add(p[1]);
        }
        
        Queue<Integer> takingQ = new LinkedList<>();        
        while(true){
            for(int i: untaken)
                if(preSets[i].isEmpty())
                    takingQ.offer(i);
            untaken.removeAll(takingQ);
            if(takingQ.isEmpty())
                return untaken.isEmpty();
            while(!takingQ.isEmpty()){
                int cur = takingQ.poll();
                for(int i: postSets[cur]){
                    if(!untaken.contains(i))
                        return false;
                    preSets[i].remove(cur);
                }
            }
        }
    }
    
    @Test
    public void test(){
        assertTrue(canFinish(2, new int[][]{{1, 0}}));
    }
    
    @Test
    public void test1(){
        assertFalse(canFinish(2, new int[][]{{1, 0}, {0, 1}}));
    }
}
