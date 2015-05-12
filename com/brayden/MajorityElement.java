package com.brayden;

import java.util.Stack;

public class MajorityElement {
    public int majorityElement(int[] num) {
        if(num == null || num.length == 0)
            return 0;
        
        Stack<Integer> stack = new Stack<>();
        stack.push(num[0]);
        for(int i = 1; i < num.length; i++)
            /** so stack only contain the same numbers **/
            if(!stack.isEmpty() && stack.peek() != num[i])
                stack.pop();
            else stack.push(num[i]);
        
         return stack.peek();
    }
    
    public int majorityElement1(int[] num) {
        if(num == null || num.length == 0)
            return 0;
        
        int count = 1, cur = num[0];
        
        for(int i = 1; i < num.length; i++)
            if(cur == num[i])
                count++;
            else{
                count--;
                if(count == 0){
                    cur = num[i];
                    count = 1;
                }
            }
        
         return cur;
    }
}
