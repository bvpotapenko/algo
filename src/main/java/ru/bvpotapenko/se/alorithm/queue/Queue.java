package ru.bvpotapenko.se.alorithm.queue;

import java.util.ArrayList;
import java.util.List;

public class Queue<V> {
    private int maxSize;
    private List<V> queue;
    private int front;
    private int rear;
    private int items;

    public Queue(int size) {
        this.maxSize = size;
        queue = new ArrayList<V>(maxSize);
        for (int i = 0; i < size; i++) {
            queue.add(null);
        }
        front = 0;
        rear = -1;
        items = 0;
    }

    public boolean isEmpty() {
        return items == 0;
    }

    public boolean isFull() {
        return items == maxSize;
    }

    public int size() {
        return items;
    }

    public void insert(V i) {
        if (isFull()) throw new ArrayIndexOutOfBoundsException("Queue is full");
        if (rear == maxSize - 1) {
            rear = -1;
        }
        queue.set(++rear, i);
        items++;
    }

    public V remove() {
        if (isEmpty()) throw new ArrayIndexOutOfBoundsException("Queue is empty");
        V temp = queue.get(front);
        queue.set(front, null);
        front++;
        if (front == maxSize) {
            front = 0;
        }
        items--;
        return temp;
    }

    public V peek() {
        return queue.get(front);
    }

    public List<V> getQueue() {
        return queue;
    }

    public int getFront() {
        return front;
    }

    public int getRear() {
        return rear;
    }

    public int getItems() {
        return items;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void setFront(int front) {
        this.front = front;
    }

    public void setRear(int rear) {
        this.rear = rear;
    }

    public void setItems(int items) {
        this.items = items;
    }
}
