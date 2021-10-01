package com.example.fiamedknuff.model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;

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

    @Test
    public void testIsNotMovableWhenPieceIsOnBoard() {
        piece.setIndex(12);
        Piece piece2 = player.getPieces().get(1);
        for (int i = 1; i < 7; i++) {
            piece2.setIndex(12+i);
            assertFalse(player.isMovable(piece, i));
        }

    }

    @Test
    public void testGetMovablePiecesTwoPieces() {
        List<Piece> pieces = player.getPieces();
        pieces.get(0).setIndex(1);
        pieces.get(1).setIndex(11);
        for (int i = 2; i < 6; i++) {
            assertEquals(2, player.getMovablePieces(pieces, i).size());
        }
    }

    @Test
    public void testGetMovablePiecesAllPieces() {
        List<Piece> pieces = player.getPieces();
        pieces.get(0).setIndex(1);
        pieces.get(1).setIndex(11);
        assertEquals(2, player.getMovablePieces(pieces, 1).size());
        assertEquals(4, player.getMovablePieces(pieces, 6).size());
    }

    @Test
    public void testGetName() {
        assertEquals(player.getName(),"TestPerson");
    }

    @Test
    public void testSetName() {
        player.setName("newNamePerson");
        assertEquals(player.getName(),"newNamePerson");
    }


}
