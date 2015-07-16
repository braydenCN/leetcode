package com.brayden;

public class LCAOfBST extends LeetcodeTree {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || p == null || q == null)
            return null;
        int pVal = p.val, qVal = q.val;
        while(root != null)
            if(root.val < pVal && root.val < qVal)
                root = root.right;
            else if(root.val > pVal && root.val > qVal)
                root = root.left;
            else
                break;
        return root;
    }
    
}
