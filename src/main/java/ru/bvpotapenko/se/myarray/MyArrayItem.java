package ru.bvpotapenko.se.myarray;

public class MyArrayItem {
    private int value;
    private MyArrayItem next;
    private MyArrayItem previous;

    public MyArrayItem (){

    }

    public MyArrayItem (int value){
        this.value = value;
        this.next = null;
        this.previous = null;
    }
    public MyArrayItem(int value, MyArrayItem previous) {
        this.value = value;
        this.previous = previous;
        this.next = null;
    }

    public MyArrayItem(int value, MyArrayItem next, MyArrayItem previous) {
        this.value = value;
        this.next = next;
        this.previous = previous;
    }

    public int getValue() {
        return value;
    }

    public MyArrayItem getNext() {
        return next;
    }

    public MyArrayItem getPrevious() {
        return previous;
    }

    public void setValue(int value) {
        this.value = value;
    }

    void setNext(MyArrayItem next) {
        this.next = next;
    }

    void setPrevious(MyArrayItem previous) {
        this.previous = previous;
    }

    public boolean hasNext() {
        return next != null;
    }

    public boolean hasPrevious() {
        return previous != null;
    }
}
