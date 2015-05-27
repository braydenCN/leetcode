package com.brayden;

import java.util.HashMap;
import java.util.Map;


public class LRUCache extends LeetcodeCommon {
    
    private ListNode remove(ListNode n){
        n.pre.next = n.next;
        n.next.pre = n.pre;
        return n;
    }
    
    private void addHead(ListNode n){
        n.next = head.next;
        head.next.pre = n;
        n.pre = head;
        head.next = n;
    }
    
    private Map<Integer, ListNode> map = new HashMap<>();
    private ListNode head, tail;
    private int capacity, size = 0;
    
    public LRUCache(int capacity) {
        this.capacity = capacity;
        head = new ListNode(0);
        tail = new ListNode(0);
        head.next = tail;
        tail.pre = head;
    }
    
    public int get(int key) {
        if(!map.containsKey(key))
            return -1;
        addHead(remove(map.get(key)));
        return head.next.val;
    }
    
    public void set(int key, int value) {
        if(map.containsKey(key)){
            ListNode n = map.get(key);
            n.val = value;
            if(n != head.next)
                addHead(remove(n));
            return;
        }
        ListNode n = new ListNode(key, value);
        map.put(key, n);
        addHead(n);
        size++;
        if(size == capacity + 1){
            map.remove(tail.pre.key);
            remove(tail.pre);
            size--;
        }
    }
}
