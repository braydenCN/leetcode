package com.brayden;

public class PartitionList extends LeetcodeLinkList {
    
    public ListNode partition(ListNode head, int x) {        
        ListNode dummy1 = new ListNode(0), dummy2 = new ListNode(0);
        ListNode tail1 = dummy1, tail2 = dummy2;
        
        while(head != null){
            if(head.val < x){
                tail1.next = head;
                tail1 = head;
            }else{
                tail2.next = head;
                tail2 = head;
            }
            head = head.next;
        }
        
        tail2.next = null;
        tail1.next = dummy2.next;
        return dummy1.next;
    }
    
    public ListNode partition1(ListNode head, int x) {
        if(head == null)
            return head;
        
        ListNode head1 = null;
        ListNode tail1 = null;
        ListNode head2 = null;
        ListNode tail2 = null;
        
        ListNode curNode = head;
        while(curNode != null){
            if(curNode.val < x){
                if(head1 == null)
                    head1 = curNode;
                else
                    tail1.next = curNode;
                tail1 = curNode;
            }else{
                if(head2 == null)
                    head2 = curNode;
                else
                    tail2.next = curNode;
                tail2 = curNode;
            }
            curNode = curNode.next;
        }
        
        if(tail2 != null)
            tail2.next = null;
        if(head1 != null){
            tail1.next = head2;
            return head1;
        }
        return head2;
    }
}
