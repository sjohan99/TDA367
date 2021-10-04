package com.example.fiamedknuff.model;

import java.io.Serializable;
import java.util.Random;

/**
 * A class Dice that creates a dice. A Dice may be rolled.
 *
 * Created by
 * @author Emma Stålberg
 */


public class Dice implements Serializable{
    private int valueMax;
    private Random rand = new Random();


    public Dice(int valueMax) {
        this.valueMax = valueMax;
    }

    public Dice() {
        this.valueMax = 6;
    }

    /**
     * Rolls the dice and returns the value of the rolled dice. The value
     * is between 1 and the maximum value of the dice.
     * @return the value of the rolled dice
     */
    int rollDice() {
        return rand.nextInt(valueMax) + 1;
    }
}
