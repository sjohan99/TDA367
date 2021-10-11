package com.example.fiamedknuff.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.*;


import com.example.fiamedknuff.NotImplementedException;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for testing Board.java
 */

public class Board3PlayerUnitTest {

    Board board3p;
    ArrayList<Piece> pieces;
    int pc = 3;

    @Before
    public void createBoard() throws NotImplementedException {
        pc = 3;
        pieces = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            pieces.add(new Piece(Color.YELLOW));
        }
        for (int i = 0; i < 4; i++) {
            pieces.add(new Piece(Color.RED));
        }
        for (int i = 0; i < 4; i++) {
            pieces.add(new Piece(Color.GREEN));
        }
        board3p = new Board(pc, pieces);
    }

    @Test(expected = NotImplementedException.class)
    public void boardPlayerAmountNotImplemented() throws NotImplementedException {
        Board board = new Board(6, new ArrayList<Piece>());
    }

    @Test
    public void boardPositionsInitializesCorrectly() {
        List<Position> positions = board3p.getPositions();
        assertEquals(57+12, positions.size());
        assertEquals(-12, positions.get(0).getPos());
        assertEquals(56, positions.get(positions.size()-1).getPos());
    }

    @Test
    public void boardHashMapInitializesCorrectly() {
        var hm = board3p.getPiecePositionHashMap();
        int i = -12;
        for (Piece piece : pieces) {
            assertEquals(hm.get(piece).getPos(), i++);
        }
    }

    @Test
    public void boardGivesPiecesCorrectOffsets() {
        var hm = board3p.getPiecePositionHashMap();
        for (Piece piece : pieces) {
            System.out.println("offset: " + piece.getOffset() + "   homeNumber: " + piece.getHomeNumber());
        }
    }

    @Test
    public void testMovePieceOutOfHome() throws Exception {
        board3p.movePiece(pieces.get(0));
        assertThat(pieces.get(0).getIndex()).isEqualTo(1);
        assertThat(board3p.getPiecePositionHashMap().get(pieces.get(0))).isEqualTo(board3p.getPositions().get(pc*4));
    }

    @Test
    public void testMovePieceType1ToMiddlePath() throws Exception {
        Piece cp = pieces.get(0);
        cp.setIndex(40);
        board3p.movePiece(cp);
        assertThat(cp.getIndex()).isEqualTo(41);
        assertThat(board3p.getPiecePositionHashMap().get(cp)).isEqualTo(board3p.getPositions().get((pc*4)+40));
    }

    @Test
    public void testMovePieceType2ToMiddlePath() throws Exception {
        Piece cp = pieces.get(4);
        cp.setIndex(40);
        board3p.movePiece(cp);
        assertThat(cp.getIndex()).isEqualTo(41);
        assertThat(board3p.getPiecePositionHashMap().get(cp)).isEqualTo(board3p.getPositions().get((pc*4)+40+4));
    }

    @Test
    public void testMovePieceType3ToMiddlePath() throws Exception {
        Piece cp = pieces.get(8);
        cp.setIndex(40);
        board3p.movePiece(cp);
        assertThat(cp.getIndex()).isEqualTo(41);
        assertThat(board3p.getPiecePositionHashMap().get(cp)).isEqualTo(board3p.getPositions().get((pc*4)+40+8));
    }
}
