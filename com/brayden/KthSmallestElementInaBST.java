package com.brayden;

import static org.junit.Assert.assertEquals;

import java.util.Stack;

import org.junit.Test;

public class KthSmallestElementInaBST extends LeetcodeTree {
    
    private int val = 0, count = 0;
    boolean found = false;
    
    public int kthSmallest1(TreeNode root, int k){
        if(root == null){
            count = 0;
            return val;
        }
        kthSmallest1(root.left, k);
        if(found)
            return val;
        if(count == k - 1){
            found = true;
            val = root.val;
        }else{
            int cur = count + 1;
            kthSmallest1(root.right, k - count - 1);
            if(!found)
                count += cur;
        }
        return val;
    }
    
    public int kthSmallest(TreeNode root, int k){
        Stack<TreeNode> stack = new Stack<>();
        while(root != null){
            stack.push(root);
            root = root.left;
        }
        int count = 0;
        while(root != null || !stack.isEmpty())
            if(root != null){
                stack.push(root);
                root = root.left;
            }else{
                if(++count == k)
                    return stack.peek().val;
                root = stack.pop().right;
            }
        return -1;
    }
    
    @Test
    public void test(){
        TreeNode root = makeTree("2, 1");
        assertEquals(2, kthSmallest(root, 2));
    }
}
