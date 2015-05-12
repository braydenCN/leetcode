package com.brayden;

import static org.junit.Assert.*;

import org.junit.Test;

public class ConstructBinaryTreeFromInorderAndPostorderTraversal 
             extends LeetcodeTree {
	
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if(inorder == null || postorder == null || 
        		inorder.length == 0 || inorder.length != postorder.length)
        	return null;
        
        return buildTreeInternal(inorder,   0,  inorder.length, 
        		                 postorder, 0,  postorder.length);
    }
    
    private TreeNode buildTreeInternal(int[] inorder, int inBegin, int inEnd, 
    		                     int[] postorder, int postBegin, int postEnd) {
    	if(inBegin >= inEnd || postBegin >= postEnd )
    		return null;
    	
    	int rootVal       =  postorder[postEnd - 1];
    	TreeNode root     =  new TreeNode(rootVal);
    	int rootIndexIn   =  getIndex(inorder, inBegin, inEnd, rootVal);
    	int rootIndexPost =  rootIndexIn - inBegin + postBegin;
    	root.left  = buildTreeInternal(inorder, inBegin, rootIndexIn,
                                       postorder, postBegin, rootIndexPost);
    	root.right = buildTreeInternal(inorder, rootIndexIn + 1, inEnd, 
                                       postorder, rootIndexPost, postEnd - 1);
    	return root;
    }
    
    @Test
    public void test(){
    	assertTrue(isSameTree(
    			makeTree("1, #, 2, #, #, 3"),
    			buildTree(new int[]{1, 3, 2}, new int[]{3, 2, 1})));
    	
    }
}
