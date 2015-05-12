package com.brayden;

public class ConvertSortedArrayToBinarySearchTree extends LeetcodeTree {
    public TreeNode sortedArrayToBST(int[] num) {
        if(num == null || num.length == 0)
        	return null;
        if(num.length == 1)
        	return new TreeNode(num[0]);

        return sortedArrayToBSTInternal(num, 0, num.length);
    }
    
    public TreeNode sortedArrayToBSTInternal(int[] num, int begin, int end) {
    	if(begin >= end)
    		return null;
    	
    	int mid = ((end - begin) >> 1) + begin;
        TreeNode root = new TreeNode(num[mid]);
        root.left  = sortedArrayToBSTInternal(num,  begin,   mid);
        root.right = sortedArrayToBSTInternal(num,  mid + 1, end);
        
        return root;
    }
}
