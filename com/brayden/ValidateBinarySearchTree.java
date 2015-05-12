package com.brayden;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ValidateBinarySearchTree extends LeetcodeTree {

    public boolean isValidBST(TreeNode root) {
        return isValidBSTInt(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
    
    private boolean isValidBSTInt(TreeNode root, long min, long max) {
        if(root == null)
            return true;
        if(root.val >= max || root.val <= min)
            return false;
        
        return isValidBSTInt(root.left, min, root.val) &&
                isValidBSTInt(root.right, root.val, max);
    }

    protected boolean isValidBST1(TreeNode root) {
        return super.isValidBST(root);
    }
	@Test
    public void test(){
    	assertTrue(isValidBST(makeTree("2, 1, 3")));
    	preNode = null;
    	assertFalse(isValidBST(makeTree("1, 2, 3")));
    	preNode = null;
    	assertTrue(isValidBST(makeTree("3, 2, #, 1")));
    	preNode = null;
    	assertFalse(isValidBST(makeTree("10,5,15,#,#,6,20")));
    }
}
