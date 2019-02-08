package ru.bvpotapenko.se.algo;

import ru.bvpotapenko.se.algo.linked.MyLinkedList;

import java.text.MessageFormat;
import java.util.Iterator;

public class App {
    public static void main(String[] args) {
        MyLinkedList<Character> list = new MyLinkedList();
        list.insertFirst('b');
        list.insertFirst('a');
        list.deleteFirst();
        list.insertFirst('c');
        list.insertFirst('d');
        list.insertLast('e');
        list.insertLast('f');
        list.insertLast('g');
        list.deleteLast();

        for (Character c : list) {
            System.out.println(c);
        }

        int vowels = 0;
        int consonants = 0;
        Iterator<Character> iter = list.iterator();
        while (iter.hasNext()) {
            Character c = iter.next();
            switch (c) {
                case 'a':
                case 'e':
                case 'i':
                case 'o':
                case 'u':
                case 'y':
                    vowels++;
                    break;
                default:
                    consonants++;
            }
        }
        System.out.println(MessageFormat.format("There are in the list:\n" +
                        "{0} vowel" +
                        (vowels != 1 ? "s" : "" )+
                "\n{1} consonant" +
                        (consonants != 1 ? "s" : ""),
                vowels, consonants));

        list.replace('b', 'B');
        System.out.println(list);
    }
}
