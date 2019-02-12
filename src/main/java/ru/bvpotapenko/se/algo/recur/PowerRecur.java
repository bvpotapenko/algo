package ru.bvpotapenko.se.algo.recur;
/**
 * Returns positive or negatve power of a given number
 * */
public class PowerRecur {
    public static double getPower(double value, int power){
        if (power == 0) return 1;
        if (value == 0) return 0;
        if (power == -2){ //Trivial for negative power
            return 1 / (value * value);
        }else if(power == 2){ //Trivial for positive power
            return value * value;
        }else if(power < 0){ //Recurrent step for negatives
            if (power % 2 == 0 ) { //Optimisation for positive values
                double tempValue = 1 / getPower(value, power / 2);
                return tempValue * tempValue;
            }else {
                return getPower(value, power + 1)/value;
            }
        }else if (power % 2 == 0) { //Optimisation for positive values
            double tempValue = getPower(value, power / 2);
            return tempValue * tempValue;
        }else{
            return value * getPower(value, power - 1);
        }
    }
    public static double getPower(float value, int power){
        return getPower((double) value, power);
    }
    public static double getPower(int value, int power){
        return getPower((double) value, power);
    }
    public static double getPower(long value, int power){
        return getPower((double) value, power);
    }
    public static double getPower(byte value, int power){
        return getPower((double) value, power);
    }

    public static double getPowerNoRecur(int value, int power){
        if (power == 0) return 1;
        if (value == 0) return 0;
        int answer = value;
        for (int i = 0; i < power-1; i++) {
            answer *= value;
        }
        return answer;
    }
}
