package com.brayden;

public class JumpGameII {
    public int jump(int[] A) {
        if(A == null || A.length <= 1)
            return 0;
        
        int len = A.length;
        
        int min[] = new int[len];
        min[0] = 0;
        
        for(int i = 1; i < len; i++){
            if(A[0] >= i){ // can reach by one jump
                min[i] = 1;
                continue;
            }
            int minJump = Integer.MAX_VALUE;
            for(int j = 1; j < i; j++)
                if(min[j] > 0 && j + A[j] >= i && min[j] + 1 < minJump)
                    minJump = min[j] + 1;
            if(minJump != Integer.MAX_VALUE)
                min[i] = minJump;
        }

        return min[len - 1];
    }
}
