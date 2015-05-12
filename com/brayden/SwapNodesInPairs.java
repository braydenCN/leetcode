package com.brayden;

public class SwapNodesInPairs extends LeetcodeLinkList {
     public ListNode swapPairs(ListNode head) {
         if(head != null && head.next != null){
             ListNode nextNode = head.next;
             head.next = nextNode.next;
             nextNode.next = head;
             head = nextNode;
             head.next.next = swapPairs(head.next.next);
         }
         
         return head;
     }
     
     public ListNode swapPairs1(ListNode head) {
         ListNode dummyHead = new ListNode(0);
         dummyHead.next = head;
         
         ListNode pre = dummyHead;
         while(head != null && head.next != null){
             ListNode next = head.next.next;
             pre.next = head.next;
             head.next.next = head;
             head.next = next;
             pre = head;
             head = next;
         }
         
         return dummyHead.next;
     }
}
