package com.brayden;

/**
 * https://oj.leetcode.com/problems/symmetric-tree/
 * <p>
 * Given a binary tree, check whether it is a mirror of itself (ie, symmetric
 * around its center).
 * 
 * @author brayden
 *
 */
public class SymmetricTree extends LeetcodeTree {
	
	private boolean isSymmetricInternal(TreeNode n1, TreeNode n2){
//		if(n1 == null && n2 == null)
//			return true;
//		if(n1 == null && n2 != null ||
//			n1 != null && n2 == null ||
//			n1.val != n2.val)
//			return false;
	    if(n1 == null || n2 == null)
	        return n1 == n2;
	    if(n1.val != n2.val)
	        return false;
		
		/** 
		 * now we know both n1 and n2 is not null and their value equals to
		 * each other. Next we compare their children.
		 */
		return isSymmetricInternal(n1.left, n2.right) &&
				isSymmetricInternal(n1.right, n2.left);
	}
	
    public boolean isSymmetric(TreeNode root) {
    	if(root == null)
    		return true;
    	return isSymmetricInternal(root.left, root.right);
    }
}
