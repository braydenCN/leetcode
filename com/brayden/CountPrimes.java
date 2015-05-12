package com.brayden;

import java.util.BitSet;

import org.junit.Test;
import static org.junit.Assert.*;

public class CountPrimes {
    
    public int countPrimes(int n) {
        BitSet bs = new BitSet(n);
        bs.set(0); bs.set(1);
        int ind = 0, count = 0;
        while(ind < n){
            ind = bs.nextClearBit(ind + 1);
            if(ind >= n)
                return count;
            count++;
            for(int i = 2 * ind; i < n; i += ind)
                bs.set(i);
        }
        return count;
    }
    
    @Test
    public void test(){
        assertEquals(4, countPrimes(10));
        assertEquals(0, countPrimes(1));
        assertEquals(0, countPrimes(2));
        assertEquals(1, countPrimes(3));
        assertEquals(8, countPrimes(20));
    }
}
