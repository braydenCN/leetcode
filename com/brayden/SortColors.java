package com.brayden;

public class SortColors {
    private final static int RED   = 0;
    private final static int WHITE = 1;
    private final static int BLUE  = 2;
    
    public void sortColors(int[] A) {
        /** 
         * get this really smart solution from the Leetcode discussion  
         */
        if(A == null || A.length == 0)
            return;
        
        int indRed = 0, indWhite = 0;
        for(int i = 0; i < A.length; i++){
            int v = A[i];
            A[i] = BLUE;
            switch(v){
            case WHITE:
                A[indWhite++] = WHITE; break;
            case RED:
                A[indWhite++] = WHITE;
                A[indRed++]   = RED;
                break;
            }
        }
    }
}
