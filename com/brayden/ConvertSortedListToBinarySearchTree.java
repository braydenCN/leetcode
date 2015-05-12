package com.brayden;

public class ConvertSortedListToBinarySearchTree extends LeetcodeLinkList {
    public TreeNode sortedListToBST(ListNode head) {
        if(head == null)
        	return null;
        if(head.next == null)
        	return new TreeNode(head.val);
        			
    	ListNode head1 = separateListIntoHalves(head);
        TreeNode root = new TreeNode(head1.val);
        root.left  = sortedListToBST(head);
        root.right = sortedListToBST(head1.next);
        
        return root;
    }

}
