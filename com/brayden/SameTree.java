package com.brayden;

/**
 * https://oj.leetcode.com/problems/same-tree/
 * <p>
 * Given two binary trees, write a function to check if they are equal or not.
 * <p>
 * Two binary trees are considered equal if they are structurally identical 
 * and the nodes have the same value. 
 * 
 * @author pengczha
 *
 */
public class SameTree extends LeetcodeTree {

	public boolean isSameTree1(TreeNode p, TreeNode q) {
		return super.isSameTree(p, q);
	}
    
    /** 
     * To improve, see this: (But mine is more readable)
    if(p==null || q==null)
        return p == q;
    return (p.val == q.val) && 
        isSameTree(p.left, q.left) && isSameTree(p.right, q.right); 
     */
}
