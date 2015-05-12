package com.brayden;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

public class LargestNumber {
    public String largestNumber(int[] num) {
        if(num == null || num.length == 0)
            return "";
        
        if(Arrays.stream(num).allMatch(n -> n == 0))
            return "0";
        
        StringBuffer sb = new StringBuffer();
        Arrays.stream(num)
              .mapToObj(n -> String.valueOf(n))
              .sorted((a, b) -> -((a + b).compareTo(b + a)))
              .forEachOrdered(n -> sb.append(n));
        
        return sb.toString();
    }
    
    @Test
    public void test(){
        /**
Submission Result: Runtime Error
Runtime Error Message:  Line 20: java.lang.IllegalArgumentException: Comparison method violates its general contract!
Last executed input:    [26,33,19,29,61,66,52,37,7,76,89,93,72,2,82,11,9,41,47,76,80,28,86,30,99,25,99,85,96,98,88,33,4,94,25,80,19,55,82,71,29,61,15,2,57,98,15,91,27,95,47,38,66,2,78,26,77,33,23,90,73,27,20,5,38,23,35,29,13,46,6,71,58,37,89,28,8,1,8,73,81,83,77,22,63,36,62,89,94,43,25,86,53,21,94,9,40,19,24,21]
         */
        assertEquals("99999998989695949494939190898989888886868583828281808078777777676737372717166666636261615857555535247474644341403838373736353333333029292928282727262625252524232322222212120191919151513111", 
                largestNumber(new int[]{26,33,19,29,61,66,52,37,7,76,89,93,72,2,82,11,9,41,47,76,80,28,86,30,99,25,99,85,96,98,88,33,4,94,25,80,19,55,82,71,29,61,15,2,57,98,15,91,27,95,47,38,66,2,78,26,77,33,23,90,73,27,20,5,38,23,35,29,13,46,6,71,58,37,89,28,8,1,8,73,81,83,77,22,63,36,62,89,94,43,25,86,53,21,94,9,40,19,24,21}));
    }
    
    @Test
    public void test1(){
        assertEquals("12121", largestNumber(new int[]{121, 12}));
    }
}
