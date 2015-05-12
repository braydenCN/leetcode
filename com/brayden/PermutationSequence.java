package com.brayden;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class PermutationSequence {
    public String getPermutation(int n, int k) {
        if(n < 1)
            return "";
        
        long[] factorial = new long[n + 1];
        factorial[0] = 1;
        
        List<Integer> list = new ArrayList<>();
        for(int i = 1; i <= n; i++){
            factorial[i] = factorial[i - 1] * i;
            list.add(i);
        }
        if(k > factorial[n])
            return "";
        
        StringBuilder sb = new StringBuilder();
        k--;
        n--;
        while(n >= 0){
            int ind = (int) (k / factorial[n]);
            sb.append(list.remove(ind));
            k %= factorial[n--];
        }
        return sb.toString();
    }
    
    @Test
    public void test(){
        assertEquals("312",  getPermutation(3, 5));
        assertEquals("2431", getPermutation(4, 12));
    }
}
