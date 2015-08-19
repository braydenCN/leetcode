package com.brayden;

import static org.junit.Assert.*;

import org.junit.Test;

public class AddDigits {
    public int addDigits(int num) {
        if (num < 10)
            return num;
        int res = num % 10;
        while ((num /= 10) > 0)
            res += num % 10;
        return addDigits(res);
    }
    
    @Test
    public void test() {
        assertEquals(2, addDigits(38));
    }
}
