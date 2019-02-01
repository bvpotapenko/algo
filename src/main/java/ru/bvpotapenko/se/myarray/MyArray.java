package ru.bvpotapenko.se.myarray;

//An unordered list of int
public class MyArray {
    private MyArrayItem head;
    private MyArrayItem tail;
    private int size;

    public MyArray(int size) {
        if (size <= 0) throw new IllegalArgumentException("Size must be greater than 0");

        this.size = size;
        this.head = new MyArrayItem();
        MyArrayItem current = head;
        for (int i = 1; i < size; i++) {
            MyArrayItem newItem = new MyArrayItem();
            newItem.setPrevious(current);
            current.setNext(newItem);
            current = newItem;
        }
        this.tail = current;
    }

    public int getElement(int index) {
        if (index >= size) throw new ArrayIndexOutOfBoundsException();

        boolean startFromHead = true;
        if (index >= size / 2) startFromHead = false;

        MyArrayItem current;
        if (startFromHead) {
            current = head;
            for (int i = 0; i < index; i++) {
                current = current.getNext();
            }
        } else {
            current = tail;
            for (int i = 0; i < size - index - 1; i++) {
                current = current.getPrevious();
            }
        }
        return current.getValue();
    }

    public void display() {
        if (size == 0) return;

        MyArrayItem current = head;
        System.out.println(current.getValue());

        while (current.hasNext()) {
            current = current.getNext();
            System.out.println(current.getValue());
        }
    }

    public void delete(int value) {
        if (size == 0) return;
        if (size == 1) deleteIfHasOneItemOnly(value);
        else deleteIfHasMoreThanOne(value);
    }

    //Deletes first found value
    private void deleteIfHasMoreThanOne(int value) {
        MyArrayItem current = findValue(value);
        //Nothing to delete
        if (current == null) return;
        //Consider border cases
        if (current == head) {
            head = head.getNext();
            head.setPrevious(null);
        } else if (current == tail) {
            tail = tail.getPrevious();
            tail.setNext(null);
        } else {
            MyArrayItem previous = current.getPrevious();
            MyArrayItem next = current.getNext();
            previous.setNext(next);
            next.setPrevious(previous);
        }
        this.size--;
    }

    private void deleteIfHasOneItemOnly(int value) {
        if (head.getValue() == value) {
            head = null;
            tail = null;
            this.size--;
        }
    }

    //Searches for the first encountered item with exact value
    private MyArrayItem findValue(int value) {
        boolean isValueFound = false;
        MyArrayItem current = head;
        //In case Head has what we need
        if (current.getValue() == value) isValueFound = true;
        //process other items
        while (current.hasNext() && !isValueFound) {
            current = current.getNext();
            if (current.getValue() == value) {
                isValueFound = true;
            }
        }
        //if value wasn't found return NULL
        if (!isValueFound) current = null;

        return current;
    }

    //Inserts in the end of the list
    public void insert(int value) {
        tail = new MyArrayItem(value, tail);
        this.size++;
    }


    public int getSize() {
        return this.size;
    }

    //Returns index of the value or -1 if not found
    public int findIndex(int value) {
        int position = 0;
        boolean isValueFound = false;
        MyArrayItem current = head;
        //In case Head has what we need
        if (current.getValue() == value) isValueFound = true;
        //process other items
        while (current.hasNext() && !isValueFound) {
            current = current.getNext();
            position++;
            if (current.getValue() == value) {
                isValueFound = true;
            }
        }
        //if value wasn't found return -1
        if (!isValueFound) position = -1;
        return position;
    }

    public void bubbleSort() {
        if (size < 2) return;
        for (int i = size - 1; i > 1; i--) {
            MyArrayItem current = head;
            for (int j = 0; j < i; j++) {
                if (current.getValue() > current.getNext().getValue())
                    swapValues(current, current.getNext());
                current = current.getNext();
            }
        }
    }

    public void selectSort() {
        if (size < 2) return;
        MyArrayItem out = head;
        while (out.hasNext() || out == tail) {
            MyArrayItem marker = out;
            MyArrayItem in = out.getNext();
            while (in.hasNext() || in == tail) {
                if (in.getValue() < marker.getValue())
                    marker = in;
                in = in.getNext();
            }
            swapValues(out, marker);
            out = out.getNext();
        }
    }

    public void insertSort() {
        if (size < 2) return;
        MyArrayItem in;
        MyArrayItem out = head.getNext();
        while (out.hasNext() || out == tail){
            MyArrayItem temp = out;
            in = out;
            while(in.hasPrevious() && in.getPrevious().getValue() >= temp.getValue()){
                in.setValue(in.getPrevious().getValue());
                in = in.getPrevious();
            }
            in.setValue(temp.getValue());
            out = out.getNext();
        }
    }

    private void swapValues(MyArrayItem a, MyArrayItem b) {
        if (a == null || b == null) throw new NullPointerException();
        if (a == b) return;
        a.setValue(a.getValue() + b.getValue());
        b.setValue(a.getValue() - b.getValue());
        a.setValue(a.getValue() - b.getValue());
    }
}

