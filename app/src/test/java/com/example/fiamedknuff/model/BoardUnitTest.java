package com.example.fiamedknuff.model;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

import com.example.fiamedknuff.NotImplementedException;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for testing Board.java
 */

public class BoardUnitTest {

    Board board4p;
    ArrayList<Piece> pieces;

    @Before
    public void createBoard() throws NotImplementedException {
        int playerCount = 4;
        pieces = new ArrayList<>();
        for (int i = 0; i < playerCount; i++) {
            pieces.add(new Piece(Color.RED));
        }
        for (int i = 0; i < playerCount; i++) {
            pieces.add(new Piece(Color.GREEN));
        }
        for (int i = 0; i < playerCount; i++) {
            pieces.add(new Piece(Color.BLUE));
        }
        for (int i = 0; i < playerCount; i++) {
            pieces.add(new Piece(Color.YELLOW));
        }
        board4p = new Board(playerCount, pieces);
    }

    @Test(expected = NotImplementedException.class)
    public void boardPlayerAmountNotImplemented() throws NotImplementedException {
        Board board = new Board(6, new ArrayList<Piece>());
    }

    @Test
    public void boardPositionsInitializesCorrectly() {
        List<Position> positions = board4p.getPositions();
        assertEquals(57+16, positions.size());
        assertEquals(-16, positions.get(0).getPos());
        assertEquals(56, positions.get(positions.size()-1).getPos());
    }

    @Test
    public void boardHashMapInitializesCorrectly() {
        var hm = board4p.getPiecePositionHashMap();
        int i = -16;
        for (Piece piece : pieces) {
            assertEquals(hm.get(piece).getPos(), i++);
        }
    }


}
