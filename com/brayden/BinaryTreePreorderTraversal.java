package com.brayden;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BinaryTreePreorderTraversal extends LeetcodeTree {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> resultList = new ArrayList<>();
        
        Stack<TreeNode> stack = new Stack<>();
        
        while(root != null){
        	resultList.add(root.val);
            if(root.right != null)
            	stack.push(root.right);
            if(root.left != null)
            	root = root.left;
            else if(stack.isEmpty())
            	root = null; // so it ends
            else 
            	root = stack.pop();
        }
        
        return resultList;
    }
}
