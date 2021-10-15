package com.example.fiamedknuff.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.*;


import com.example.fiamedknuff.exceptions.NotFoundException;
import com.example.fiamedknuff.exceptions.NotImplementedException;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for testing Board.java
 */

public class BoardUnitTest {

    Board board4p;
    ArrayList<Piece> pieces;
    Position pos;

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
    public void testMovePieceType1ToMiddlePath() {
        Piece cp = pieces.get(0);
        cp.setIndex(40);
        board4p.movePiece(cp);
        assertThat(cp.getIndex()).isEqualTo(41);
        assertThat(board4p.getPiecePositionHashMap().get(cp)).isEqualTo(board4p.getPositions().get(16+40));
    }

    @Test
    public void testMovePieceType2ToMiddlePath() {
        Piece cp = pieces.get(4);
        cp.setIndex(40);
        board4p.movePiece(cp);
        assertThat(cp.getIndex()).isEqualTo(41);
        assertThat(board4p.getPiecePositionHashMap().get(cp)).isEqualTo(board4p.getPositions().get(16+40+4));
    }

    @Test
    public void testMovePieceType3ToMiddlePath() {
        Piece cp = pieces.get(8);
        cp.setIndex(40);
        board4p.movePiece(cp);
        assertThat(cp.getIndex()).isEqualTo(41);
        assertThat(board4p.getPiecePositionHashMap().get(cp)).isEqualTo(board4p.getPositions().get(16+40+8));
    }

    @Test
    public void testMovePieceType4ToMiddlePath() {
        Piece cp = pieces.get(12);
        cp.setIndex(40);
        board4p.movePiece(cp);
        assertThat(cp.getIndex()).isEqualTo(41);
        assertThat(board4p.getPiecePositionHashMap().get(cp)).isEqualTo(board4p.getPositions().get(16+40+12));
    }

    @Test
    public void testMovePieceAboutToLap() {
        var hMap = board4p.getPiecePositionHashMap();
        Piece piece = pieces.get(0);
        piece.setIndex(44);
        pos = board4p.getPositions().get(16+39);
        hMap.put(piece, pos);
        board4p.movePiece(piece);
        assertThat(piece.getIndex()).isEqualTo(45);
        assertThat(board4p.getPiecePositionHashMap().get(piece)).isEqualTo(board4p.getPositions().get(16));
    }

    @Test
    public void testMovePieceAsPerUsual() {
        var hMap = board4p.getPiecePositionHashMap();
        Piece piece = pieces.get(0);
        piece.setIndex(12);
        pos = board4p.getPositions().get(16+12);
        hMap.put(piece, pos);
        board4p.movePiece(piece);
        assertThat(piece.getIndex()).isEqualTo(13);
        assertThat(board4p.getPiecePositionHashMap().get(piece)).isEqualTo(board4p.getPositions().get(16+13));
    }

    @Test
    public void testMovePieceBackwards() {
        var hMap = board4p.getPiecePositionHashMap();
        Piece piece = pieces.get(0);
        piece.setIndex(42);
        pos = board4p.getPositions().get(16+41);
        hMap.put(piece, pos);
        board4p.movePieceBackwards(piece);
        assertThat(piece.getIndex()).isEqualTo(41);
        assertThat(board4p.getPiecePositionHashMap().get(piece)).isEqualTo(board4p.getPositions().get(16+40));
    }

    @Test
    public void testMovePieceType1BackwardsExitMiddlePath() {
        var hMap = board4p.getPiecePositionHashMap();
        Piece piece = pieces.get(0);
        piece.setIndex(41);
        pos = board4p.getPositions().get(16+40);
        hMap.put(piece, pos);
        board4p.movePieceBackwards(piece);
        assertThat(piece.getIndex()).isEqualTo(40);
        assertThat(board4p.getPiecePositionHashMap().get(piece)).isEqualTo(board4p.getPositions().get(16+39));
    }

    @Test
    public void testMovePieceType2BackwardsExitMiddlePath() {
        var hMap = board4p.getPiecePositionHashMap();
        Piece piece = pieces.get(4);
        piece.setIndex(41);
        pos = board4p.getPositions().get(16+44);
        hMap.put(piece, pos);
        board4p.movePieceBackwards(piece);
        assertThat(piece.getIndex()).isEqualTo(40);
        assertThat(board4p.getPiecePositionHashMap().get(piece)).isEqualTo(board4p.getPositions().get(16+9));
    }

    @Test
    public void testMovePieceType3BackwardsExitMiddlePath() {
        var hMap = board4p.getPiecePositionHashMap();
        Piece piece = pieces.get(8);
        piece.setIndex(41);
        pos = board4p.getPositions().get(16+48);
        hMap.put(piece, pos);
        board4p.movePieceBackwards(piece);
        assertThat(piece.getIndex()).isEqualTo(40);
        assertThat(board4p.getPiecePositionHashMap().get(piece)).isEqualTo(board4p.getPositions().get(16+19));
    }

    @Test
    public void testMovePieceType4BackwardsExitMiddlePath() {
        var hMap = board4p.getPiecePositionHashMap();
        Piece piece = pieces.get(12);
        piece.setIndex(41);
        pos = board4p.getPositions().get(16+52);
        hMap.put(piece, pos);
        board4p.movePieceBackwards(piece);
        assertThat(piece.getIndex()).isEqualTo(40);
        assertThat(board4p.getPiecePositionHashMap().get(piece)).isEqualTo(board4p.getPositions().get(16+29));
    }

    @Test
    public void testIsKnockoutFalse() {
        var hMap = board4p.getPiecePositionHashMap();
        Piece piece = pieces.get(0);
        piece.setIndex(9);
        pos = board4p.getPositions().get(16+11);
        hMap.put(piece, pos);
        assertFalse(board4p.isKnockout(piece));
        assertThat(board4p.getPiecePositionHashMap().get(piece)).isEqualTo(board4p.getPositions().get(16+11));
    }

    @Test
    public void testKnockoutPieceIfOccupied() throws NotFoundException {
        var hMap = board4p.getPiecePositionHashMap();

        // Initialize position and index for piece to knockout another piece
        Piece piece = pieces.get(0);
        piece.setIndex(9);
        pos = board4p.getPositions().get(16+11);
        hMap.put(piece, pos);

        // Initialize position and index for piece to be knocked out
        Piece pieceToBeKnockedOut = pieces.get(4);
        pieceToBeKnockedOut.setIndex(1);
        Position secondPos = board4p.getPositions().get(16+11);
        hMap.put(pieceToBeKnockedOut, secondPos);

        assertTrue(board4p.isKnockout(piece));
        board4p.knockOutPieceIfOccupied(piece);
        assertThat(board4p.getPiecePositionHashMap().get(piece)).isEqualTo(board4p.getPositions().get(16+11));
    }
}
