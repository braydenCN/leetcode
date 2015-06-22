package com.brayden;

import static org.junit.Assert.*;

import org.junit.Test;

public class CountCompleteTreeNodes extends LeetcodeTree {
    public int countNodes(TreeNode root) {
        int depth = depth(root);
        return countNodesInt(root, depth);
    }

    private int countNodesInt(TreeNode root, int depth) {
        if(root == null)
            return 0;
        if(completeTree(root.left, depth - 1))
            return count(depth - 1) + countNodesInt(root.right, depth - 1) + 1;
        return count(depth - 2) + countNodesInt(root.left, depth - 1) + 1;
    }

    private int count(int depth){
        return (1 << depth) - 1;
    }
    
    private boolean completeTree(TreeNode root, int depth) {
        while(root != null && depth-- > 0)
            root = root.right;
        return depth == 0;
    }

    private int depth(TreeNode root) {
        int depth = 0;
        for(; root != null; depth++, root = root.left);
        return depth;
    }
    
    @Test
    public void test1(){
        TreeNode root = makeTree("1");
        assertEquals(1, countNodes(root));
    }
    
    @Test
    public void test2(){
        TreeNode root = makeTree("1, 1");
        assertEquals(2, countNodes(root));
    }
    
    @Test
    public void test3(){
        TreeNode root = makeTree("1, 1, 1");
        assertEquals(3, countNodes(root));
    }
    
    @Test
    public void test4(){
        TreeNode root = makeTree("1, 1, 1, 1");
        assertEquals(4, countNodes(root));
    }
}
