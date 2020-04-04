package fr.leroideskiwis.virusattack.utils;

public class Interval {

    private final int number;

    public Interval(int number){
        this.number = number;
    }

    public boolean isBetween(int min, int max){
        return number >= min && number <= max;
    }

    public boolean isNotBetween(int max, int min){
        return !isBetween(max, min);
    }

}
