package com.brayden;

/**
 * https://oj.leetcode.com/problems/balanced-binary-tree/
 * <p>
 * 
 * Given a binary tree, determine if it is height-balanced.
 * <p>
 * For this problem, a height-balanced binary tree is defined as a binary tree
 * in which the depth of the two subtrees of every node never differ by more 
 * than 1. 
 * 
 * @author brayden.zhang
 *
 */
public class BalancedBinaryTree extends LeetcodeTree {
	
    /** return -1 if not balanced; otherwise return the depth */
    private int isBanlancedInt(TreeNode root){
        if(root == null)
            return 0;
        
        int left = isBanlancedInt(root.left);
        if(left == -1)
            return -1;
        int right = isBanlancedInt(root.right);
        if(right == -1 || Math.abs(left - right) > 1)
            return - 1;
        
        return Math.max(left, right) + 1;
    }
    
    public boolean isBalanced(TreeNode root) {
        return isBanlancedInt(root) != -1;
    }
    
    
    
	private boolean isBalancedInternal(TreeNode n){
		if(n == null)
			return true;
		
		if(!isBalancedInternal(n.left) || !isBalancedInternal(n.right))
			return false;
		
//		int leftChildDepth  = getDepth(n.left);
//		int rightChildDepth = getDepth(n.right);
//		if(leftChildDepth - rightChildDepth < -1 ||
//		   leftChildDepth - rightChildDepth > 1)
		if(Math.abs(getDepth(n.left) - getDepth(n.right)) > 1)
			return false;
		
		return true;
	}

	public boolean isBalanced1(TreeNode root) {
        return isBalancedInternal(root);
    }
	
	/**
	 * To improve, using only one recursion.
	 */
}
