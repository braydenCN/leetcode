package com.brayden;

public class PopulatingNextRightPointersInEachNodeII extends LeetcodeTree{
    public void connect(TreeLinkNode root) {
        if(root == null)
            return;
        TreeLinkNode dummy = new TreeLinkNode(0);
        dummy.next = root;
        while(dummy.next != null){
            TreeLinkNode cur = dummy.next, pre = dummy;
            dummy.next = null;
            while(cur != null){
                if(cur.left != null){
                    pre.next = cur.left;
                    pre = pre.next;
                }
                if(cur.right != null){
                    pre.next = cur.right;
                    pre = pre.next;
                }
                cur = cur.next;
            }
        }
    }
}
