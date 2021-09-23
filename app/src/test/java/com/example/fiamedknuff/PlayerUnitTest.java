package com.example.fiamedknuff;

import com.example.fiamedknuff.model.Color;
import com.example.fiamedknuff.model.*;

import org.junit.Before;
import org.junit.Test;
import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.assertFalse;

public class PlayerUnitTest {

    Player player;
    String name;
    Color color;

    @Before
    public void createPlayer() {
        name = "TestPerson";
        color = Color.BLUE;
        player = new Player(name, color);

    }

    @Test
    public void testIsNotMovable() {
        //assertFalse(player.isMovable(player.getPieces().get(0), 3));
    }

}
