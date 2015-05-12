package com.brayden;

public class RemoveLinkedListElements extends LeetcodeLinkList {
    public ListNode removeElements(ListNode head, int val) {
        if(head == null)
            return head;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        head = dummy;
        while(head.next != null)
            if(head.next.val == val)
                head.next = head.next.next;
            else 
                head = head.next;
        return dummy.next;
    }
    
    public ListNode removeElements1(ListNode head, int val) {
        if(head == null)
            return head;
        
        head.next = removeElements1(head.next, val);
        return head.val == val ? head.next : head;
    }
}
