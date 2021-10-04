package com.example.fiamedknuff.model;

import java.io.Serializable;
import java.util.Random;

/**
 * A class Dice that creates a dice. A Dice may be rolled.
 *
 * Created by
 * @author Emma Stålberg
 */


public class Dice {
    private int valueMax;
    private Random rand = new Random();

    // set to true when used and are waiting to be rolled, false if it is rolled and not used yet
    private Boolean isUsed;

    public Dice(int valueMax) {
        this.valueMax = valueMax;
        this.isUsed = true;
    }

    public Dice() {
        this.valueMax = 6;
        this.isUsed = true;
    }

    public Boolean getIsUsed() {
        return this.isUsed;
    }

    public void setIsUsed(Boolean isUsed) {
        this.isUsed = isUsed;
    }

    /**
     * Rolls the dice and returns the value of the rolled dice. The value
     * is between 1 and the maximum value of the dice. Sets the variable isUsed to false.
     * @return the value of the rolled dice
     */
    int rollDice() {
        isUsed = false;
        return rand.nextInt(valueMax) + 1;
    }


}
