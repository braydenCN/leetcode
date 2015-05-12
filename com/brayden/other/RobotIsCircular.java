package com.brayden.other;

import static org.junit.Assert.*;

import org.junit.Test;

public class RobotIsCircular {

    private static enum DIR {
        E, S, W, N;
        public static DIR turnLeft(DIR dir){
            switch(dir){
            case E: return N;
            case S: return E;
            case W: return S;
            case N: return W;
            }
            return dir;
        }
        
        public static DIR turnRight(DIR dir){
            switch(dir){
            case E: return S;
            case S: return W;
            case W: return N;
            case N: return E;
            }
            return dir;
        }
        
    };
    
    public boolean isCircular(String seq){
        int x = 0, y = 0;
        DIR dir = DIR.E;
        
        for(char c: seq.toCharArray()){
            switch(c){
            case 'L': dir = DIR.turnLeft(dir); break;
            case 'R': dir = DIR.turnRight(dir); break;
            case 'G': 
                switch(dir){
                case E: x++; break;
                case S: y--; break;
                case W: x--; break;
                case N: y++; break;
                }
                break;
            }
        }
        
        return x == 0 && y == 0;
    }
    
    @Test
    public void test(){
        assertTrue(isCircular("GLGLGLG"));
        assertTrue(isCircular("GLLG"));
    }
}
