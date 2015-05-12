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
public class MinStack {
    
    private static class MinPair{
         int min;
         /** the position for the first item of value = min **/
         int pos; 
         MinPair(int m, int p){this.min = m; this.pos = p;}
    }
    
    /** this stack records where a min value entered **/ 
    private Stack<MinPair> minStack = new Stack<>();
    
    /** the real stack for storing items **/
    private Stack<Integer> realStack = new Stack<>();
    
    /** this is the current min value in the stack **/
    private int min;
    
    public void push(int x) {
         if(realStack.isEmpty() || min > x){
        	  /** first encounter this 'min' value **/
              min = x;
              minStack.push(new MinPair(min, realStack.size()));
         }
         realStack.push(x);
    }

    public void pop() {
         realStack.pop();
        if(!minStack.isEmpty() && (minStack.peek().pos == realStack.size())){
        	 /** yes, this is the position we got items into our minStack **/
             minStack.pop();
             if(!minStack.isEmpty())
                  min = minStack.peek().min;
        }
    }

    public int top() {
        return realStack.peek();
    }

    public int getMin() {
         if(realStack.isEmpty())
              throw new EmptyStackException();
        return min;
    }
    
    @Test
    public void test(){
         MinStack s = new MinStack();
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
}