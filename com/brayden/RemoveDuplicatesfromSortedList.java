package com.brayden;

/**
 * https://oj.leetcode.com/problems/remove-duplicates-from-sorted-list/
 * <p>
 * Given a sorted linked list, delete all duplicates such that each element
 * appear only once. 
 * @author pengczha
 *
 */
public class RemoveDuplicatesfromSortedList extends LeetcodeLinkList{
	
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null)
            return head;

        ListNode cur = head;
        while(cur.next != null)
            if(cur.val == cur.next.val)
                cur.next = cur.next.next;
            else 
                cur = cur.next;
            
        return head;
    }
    
    public ListNode deleteDuplicates1(ListNode head) {
    	if(head == null)
    		return head;

    	ListNode curNode = head;
        ListNode nextNode = curNode.next;
        while(nextNode != null){
        	if(curNode.val == nextNode.val)
        		/** delete nextNode; no need to move curNode **/
        		curNode.next = nextNode.next;
        	else
        		curNode = nextNode;
        	nextNode = curNode.next;
        }
        return head;
    }

}
