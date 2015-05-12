package com.brayden.other;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import org.junit.Test;

import com.brayden.LeetcodeTree;

public class FindTheClosestLeafInABinaryTree extends LeetcodeTree {
    public int findClosest(TreeNode root, int key){
        if(root == null)
            return 0;
        
        Stack<TreeNode> stack = new Stack<>();
        if(!findAncestors(root, key, stack) || isLeaf(stack.peek()))
            return 0;
        Queue<TreeNode> q = new LinkedList<>();
        Queue<TreeNode> nextQ = new LinkedList<>();
        TreeNode lastInPath = stack.pop();
        q.offer(lastInPath);
        int closest = 0;
        boolean firstRound = true;
        while(!q.isEmpty() || !stack.isEmpty()){
            closest++;
            while(!q.isEmpty()){
                TreeNode cur = q.poll();
                if(cur.left != null){
                    if(isLeaf(cur.left))
                        return closest;
                    nextQ.offer(cur.left);
                }
                if(cur.right != null){
                    if(isLeaf(cur.right))
                        return closest;
                    nextQ.offer(cur.right);
                }
            }
            if(!stack.isEmpty() && !firstRound){
                firstRound = false;
                TreeNode node = stack.pop();
                TreeNode sibling = 
                        (node.left == lastInPath) ? node.right : node.left;
                if(isLeaf(sibling))
                    return closest;
                nextQ.offer(sibling);
                lastInPath = node;
            }
            Queue<TreeNode> tmp = nextQ;
            q = tmp;
            nextQ = q;
        }
        return closest;
    }

    private boolean findAncestors(TreeNode root, int key, Stack<TreeNode> stack) {
        if(root == null)
            return false;
        
        stack.push(root);
        if(root.val == key)
            return true;
        if(findAncestors(root.left, key, stack) || findAncestors(root.right, key, stack))
            return true;
        stack.pop();
        return false;
    }
    
    private boolean findAncestors1(TreeNode root, int key, Stack<TreeNode> resStack) {
        if(root == null)
            return false;
        
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while(!stack.isEmpty() || cur != null){
            if(cur != null){
                stack.push(cur);
                cur = cur.left;
            }else{
                cur = stack.pop().right;
            }
        }
        if(root.val == key)
            return true;
        if(findAncestors(root.left, key, resStack) || findAncestors(root.right, key, resStack))
            return true;
        resStack.pop();
        return false;
    }
    
    private static TreeNode root = makeTree("1, 2, 3, 4, 5, #, #, 6, #, 7, #, #, #, #, #, 8, 9");
    @Test
    public void test(){
        assertEquals(1, findClosest(root, 1));
        assertEquals(2, findClosest(root, 2));
        assertEquals(2, findClosest(root, 4));
    }
}
