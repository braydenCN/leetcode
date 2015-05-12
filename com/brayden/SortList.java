package com.brayden;

import org.junit.Test;

/**
 * https://oj.leetcode.com/problems/sort-list/
 * <p>
 * Sort a linked list in O(n log n) time using constant space complexity.
 * 
 * @author pengczha
 *
 */
public class SortList extends LeetcodeLinkList {

	private ListNode sortListInternal(ListNode head) {
        if(head.next == null) // one-node list
        	return head;
        
		ListNode head1 = separateListIntoHalves(head);
		return mergeIterately(sortListInternal(head), sortListInternal(head1));
	}
	
	public ListNode sortList(ListNode head) {
		if(head == null || head.next == null)
			return head;
		
        return sortListInternal(head);
	}

	@Test
	public void test(){
		assertLinkListEquals(makeList(new int[]{1, 2, 3}), 
				         sortList(makeList(new int[]{3, 2, 1})));
	}

}
