package ru.bvpotapenko.se;
import ru.bvpotapenko.se.myarray.MyArray;

import java.util.Random;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        final int  ARR_SIZE = 100000;

        System.out.println("START");
        MyArray arr = new MyArray(ARR_SIZE);
        System.out.println("ARRAY CREATED");
        arr = fillArray(arr);
        testBubble(arr);
        arr = new MyArray(ARR_SIZE);
        testInsert(fillArray(arr));
        arr = new MyArray(ARR_SIZE);
        testSelect(fillArray(arr));
    }

    static void testBubble(MyArray arr) {
        System.out.println("Bubble");
        long startTime = System.nanoTime();
        arr.bubbleSort();
        long endTime = System.nanoTime();
        long timeElapsed = endTime - startTime;
        System.out.println("Execution time in milliseconds : " +
                timeElapsed / 1000000);
    }

    static void testSelect(MyArray arr) {
        System.out.println("Select");
        long startTime = System.nanoTime();
        arr.selectSort();
        long endTime = System.nanoTime();
        long timeElapsed = endTime - startTime;
        System.out.println("Execution time in milliseconds : " +
                timeElapsed / 1000000);
    }

    static void testInsert(MyArray arr) {
        System.out.println("Insert");
        long startTime = System.nanoTime();
        arr.insertSort();
        long endTime = System.nanoTime();
        long timeElapsed = endTime - startTime;
        System.out.println("Execution time in milliseconds : " +
                timeElapsed / 1000000);
    }

    private static MyArray fillArray(MyArray arr) {
        int capacity = arr.getCapacity();
        for (int i = 0; i < capacity; i++)
            arr.insert(new Random().nextInt(1000));
        return arr;
    }
}
