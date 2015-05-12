package com.brayden;

import org.junit.Test;

public class ReverseLinkedListII extends LeetcodeLinkList {
    

    public ListNode reverseBetween(ListNode head, int m, int n) {
        if(head == null)
            return head;
        
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        
        ListNode pre = dummy;
        for(int i = 0; i < m - 1; i++)
            pre = pre.next;
        
        ListNode cur = pre.next, tail = pre.next;
        for(int i = 0; i < n - m + 1; i++){
            ListNode next = cur.next;
            cur.next = pre.next;
            pre.next = cur;
            cur = next;
        }
        
        tail.next = cur;
        return dummy.next;
    }
    
    public ListNode reverseBetween1(ListNode head, int m, int n) {
        if(head == null)
            return head;
        
        if(m == 1)
            return reverseFirstNthElements(head, n);
        
        ListNode preNode = getNthElement(head, m - 1);
        if(preNode != null && preNode.next != null){ // has no m nodes
            preNode.next = reverseFirstNthElements(preNode.next, n - m + 1);
        }
        
        return head;
    }
    
    @Test
    public void test(){
        assertLinkListEquals(makeList(new int[]{1, 4, 3, 2, 5}), 
                reverseBetween(makeList(new int[]{1, 2, 3, 4, 5}), 2, 4));
    }
    
    @Test
    public void test1(){
        /**
Submission Result: Wrong Answer
Input:  {3,5}, 1, 2
Output:     {3,5}
Expected:   {5,3}
         */
        assertLinkListEquals(makeList(new int[]{5, 3}), 
                reverseBetween(makeList(new int[]{3, 5}), 1, 2));
    }
}
