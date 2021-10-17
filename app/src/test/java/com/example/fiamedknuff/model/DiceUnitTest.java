package com.example.fiamedknuff.model;

import org.junit.Before;
import org.junit.Test;
import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;

public class DiceUnitTest {

    Dice dice;

    @Before
    public void createDice() {
        dice = new Dice();
    }

    @Test
    public void testConstructorIncomingMaxValue() {
        dice = new Dice(8);
        for (int i = 0; i < 50; i++) {
            assertThat(dice.rollDice()).isBetween(0, 8);
        }
    }

    @Test
    public void testIsUSed() {
        assertTrue(dice.getIsUsed());
        dice.setIsUsed(false);
        assertFalse(dice.getIsUsed());
        dice.setIsUsed(true);
        assertTrue(dice.getIsUsed());
    }

    @Test
    public void testRollDice() {
        for (int i = 0; i < 50; i++) {
            assertThat(dice.rollDice()).isBetween(0, 6);
        }
    }

}
