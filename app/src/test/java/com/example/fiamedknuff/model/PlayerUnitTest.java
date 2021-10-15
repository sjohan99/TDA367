package com.example.fiamedknuff.model;

import org.junit.Before;
import org.junit.Test;
import java.util.List;
import static org.junit.Assert.*;

public class PlayerUnitTest {

    Player player;
    Piece piece;
    int minDiceValue = 1;
    int maxDiceValue = 6;

    @Before
    public void createPlayer() {
        player = new Player("TestPerson", Color.RED);
        piece = player.getPieces().get(0);
    }

    @Test
    public void testIstMovableInHome() {
        assertTrue(player.isMovable(piece, minDiceValue));
        assertTrue(player.isMovable(piece, maxDiceValue));
    }

    @Test
    public void testIsNotMovableInHome() {
        for (int i = minDiceValue + 1; i < maxDiceValue; i++) {
            assertFalse(player.isMovable(piece, i));
        }
    }

    @Test
    public void testIsMovableWhenPieceIsOnBoard() {
        piece.setIndex(12);
        for (int i = minDiceValue; i <= maxDiceValue; i++) {
            assertTrue(player.isMovable(piece, i));
        }
    }

    @Test
    public void testIsNotMovableWhenPieceIsOnBoard() {
        piece.setIndex(12);
        Piece secondPiece = player.getPieces().get(1);
        for (int i = minDiceValue; i <= maxDiceValue; i++) {
            secondPiece.setIndex(12+i);
            assertFalse(player.isMovable(piece, i));
        }
    }

    @Test
    public void testGetMovablePiecesTwoPieces() {
        List<Piece> pieces = player.getPieces();
        pieces.get(0).setIndex(1);
        pieces.get(1).setIndex(11);
        for (int i = minDiceValue + 1; i < maxDiceValue; i++) {
            assertEquals(2, player.getMovablePieces(pieces, i).size());
        }
    }

    @Test
    public void testGetMovablePiecesAllPieces() {
        List<Piece> pieces = player.getPieces();
        pieces.get(0).setIndex(1);
        pieces.get(1).setIndex(11);
        assertEquals(2, player.getMovablePieces(pieces, minDiceValue).size());
        assertEquals(4, player.getMovablePieces(pieces, maxDiceValue).size());
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

    @Test
    public void testRemovePiece() {
        assertEquals(4, player.getPieces().size());
        for (int i = player.getPieces().size() - 1; i >= 0; i--) {
            player.removePiece(player.getPieces().get(i));
            assertEquals(i, player.getPieces().size());
        }
    }

}
