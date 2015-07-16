package com.brayden;

import java.util.Stack;

public class ImplementQueueUsingStacks {
    
    private Stack<Integer> inStack = new Stack<>(), outStack = new Stack<>();
    
    // Push element x to the back of queue.
    public void push(int x) {
        inStack.push(x);
    }

    // Removes the element from in front of queue.
    public void pop() {
        prepareOutput();
        outStack.pop();
    }

    private void prepareOutput() {
        if(outStack.isEmpty())
            while(!inStack.isEmpty())
                outStack.push(inStack.pop());
    }

    // Get the front element.
    public int peek() {
        prepareOutput();
        return outStack.peek();
    }

    // Return whether the queue is empty.
    public boolean empty() {
        return outStack.isEmpty() && inStack.isEmpty();
    }
}
