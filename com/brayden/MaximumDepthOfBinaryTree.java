package com.brayden;

/**
 * https://oj.leetcode.com/problems/maximum-depth-of-binary-tree/
 * <p>
 * Given a binary tree, find its maximum depth.
 * <p>
 * The maximum depth is the number of nodes along the longest path from the 
 * root node down to the farthest leaf node.
 * 
 * @author pengczha
 *
 */
public class MaximumDepthOfBinaryTree extends LeetcodeTree {
	
    public int maxDepth(TreeNode n) {
    	if(n == null)
    		return 0;
//	
//    	if(n.left == null)
//			return maxDepth(n.right) + 1;
//		if(n.right == null)
//			return maxDepth(n.left) + 1;
		
		return Math.max(maxDepth(n.right), maxDepth(n.left)) + 1;
    }

    /**
     * To improve, no need for null check of left and right
     */
}
