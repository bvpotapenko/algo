package ru.bvpotapenko.se.alorithm;

import org.junit.jupiter.api.Test;
import ru.bvpotapenko.se.alorithm.queue.Deque;
import ru.bvpotapenko.se.alorithm.queue.PriorityQueue;
import ru.bvpotapenko.se.alorithm.queue.Queue;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Unit test for simple App.
 */
public class AppTest {
    @Test
    public void reverseStringTest() {
        String input = "123456789";
        String expected = "987654321";
        String result = App.reverseString(input);
        assertTrue(expected.equals(result));
    }

    @Test
    public void queueTest() {
        Queue<String> q = new Queue(7);
        boolean isEmpty = q.isEmpty();
        assertTrue(isEmpty);

        for (int i = 0; i < q.getMaxSize(); i++) {
            q.insert("Item_" + i);
        }
        boolean isFull = q.isFull();
        assertTrue(isEmpty);

        StringBuilder s = new StringBuilder(q.size());
        String sExpected = "Item_0Item_1Item_2Item_3Item_4Item_5Item_6";
        while (!q.isEmpty()) s.append(q.remove());
        assertTrue(sExpected.equals(s.toString()));
    }

    @Test
    public void dequeTest() {
        Deque<String> dq = new Deque<>(4);
        dq.insertLeft("Left_1");
        dq.insertRight("Right_1");
        dq.insertLeft("Left_2");
        dq.insertRight("Right_2");

        StringBuilder s = new StringBuilder();
        while (!dq.isEmpty()){
            try {
                s.append(dq.removeLeft());
                s.append(dq.removeRight());
            }catch (Exception e) {
                fail(e.getMessage());
            }
        }
        String sExpected = "Left_2Right_2Left_1Right_1";

        assertTrue(sExpected.equals(s.toString()));
    }

    @Test
    public void priorityQueueTest(){
        PriorityQueue<String> pq = new PriorityQueue<>(4, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        });
        pq.insert("123!");
        pq.insert("123456789!");
        pq.insert("1234!");
        pq.insert("1234567!");

        StringBuilder sb = new StringBuilder();
        String sExpected = "123!1234!1234567!123456789!";
        while(!pq.isEmpty()){
            sb.append(pq.remove());
        }
        System.out.println(sb.toString());
        boolean isEqual = sExpected.equals(sb.toString());
        assertTrue(isEqual);
    }
}
