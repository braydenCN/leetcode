package com.brayden;

public class LinkedListCycle extends LeetcodeLinkList {
	
	public boolean hasCycle(ListNode head){
		return !(super.findOneNodeInCycle(head) == null);
	}
    
    /**
     * To improve: 
while (fast != null && fast.next != null) {
    slow = slow.next;
    fast = fast.next.next;
    if (slow == fast) {
        return true;
    }
}
     */
}
