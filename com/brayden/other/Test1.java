package com.brayden.other;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.Test;

public class Test1 {
    @Test
    public void test() {
        Collections.sort(new ArrayList<String>(), (s1, s2) -> s1.compareTo(s2));
    }
    
    
    public List<String> permutation(List<String> inputList){
        return permutationInt(inputList, 0, new StringBuffer());
    }
    
    
}
