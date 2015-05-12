package com.brayden;

import org.junit.Test;

public class InsertionSortList extends LeetcodeLinkList {
	
    public ListNode insertionSortList(ListNode head) {
        ListNode dummy = new ListNode(0);
        while(head != null){
            ListNode next = head.next, pre = dummy;
            while(pre.next != null && pre.next.val <= head.val)
                pre = pre.next;
            head.next = pre.next;
            pre.next = head;
            head = next;
        }
        return dummy.next;
    }
    
    public ListNode insertionSortList1(ListNode head) {
        if(head == null || head.next == null)
        	return head;
        
        ListNode next = head.next;
        head.next = null;
        while(next != null){
	        ListNode cur = next;
    		next = next.next;
    		
        	if(cur.val < head.val){ 
        		/** special case to insert before head **/
        		cur.next = head;
        		head = cur;
        		continue;
        	}
        	
        	ListNode i = head;
        	for(; i.next != null && cur.val >= i.next.val; i = i.next);
        	insertAfter(i, cur);
        }
        
        return head;
    }

	@Test
	public void test(){
		assertLinkListEquals(makeList(new int[]{1, 1}), insertionSortList(makeList(new int[]{1, 1})));
	}
	
	@Test
    public void test1(){
        assertLinkListEquals(makeList(new int[]{1, 2}), insertionSortList(makeList(new int[]{2, 1})));
    }
	@Test
    public void test2(){
        assertLinkListEquals(makeList(new int[]{1, 2, 3}), insertionSortList(makeList(new int[]{2, 3, 1})));
    }
	@Test
    public void test3(){
        assertLinkListEquals(makeList(new int[]{1, 1, 2}), insertionSortList(makeList(new int[]{2, 1, 1})));
    }
}
