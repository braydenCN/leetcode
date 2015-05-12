package com.brayden;

import static org.junit.Assert.*;

public class ReverseBits {

    public int reverseBits(int n) {
        long result = 0L;
        long mask   = 1L << 31;
        while(n != 0){
            if((n & 1L) > 0)
                result += mask;
            n >>>= 1;
            mask >>= 1;
        }
        return (int)result;
    }

    
    public static void main(String[] args){
        int x = new ReverseBits().reverseBits(1);
        x = new ReverseBits().reverseBits(1 << 31);
        return;
    }
    /**
Submission Result: Wrong Answer
Input:  2147483648 (10000000000000000000000000000000)
Output:     0 (00000000000000000000000000000000)
Expected:   1 (00000000000000000000000000000001)
     */
}
