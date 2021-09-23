package com.example.fiamedknuff;

import com.example.fiamedknuff.model.Color;
import com.example.fiamedknuff.model.*;

import org.junit.Before;
import org.junit.Test;
import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;

public class PlayerUnitTest {

    Player player;
    String name;
    Color color;
    Piece piece;

    @Before
    public void createPlayer() {
        name = "TestPerson";
        color = Color.BLUE;
        player = new Player(name, color);
        piece = player.getPieces().get(0);

    }

    @Test
    public void testIstMovableInHome() {
        assertTrue(player.isMovable(piece, 6));
    }

    @Test
    public void testIsNotMovableInHome() {
        assertFalse(player.isMovable(piece, 3));
    }

    @Test
    public void testIsMovableWhenPieceIsOnBoard() {
        piece.setIndex(12);
        for (int i = 1; i < 7; i++) {
            assertTrue(player.isMovable(piece, i));
        }
    }


}
