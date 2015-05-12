package com.brayden;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * 
 * @author pengczha
 *
 */
public class LeetcodeLinkList extends LeetcodeCommon {

	protected static class RandomListNode {
            int label;
            RandomListNode next, random;
            RandomListNode(int x) { this.label = x; }
        }

    protected ListNode makeList(int[] inputArr){
		ListNode head = new ListNode(inputArr[0]);
		ListNode tmp  = head;
		for(int i = 1; i < inputArr.length; i++){
			tmp.next = new ListNode(inputArr[i]);
			tmp = tmp.next;
		}
		return head;
	}
	
	protected void assertLinkListEquals(ListNode a, ListNode b) {
		while(a != null && b != null){
			assertEquals(a.val, b.val);
			a = a.next; 
			b = b.next;
		}
		assertTrue(a == null && b == null);
	}

	protected int getLength(ListNode head) {
		int count = 1;
		while((head = head.next) != null)
			count++;
		return count;
	}

	protected ListNode mergeIterately(ListNode l1, ListNode l2) {
	    if(l1 == null)
	    	return l2;
	    if(l2 == null)
	    	return l1;
	    
	    ListNode dummyHead = new ListNode(-1);
	    ListNode cur = dummyHead;
	    
	    while(l1 != null && l2 !=null){
	    	if(l1.val < l2.val){
	        	cur.next = l1;
	        	l1 = l1.next;
	        }else{
	        	cur.next = l2;
	        	l2 = l2.next;
	        }
	    	cur = cur.next;
	    }
	    
	    if(l1 != null)
	    	cur.next = l1;
	    if(l2 != null)
	    	cur.next = l2;
	    
	    return dummyHead.next;
	}

	protected ListNode mergeRecursively(ListNode l1, ListNode l2) {
        if(l1 == null)
        	return l2;
        if(l2 == null)
        	return l1;
        
        if(l1.val < l2.val){
        	l1.next = mergeRecursively(l1.next, l2);
        	return l1;
        }else{
        	l2.next = mergeRecursively(l1, l2.next);
        	return l2;
        }
    }
	
	protected void insertAfter(ListNode pre, ListNode n) {
		n.next = pre.next;
		pre.next = n;
	}

	protected ListNode reverse1(ListNode head) {
	    if(head == null)
	        return head;
	    
	    ListNode curNode  =  head.next;
	    ListNode nextNode =  null;
	    head.next         =  null;
	    
	    while(curNode != null){
	        nextNode      =  curNode.next;
	        curNode.next  =  head;
	        head          =  curNode;
	        curNode       =  nextNode;
	    }
	    return head;
	}
	
    protected ListNode reverse(ListNode head) {
        ListNode curNode = head, tmpNode;
        head = null;
        
        while(curNode != null){
            tmpNode = curNode;
            curNode = curNode.next;
            tmpNode.next = head;
            head = tmpNode;
        }
        return head;
    }	
	
//    @Test
    public void testReverse(){
        ListNode input = makeList(new int[]{1});
        assertLinkListEquals(input, reverse(input));
        
        input = makeList(new int[]{1, 2});
        ListNode expected = makeList(new int[]{2, 1});
        assertLinkListEquals(expected, reverse(input));
        
        input = makeList(new int[]{1, 2, 3});
        expected = makeList(new int[]{3, 2, 1});
        assertLinkListEquals(expected, reverse(input));
        
        
    }
    
	protected ListNode getPreMiddleNode(ListNode head){
    	if(head == null)
    		return null;
    	
        ListNode slowNode =  head;
        ListNode fastNode =  head;
        while(fastNode.next != null && fastNode.next.next != null && 
        		fastNode.next.next.next != null){
            slowNode      =  slowNode.next;
            fastNode      =  fastNode.next.next;
        }
        
        /**
         * so if listLen = 3, slowNode points to the 1st node;
         * if listLen = 4, slowNode points to the 2nd node;
         * if listLen = 5, slowNode points to the 2nd node;
         * if listLen = 6, slowNode points to the 3rd node;
         * ...
         */
        return slowNode;
    }
	
    protected ListNode getMiddleNode(ListNode head){
    	if(head == null)
    		return null;
    	
        ListNode slowNode =  head;
        ListNode fastNode =  head;
        while(fastNode.next != null && fastNode.next.next != null){
            slowNode      =  slowNode.next;
            fastNode      =  fastNode.next.next;
        }
        
        /**
         * so if listLen = 3, slowNode points to the 2nd node;
         * if listLen = 4, slowNode points to the 2nd node;
         * if listLen = 5, slowNode points to the 3rd node;
         * if listLen = 6, slowNode points to the 3rd node;
         * ...
         */
        return slowNode;
    }
	
	/** 
	 * the list should contain at least two nodes 
	 * the first list has node number equals or one less than the second
	 * one 
	 */
	protected ListNode separateListIntoHalves(ListNode head) {
//		int len = getLength(head);
//		ListNode head1 = head;
//		for(int i = 0; i < len / 2 - 1; i++)
//			head1 = head1.next;
		ListNode head1 = getPreMiddleNode(head);
		head = head1.next;
		head1.next = null;
		return head;
	}
	
	/** 
	 * V2: the first list has node number equals or one more than the second
	 * one 
     */
	protected ListNode separateListIntoHalvesV2(ListNode head) {
//		int len = getLength(head);
//		ListNode head1 = head;
//		for(int i = 0; i < (len - 1) / 2; i++)
//			head1 = head1.next;
		
		ListNode head1 = getMiddleNode(head);
		head = head1.next;
		head1.next = null;
		return head;
	}

	protected ListNode firstCommonNode(ListNode head1, ListNode head2) {
		int lenDiff = getLength(head1) - getLength(head2);
		
		if(lenDiff > 0)
			for(int i = 0; i < lenDiff; i++)
				head1 = head1.next;
		else
			for(int i = 0; i > lenDiff; i--)
				head2 = head2.next;
		
		while(head1 != head2){
			head1 = head1.next;
			head2 = head2.next;
		}
		
		return head1;
	}

	/**
	 * 
	 * @param head
	 * @return a node in cycle or null if no cycle detected
	 */
	protected ListNode findOneNodeInCycle(ListNode head) {
	    ListNode fastNode = head;
	    ListNode slowNode = head;
	    while(fastNode != null && slowNode != null){
	    	slowNode = slowNode.next;
	    	fastNode = fastNode.next;
	    	if(fastNode == null)
	    		return null;
	    	fastNode = fastNode.next;
	    	
	    	if(fastNode == slowNode && fastNode != null)
	    		return fastNode;
	    }
	    return null;
	}

    /** return null if list contains no n items **/
    protected ListNode getNthElement(ListNode head, int n) {
        while(head != null && --n > 0)
            head = head.next;
        
        return head;
    }

    protected ListNode reverseFirstNthElements1(ListNode head, int n) {
        ListNode curNode = head.next;
        ListNode tailNode = head;
        
        while(curNode != null && --n > 0){
            ListNode nextNode = curNode.next;
            curNode.next = head;
            head = curNode;
            curNode = nextNode;
        }
        
        tailNode.next = curNode;
        return head;
    }
    
    protected ListNode reverseFirstNthElements(ListNode head, int n) {
        ListNode newHead = null, tail = head;
        
        while(head != null && n-- > 0){
            ListNode next = head.next;
            head.next = newHead;
            newHead = head;
            head = next;
        }
        
        tail.next = head;
        return newHead;
    }
}
