package ru.bvpotapenko.se.algo;

import ru.bvpotapenko.se.algo.bst.BST;

import java.util.Random;

/**
 * Demo for binary search tree
 *
 */
public class App 
{
    public static void main( String[] args ){
       check20Trees();
    }
    private static void check20Trees(){
        double balanced = 0;
        for (int i = 0; i < 20; i++) {
            BST tree = getBST(6);
            if(tree.isBalanced()){
                balanced++;
                System.out.println("\nTree is BALANSED");
            }else{
                System.out.println("\nNOT balanced");
            }
            System.out.println(tree);
            System.out.println("===============================");
            System.out.println(tree.simpleToString());
        }

        System.out.println("Balanced trees amount: " +(balanced /20d * 100) + "%" );
    }
    private static BST getBST(int level){
        Random rnd = new Random();
        BST<Integer, Character> tree = new BST<>();
        while (tree.height() <= level){
            tree.put(-100 + rnd.nextInt(201), (char)(rnd.nextInt(26) + 65));
        }
        return tree;
    }
}
