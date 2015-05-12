package com.brayden;

import static org.junit.Assert.*;

import org.junit.Test;

public class LRUCacheTest {
    @Test
    public void test(){
        LRUCache1 cache = new LRUCache1(2);
        cache.set(2,1);
        cache.set(2,2);
        cache.get(2);
        cache.set(1,1);
        cache.set(4,1);
        cache.get(2);
    }
    
    @Test
    public void test1(){
        /**
Submission Result: Wrong Answer
Input:  2,[set(2,1),set(1,1),get(2),set(4,1),get(1),get(2)]
Output:     [1,1,-1]
Expected:   [1,-1,1]
         */
        LRUCache1 cache = new LRUCache1(2);
        cache.set(2,1);
        cache.set(1,1);
        assertEquals(1, cache.get(2));
        cache.set(4,1);
        assertEquals(-1, cache.get(1));
        assertEquals(1, cache.get(2));
    }
    
    @Test
    public void test3(){
        /**
Submission Result: Wrong Answer
Input:  2,[set(2,1),set(1,1),set(2,3),set(4,1),get(1),get(2)]
Output:     [-1,1]
Expected:   [-1,3]
         */
        LRUCache1 cache = new LRUCache1(2);
        cache.set(2,1);
        cache.set(1,1);
        cache.set(2,3);
        cache.set(4,1);
        assertEquals(-1, cache.get(1));
        assertEquals(3, cache.get(2)); 
    }
}
