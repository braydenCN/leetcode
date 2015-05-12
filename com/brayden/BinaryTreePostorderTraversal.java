package com.brayden;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

import org.junit.Test;

public class BinaryTreePostorderTraversal extends LeetcodeTree {
    
    private static class Triple{
        TreeNode node;
        boolean leftDone, rightDone;
        public Triple(TreeNode node, boolean leftDone, boolean rightDone) {
            this.node = node;
            this.leftDone = leftDone;
            this.rightDone = rightDone;
        }
        
    }
    public List<Integer> postorderTraversal(TreeNode root) {
        if(root == null)
            return Collections.emptyList();
        
        Stack<Triple> stack = new Stack<>();
        List<Integer> resList = new ArrayList<>();
        
        stack.push(new Triple(root, false, false));
        while(!stack.isEmpty()){
            Triple triple = stack.pop();
            TreeNode curNode = triple.node;
            if(!triple.leftDone){
                stack.push(new Triple(curNode, true, false));
                if(curNode.left != null)
                    stack.push(new Triple(curNode.left, false, false));
            }else if(!triple.rightDone){
                stack.push(new Triple(curNode, true, true));
                if(curNode.right != null)
                    stack.push(new Triple(curNode.right, false, false));
            }else
                resList.add(curNode.val);
        }
        return resList;
    }
    
    @Test
    public void testPost(){
        assertListEquals(Arrays.asList(new Integer[]{3, 2, 1}), 
                postorderTraversal(makeTree("1, #, 2, #, #, 3")));
    }
}
