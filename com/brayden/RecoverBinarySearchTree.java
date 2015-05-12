package com.brayden;

import static org.junit.Assert.assertTrue;

import java.util.Stack;

import org.junit.Test;

public class RecoverBinarySearchTree extends LeetcodeTree {
    
    public void recoverTree(TreeNode root) {
        TreeNode node1 = null, node2 = null;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root, pre = root;
        while(preNode.left != null)
            preNode = preNode.left;
        
        while(!stack.isEmpty() || cur != null)
            if(cur != null){
                stack.push(cur);
                cur = cur.left;
            }else{
                cur = stack.pop();
                if(pre.val > cur.val){
                    if(node1 == null)
                        node1 = pre;
                    node2 = cur;
                }
                pre = cur;
                cur = cur.right;
            }
        
        if(node1 == null || node2 == null)
            return;
        
        int tmp = node1.val;
        node1.val = node2.val;
        node2.val = tmp;
    }
    
    
    private TreeNode node1 = null, node2 = null, preNode;
    
    public void recoverTree1(TreeNode root) {
        if(root == null)
            return;
        
        preNode = root;
        while(preNode.left != null)
            preNode = preNode.left;
        
        findSwappedNodes(root);
        if(node1 == null || node2 == null)
            return;
        
        int tmp = node1.val;
        node1.val = node2.val;
        node2.val = tmp;
    }
    
    private void findSwappedNodes(TreeNode root) {
        if(root == null)
            return;
        
        findSwappedNodes(root.left);
        if(preNode.val > root.val){
            if(node1 == null)
                node1 = preNode;
            node2 = root;
        }
        preNode = root;
        findSwappedNodes(root.right);
    }

    @Test
    public void test(){
        TreeNode root = makeTree("2, 3, 1");
        recoverTree(root);
        assertTrue(isValidBST(root));
    }
    
    @Test
    public void test1(){
        TreeNode root = makeTree("1, 2, 3");
        recoverTree(root);
        assertTrue(isValidBST(root));
    }
    
    @Test
    public void test2(){
        TreeNode root = makeTree("1, 2");
        recoverTree(root);
        assertTrue(isValidBST(root));
    }
    
    @Test
    public void test3(){
        TreeNode root = makeTree("1, 2, 4, 3");
        recoverTree(root);
        assertTrue(isValidBST(root));
    }
    
    @Test
    public void test4(){
        TreeNode root = makeTree("3, 2, 1, 4");
        recoverTree(root);
        assertTrue(isValidBST(root));
    }
    
    @Test
    public void test5(){
        TreeNode root = makeTree("3, 1, 2");
        recoverTree(root);
        assertTrue(isValidBST(root));
    }
}
