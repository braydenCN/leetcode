package com.brayden;

import org.junit.Test;

public class AddTwoNumbers extends LeetcodeLinkList {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if(l1 == null)
            return l2;
        if(l2 == null)
            return l1;

        int tmp          =  l1.val + l2.val;
        int val          =  tmp % 10;
        int carry        =  tmp / 10;
        ListNode newHead =  new ListNode(val);
        ListNode cur     =  newHead;;
        l1               =  l1.next;
        l2               =  l2.next;
        
        while(l1 != null && l2 != null){
            tmp          =  l1.val + l2.val + carry;
            val          =  tmp % 10;
            carry        =  tmp / 10;
            cur.next     =  new ListNode(val);
            cur          =  cur.next;
            l1           =  l1.next;
            l2           =  l2.next;
        }
            
        while(l1 != null){
            tmp          =  l1.val + carry;
            val          =  tmp % 10;
            carry        =  tmp / 10;
            cur.next     =  new ListNode(val);
            cur          =  cur.next;
            l1           =  l1.next;
        }
        
        while(l2 != null){
            tmp          =  l2.val + carry;
            val          =  tmp % 10;
            carry        =  tmp / 10;
            cur.next     =  new ListNode(val);
            cur          =  cur.next;
            l2           =  l2.next;
        }
        
        if(carry == 1)
            cur.next     =  new ListNode(carry);
            
        return newHead;
    }
    
    public ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        if(l1 == null)
            return l2;
        if(l2 == null)
            return l1;

        int val, carry = 0;
        ListNode dummyHead = new ListNode(-1);
        ListNode cur = dummyHead;
        
        while(l1 != null || l2 != null){
            val = carry;
            if(l1 != null)
                val += l1.val;
            if(l2 != null)
                val += l2.val;
            carry = 0;
            if(val >= 10){
                val -= 10;
                carry = 1;
            }
            cur.next = new ListNode(val);
            cur = cur.next;
            if(l1 != null)
                l1 = l1.next;
            if(l2 != null)
                l2 = l2.next;
        }
        
        if(carry == 1)
            cur.next = new ListNode(carry);
            
        return dummyHead.next;
    }
    
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        return addTwoNumbersInt(l1, l2, 0);
    }
    
    private ListNode addTwoNumbersInt(ListNode l1, ListNode l2, int carry) {
        if(l1 == null)
            return addNumber(l2, carry);
        if(l2 == null)
            return addNumber(l1, carry);
        
        int val = carry + l1.val + l2.val;
        carry = 0;
        if(val >= 10){
            val -= 10;
            carry = 1;
        }
        ListNode cur = new ListNode(val);
        cur.next = addTwoNumbersInt(l1.next, l2.next, carry);
        return cur;
    }

    private ListNode addNumber(ListNode l, int carry) {
        if(carry == 0)
            return l;
        if(l == null) // && carry == 1)
            return new ListNode(1);
        
        l.val++;
        if(l.val == 10){
            l.val = 0;
            l.next = addNumber(l.next, 1);
        }
        return l;
    }

    @Test
    public void test(){
        /**
Submission Result: Wrong Answer
Input:  {5}, {5}
Output:     {0}
Expected:   {0,1}
         * YES, last carry missing!!
         */
    }
}
