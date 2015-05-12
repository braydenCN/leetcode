package com.brayden;

import org.junit.Test;

public class RemoveDuplicatesFromSortedListII extends LeetcodeLinkList {
    
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null)
            return head;
        
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        
        ListNode pre = dummy;
        ListNode cur = pre.next;
        while(cur != null && cur.next != null){
            if(cur.val == cur.next.val){
                do{
                    cur = cur.next;
                }while(cur.next != null && cur.val == cur.next.val);
                pre.next = cur.next;
            }else
                pre = cur;
            cur = cur.next;
        }
        
        return dummy.next;
    }
    
    public ListNode deleteDuplicates1(ListNode head) {
        if(head == null || head.next == null)
            return head;
        
        while(head != null){
            ListNode lastDup = getLastDup(head);
            if(lastDup == head)
                break;
            head = lastDup.next;
        }
        
        if(head == null || head.next == null)
            return head;
        
        /** so head != head.next now **/
        ListNode preNode = head;
        ListNode curNode = head.next;
        
        while(curNode != null){
            ListNode lastDup = getLastDup(curNode);
            if(lastDup == curNode){ // no dup
                preNode.next = curNode;
                preNode = curNode;
            }
            curNode = lastDup.next;
        }

        preNode.next = null;
        return head;
    }
    
    private ListNode getLastDup(ListNode n){
        ListNode lastDup = n;
        while(n.next != null && n.next.val == n.val){
            lastDup = n.next;
            n = n.next;
        }
        return lastDup;
    }
    
    @Test
    public void test(){
        /**
Submission Result: Wrong Answer
Input:      {1,2,2}
Output:     {1,2}
Expected:   {1}
         */
        assertLinkListEquals(makeList(new int[]{1}), 
                deleteDuplicates(makeList(new int[]{1, 2, 2})));
    }
    
    @Test
    public void test1(){
        /**
Submission Result: Runtime Error
Runtime Error Message:  Line 35: java.lang.NullPointerException
Last executed input:    {1,1}
         */
        assertLinkListEquals(null, 
                deleteDuplicates(makeList(new int[]{1, 1})));
    }
    
    @Test
    public void test2(){
        /**
Submission Result: Wrong Answer
Input:  {1,2,2,3}
Output:     {1,2,3}
Expected:   {1,3}
         */
        assertLinkListEquals(makeList(new int[]{1, 3}), 
                deleteDuplicates(makeList(new int[]{1, 2, 2, 3})));
    }
}
