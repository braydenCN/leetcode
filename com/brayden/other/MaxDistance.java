package com.brayden.other;

import static org.junit.Assert.*;

import java.util.Stack;

import org.junit.Test;

/**
 * Given an array A of integers, find the maximum of j-i subjected to the constraint of A[i] < A[j].
 * @author pengczha
 *
 */
public class MaxDistance {
    
    public int maxDist(int[] arr){
        if(arr == null || arr.length < 2)
            return -1;
        Stack<Integer> stack = new Stack<>();
        int len = arr.length;
        for(int i = 0; i < len; i++)
            if(stack.isEmpty() || arr[stack.peek()] > arr[i])
                stack.push(i);
        
        int right = len - 1;
        int max = -1;
        while(!stack.isEmpty() && right >= 0){
            int left = stack.pop();
            while(right > left){
                if(arr[right] > arr[left]){
                    if(max < right - left)
                        max = right - left;
                    break;
                }
                right--;
            }
        }
        return max;
    }
    
    @Test
    public void test(){
        int[] input = new int[]{100, 101, 90, 91, 80, 79, 79, 79, 81, 10, 11, 12, 13};
        assertEquals(4, maxDist(input));
    }
}
