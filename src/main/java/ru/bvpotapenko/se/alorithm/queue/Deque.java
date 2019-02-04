package ru.bvpotapenko.se.alorithm.queue;

public class Deque<V> extends Queue<V> {

    public Deque(int size) {
        super(size);
    }

    //insert head
    public void insertLeft(V i) {
        if (isFull()) throw new ArrayIndexOutOfBoundsException("Queue is full");
        int front = getFront();
        if(isEmpty())
            setRear(0);
        else {
            front--;
        }
        front = front == -1 ? getMaxSize() - 1 : front;
        getQueue().set(front, i);
        setFront(front);
        setItems(getItems() + 1);
    }

    //insert end
    public void insertRight(V i) {
        insert(i);
    }

    //remove head
    public V removeLeft() {
        return remove();
    }

    //remove end
    public V removeRight() {
        if (isEmpty()) throw new ArrayIndexOutOfBoundsException("Queue is empty");
        int rear = getRear();
        V temp = getQueue().get(rear);
        getQueue().set(rear, null);
        rear--;
        setRear(rear == -1 ? getMaxSize() - 1 : rear);
        setItems(getItems() - 1);
        return temp;
    }

    //peek head
    public V peekLeft() {
        return peek();
    }

    //peek tail
    public V peekRight() {
        return getQueue().get(getRear());
    }
}
