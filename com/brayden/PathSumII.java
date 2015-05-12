package com.brayden;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class PathSumII extends LeetcodeTree {
	
	List<List<Integer>> resList;
	
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
    	resList = new ArrayList<>();
        if(root == null)
        	return resList;

        List<Integer> pathList = new ArrayList<>();
        pathSumInternal(root, sum, pathList);
        
        return resList;
    }

	private void pathSumInternal(TreeNode n, int sum, List<Integer> pathList) {
		if(n.left == null && n.right == null && n.val != sum)
			return;

		List<Integer> list = cloneList(pathList);
		list.add(n.val);
		
		if(n.left == null && n.right == null && n.val == sum){
				resList.add(list);
				return;
			}
		
		if(n.left != null)
			pathSumInternal(n.left, sum - n.val, list);
		if(n.right != null)
			pathSumInternal(n.right, sum - n.val, list);		
	}

	@Test
	public void test(){
		TreeNode root = makeTree("1, 2");
		List<List<Integer>> expectedList = new ArrayList<>();
		assertListOfListEquals(expectedList, pathSum(root, 0));
		
		root = makeTree("5, 4, 8, 11, #, 13, 4, 7, 2, #, #, #, #, 5, 1");
		expectedList.clear();
		expectedList.add(Arrays.asList(new Integer[]{5, 4, 11, 2}));
		expectedList.add(Arrays.asList(new Integer[]{5, 8, 4, 5}));
		assertListOfListEquals(expectedList, pathSum(root, 22));
		
	}
}
