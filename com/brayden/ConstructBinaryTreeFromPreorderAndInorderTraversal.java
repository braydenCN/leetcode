package com.brayden;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ConstructBinaryTreeFromPreorderAndInorderTraversal 
               extends LeetcodeTree {
    
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if(preorder == null || inorder == null || 
        		preorder.length == 0 || preorder.length != inorder.length)
        	return null;
        
        return buildTreeInternal(preorder,   0,  preorder.length, 
        		                 inorder,    0,  inorder.length);
    }
    
    private TreeNode buildTreeInternal(int[] preorder, int preBegin, int preEnd, 
    		                     int[] inorder, int inBegin, int inEnd) {
    	if(preBegin >= preEnd || inBegin >= inEnd )
    		return null;
    	
    	int rootVal       =  preorder[preBegin];
    	TreeNode root     =  new TreeNode(rootVal);
    	int rootIndexIn   =  getIndex(inorder, inBegin, inEnd, rootVal);
    	int rootIndexPre  =  rootIndexIn - inBegin + preBegin;
    	root.left  = buildTreeInternal(preorder, preBegin + 1, rootIndexPre + 1,
                                       inorder, inBegin, rootIndexIn);
    	root.right = buildTreeInternal(preorder, rootIndexPre + 1, preEnd, 
                                       inorder, rootIndexIn + 1, inEnd);
    	return root;
    }
    
    @Test
    public void test(){
    	assertTrue(isSameTree(
    			makeTree("1, #, 2, #, #, 3"),
    			buildTree(new int[]{1, 2, 3}, new int[]{1, 3, 2})));
    	
    }
}
