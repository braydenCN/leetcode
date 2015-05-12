package com.brayden;

import java.util.HashMap;
import java.util.Map;


public class LRUCache1 extends LeetcodeCommon {
    
    private void remove(ListNode n){
        if(n == head)
            head = n.next;
        if(n == tail)
            tail = n.pre;
        if(n.pre != null)
            n.pre.next = n.next;
        if(n.next != null)
            n.next.pre = n.pre;
    }
    
    private void addHead(ListNode n){
        if(head != null)
            head.pre = n;
        n.next = head;
        n.pre = null;
        head = n;
        if(tail == null)
            tail = n;
    }
    
    private Map<Integer, ListNode> map = new HashMap<>();
    private ListNode head = null, tail = null;
    private int capacity, size = 0;
    
    public LRUCache1(int capacity) {
        this.capacity = capacity;
    }
    
    public int get(int key) {
        if(!map.containsKey(key))
            return -1;
        ListNode n = map.get(key);
        remove(n);
        addHead(n);
        return n.val;
    }
    
    public void set(int key, int value) {
        ListNode n = null;
        if(map.containsKey(key)){
            n = map.get(key);
            n.val = value;
            remove(n);
        }else{
            n = new ListNode(key, value);
            map.put(key, n);
            if(size == capacity){
                map.remove(tail.key);
                remove(tail);
            }else size++;
        }
        addHead(n);
    }
}
