package com.brayden;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://oj.leetcode.com/problems/minimum-depth-of-binary-tree/
 * <p>
 * Given a binary tree, find its minimum depth.
 * The minimum depth is the number of nodes along the shortest path from the 
 * root node down to the nearest leaf node.
 * 
 * @author brayden.zhang
 *
 */
public class MinimumDepthOfBinaryTree extends LeetcodeTree {
	
    public int minDepth(TreeNode root) {
        if(root == null)
            return 0;
        
        Queue<TreeNode> q = new LinkedList<>();
        int count = 1;
        q.add(root);
        while(!q.isEmpty()){
            int size = q.size();
            while(size-- > 0){
                TreeNode n = q.poll();
                if(n.left == null && n.right == null)
                    return count;
                if(n.left != null)
                    q.offer(n.left);
                if(n.right != null)
                    q.offer(n.right);
            }
            count++;
        }
        return -1;//should never get here
    }
    
    
    public int minDepth2(TreeNode root) {
        if(root == null)
            return 0;

        if(root.left == null)
            return minDepth(root.right) + 1;
        if(root.right == null)
            return minDepth(root.left) + 1;
        
        return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
    }
    
	public int minDepthInternal(TreeNode n){
		if(n.left == null && n.right == null)
			return 1;
		if(n.left == null)
			return minDepthInternal(n.right) + 1;
		if(n.right == null)
			return minDepthInternal(n.left) + 1;
		return Math.min(minDepthInternal(n.right), 
				minDepthInternal(n.left)) + 1;
	}

	public int minDepth1(TreeNode root) {
    	if(root == null)
    		return 0;
		return minDepthInternal(root);
    }
}
