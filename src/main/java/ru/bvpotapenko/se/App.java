package ru.bvpotapenko.se;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import ru.bvpotapenko.se.myarray.MyArray;

import java.util.Random;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        System.out.println("START");
        MyArray arr = new MyArray(10000000);
        System.out.println("ARRAY CREATED");
        arr= fillArray(arr);
        System.out.println("Array size = " + arr.getSize());
        testBubble(arr);
        testInsert(fillArray(arr));
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
        for (int i = 0; i < arr.getSize(); i++)
            arr.insert(new Random().nextInt(10000000));
        return arr;
    }
}
