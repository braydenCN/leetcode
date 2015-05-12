package com.brayden;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import org.junit.Test;

public class GrayCode extends LeetcodeCommon {
    public List<Integer> grayCode(int n) {
        List<Integer> resultList;
        if(n <= 0)
            return new ArrayList<>(Arrays.asList(new Integer[]{0}));
        
        Stack<Integer> curStack = new Stack<>();
        Stack<Integer> nextStack = new Stack<>();
        curStack.add(0);
        curStack.add(1);
        
        int pow = 1;
        for(int i = 1; i < n; i++){
            nextStack.addAll(curStack);
            pow *= 2;
            while(!curStack.isEmpty())
                nextStack.push(pow + curStack.pop());
            Stack<Integer> tmp = curStack;
            curStack = nextStack;
            nextStack = tmp;
        }
        
        resultList = new ArrayList<>(curStack);
        return resultList;
    }
    
    @Test
    public void test(){
        assertListEquals(Arrays.asList(new Integer[]{0, 1, 3, 2}), grayCode(2));
    }
    
    @Test
    public void test2(){
        Stack<Integer> s = new Stack<>();
        s.push(1);
        s.push(2);
        List<Integer> resultList = new ArrayList<>(s);
        System.out.println(resultList);
    }
}
