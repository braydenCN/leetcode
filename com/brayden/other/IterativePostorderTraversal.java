package com.brayden.other;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

import org.junit.Test;

import com.brayden.LeetcodeTree;

public class IterativePostorderTraversal extends LeetcodeTree {

    public List<Integer> postorderTraversal(TreeNode root){
        if(root == null)
            return Collections.emptyList();
        
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while(cur != null || !stack.isEmpty()){
            while(cur != null){
                if(cur.right != null)
                    stack.push(cur.right);
                stack.push(cur);
                cur = cur.left;
            }
            
            cur = stack.pop();
            if(cur.right != null && !stack.isEmpty() 
                    && cur.right == stack.peek()){
                stack.pop();
                stack.push(cur);
                cur = cur.right;
            }else{
                list.add(cur.val);
                cur = null;
            }
        }
        
        return list; 
    }
    
    @Test
    public void test(){
        TreeNode root = makeTree("1, 2, 3, 4, 5, #, 6, 7");
        List<Integer> expected = Arrays.asList(7, 4, 5, 2, 6, 3, 1);
        assertListEquals(expected, postorderTraversal(root));
    }
}
