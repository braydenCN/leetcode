package com.brayden;

import static org.junit.Assert.assertEquals;

import java.util.Stack;

import org.junit.Test;

public class BasicCalculator {
    public int calculate(String s) {
        if(s == null)
            return 0;
        s = reform(s);
        int result = 0, num = 0, base = 1;
        for(char c: s.toCharArray())
            switch(c){
            case '+': result += num; num = 0; base = 1; break;
            case '-': result -= num; num = 0; base = 1; break;
            default: num += (c - '0') * base; base *= 10;
            }
        return result;
    }

    private String reform(String s) {
        StringBuilder sb = new StringBuilder();
        Stack<Boolean> stack = new Stack<>();
        stack.push(true);
        boolean add = true;
        for(char c: s.toCharArray())
            switch(c){
            case ' ': break;
            case '(': stack.push(add); break;
            case ')': stack.pop(); break;
            case '+': 
                add = stack.peek(); 
                sb.append(stack.peek() ? '+' : '-'); 
                break;
            case '-': 
                add = !stack.peek(); 
                sb.append(stack.peek() ? '-' : '+'); 
                break;
            default: sb.append(c);
            }
        if(sb.charAt(0) != '+' || sb.charAt(0) != '-')
            sb.insert(0, '+');
        return sb.reverse().toString();
    }
    
    @Test
    public void test(){
        assertEquals(2, calculate("1 + 1"));
        assertEquals(3, calculate(" 2-1 + 2 "));
        assertEquals(23, calculate("(1+(4+5+2)-3)+(6+8)"));
    }
    
    @Test
    public void test1(){
        /**
Input:  "2-(5-6)"
Output:     -9
Expected:   3
         */
        assertEquals(3, calculate("2-(5-6)"));
    }
}
