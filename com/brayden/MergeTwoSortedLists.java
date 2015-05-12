package com.brayden;

public class MergeTwoSortedLists extends LeetcodeLinkList{
	
    public ListNode mergeTwoLists1(ListNode l1, ListNode l2) {
        return super.mergeRecursively(l1, l2);
    }
    
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        return super.mergeIterately(l1, l2);
    }
}
