package ru.bvpotapenko.se.algo.linked;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyLinkedList<Item> implements Iterable<Item> {

    @Override
    public Iterator<Item> iterator() {
        return new MyLinkedListIterator();
    }

    private class MyLinkedListIterator implements Iterator<Item> {
        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    private class Node {
        Item item;
        Node next;
        Node previous;

        Node(Item item, Node next, Node previous) {
            this.item = item;
            this.next = next;
            this.previous = previous;
        }
    }

    private int size = 0;
    private Node first;
    private Node last;

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void insertFirst(Item item) {
        Node oldFirst = first;
        first = new Node(item, oldFirst, null);
        if (isEmpty()) {
            last = first;
        } else {
            oldFirst.previous = first;
        }
        size++;
    }

    public Item getFirst() {
        if (isEmpty()) throw new NoSuchElementException("List is empty.");
        return first.item;
    }

    public Item deleteFirst() {
        if (isEmpty()) throw new NoSuchElementException("List is empty.");
        Item tempItem = first.item;
        first = first.next;
        if (first != null) {
            first.previous = null;
        }
        size--;
        if (isEmpty())
            last = null;
        return tempItem;
    }

    public void insertLast(Item item) {
        Node oldlast = last;
        last = new Node(item, null, oldlast);
        if (isEmpty()) {
            first = last;
        } else {
            oldlast.next = last;
        }
        size++;
    }

    public Item getLast() {
        if (isEmpty()) throw new NoSuchElementException("List is empty.");
        return last.item;
    }

    public Item deleteLast() {
        if (isEmpty()) throw new NoSuchElementException("List is empty.");
        Item tempItem = last.item;
        if (size == 1) {
            first = last = null;
            size = 0;
        } else {
            last = last.previous;
            last.next.previous = null;
            last.next = null;
            size--;
        }
        return tempItem;
    }

    public Item get(int index) {
        if (index < 0 || index > size - 1) throw new ArrayIndexOutOfBoundsException();
        boolean startFromHead = true;

        return getNodeByIndex(index).item;
    }

    public void set(int index, Item value) {
        getNodeByIndex(index).item = value;
    }

    public boolean find(Item value) {
        Node current = first;
        while (current != null && !current.item.equals(value)) {
            current = current.next;
        }
        return current != null;
    }

    public Item delete(Item item) {
        Node current = first;
        while (current != null && !current.item.equals(item)) {
            current = current.next;
        }
        if (current == null) {
            return null;
        } else if (current == first) {
            return deleteFirst();
        } else if (current == last) {
            return deleteLast();
        }
        join(current.previous, current.next);
        cutLinks(current);
        size--;
        return current.item;
    }

    private void join(Node previous, Node next) {
        previous.next = next;
        next.previous = previous;
    }

    private void cutLinks(Node node) {
        node.previous = null;
        node.next = null;
    }

    private void insert(int index, Item item) {
        if (index < 0 || index > size) throw new ArrayIndexOutOfBoundsException();
        if (index == 0) {
            insertFirst(item);
            return;
        } else if (index == size) {
            insertLast(item);
            return;
        }
        Node curr = getNodeByIndex(index);
        Node newNode = new Node(item, curr, curr.previous);
        curr.previous.next = newNode;
        curr.previous = newNode;
        size++;
    }

    private Node getNodeByIndex(int index) {
        if (index < 0 || index > size - 1) throw new ArrayIndexOutOfBoundsException();
        boolean startFromHead = true;
        Node curr;
        if (index > size / 2) startFromHead = false;
        if (startFromHead) {
            int currentIndex = 0;
            curr = first;
            while (currentIndex < index) {
                curr = curr.next;
                currentIndex++;
            }
        } else {
            int currentIndex = size - 1;
            curr = last;
            while (currentIndex > index) {
                curr = curr.previous;
                currentIndex--;
            }
        }
        return curr;
    }

    public String toString() {
        return "";// TODO: 08-Feb-19
    }


}

