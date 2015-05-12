package com.brayden;

import java.util.Stack;

public class EvaluateReversePolishNotation {
    public int evalRPN(String[] tokens) {
        if(tokens == null || tokens.length == 0)
            return -1;
        
        Stack<Integer> stack = new Stack<>();
        for(String token: tokens)
            switch(token){
            case "+":
                stack.push(stack.pop() + stack.pop()); break;
            case "-":
                stack.push(-stack.pop() + stack.pop()); break;
            case "*":
                stack.push(stack.pop() * stack.pop()); break;
            case "/":
                int tmp = stack.pop();
                stack.push(stack.pop() / tmp); break;
            default:
                stack.push(Integer.valueOf(token));
            }
        return stack.pop();
    }
}
