package com.brayden;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.Stack;

import org.junit.Test;

public class LCAOfBinaryTree extends LeetcodeTree {
    
    public TreeNode lcaUtil(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || root == p || root == q)
            return root;

        TreeNode left = lcaUtil(root.left, p, q);
        TreeNode right = lcaUtil(root.right, p, q);
        
        if(left == null)
            return right;
        if(right == null)
            return left;
        return root;
    }
    
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || p == null || q == null)
            return null;
        if(root == p || root == q)
            return root;
        if(p == q)
            return p;

        return lcaUtil(root, p, q);
    }
    
    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || p == null || q == null)
            return null;
        if(root == p || root == q)
            return root;
        if(p == q)
            return p;
        
        LinkedList<TreeNode> lp = null, lq = null, list = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while(cur != null || !stack.isEmpty()){
            while(cur != null){
                if(cur.right != null)
                    stack.push(cur.right);
                stack.push(cur);
                list.addLast(cur);
                cur = cur.left;
            }
            
            cur = stack.pop();
            if(cur.right != null && !stack.isEmpty() 
                    && cur.right == stack.peek()){
                stack.pop();
                stack.push(cur);
                cur = cur.right;
            }else{
                if(cur == p)
                    lp = new LinkedList<>(list);
                if(cur == q)
                    lq = new LinkedList<>(list);
                list.removeLast();
                cur = null;
            }
        }
        
        int i = 0;
        for(; i < lp.size() && i < lq.size() && lp.get(i) == lq.get(i); i++);
        return lp.get(i - 1);
    }
    
    @Test
    public void test(){
        TreeNode root = makeTree("1, 2, 3");
        assertSame(root, lowestCommonAncestor(root, root.left, root.right));
    }
    
    @Test
    public void test2(){
        TreeNode root = makeTree("1, 2, 3");
        assertSame(root, lowestCommonAncestor(root, root.right, root.left));
    }
    
    @Test
    public void test1(){
        TreeNode root = makeTree("-1,0,3,-2,4,#,#,8");
        assertSame(root.left, lowestCommonAncestor(root, root.left, root.left.left.left));
    }
    
    @Test
    public void test3(){
        TreeNode root = makeTree("-1,0,3,-2,4,#,#,8");
        assertSame(root.left, lowestCommonAncestor(root, root.left.left.left, root.left));
    }
}
