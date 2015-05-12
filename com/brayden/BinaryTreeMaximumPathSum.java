package com.brayden;

import static org.junit.Assert.*;

import org.junit.Test;

public class BinaryTreeMaximumPathSum extends LeetcodeTree {

    private long maxPathSum = Long.MIN_VALUE;
    
    public int maxPathSum(TreeNode root) {
        maxPathSumInternal(root);
        return (int) maxPathSum;
    }

    private int maxPathSumInternal(TreeNode n) {
        if(n == null)
            return 0;
                    
        int maxPathToRootLeft  = maxPathSumInternal(n.left);
        int maxPathToRootRight = maxPathSumInternal(n.right);
         
        int maxPathSum = maxPathToRootLeft + maxPathToRootRight + n.val;
        if(maxPathSum > this.maxPathSum)
            this.maxPathSum = maxPathSum;
        
        int maxPathToRoot = 
                Math.max(maxPathToRootLeft, maxPathToRootRight) + n.val;
        return maxPathToRoot > 0 ? maxPathToRoot : 0;
    }
    
    @Test
    public void test(){
        /**
Submission Result: Wrong Answer
Input:      {-3}
Output:     0
Expected:   -3
         */
        assertEquals(-3, maxPathSum(makeTree("-3")));
    }

    @Test
    public void test1(){
    /**
Submission Result: Wrong Answer
Input:      {2,-1}
Output:     1
Expected:   2
     */
        assertEquals(2, maxPathSum(makeTree("2, -1")));
    }
    
    @Test
    public void test2(){
        assertEquals(4, maxPathSum(makeTree("2, -1, -4, 3")));
    }
}
