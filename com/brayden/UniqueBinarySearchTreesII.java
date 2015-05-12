package com.brayden;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

public class UniqueBinarySearchTreesII extends LeetcodeTree {
    public List<TreeNode> generateTrees(int n) {
    	List<TreeNode> resList = new LinkedList<>();
        if(n <= 0){
            resList.add(null);
        	return resList;
        }
        
    	TreeNode root1 = new TreeNode(1);
        resList.add(root1);
        
        List<TreeNode> tmpList = new LinkedList<>();
        for(int i = 2; i < n + 1; i++){
        	/** calculate from 2 node to n node BST **/
        	tmpList.clear();
        	tmpList.addAll(resList);
        	resList.clear();
        	
        	for(TreeNode root: tmpList){// for every trees with i-1 nodes
        		/** 
        		 * a new tree with node i as the root; add the old tree as 
        		 * root's left child 
                 */
        		TreeNode iNode = new TreeNode(i); 
        		iNode.left = root;
        		resList.add(iNode);

        		/** 
        		 * add a new tree for each j-th node on the rightmost edge,
        		 * include the NULL
        		 */
        		int j = 1;
        		TreeNode ind = root;
        		while(ind != null){
        			TreeNode newTree = cloneTree(root);
        			TreeNode parentOfjThNode = newTree;
        			for(int k = 0; k < j - 1; k++){
        				parentOfjThNode = parentOfjThNode.right; // j-th node's parent
        			}
        			iNode = new TreeNode(i);
        			iNode.left = parentOfjThNode.right;
        			parentOfjThNode.right = iNode;
        			resList.add(newTree);
        			ind = ind.right;
        			j++;
        		}
        	}
        }
        return resList;
    }

	@Test
	public void test(){
		assertEquals(1,       generateTrees(1).size());
		assertEquals(2,       generateTrees(2).size());
		assertEquals(5,       generateTrees(3).size());
		assertEquals(14,      generateTrees(4).size());
		assertEquals(742900,  generateTrees(13).size());
		assertEquals(9694845, generateTrees(15).size());
	}
}
