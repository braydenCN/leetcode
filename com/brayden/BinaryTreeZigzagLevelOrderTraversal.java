package com.brayden;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.junit.Test;


public class BinaryTreeZigzagLevelOrderTraversal extends LeetcodeTree {
	
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if(root == null)
            return Collections.emptyList();
        
        List<List<Integer>> result = new ArrayList<>();
        Queue<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        boolean forward = true;
        while(!q.isEmpty()){
            LinkedList<Integer> list = new LinkedList<>();
            int size = q.size();
            while(size-- > 0){
                TreeNode node = q.poll();
                if(node.left != null)
                    q.offer(node.left);
                if(node.right != null)
                    q.offer(node.right);
                if(forward)
                    list.addLast(node.val);
                else
                    list.addFirst(node.val);
            }
            forward = !forward;
            result.add(list);
        }
        return result;
    }
    
    
    public List<List<Integer>> zigzagLevelOrder1(TreeNode root) {
        List<List<Integer>> resList = new ArrayList<>();
		if(root == null)
			return resList;
		
		Queue<TreeNode> curQ = new ArrayDeque<>();
		Queue<TreeNode> nextLevelQ = new ArrayDeque<>();
        
        curQ.offer(root);
        boolean forward = true;
        while(true){
        	resList.add(makeListFromQ(curQ, forward));
            while(!curQ.isEmpty()){
            	TreeNode curNode = curQ.poll();
        	    if(curNode.left != null)
            	    nextLevelQ.offer(curNode.left);
            	if(curNode.right != null)
            		nextLevelQ.offer(curNode.right);
            }
            if(nextLevelQ.isEmpty())
            	break;
            forward = !forward;
            curQ.addAll(nextLevelQ);
            nextLevelQ.clear();
        }
        return resList;
    }
    
    private List<Integer> makeListFromQ(Queue<TreeNode> q, boolean forward) {
    	List<Integer> list = new LinkedList<>();
    	for(TreeNode n: q){
    		if(forward)
    			list.add(n.val);
    		else
    			list.add(0, n.val);
    	}
    	return list;
	}

	@Test
    public void test(){
    	TreeNode root = makeTree("3,9,20,#,#,15,7");
    	List<List<Integer>> expected = new ArrayList<>();
    	expected.add(Arrays.asList(new Integer[]{3}));
    	expected.add(Arrays.asList(new Integer[]{20, 9}));
    	expected.add(Arrays.asList(new Integer[]{15, 7}));
    	
    	assertListOfListEquals(expected, zigzagLevelOrder(root));
    }
}
