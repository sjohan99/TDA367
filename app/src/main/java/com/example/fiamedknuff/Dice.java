package com.example.fiamedknuff;

import java.util.Random;

public class Dice {
    int valueMax;
    Random rand = new Random();

    public Dice(int valueMax) {
        this.valueMax = valueMax;
    }

    int rollDice() {
        return rand.nextInt(valueMax) + 1;
    }
}
