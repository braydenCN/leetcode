package com.brayden;

public class UniquePathsII {
    
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if(obstacleGrid == null || obstacleGrid.length == 0 || 
                obstacleGrid[0].length == 0)
            return 0;
        
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        
        int[] pathCount = new int[n];
        pathCount[0] = isObstacle(obstacleGrid, 0, 0) ? 0 : 1;
        for(int i = 1; i < n; i++)
            pathCount[i] = 
                isObstacle(obstacleGrid, 0, i) ? 0 : pathCount[i - 1];
            
        for(int i = 1; i < m; i++){
            if(isObstacle(obstacleGrid, i, 0))
                pathCount[0] = 0;
            for(int j = 1; j < n; j++)
                pathCount[j] = 
                    isObstacle(obstacleGrid, i, j) ? 0 
                        : pathCount[j - 1] + pathCount[j];
        }
        return pathCount[n - 1];
    }

    private boolean isObstacle(int[][] obstacleGrid, int i, int j) {
        return obstacleGrid[i][j] == 1;
    }

    /**
     * To improve, use a (m+1)*(n+1) matrix to avoid dealing with the edge
     */
}
