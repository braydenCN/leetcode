package com.brayden;

public class RemoveNthNodeFromEndOfList extends LeetcodeLinkList {

	public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode nodeAhead = head, nodeBehind = head;
        int i = 0;
        for(; i < n && nodeAhead != null; i++)
        	nodeAhead = nodeAhead.next;
        if(i < n) // the list has less than n node
        	return head;
        if(nodeAhead == null) // we need to remove the head
        	return head.next;
        nodeAhead = nodeAhead.next;
        while(nodeAhead != null){
        	nodeAhead  = nodeAhead.next;
        	nodeBehind = nodeBehind.next;
        }
        
        /** now nodeBehind is at the previous node of Nth node from end **/
        nodeBehind.next = nodeBehind.next.next;
        
        return head;
	}
}
