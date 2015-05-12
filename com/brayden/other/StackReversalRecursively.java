package com.brayden.other;

import java.util.Stack;

import org.junit.Test;

import com.brayden.LeetcodeCommon;

public class StackReversalRecursively extends LeetcodeCommon {

    public void reverse(Stack<Integer> stack){
        if(stack.isEmpty())
            return;
        int tmp = stack.pop();
        reverse(stack);
        insertIntoBottom(stack, tmp);
    }

    private void insertIntoBottom(Stack<Integer> stack, int cur) {
        if(stack.isEmpty())
            stack.push(cur);
        else{
            int tmp = stack.pop();
            insertIntoBottom(stack, cur);
            stack.push(tmp);
        }
    }
    
    @Test
    public void test(){
        Stack<Integer> input = new Stack<>(), expected = new Stack<>();
        input.push(1); input.push(2); input.push(3);
        reverse(input);
        expected.push(3); expected.push(2); expected.push(1);
        assertStackEquals(input, expected);
    }
}
