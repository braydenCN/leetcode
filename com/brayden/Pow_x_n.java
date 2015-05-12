package com.brayden;

import static org.junit.Assert.*;

import org.junit.Test;

public class Pow_x_n {
    public double pow(double x, int n) {
//        if(n < 0)
//            return -1;
        boolean negativeN = false;
        if(n < 0){
            negativeN = true;
            n = -n;
        }
            
        double result =  1;
        while(n > 0){
            if(n % 2 == 1)
                result *= x;
            n >>= 1;
            x *= x;
        }
        return negativeN ? 1 / result : result;
    }
    
    @Test
    public void test(){
        assertEquals(Math.pow(2, 2), pow(2, 2), 0.000001);
        assertEquals(Math.pow(10, 5), pow(10, 5), 0.000001);
    }
    
    @Test
    public void test1(){
        /**
Submission Result: Wrong Answer
Input:  34.00515, -3
Output:     -1.00000
Expected:   0.00003
        */
        assertEquals(Math.pow(34.00515, -3), pow(34.00515, -3), 0.000001);
    }
}
