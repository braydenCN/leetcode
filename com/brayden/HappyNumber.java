package com.brayden;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class HappyNumber {
    public boolean isHappy(int n) {
        Set<Integer> set = new HashSet<>();
        while(n != 1 && !set.contains(n)){
            set.add(n);
            int next = 0;
            do{
                next += (n % 10) * (n % 10);
            }while((n /= 10) > 0);
            n = next;
        }
        return n == 1;
    }
    
    @Test
    public void test(){
        assertTrue(isHappy(1));
        assertTrue(isHappy(19));
        assertFalse(isHappy(Integer.MAX_VALUE));
    }
}
