package ru.bvpotapenko.se.algo.linked;

public class MyLinkedQueue<Item> {
    MyLinkedList<Item> queue = new MyLinkedList();

    public void insert(Item item){
        queue.insertLast(item);
    }

    public Item delete(){
        return queue.deleteFirst();
    }

    public boolean isEmpty(){
        return queue.isEmpty();
    }

    public String toString(){
        return queue.toString();
    }
}
