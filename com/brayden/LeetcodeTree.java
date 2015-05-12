package com.brayden;

public abstract class LeetcodeTree extends LeetcodeCommon {
	protected static class TreeLinkNode {
			int val;
			TreeLinkNode left, right, next;
			TreeLinkNode(int x) {
				val = x;
			}
			@Override
			public String toString() {
				return "TreeLinkNode [val=" + val + ", left=" + left
						+ ", right=" + right + ", next=" + next + "]";
			}
		}

	protected int getDepth(TreeNode n) {
		if (n == null)
			return 0;

		return Math.max(getDepth(n.right), getDepth(n.left)) + 1;
	}

	/** is the left child of parent; assume full binary tree **/
	protected static boolean leftChildOfParent(int i) {
		return i % 2 == 1;
	}

	/** get parent index; assume full binary tree **/
	protected static int parent(int i) {
		return (i - 1) / 2;
	}

	/** 
	 * https://oj.leetcode.com/problems/binary-tree-level-order-traversal-ii/ 
	 */
	protected static TreeNode makeTree(String treeSerialization) {
		String[] nodeNum = treeSerialization.replaceAll("\\s+", "").split(",");
		int len = nodeNum.length;
		TreeNode[] cache = new TreeNode[len];
		TreeNode root = new TreeNode(Integer.valueOf(nodeNum[0]));
		cache[0] = root;
		for(int i = 1; i < len; i++){
			TreeNode curNode = null;
			if(!nodeNum[i].equals("#")) 
				curNode = new TreeNode(Integer.valueOf(nodeNum[i]));
			cache[i] = curNode;
			
			TreeNode parent = cache[parent(i)];
			if(parent == null)
				continue;
			if(leftChildOfParent(i))
				parent.left = curNode;
			else
				parent.right = curNode;
			
		}
		return root;
	}

	protected boolean isSameTree(TreeNode p, TreeNode q) {
		if(p == null && q == null)
			return true;
		
		if(p == null && q != null ||
			p != null && q == null ||
			p.val != q.val)
			return false;
		
		return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
	}

    protected TreeNode cloneTree(TreeNode root) {
    	if(root == null)
    		return null;
    	
    	TreeNode newRoot = new TreeNode(root.val);
    	newRoot.left = cloneTree(root.left);
    	newRoot.right = cloneTree(root.right);
    	return newRoot;
    }

    protected TreeNode preNode;
    protected boolean isValidBST(TreeNode root) {
        if(root == null)
        	return true;
        
        if(root.left != null && !isValidBST(root.left) ||
        		preNode != null && preNode.val >= root.val)
        	return false;
        preNode = root;
        if(root.right != null && !isValidBST(root.right))
        	return false;
        
        return true;
    }

    protected boolean isLeaf(TreeNode node) {
        return node != null && node.left == null && node.right == null;
    }

}
