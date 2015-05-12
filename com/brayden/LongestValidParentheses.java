package com.brayden;

import static org.junit.Assert.assertEquals;

import java.util.Stack;

import org.junit.Test;

public class LongestValidParentheses {
    public int longestValidParentheses1(String s) {
        if(s == null || s.length() <= 1)
            return 0;
        
        int longest = 0, curLongest = 0;
        Stack<Integer> levels = new Stack<>();
        char pre = s.charAt(0);
        
        for(int i = 1; i < s.length(); i++){
            char cur = s.charAt(i);
            if(pre == '(' && cur == '('){ // begin another level
                levels.push(curLongest);
                curLongest = 0;
            }else if(pre == ')' && cur == ')'){
                if(!levels.isEmpty()){  // end one level
                    curLongest += (2 + levels.pop());
                    if(curLongest > longest)
                        longest = curLongest;
                }else                  // invalid from current location
                    curLongest = 0;
            }else if(pre == '(' && cur == ')'){
                curLongest += 2;
                if(curLongest > longest)
                    longest = curLongest;
            }
            pre = cur;
        }
        return longest;
    }
    
    public int longestValidParentheses(String s) {
        if(s == null || s.length() < 2)
            return 0;
        
        int longest = 0, leftCount = 0, rightCount = 0;
        for(char c: s.toCharArray())
            switch(c){
            case '(': leftCount++; break;
            case ')': 
                if(leftCount == rightCount){
                    longest = Math.max(longest, 2 * rightCount);
                    leftCount = rightCount = 0;
                }
                else 
                    rightCount++;
            }
        
        leftCount = rightCount = 0;
        for(int i = s.length() - 1; i >= 0; i--)
            switch(s.charAt(i)){
            case ')': rightCount++; break;
            case '(': 
                if(leftCount == rightCount)
                    return Math.max(longest, 2 * leftCount);
                else 
                    leftCount++;
                break;
            }
        
        return longest;
    }
    
    @Test
    public void test(){
        assertEquals(2, longestValidParentheses("(()"));
    }
    
    @Test
    public void test1(){
        assertEquals(4, longestValidParentheses(")()())"));
    }
    
    @Test
    public void test2(){
        assertEquals(6, longestValidParentheses("())(()()))("));
    }    
    
    @Test
    public void test3(){
        assertEquals(2, longestValidParentheses("()(()"));
    }
    
    @Test
    public void test4(){
        assertEquals(0, longestValidParentheses("))))))))"));
    }
    
    @Test
    public void test5(){
        /**
Submission Result: Wrong Answer
Input:  "))))())()()(()"
Output:     6
Expected:   4
         */
        assertEquals(4, longestValidParentheses("))))())()()(()"));
    }
    
}
