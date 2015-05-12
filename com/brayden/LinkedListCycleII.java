package com.brayden;

import static org.junit.Assert.*;

import org.junit.Test;

public class LinkedListCycleII extends LeetcodeLinkList {
	
    public ListNode detectCycle(ListNode head) {
    	ListNode node = findOneNodeInCycle(head);
    	if(node == null)
    		return node;
    	return findBeginningCycleNode(head, node);
    }

	private ListNode findBeginningCycleNode(ListNode head1, 
			ListNode nodeInCycle) {
		ListNode head2   =  nodeInCycle.next;
		nodeInCycle.next =  null; // break the cycle
		return firstCommonNode(head1, head2);
	}
    
	@Test
	public void test(){
		ListNode node = makeList(new int[]{1, 2});
		node.next.next = node;
		assertNotNull(detectCycle(node)); // both nodes are ok
		
		node = makeList(new int[]{1, 2, 3});
		node.next.next.next = node.next;
		assertSame(node.next, detectCycle(node));
	}
	
	/**
	 * To improve, by calculation, the two lists always have the same length!
	 */
}
