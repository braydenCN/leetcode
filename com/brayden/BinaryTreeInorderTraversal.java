package com.brayden;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import org.junit.Test;

public class BinaryTreeInorderTraversal extends LeetcodeTree {
    
    private static class Pair{
        TreeNode node;
        boolean leftVisited = false;
        public Pair(TreeNode node, boolean leftVisited) {
            this.node = node;
            this.leftVisited = leftVisited;
        }
        @Override
        public String toString() {
            return "Pair [node=" + node + ", leftVisited=" + leftVisited + "]";
        }
    }
    
    public List<Integer> inorderTraversal(TreeNode root) {
    	List<Integer> resList = new LinkedList<>();
    	if(root == null)
    		return resList;
    	
        Stack<Pair> stack = new Stack<>();
        stack.push(new Pair(root, false));
        while(!stack.isEmpty()){
            Pair p = stack.pop();
            TreeNode curNode = p.node;
            if(p.leftVisited){
                resList.add(curNode.val);
                continue;
            }
            
            if(curNode.right != null)
                stack.push(new Pair(curNode.right, false));
            stack.push(new Pair(curNode, true));
            if(curNode.left != null)
                stack.push(new Pair(curNode.left, false));
        }
        return resList;
    }
    
    @Test
    public void test(){
        assertListEquals(Arrays.asList(new Integer[]{1, 3, 2}), 
                inorderTraversal(makeTree("1, #, 2, #, #, 3")));
    }
    
    /**
     * this simple code works...
     * (curr == null really means two cases: 
     *   1). curr is the leftmost node of some sub-tree; next its parent need
     *       to be processed;
     *   2). curr is the rightmost node of some sub-tree; leave this sub-tree
     *        now)
    Stack<TreeNode> todo = new Stack<TreeNode>();
    ArrayList<Integer> res = new ArrayList<Integer>();
    while(!todo.isEmpty() || curr != null){
        if(curr != null){
            todo.add(curr);
            curr = curr.left;
        }
        else{
            curr = (TreeNode)todo.pop();
            res.add(new Integer(curr.val));
            curr = curr.right;
        }
    }
    return res;
     */
    
    public List<Integer> preorderTraversal(TreeNode root) {
        Stack<TreeNode> todo = new Stack<>();
        List<Integer> res = new ArrayList<>();
        TreeNode curNode = root;
        while(!todo.isEmpty() || curNode != null){
            if(curNode != null){
                todo.push(curNode);
                res.add(curNode.val);
                curNode = curNode.left;
            }
            else
                curNode = todo.pop().right;
        }
        return res;
    }

    

    
    
    @Test
    public void testPre(){
        assertListEquals(Arrays.asList(new Integer[]{1, 2, 3}), 
                preorderTraversal(makeTree("1, #, 2, #, #, 3")));
    }
    

}
