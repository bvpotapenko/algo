package ru.bvpotapenko.se.algo.recur;

import java.util.ArrayList;
import java.util.List;

public class TowerOfHanoi {
    //Constants
    private final int POLES_AMOUNT = 3;
    private final int MAX_DISC_AMOUNT = 10;
    private final int MIN_DISC_AMOUNT = 1;
    private final int POLES_INDEX_SUM = 6;

    //Decoration
    private final String BACKGROUND = ".";
    private final char POLE = '|';
    private final char DISC_LEFT_BOUND = '[';
    private final char DISC_RIGHT_BOUND = ']';
    private final char DISC_FILLING = '=';
    private final char POLE_BASE = '_';

    private List<Integer> initialPole;
    private List<Integer> targetPole;
    private List<Integer> additionalPole;
    private int totalDiscAmount;

    public TowerOfHanoi(int discAmount) {
        //Check arguments
        if (discAmount > MAX_DISC_AMOUNT || discAmount < 1)
            throw new IllegalArgumentException("Too many discs. Must be from "
                    + MIN_DISC_AMOUNT + " to " + MAX_DISC_AMOUNT);

        //Init fields
        totalDiscAmount = discAmount;
        initialPole = new ArrayList<>();
        targetPole = new ArrayList<>();
        additionalPole = new ArrayList<>();
        /**
         * Filling initial pole with discs of odd width (1, 3, 5, 7, ...)
         */
        for (int i = 0; i < totalDiscAmount; i++) {
            int currentDiscSize = (totalDiscAmount - i) * 2 - 1;
            initialPole.add(currentDiscSize);
        }
        drawPoles();
    }


    public void visualizeSolution() {
        move(totalDiscAmount, initialPole, targetPole, additionalPole);
    }

    private void move(int discAmount, List<Integer> fromPole, List<Integer> toPole, List<Integer> additionalPole) {
        if (discAmount == 0) return;
        move(discAmount - 1, fromPole, additionalPole, toPole);
        int disc = fromPole.remove(fromPole.size()-1);
        toPole.add(disc);
        drawPoles();
        move(discAmount - 1, additionalPole, toPole, fromPole);
    }

    private void drawPoles() {
        StringBuilder sb = new StringBuilder();
        for (int discPosition = totalDiscAmount - 1; discPosition >= 0; discPosition--) {
            sb.append(getDiscFromPole(discPosition, initialPole));
            sb.append(getDiscFromPole(discPosition, additionalPole));
            sb.append(getDiscFromPole(discPosition, targetPole));
            sb.append('\n');
            //Drawing pole base if it was the bottom layer
            if (discPosition == 0) {
                for (int i = 0; i < (totalDiscAmount * 2 + 1) * 3; i++) {
                    sb.append(POLE_BASE);
                }
            }
        }
        System.out.println(sb.toString());
    }

    private String getDiscFromPole(int position, List<Integer> pole) {
        StringBuilder sb = new StringBuilder();
        int currentDiscSize = position > pole.size() - 1 ? 0 : pole.get(position);
        int spaceAroundDisc = (totalDiscAmount * 2 - 1 - currentDiscSize) / 2;
        //Space before current disc
        for (int i = 0; i < spaceAroundDisc; i++) {
            sb.append(BACKGROUND);
        }
        //Current disc [===] or |
        if (currentDiscSize == 0) {
            sb.append(BACKGROUND);
            sb.append(POLE);
            sb.append(BACKGROUND);
        } else {
            sb.append(DISC_LEFT_BOUND);
            for (int i = 0; i < currentDiscSize; i++) {
                sb.append(DISC_FILLING);
            }
            sb.append(DISC_RIGHT_BOUND);
        }
        //Space after current disc
        for (int i = 0; i < spaceAroundDisc; i++) {
            sb.append(BACKGROUND);
        }
        //Space between poles
        sb.append(BACKGROUND);
        return sb.toString();
    }
}
