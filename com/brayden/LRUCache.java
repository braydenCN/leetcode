package com.brayden;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    
    private static class Heap{
        private HeapItem[] arr;
        private Map<Integer, Integer> map = new HashMap<>();
        private int capacity;
        private int size;
        private long millis = 1;
        
        Heap(int capacity){
            this.capacity = capacity;
            this.arr      = new HeapItem[capacity];
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
        
        private void siftDown(int ind){
            while(hasLeftChild(ind)){
                int child = leftChild(ind);
                if(hasRightChild(ind) && 
                        arr[child + 1].millis < arr[child].millis)
                    child++; // compare with right child now
                if(arr[ind].millis > arr[child].millis){
                    map.put(arr[child].key, ind);
                    swap(ind, child);
                }
                ind = child;
            }
            map.put(arr[ind].key, ind);
        }
        
        private void add(HeapItem p){
            if(isFull()){
                map.remove(arr[0].key);
                arr[0] = p;
                map.put(p.key, 0);
                siftDown(0);
            }else{
                arr[size++] = p;
                map.put(p.key, size - 1);
            }
        }
        
        private void updateTimeMills(int ind, long timeMills){
            arr[ind].millis = timeMills;
            siftDown(ind);
        }
        
        private void updateVal(int ind, int val){
            arr[ind].val = val;
        }
        
        private boolean isFull(){
            return size == capacity;
        }
        
        private void swap(int i1, int i2) {
            HeapItem tmp = arr[i1];
            arr[i1]  = arr[i2];
            arr[i2]  = tmp;
         }

        private void set(int key, int value) {
            if(map.containsKey(key)){
                int ind = map.get(key);
                updateVal(ind, value);
                updateTimeMills(ind, ++millis);
                return;
            }
            add(new HeapItem(key, value, ++millis));
        }
        
        private static class HeapItem {
            int key;
            int val;
            long millis;
            HeapItem(int key, int val, long millis) {
                this.key = key;
                this.val = val;
                this.millis = millis;
            }
            @Override
            public String toString() {
                return "HeapItem [key=" + key + ", val=" + val + ", millis="
                        + millis + "]";
            }
        }

        private int get(int key) {
            if(map.containsKey(key)){
                int ind = map.get(key);
                int val = arr[ind].val;
                updateTimeMills(ind, ++millis);
                return val;
            }
            return -1;
        }
    }
    
    private Heap heap;
    
    public LRUCache(int capacity) {
        heap = new Heap(capacity);
    }
    
    public int get(int key) {
        return heap.get(key);
    }
    
    public void set(int key, int value) {
        heap.set(key, value);
    }


}
