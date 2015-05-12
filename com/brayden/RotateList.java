package com.brayden;

import org.junit.Test;

public class RotateList extends LeetcodeLinkList {
    public ListNode rotateRight(ListNode head, int n) {
        if(head == null || n <= 0)
            return head;
        
        /** find last (n+1)-th node and the tail node **/
        ListNode nodeAhead = head;
        while(n-- > 0)
            if(nodeAhead.next == null)
                nodeAhead = head;
            else 
                nodeAhead = nodeAhead.next;
        if(nodeAhead == head) // this means to rotate m*(length of list)
            return head;
        
        ListNode nodeBehind = head;
        while(nodeAhead.next != null){
            nodeAhead  = nodeAhead.next;
            nodeBehind = nodeBehind.next;
        }
        
        /** 
         * now nodeBehind is the last (n+1)-th node;
         * nodeAhead is at the tail node
         */
        ListNode newHead = nodeBehind.next;
        nodeBehind.next = null;
        nodeAhead.next = head;
        
        return newHead;
    }
    
    @Test
    public void test(){
        /**
Submission Result: Wrong Answer
Input:      {1}, 0
Output:     {}
Expected:   {1}
         */
        assertLinkListEquals(makeList(new int[]{1}), 
                rotateRight(makeList(new int[]{1}), 0));
    }
    
    @Test
    public void test1(){
        /**
Submission Result: Wrong Answer
Input:      {1,2}, 3
Output:     {1,2}
Expected:   {2,1}
         */
        assertLinkListEquals(makeList(new int[]{2, 1}), 
                rotateRight(makeList(new int[]{1, 2}), 3));
    }
    
    @Test
    public void test2(){
        /**
Submission Result: Wrong Answer
Input:      {1}, 1
Output:     {}
Expected:   {1}
         */
        assertLinkListEquals(makeList(new int[]{1}), 
                rotateRight(makeList(new int[]{1}), 1));
    }    
    
    /** 
     * To improve, maybe it makes more sense to first get list len, 
     * then do n %= len
     */
}
