package com.brayden;

import org.junit.Test;

public class ReverseNodesInKGroup extends LeetcodeLinkList {
    public ListNode reverseKGroup(ListNode head, int k) {
        if(k <= 1)
            return head;
        
        return reverseKGroupInternal(head, k);
    }
    
    public ListNode reverseKGroupInternal(ListNode head, int k) {
        if(head == null)
            return head;
        
        ListNode tail = head;
        int i = k;
        while(tail != null && --i > 0)
            tail = tail.next;
        if(tail == null)
            return head;
        tail.next = reverseKGroup(tail.next, k);
        
        ListNode cur = head, next, pivot = tail.next;
        while(cur.next != pivot){ // pivot could be null
            next = cur.next;
            cur.next = cur.next.next;
            next.next = head;
            head = next;
        }
        return head;
    }
    
    public ListNode reverseKGroup1(ListNode head, int k) {
        if(head == null || k < 2)
            return head;
        
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        
        ListNode pre = dummyHead;
        while(head != null){
            ListNode tail = head;
            int i = k;
            while(tail != null && --i > 0)
                tail = tail.next;
            if(tail == null)
                break;
            pre.next = tail.next;
            ListNode nextHead = tail.next, nextPre = head;
            
            while(head != nextHead){
                ListNode next = head.next;
                head.next = pre.next;
                pre.next = head;
                head = next;
            }
            pre = nextPre;
        }
        return dummyHead.next;
    }
    
    @Test
    public void test(){
        /**
Submission Result: Time Limit Exceeded
Last executed input:    {1,2,3}, 3
         */
        reverseKGroup1(makeList(new int[]{1, 2, 3}), 3);
    }
    
    @Test
    public void test1(){
        reverseKGroup1(makeList(new int[]{1, 2, 3, 4}), 2);
    }
}
