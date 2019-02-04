package ru.bvpotapenko.se.alorithm.stack;

import java.util.ArrayList;
import java.util.List;

public class Stack<V> {
    private int maxSize;
    private List<V> stack;
    private int top;

    public Stack(int size){
        this.maxSize = size;
        this.stack = new ArrayList<V>(this.maxSize);
        this.top = -1;
    }

    public void push(V i){
        stack.add(i);
        top++;
    }

    public V pop(){
        return stack.remove(top--);
    }

    public V peek(){
        return stack.get(top);
    }

    public boolean isEmpty(){
        return stack.isEmpty();
    }

    public boolean isFull(){
        return top ==  maxSize - 1;
    }
}
