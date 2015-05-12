package com.brayden;

import org.junit.Test;


public class ReorderList extends LeetcodeLinkList{
	
	public void reorderList(ListNode head) {
        if(head == null || head.next == null)
        	return;
    	
        ListNode head1 = head;
    	ListNode head2 = reverse(separateListIntoHalvesV2(head));
        ListNode tmpNode;
        while(head2 != null){
        	tmpNode    =  head2.next;
        	head2.next =  head1.next;
        	head1.next =  head2;
        	head1      =  head2.next;
        	head2      =  tmpNode;
        }
    }
    
    @Test
    public void test(){
    	
    	ListNode resultList = makeList(new int[]{1, 2, 3});
    	reorderList(resultList);
    	
    	assertLinkListEquals(makeList(new int[]{1, 3, 2}), resultList);
    }
    
}
