package com.brayden;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://oj.leetcode.com/problems/binary-tree-level-order-traversal-ii/
 * <p>
 * Given a binary tree, return the bottom-up level order traversal of its 
 * nodes' values. (ie, from left to right, level by level from leaf to root).
 * 
 * @author pengczha
 *
 */
public class BinaryTreeLevelOrderTraversalII extends LeetcodeTree {
	
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        if(root == null)
            return Collections.emptyList();
        
        List<List<Integer>> result = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while(!q.isEmpty()){
            List<Integer> tList = new ArrayList<>();
            int size = q.size();
            while(size-- > 0){
                TreeNode n = q.poll();
                if(n.left != null)
                    q.offer(n.left);
                if(n.right != null)
                    q.offer(n.right);
                tList.add(n.val);
            }
            result.add(tList);
        }
        
        Collections.reverse(result);
        return result;
    }
    
    
    public List<List<Integer>> levelOrderBottom1(TreeNode root) {
    	
    	/** get level order bottom, but in List<List<TreeNode> **/
    	List<List<TreeNode>> treeNodeListOfList = getTreeNodeListOfList(root);
    	
    	/** get the final result **/
    	return getResult(treeNodeListOfList);
    }

	private List<List<Integer>> getResult(
			List<List<TreeNode>> treeNodeOutterList) {
		List<List<Integer>> resultOutterList = new ArrayList<>();
    	List<Integer> resultInnerList;
    	int i = treeNodeOutterList.size() - 1;
    	for(; i >= 0; i--){
    		List<TreeNode> treeNodeInnerList = treeNodeOutterList.get(i);
    		resultInnerList = new ArrayList<>();
    		for(TreeNode n : treeNodeInnerList)
    			resultInnerList.add(n.val);
    		resultOutterList.add(resultInnerList);
    	}
    	return resultOutterList;
	}

	private List<List<TreeNode>> getTreeNodeListOfList(TreeNode root) {
		List<List<TreeNode>> outterList = new ArrayList<>();
		
		if(root == null)
			return outterList;
		
    	List<TreeNode> innerList = new ArrayList<>();
    	innerList.add(root);
    	outterList.add(innerList);
    	
    	boolean hasAnotherLevel = true; 
    	
    	List<TreeNode> lastList;
    	while(hasAnotherLevel){
    		hasAnotherLevel = false;
    		innerList = new ArrayList<>();
    		lastList = outterList.get(outterList.size() - 1);
    		for(TreeNode n : lastList){
    			if(n.left != null){
    				innerList.add(n.left);
    				hasAnotherLevel = true;
    			}
    			if(n.right != null){
    				innerList.add(n.right);
    				hasAnotherLevel = true;
    			}
    		}
    		if(hasAnotherLevel)
    			outterList.add(innerList);
    	}
    	return outterList;
	}
	
}
