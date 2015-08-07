package com.brayden.other;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import org.junit.Test;

public class GraphSearch {

    private static class Pair{
        char c;
        int duration;

        public Pair(char c, int duration) {
            this.c = c;
            this.duration = duration;
        }
    }
    
    public int search(String[] input){
        Map<Character, Set<Pair>> map = new HashMap<>();
        for(String s: input){
            char c1 = s.charAt(0), c2 = s.charAt(1);
            int duration = s.charAt(2) - '0';
            if(!map.containsKey(c1))
                map.put(c1, new HashSet<>());
            map.get(c1).add(new Pair(c2, duration));
        }
        
        int count = 0;
        Queue<Pair> q = new LinkedList<Pair>();
        q.offer(new Pair('C', 0));
        while(!q.isEmpty()){
            int size = q.size();
            while(size-- > 0){
                Pair cur = q.poll();
                for(Pair p: map.get(cur.c)){
                    if(cur.duration + p.duration >= 30)
                        continue;
                    if(p.c == 'C')
                        count++;
                    q.offer(new Pair(p.c, cur.duration + p.duration));
                }
            }
        }
        return count;
    }
    
    @Test
    public void test(){
        assertEquals(7, search(new String[]{"AB5", "BC4", "CD8", "DC8", "DE6", 
                "AD5", "CE2", "EB3", "AE7"}));
    }
}
