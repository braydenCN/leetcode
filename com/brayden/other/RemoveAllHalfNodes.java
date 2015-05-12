package com.brayden.other;

import static org.junit.Assert.*;

import org.junit.Test;

import com.brayden.LeetcodeTree;

public class RemoveAllHalfNodes extends LeetcodeTree {

    public TreeNode removeAllHalfNodes(TreeNode root){
        if(root == null || root.left == null && root.right == null)
            return root;
        
        if(root.left == null)
            return removeAllHalfNodes(root.right);
        
        if(root.right == null)
            return removeAllHalfNodes(root.left);
        
        root.left = removeAllHalfNodes(root.left);
        root.right = removeAllHalfNodes(root.right);
        return root;
    }
    
    @Test
    public void test(){
        TreeNode root = makeTree("2, 7, 5, #, 6, #, 9, #, #, 1, 11, #, #, 4");
        TreeNode expected = makeTree("2, 6, 4, 1, 11");
        assertTrue(isSameTree(expected, removeAllHalfNodes(root)));
    }
}
