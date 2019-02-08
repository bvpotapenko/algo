package ru.bvpotapenko.se.algo.linked;

public class MyLinkedStack<Item> {
    private MyLinkedList<Item> stack = new MyLinkedList<>();

    public void push(Item item) {
        stack.insertFirst(item);
    }

    public Item pop() {
        return stack.deleteFirst();
    }

    public Item peek() {
        return stack.getFirst();
    }

    public int size(){ return size();}

    public boolean isEmpty(){return isEmpty();}

    public String toString(){
        return stack.toString();
    }
}
