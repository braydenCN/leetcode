package com.brayden;

import org.junit.Test;

public class UniquePaths {
    public int uniquePaths(int m, int n) {
        if(m <= 0 || m <= 0)
            return 0;
        
        int[] pathCount = new int[n];  
        for(int i = 0; i < n; i++)
            pathCount[i] = 1;
        
        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++)
                pathCount[j] = pathCount[j - 1] + pathCount[j];
        }
        return pathCount[n - 1];
    }
    
    @Test
    public void test(){
        uniquePaths(1, 1);
    }
}
