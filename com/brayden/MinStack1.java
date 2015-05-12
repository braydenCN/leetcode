package com.brayden;

import static org.junit.Assert.assertEquals;

import java.util.EmptyStackException;
import java.util.Stack;

import org.junit.Test;

/**
 * https://oj.leetcode.com/problems/min-stack/<p>
 *  
 *  Design a stack that supports push, pop, top, and retrieving the minimum 
 *  element in constant time.
 *
 *   <li>push(x) -- Push element x onto stack.
 *   <li>pop() -- Removes the element on top of the stack.
 *   <li>top() -- Get the top element.
 *   <li>getMin() -- Retrieve the minimum element in the stack.

 * @author brayden.zhang
 *
 */
public class MinStack1 {
    
    private Stack<Integer> minStack = new Stack<>();
    private Stack<Integer> realStack = new Stack<>();
    
    public void push(int x) {
        realStack.push(x);
        if(minStack.isEmpty() || x <= minStack.peek())
            minStack.push(x);
    }

    public void pop() {
        if(minStack.peek().equals(realStack.pop()))
            minStack.pop();
    }

    public int top() {
        return realStack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
    
    @Test
    public void test(){
         MinStack1 s = new MinStack1();
         s.push(4);
         s.push(6);
         s.push(-11);
         s.push(4);
         s.push(4);
         /** 
          * [4, 6, -11, 4, 4]
          * [4,    -11      ] 
          */
         assertEquals(-11, s.getMin());
         s.pop();
         /** 
          * [4, 6, -11, 4]
          * [4,    -11]
          */
         assertEquals(-11, s.getMin());
         s.push(-12);
         /** 
          * [4, 6, -11, 4, -12]
          * [4,    -11,    -12]
          */
         assertEquals(-12, s.getMin());
         s.pop();
         /** 
          * [4, 6, -11, 4]
          * [4,    -11   ] 
          */
         assertEquals(-11, s.getMin());
         s.pop();
         /** 
          * [4, 6, -11]
          * [4,    -11]
          */
         assertEquals(-11, s.getMin());
         s.pop();
         /** 
          * [4, 6]
          * [4   ]
          */
         assertEquals(4, s.getMin());
         s.pop();
         /** 
          * [4]
          * [4]
          */
         assertEquals(4, s.getMin());
    }
    
    @Test
    public void test1(){
        /**
Submission Result: Wrong Answer
Input:  push(512),push(-1024),push(-1024),push(512),pop,getMin,pop,getMin,pop,getMin
Output:     [-1024,-1024,-1024]
Expected:   [-1024,-1024,512]
         */
        MinStack1 s = new MinStack1();
        s.push(512);
        s.push(-1024);
        s.push(-1024);
        s.push(512);
        s.pop();
        assertEquals(-1024, s.getMin());
        s.pop();
        assertEquals(-1024, s.getMin());
        s.pop();
        assertEquals(512, s.getMin());
    }
}