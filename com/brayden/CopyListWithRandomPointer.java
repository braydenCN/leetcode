package com.brayden;

import org.junit.Test;

public class CopyListWithRandomPointer extends LeetcodeLinkList {
    
    public RandomListNode copyRandomList(RandomListNode head) {
        if(head == null)
            return head;
        
        RandomListNode curNode = head;
        while(curNode != null){
            RandomListNode tmpNode = new RandomListNode(curNode.label);
            tmpNode.next = curNode.next;
            curNode.next = tmpNode;
            curNode      = tmpNode.next;
        }
        
        curNode = head;
        while(curNode != null){
            if(curNode.random != null)
                curNode.next.random = curNode.random.next;
            curNode = curNode.next.next;
        }
        
        curNode = head;
        RandomListNode newHead = head.next;
        while(curNode != null){
            RandomListNode nextNode = curNode.next;
            curNode.next = nextNode.next;
            nextNode.next = (nextNode.next == null) ? null 
                    : nextNode.next.next;
            curNode = curNode.next;
        }
        
        return newHead;
    }
    
    @Test
    public void test(){
        /**
Submission Result: Runtime Error
Runtime Error Message:  Line 24: java.lang.NullPointerException
Last executed input:    {}
         */
        // Yes, random could point to null
    }
}
