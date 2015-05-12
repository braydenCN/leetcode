package com.brayden.other;

import org.junit.Test;

import com.brayden.LeetcodeLinkList;

public class IntersectionOfTwoSortedLinkedLists extends LeetcodeLinkList {
    ListNode sortedIntersect(ListNode head1, ListNode head2){
        if(head1 == null || head2 == null)
            return null;
        
        int num1 = head1.val;
        int num2 = head2.val;
        if(num1 == num2){
            ListNode newHead = new ListNode(num1);
            newHead.next = sortedIntersect(head1.next, head2.next);
            return newHead;
        }
        return num1 < num2 ? sortedIntersect(head1.next, head2) :
                             sortedIntersect(head1, head2.next);
    }

    @Test
    public void test(){
        ListNode list1 = makeList(new int[]{1, 2, 3, 4, 5, 6, 7});
        ListNode list2 = makeList(new int[]{3, 5, 8, 9, 10});
        ListNode list3 = makeList(new int[]{3, 5});
        assertLinkListEquals(list3, sortedIntersect(list1, list2));
    }
}
