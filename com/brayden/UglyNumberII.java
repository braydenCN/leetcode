package com.brayden;

import static org.junit.Assert.*;

import org.junit.Test;

public class UglyNumberII {
    public int nthUglyNumber(int n) {
        if (n < 1)
            return -1;
        int[] uglyNums = new int[n];
        uglyNums[0] = 1; 
        int ind2 = 0, ind3 = 0, ind5 = 0;
        for (int i = 1; i < n; i++) {
            int next2 = 2 * uglyNums[ind2];
            int next3 = 3 * uglyNums[ind3];
            int next5 = 5 * uglyNums[ind5];
            uglyNums[i] = min(next2, next3, next5);
            if (next2 == uglyNums[i])
                ind2++;
            if (next3 == uglyNums[i])
                ind3++;
            if (next5 == uglyNums[i])
                ind5++;
        }
        return uglyNums[n - 1];
    }

    private int min(int a, int b, int c) {
        return Math.min(Math.min(a, b), c);
    }
    
    @Test
    public void test() {
        /** 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 **/
        assertEquals(1, nthUglyNumber(1));
        assertEquals(2, nthUglyNumber(2));
        assertEquals(3, nthUglyNumber(3));
        assertEquals(4, nthUglyNumber(4));
        assertEquals(5, nthUglyNumber(5));
        assertEquals(6, nthUglyNumber(6));
        assertEquals(8, nthUglyNumber(7));
        assertEquals(9, nthUglyNumber(8));
        assertEquals(10, nthUglyNumber(9));
        assertEquals(12, nthUglyNumber(10));
    }
}
