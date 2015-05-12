package com.brayden;

/**
 * Given a binary tree and a sum, determine if the tree has a root-to-leaf 
 * path such that adding up all the values along the path equals the given sum. 
 * @author brayden.zhang
 *
 */
public class PathSum extends LeetcodeTree {
	
	private boolean hasPathSumInternal(TreeNode n, int sum){
		/** yes, it means both the children is null, sigh... **/
		if(n.val == sum && n.left == null && n.right == null)
			return true;
		boolean result = false;
		if(n.left != null && hasPathSumInternal(n.left, sum - n.val))
			result = true;
		if(result != true && //stops if we already get an answer
				n.right != null && hasPathSumInternal(n.right, sum - n.val))
			result = true;
		return result;
	}
	
    public boolean hasPathSum(TreeNode root, int sum) {
    	if(root == null)
    		return false;
    	return hasPathSumInternal(root, sum);
    }
    
    /**
     * To improve, no need for result flag, just return directly;
     * n.left != null, n.right != null not needed, it becomes:
     * return hasPathSumInternal(n.left, sum - n.val) || 
     *     hasPathSumInternal(n.right, sum - n.val))
     */
    
    public boolean hasPathSum_updated(TreeNode root, int sum) {
    	if(root == null)
    		return false;
		if(root.val == sum && root.left == null && root.right == null)
			return true;
		return hasPathSumInternal(root.left, sum - root.val) || 
			   hasPathSumInternal(root.right, sum - root.val);
    }
}
