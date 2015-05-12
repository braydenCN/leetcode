package com.brayden;

import java.util.Stack;

public class BSTIterator extends LeetcodeTree {
    
    Stack<TreeNode> stack = new Stack<>();
    TreeNode cur = null;
    
    public BSTIterator(TreeNode root) {
        if(root == null)
            return;
        
        cur = root;
        while(cur != null){
            stack.push(cur);
            cur = cur.left;
        }
        cur = stack.pop();
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stack.isEmpty() || cur != null;
    }

    /** @return the next smallest number */
    public int next() {
        int result = -1;
        while(!stack.isEmpty() || cur != null)
            if(cur != null){
                stack.push(cur);
                cur = cur.left;
            }else{
                result = stack.peek().val;
                cur = stack.pop().right;
                break;
            }

        return result;
    }
}
