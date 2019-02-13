package ru.bvpotapenko.se.algo;

import ru.bvpotapenko.se.algo.recur.PowerRecur;
import ru.bvpotapenko.se.algo.recur.TowerOfHanoi;

public class App {
    public static void main(String[] args) {

        //Recurrent positive and negative power with optimisation
        System.out.println(PowerRecur.getPower(5, 3));
        System.out.println(PowerRecur.getPower(5, -2));
        //Not recurrent
        System.out.println(PowerRecur.getPowerNoRecur(6, 4));

        new TowerOfHanoi(5).visualizeSolution();
    }
}
