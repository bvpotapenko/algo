package ru.bvpotapenko.se.alorithm.queue;

import java.util.Comparator;

public class PriorityQueue<V> extends Queue<V> {
    Comparator<V> comparator;

    public PriorityQueue(int size, Comparator<V> comparator) {
        super(size);
        this.comparator = comparator;
    }

    public void insert(V newItem) {
        if (isFull()) throw new ArrayIndexOutOfBoundsException("Queue is full");
        int position;
        if (isEmpty()) {
            super.insert(newItem);
        } else {
            for (position = getItems() - 1; position >= 0; position--) {
                if (comparator.compare(newItem, getQueue().get(position)) < 0) {
                    getQueue().set(position + 1, getQueue().get(position));
                } else
                    break;
            }
            getQueue().set(position + 1, newItem);
            setItems(getItems() + 1);
        }

    }
}
