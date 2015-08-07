package com.brayden;

import static org.junit.Assert.*;

import org.junit.Test;

public class PalindromeLinkedList extends LeetcodeLinkList {
    public boolean isPalindrome(ListNode head) {
        if(head == null || head.next == null)
            return true;
        ListNode fast = head, slow = head, head2;
        while(fast.next != null && fast.next.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        head2 = reverse(slow.next);
        while(head2 != null && head2.val == head.val){
            head = head.next;
            head2 = head2.next;
        }
        return head2 == null;
    }
    
    protected ListNode reverse(ListNode head) {
        ListNode newHead = null;
        while(head != null){
            ListNode next = head.next;
            head.next = newHead;
            newHead = head;
            head = next;
        }
        return newHead;
    }
    
    @Test
    public void test(){
        assertFalse(isPalindrome(makeList(new int[]{1, 2})));
    }
    
    @Test
    public void test1(){
        assertTrue(isPalindrome(makeList(new int[]{1, 2, 1})));
    }
    
    @Test
    public void test2(){
        assertTrue(isPalindrome(makeList(new int[]{1, 2, 2, 1})));
    }
}
