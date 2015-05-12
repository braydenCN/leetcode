package com.brayden;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import org.junit.Test;

public class IntersectionOfTwoLinkedLists extends LeetcodeLinkList {
	
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    	if(headA == null || headB == null)
    		return null;
    	
        return firstCommonNode(headA, headB);
    }
	
	@Test
	public void test(){
		ListNode node = makeList(new int[]{2, 3});
		assertSame(node.next, getIntersectionNode(node.next, node));
		
		assertNull(getIntersectionNode(node, makeList(new int[]{3})));
	}
}
