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

    private int valueMax; // The maximum value the dice can have
    private final Random rand = new Random(); // A variable Random to acquire a random number for the dice roll
    private Boolean isUsed; // Set to true when used and are waiting to be rolled, false if it is rolled and not used yet
    private int rolledValue; // A variable to hold the value of the rolled dice

    /**
     * Constructor that creates a dice with possible numbers from 1 to valueMax value.
     *
     * @param valueMax the highest number the dice can roll.
     */
    public Dice(int valueMax) {
        this.valueMax = valueMax;
        this.isUsed = true;
    }

    /**
     * Constructor that creates a dice with possible numbers from 1 to 6.
     */
    public Dice() {
        this.valueMax = 6;
        this.isUsed = true;
    }

    /**
     * Gets the isUsed boolean value.
     *
     * @return true if dice has been used, else false.
     */
    public Boolean getIsUsed() {
        return this.isUsed;
    }

    /**
     * Sets isUsed variable according to argument.
     *
     * @param isUsed the boolean value isUsed variable to be changed to.
     */
    public void setIsUsed(Boolean isUsed) {
        this.isUsed = isUsed;
    }

    /**
     * Returns the number rolled by the dice.
     *
     * @return rolled number.
     */
    public int getRolledValue() {
        return rolledValue;
    }

    /**
     * Rolls the dice and returns the value of the rolled dice. The value
     * is between 1 and the maximum value of the dice. Sets the variable isUsed to false.
     *
     * @return the value of the rolled dice.
     */
    int rollDice() {
        isUsed = false;
        rolledValue = rand.nextInt(valueMax) + 1;
        return rolledValue;
    }


}
