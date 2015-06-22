package com.brayden;

import java.util.LinkedList;
import java.util.Queue;

import org.junit.Test;

public class ImplementStackUsingQueues {
    /* cur refers to Q containing all the elements */
    Queue<Integer> cur = new LinkedList<>();
    
    /* other used as tmp Q; always empty after API called */
    Queue<Integer> other = new LinkedList<>();
    
    private void swapQ(){
        Queue q1 = null, q2 = null;
        q1.add(q2);
        
        Queue<Integer> tmp = cur;
        cur = other;
        other = tmp;
    }
    
    // Push element x onto stack.
    public void push(int x) {
        cur.offer(x);
    }

    // Removes the element on top of the stack.
    public void pop() {
        while(cur.size() > 1)
            other.offer(cur.poll());
        cur.poll();
        swapQ();
    }

    // Get the top element.
    public int top() {
        while(cur.size() > 1)
            other.offer(cur.poll());
        int res = cur.poll();
        other.offer(res);
        swapQ();
        return res;
    }

    // Return whether the stack is empty.
    public boolean empty() {
        return cur.isEmpty();
    }
    
    @Test
    public void test(){
        /**
Submission Result: Runtime Error
Last executed input:    push(1),push(2),top,pop,top,pop,empty
         */
        push(1);
        push(2);
        top();
        pop();
        top();
        pop();
        empty();
    }
}
