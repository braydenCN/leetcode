package com.brayden;

import static org.junit.Assert.*;

import java.util.Stack;

import org.junit.Test;

public class FlattenBinaryTreeToLinkedList extends LeetcodeTree {
	private TreeNode preNode = null;
	
    public void flatten(TreeNode root) {
        if(root == null)
    	    return;
        
    	preNode = root;
    	TreeNode rightNode = root.right;
    	flattenInternal(root.left);
    	flattenInternal(rightNode);
    	
    	preNode.right = null;
    }

	private void flattenInternal(TreeNode n) {
		if(n == null)
			return;
		
		preNode.right = n;
		preNode.left  = null;
		preNode       = n;
    	TreeNode rightNode = n.right;// right node will be changed in recursion
		flattenInternal(n.left);
    	flattenInternal(rightNode);
	}
    
	public void flatten1(TreeNode root) {
        if(root == null)
            return;
        
        TreeNode cur = root, pre = null, tmp;
        Stack<TreeNode> stack = new Stack<>();
        while(cur != null || !stack.isEmpty())
            if(cur != null){
                if(cur.right != null)
                    stack.push(cur.right);
                tmp = cur;
                cur = cur.left;
                if(pre != null){
                    pre.right = tmp;
                    tmp.left = null;
                }
                pre = tmp;
            }else
                cur = stack.pop();
        
        root.left = null;
    }
	
	   public void flatten2(TreeNode root) {
	        if(root == null)
	            return;
	        
	        Stack<TreeNode> stack = new Stack<>();
	        stack.push(root);
	        TreeNode cur = root.left, pre = root;
	        while(cur != null || !stack.isEmpty())
	            if(cur != null){
	                pre.right = cur;
	                pre = cur;
	                stack.push(cur);
	                cur = cur.left;
	            }else
	                cur = stack.pop().right;
	        
	        root.left = null;
	    }
	
	@Test
	public void test(){
	    TreeNode root = makeTree("1, 2");
	    flatten2(root);
	    TreeNode expected = makeTree("1, #, 2");
	    assertTrue(isSameTree(expected, root));
	}
}
