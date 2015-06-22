package com.brayden;

import static org.junit.Assert.*;

import org.junit.Test;

public class BasicCalculatorII {
    public int calculate(String s) {
        if(s == null || s.isEmpty())
            return 0;
        
        char lastOp = '+', lastLowOp = '+';
        boolean lowLevel = true;
        int result = 0, resultH = 0, num = 0;
        
        s = s + "+"; // handle last operator
        for(char c: s.toCharArray()){
            if(c == ' ') continue;
            if(c >= '0' && c <= '9'){
                num = num * 10 + c - '0';
                continue;
            }
            if(c == '*' || c == '/'){
                if(lowLevel){
                    lastLowOp = lastOp;
                    resultH = num;
                    lowLevel = false;
                }else
                    resultH = compute(lastOp, resultH, num);
            }else{ // c == '+' || c == '-' 
                if(!lowLevel){
                    resultH = compute(lastOp, resultH, num);
                    result = compute(lastLowOp, result, resultH);
                    lowLevel = true;
                }else
                    result = compute(lastOp, result, num);
            }
            lastOp = c;
            num = 0;
        }
        return result;
    }

    private int compute(char operator, int result, int num) {
        switch(operator){
        case '+': return result + num;
        case '-': return result - num;
        case '*': return result * num;
        case '/': return result / num;
        default:
            return -1;
        }
    }
    
    @Test
    public void test(){
        /**
"3+2*2" = 7
" 3/2 " = 1
" 3+5 / 2 " = 5
         */
        assertEquals(7, calculate("3+2*2"));
        assertEquals(1, calculate(" 3/2 "));
        assertEquals(5, calculate(" 3+5 / 2 "));
    }
    
    @Test
    public void test1(){
        assertEquals(-2, calculate("-1*2"));
        assertEquals(2, calculate("1*2"));
        assertEquals(-1, calculate("-1"));
        assertEquals(-2, calculate(" -1-1 "));
        assertEquals(4, calculate("1+2*3/4+1+2*2/3"));
    }
}
