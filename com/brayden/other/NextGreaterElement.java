package com.brayden.other;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

import org.junit.Test;
import static org.junit.Assert.*;

import com.brayden.LeetcodeCommon;

public class NextGreaterElement extends LeetcodeCommon {
    
    public List<Integer> getNextGreaterElement1(int arr[]){
        if(arr == null || arr.length <= 1)
            return Collections.singletonList(-1);
        
        int len = arr.length;
        Integer[] resArr = new Integer[len];
        resArr[len - 1] = -1;
        Stack<Pair> stack = new Stack<>();
        for(int i = 0; i < len - 1; i++){
            int cur = arr[i], next = arr[i + 1];
            if(!stack.isEmpty() && cur > stack.peek().val)
                resArr[stack.pop().index] = cur;
            if(cur >= next)
                stack.push(new Pair(i, cur));
            else
                resArr[i] = next;
        }
        
        while(!stack.isEmpty())
            resArr[stack.pop().index] = -1;
        
        return Arrays.asList(resArr);
    }
    
    public void getNextGreaterElement(int arr[]){
        if(arr == null || arr.length <= 1)
            return;
        
        int len = arr.length;
        Stack<Integer> stack = new Stack<>();
        for(int i = len - 1; i >= 0; i--){
            int cur = arr[i];
            while(!stack.isEmpty() && cur >= stack.peek())
                stack.pop();
            
            arr[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(cur);
        }
    }
    
    @Test
    public void test(){
        int[] input = new int[]{11, 13, 21, 3};
        List<Integer> expected = Arrays.asList(new Integer[]{13, 21, -1, -1});
        assertListEquals(expected, getNextGreaterElement1(input));
    }
    
    @Test
    public void test1(){
        int[] input = new int[]{1, 2, 3, 2, 1, 2, 2};
        List<Integer> expected = 
                Arrays.asList(new Integer[]{2, 3, -1, -1, 2, -1, -1});
        assertListEquals(expected, getNextGreaterElement1(input));
    }
    
    @Test
    public void test2(){
        int[] input = new int[]{11, 13, 21, 3};
        int[] expected = new int[]{13, 21, -1, -1};
        getNextGreaterElement(input);
        assertArrayEquals(expected, input);
    }
    
    @Test
    public void test3(){
        int[] input = new int[]{1, 2, 3, 2, 1, 2, 2};
        int[] expected = new int[]{2, 3, -1, -1, 2, -1, -1};
        getNextGreaterElement(input);
        assertArrayEquals(expected, input);
    }
}
