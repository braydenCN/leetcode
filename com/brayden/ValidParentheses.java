package com.brayden;

import static org.junit.Assert.*;

import java.util.Stack;

import org.junit.Test;

public class ValidParentheses {
    public boolean isValid(String s) {
    	if(s == null)
    		return true;
    	
        Stack<Character> stack = new Stack<>();
        for(char c: s.toCharArray())
        	switch(c){
        	case '(': case '{': case '[':
        	    stack.push(c); break;
        	case ')': 
        		if(stack.isEmpty() || stack.pop() != '(')
        			return false;
        		break;
        	case '}':
        		if(stack.isEmpty() || stack.pop() != '{')
        			return false;
        		break;
        	case ']':
        		if(stack.isEmpty() || stack.pop() != '[')
        			return false;
        	}
        return stack.isEmpty();
    }
    
    @Test
    public void test(){
    	assertTrue(isValid("abcde"));
    	assertTrue(isValid("(a){b}[c]"));
    	assertTrue(isValid("$(x{s}e[d]d)"));
    	assertTrue(isValid("((()))"));
    	assertTrue(isValid("(({}))"));
    	assertFalse(isValid("["));
    	assertFalse(isValid("}"));
    	assertFalse(isValid("({}))"));
    	assertFalse(isValid("({}})"));
    }
}
