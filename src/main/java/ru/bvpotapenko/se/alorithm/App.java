package ru.bvpotapenko.se.alorithm;

import ru.bvpotapenko.se.alorithm.queue.Queue;
import ru.bvpotapenko.se.alorithm.stack.Stack;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class App
{
    /**
    *   This method demonstrates two ways of string reversion.
    *   Other structures are tested in JUnit tests (AppTest class).
    * */
    public static void main( String[] args ){

        System.out.println(reverseString("123456789"));
        System.out.println(reverseStringNoStack("AbCdEfG"));
    }

    public static String reverseString(String input){
        StringBuilder output = new StringBuilder(input.length());
        Stack<Character> stack = new Stack(input.length());
        for(char ch: input.toCharArray()){
            stack.push(ch);
        }
        while (!stack.isEmpty()) output.append(stack.pop());
        return output.toString();
    }

    public static String reverseStringNoStack(String input){
        StringBuilder out = new StringBuilder(input.length());
        for (int i = input.length()-1; i>=0; i--)
            out.append(input.charAt(i));
        return out.toString();
    }
}
