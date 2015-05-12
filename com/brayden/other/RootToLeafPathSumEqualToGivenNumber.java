package com.brayden.other;

import static org.junit.Assert.*;

import org.junit.Test;

import com.brayden.LeetcodeTree;

public class RootToLeafPathSumEqualToGivenNumber extends LeetcodeTree {
    public boolean hasPathSum(TreeNode root, int sum){
        if(root == null)
            return false;
        return hasPathSumInt(root, sum, 0);
    }

    private boolean hasPathSumInt(TreeNode node, int sum, int pathSum) {
        int mySum = node.val + pathSum;
        
        if(node.left == null && node.right == null) // leaf
            return sum == mySum;
        
        if(node.left != null && hasPathSumInt(node.left, sum, mySum))
            return true;
        
        return node.right != null && hasPathSumInt(node.right, sum, mySum);
    }
    
    @Test
    public void test(){
        TreeNode root = makeTree("1, 2, 3, 4, 5");
        assertTrue(hasPathSum(root, 8));
        assertFalse(hasPathSum(root, 9));
    }
}
