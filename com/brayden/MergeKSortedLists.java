package com.brayden;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class MergeKSortedLists extends LeetcodeLinkList {
    
    public ListNode mergeKLists(List<ListNode> lists) {
        if(lists == null || lists.size() == 0)
            return null;
        
        while(lists.size() > 1){
            lists.add(mergeRecursively(lists.get(0), lists.get(1)));
            lists.remove(0);
            lists.remove(0);
        }
        return lists.get(0);
    }
    
    
    private static class HeapItem implements Comparable<HeapItem>{
        ListNode n;
        int      listNo;
        HeapItem(ListNode n, int listNo) {
            this.n = n;
            this.listNo = listNo;
        }
        @Override
        public String toString() {
            return "HeapItem [n=" + n.val + ", listNo=" + listNo + "]";
        }
        @Override
        public int compareTo(HeapItem o) {
            return n.val - o.n.val;
        }
    }
    
    private static class Heap<T extends Comparable<T>>{
        private T[] arr;
        private int size = 0;
        
        @SuppressWarnings("unchecked")
        Heap(int size){
            this.arr = (T[]) java.lang.reflect.Array.newInstance(HeapItem.class, size);
        }
        
        private int leftChild(int ind){
            return ind * 2 + 1;
        }
        
        private int rightChild(int ind){
            return ind * 2 + 2;
        }
        
        private boolean hasLeftChild(int ind){
            return leftChild(ind) < size;
        }
        
        private boolean hasRightChild(int ind){
            return rightChild(ind) < size;
        }
        
        private int parent(int ind){
            return (ind - 1) / 2;
        }
        
        private void siftDown(int ind){
            while(hasLeftChild(ind)){
                int child = leftChild(ind);
                if(hasRightChild(ind) && 
                        arr[child + 1].compareTo(arr[child]) < 0)
                    child++; // compare with right child now
                if(arr[child].compareTo(arr[ind]) < 0)
                    swap(ind, child);
                ind = child;
            }
        }
        
        private void siftUp(int ind){
            while(ind != 0){            
                int parent = parent(ind);
                if(arr[ind].compareTo(arr[parent]) < 0){
                    swap(ind, parent);
                    ind = parent;
                }else break;
            }
        }
        
        void add(T p){
            if(size != arr.length){
                arr[size++] = p;
                siftUp(size - 1);
            }
        }
        
        void addAndRemoveTop(T p){
            arr[0] = p;
            siftDown(0);
        }
        
        T getTop(){
            return arr[0];
        }
        
        private void swap(int i1, int i2) {
            T tmp = arr[i1];
            arr[i1]  = arr[i2];
            arr[i2]  = tmp;
         }

        public void removeTop() {
            arr[0] = arr[--size];
            siftDown(0);
        }
    }
    
    public ListNode mergeKLists1(List<ListNode> lists) {
        if(lists == null || lists.size() == 0)
            return null;
        
        if(lists.size() == 1)
            return lists.get(0);
        
        int size = lists.size();
        Heap<HeapItem> heap = new Heap<>(size);
        int notNullListNum = 0;
        for(int i = 0; i < size; i++){
            ListNode n = lists.get(i);
            if(n != null){
                notNullListNum++;
                heap.add(new HeapItem(n, i));
                lists.set(i, n.next);
            }
        }
        
        if(notNullListNum == 0)
            return null;
        
        ListNode head = heap.getTop().n;
        ListNode tail = head;
        
        boolean inited = false;
        while(notNullListNum > 0){
            HeapItem i = heap.getTop();
            
            if(inited){
                tail.next = i.n;
                tail = tail.next;
            }else
                inited = true;
            
            ListNode nextNode = lists.get(i.listNo);
            if(nextNode == null){
                heap.removeTop();
                notNullListNum--;
            }else{
                lists.set(i.listNo, nextNode.next);
                heap.addAndRemoveTop(new HeapItem(nextNode, i.listNo));
            }
        }
        return head;
    }
    
    @Test
    public void test(){
        ListNode expected = makeList(new int[]{0, 1});
        assertLinkListEquals(expected, 
                mergeKLists(new ArrayList<>(Arrays.asList(new ListNode[]{makeList(new int[]{1}), makeList(new int[]{0})}))));
    }
    
    @Test
    public void test1(){
        ListNode expected = makeList(new int[]{-3, -2, -1, -1, 0, 1, 1, 2, 4});
        assertLinkListEquals(expected, 
                mergeKLists(new ArrayList<>(Arrays.asList(new ListNode[]{makeList(new int[]{-1,1}), 
                                                         makeList(new int[]{-3,1,4}), 
                                                         makeList(new int[]{-2,-1,0,2})}
                                          ))));
    }
    
    @Test
    public void test2(){
        /**
Submission Result: Wrong Answer
Input:  [{-9,-7,-7},{-6,-4,-1,1},{-6,-5,-2,0,0,1,2},{-9,-8,-6,-5,-4,1,2,4},{-10},{-5,2,3}]
Output:     {-9,-10,-9,-8,-7,-7,-6,-6,-6,-5,-5,-5,-4,-4,-2,-1,0,0,1,1,1,2,2,2,3,4}
Expected:   {-10,-9,-9,-8,-7,-7,-6,-6,-6,-5,-5,-5,-4,-4,-2,-1,0,0,1,1,1,2,2,2,3,4}
         */
        ListNode expected = makeList(new int[]{-10,-9,-9,-8,-7,-7,-6,-6,-6,-5,-5,-5,-4,-4,-2,-1,0,0,1,1,1,2,2,2,3,4});
        assertLinkListEquals(expected, 
                mergeKLists(new ArrayList<>(Arrays.asList(new ListNode[]{makeList(new int[]{-9,-7,-7}), 
                                                         makeList(new int[]{-6,-4,-1,1}), 
                                                         makeList(new int[]{-6,-5,-2,0,0,1,2}),
                                                         makeList(new int[]{-9,-8,-6,-5,-4,1,2,4}),
                                                         makeList(new int[]{-10}),
                                                         makeList(new int[]{-5,2,3})
                                                         }
                                          ))));
    }
}
