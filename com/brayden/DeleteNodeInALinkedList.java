package com.brayden;

public class DeleteNodeInALinkedList extends LeetcodeLinkList {
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
