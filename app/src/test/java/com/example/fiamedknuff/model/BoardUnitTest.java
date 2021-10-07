package com.example.fiamedknuff.model;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.*;


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
            pieces.add(new Piece(Color.YELLOW));
        }
        for (int i = 0; i < playerCount; i++) {
            pieces.add(new Piece(Color.RED));
        }
        for (int i = 0; i < playerCount; i++) {
            pieces.add(new Piece(Color.GREEN));
        }
        for (int i = 0; i < playerCount; i++) {
            pieces.add(new Piece(Color.BLUE));
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

    @Test
    public void boardGivesPiecesCorrectOffsets() {
        var hm = board4p.getPiecePositionHashMap();
        for (Piece piece : pieces) {
            System.out.println("offset: " + piece.getOffset() + "   homeNumber: " + piece.getHomeNumber());
        }
    }

    @Test
    public void testMovePieceOutOfHome() throws Exception {
        board4p.movePiece(pieces.get(0));
        assertThat(pieces.get(0).getIndex()).isEqualTo(1);
        assertThat(board4p.getPiecePositionHashMap().get(pieces.get(0))).isEqualTo(board4p.getPositions().get(16));
    }

    @Test
    public void testMovePieceType1ToMiddlePath() throws Exception {
        Piece cp = pieces.get(0);
        cp.setIndex(40);
        board4p.movePiece(cp);
        assertThat(cp.getIndex()).isEqualTo(41);
        assertThat(board4p.getPiecePositionHashMap().get(cp)).isEqualTo(board4p.getPositions().get(16+40));
    }

    @Test
    public void testMovePieceType2ToMiddlePath() throws Exception {
        Piece cp = pieces.get(4);
        cp.setIndex(40);
        board4p.movePiece(cp);
        assertThat(cp.getIndex()).isEqualTo(41);
        assertThat(board4p.getPiecePositionHashMap().get(cp)).isEqualTo(board4p.getPositions().get(16+40+4));
    }

    @Test
    public void testMovePieceType3ToMiddlePath() throws Exception {
        Piece cp = pieces.get(8);
        cp.setIndex(40);
        board4p.movePiece(cp);
        assertThat(cp.getIndex()).isEqualTo(41);
        assertThat(board4p.getPiecePositionHashMap().get(cp)).isEqualTo(board4p.getPositions().get(16+40+8));
    }

    @Test
    public void testMovePieceType4ToMiddlePath() throws Exception {
        Piece cp = pieces.get(12);
        cp.setIndex(40);
        board4p.movePiece(cp);
        assertThat(cp.getIndex()).isEqualTo(41);
        assertThat(board4p.getPiecePositionHashMap().get(cp)).isEqualTo(board4p.getPositions().get(16+40+12));
    }

}
