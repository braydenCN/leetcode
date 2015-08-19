package com.brayden;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class BinaryTreePaths extends LeetcodeTree {
    public List<String> binaryTreePaths1(TreeNode root) {
        if (root == null)
            return Collections.emptyList();
        
        List<String> res = new LinkedList<>();
        btPaths(root, "", res);
        return res;
    }

    private void btPaths(TreeNode n, String path, List<String> res) {
        if (n.left == null && n.right == null) {
            res.add(path + n.val);
            return;
        }
        String curPath = path + n.val + "->";
        if (n.left != null)
            btPaths(n.left, curPath, res);
        if (n.right != null)
            btPaths(n.right, curPath, res);
    }
    
    public List<String> binaryTreePaths(TreeNode root) {
        if(root == null)
            return Collections.emptyList();
        
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
           if (cur != null) {
               if (cur.right != null)
                   stack.push(cur.right);
               stack.push(cur);
               cur = cur.left;
           } else {
               cur = stack.pop();
               if (cur.right != null && !stack.isEmpty() && 
                       cur.right == stack.peek()) {
                   stack.pop();
                   stack.push(cur);
                   cur = cur.right;
               } else {
                   // process
                   cur = null;
               }
               
           }
           
        }
    }
    
}
