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
    int firstPos;
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

        firstPos = board4p.getFirstPositionIndexInLap();
    }

    @Test(expected = NotImplementedException.class)
    public void boardPlayerAmountNotImplemented() throws NotImplementedException {
        Board board = new Board(6, new ArrayList<>());
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
        var hMap = board4p.getPiecePositionHashMap();
        int i = -16;
        for (Piece piece : pieces) {
            assertEquals(hMap.get(piece).getPos(), i++);
        }
    }

    @Test
    public void boardGivesPiecesCorrectOffsets() {
        for (Piece piece : pieces) {
            System.out.println("offset: " + piece.getOffset() + "   homeNumber: " + piece.getHomeNumber());
        }
    }

    @Test
    public void testGetPosition() {
        Piece piece = pieces.get(0);
        for (int i = 0; i < 10; i++) {
            board4p.movePiece(piece);
            assertThat(board4p.getPosition(piece)).isEqualTo(board4p.getPositions().get(firstPos + i));
        }
    }

    @Test
    public void testMovePieceOutOfHome() {
        board4p.movePiece(pieces.get(0));
        assertThat(pieces.get(0).getIndex()).isEqualTo(1);
        assertThat(board4p.getPiecePositionHashMap().get(pieces.get(0))).isEqualTo(board4p.getPositions().get(firstPos));
    }

    @Test
    public void testMovePieceType1ToMiddlePath() {
        Piece cp = pieces.get(0);
        cp.setIndex(40);
        board4p.movePiece(cp);
        assertThat(cp.getIndex()).isEqualTo(41);
        assertThat(board4p.getPiecePositionHashMap().get(cp)).isEqualTo(board4p.getPositions().get(16+40));
        board4p.movePiece(cp);
        board4p.movePiece(cp);
        board4p.movePiece(cp);
        board4p.movePiece(cp);
        board4p.movePieceBackwards(cp);
        board4p.movePieceBackwards(cp);
        assertThat(board4p.getPiecePositionHashMap().get(cp)).isEqualTo(board4p.getPositions().get(16+42));
    }

    @Test
    public void testMovePieceType2ToMiddlePath() {
        Piece cp = pieces.get(4);
        cp.setIndex(40);
        board4p.movePiece(cp);
        assertThat(cp.getIndex()).isEqualTo(41);
        assertThat(board4p.getPiecePositionHashMap().get(cp)).isEqualTo(board4p.getPositions().get(16+40+4));
        board4p.movePiece(cp);
        board4p.movePiece(cp);
        board4p.movePiece(cp);
        board4p.movePiece(cp);
        board4p.movePieceBackwards(cp);
        board4p.movePieceBackwards(cp);
        assertThat(board4p.getPiecePositionHashMap().get(cp)).isEqualTo(board4p.getPositions().get(16+42+4));
    }

    @Test
    public void testMovePieceType3ToMiddlePath() {
        Piece cp = pieces.get(8);
        cp.setIndex(40);
        board4p.movePiece(cp);
        assertThat(cp.getIndex()).isEqualTo(41);
        assertThat(board4p.getPiecePositionHashMap().get(cp)).isEqualTo(board4p.getPositions().get(16+40+8));
        board4p.movePiece(cp);
        board4p.movePiece(cp);
        board4p.movePiece(cp);
        board4p.movePiece(cp);
        board4p.movePieceBackwards(cp);
        board4p.movePieceBackwards(cp);
        assertThat(board4p.getPiecePositionHashMap().get(cp)).isEqualTo(board4p.getPositions().get(16+42+8));
    }

    @Test
    public void testMovePieceType4ToMiddlePath() {
        Piece cp = pieces.get(12);
        cp.setIndex(40);
        board4p.movePiece(cp);
        assertThat(cp.getIndex()).isEqualTo(41);
        assertThat(board4p.getPiecePositionHashMap().get(cp)).isEqualTo(board4p.getPositions().get(16+40+12));
        board4p.movePiece(cp);
        board4p.movePiece(cp);
        board4p.movePiece(cp);
        board4p.movePiece(cp);
        board4p.movePieceBackwards(cp);
        board4p.movePieceBackwards(cp);
        assertThat(board4p.getPiecePositionHashMap().get(cp)).isEqualTo(board4p.getPositions().get(16+42+12));
    }

    @Test
    public void testMovePieceAboutToLap() {
        var hMap = board4p.getPiecePositionHashMap();
        Piece piece = pieces.get(0);
        piece.setIndex(44);
        pos = board4p.getPositions().get(firstPos+39);
        hMap.put(piece, pos);
        board4p.movePiece(piece);
        assertThat(piece.getIndex()).isEqualTo(45);
        assertThat(hMap.get(piece)).isEqualTo(board4p.getPositions().get(firstPos));
    }

    @Test
    public void testMovePieceAsPerUsual() {
        var hMap = board4p.getPiecePositionHashMap();
        Piece piece = pieces.get(0);
        piece.setIndex(13);
        pos = board4p.getPositions().get(firstPos+12);
        hMap.put(piece, pos);
        board4p.movePiece(piece);
        assertThat(piece.getIndex()).isEqualTo(14);
        assertThat(hMap.get(piece)).isEqualTo(board4p.getPositions().get(firstPos+13));
    }

    @Test
    public void testMovePieceBackwards() {
        var hMap = board4p.getPiecePositionHashMap();
        Piece piece = pieces.get(0);
        piece.setIndex(42);
        pos = board4p.getPositions().get(firstPos+41);
        hMap.put(piece, pos);
        board4p.movePieceBackwards(piece);
        assertThat(piece.getIndex()).isEqualTo(41);
        assertThat(hMap.get(piece)).isEqualTo(board4p.getPositions().get(firstPos+40));
    }

    @Test
    public void testMovePieceType1BackwardsExitMiddlePath() {
        var hMap = board4p.getPiecePositionHashMap();
        Piece piece = pieces.get(0);
        piece.setIndex(41);
        pos = board4p.getPositions().get(firstPos+40);
        hMap.put(piece, pos);
        board4p.movePieceBackwards(piece);
        assertThat(piece.getIndex()).isEqualTo(40);
        assertThat(hMap.get(piece)).isEqualTo(board4p.getPositions().get(firstPos+39));
    }

    @Test
    public void testMovePieceType2BackwardsExitMiddlePath() {
        var hMap = board4p.getPiecePositionHashMap();
        Piece piece = pieces.get(4);
        piece.setIndex(41);
        pos = board4p.getPositions().get(firstPos+44);
        hMap.put(piece, pos);
        board4p.movePieceBackwards(piece);
        assertThat(piece.getIndex()).isEqualTo(40);
        assertThat(hMap.get(piece)).isEqualTo(board4p.getPositions().get(firstPos+9));
    }

    @Test
    public void testMovePieceType3BackwardsExitMiddlePath() {
        var hMap = board4p.getPiecePositionHashMap();
        Piece piece = pieces.get(8);
        piece.setIndex(41);
        pos = board4p.getPositions().get(firstPos+48);
        hMap.put(piece, pos);
        board4p.movePieceBackwards(piece);
        assertThat(piece.getIndex()).isEqualTo(40);
        assertThat(hMap.get(piece)).isEqualTo(board4p.getPositions().get(firstPos+19));
    }

    @Test
    public void testMovePieceType4BackwardsExitMiddlePath() {
        var hMap = board4p.getPiecePositionHashMap();
        Piece piece = pieces.get(12);
        piece.setIndex(41);
        pos = board4p.getPositions().get(firstPos+52);
        hMap.put(piece, pos);
        board4p.movePieceBackwards(piece);
        assertThat(piece.getIndex()).isEqualTo(40);
        assertThat(hMap.get(piece)).isEqualTo(board4p.getPositions().get(firstPos+29));
    }

    @Test
    public void testIsKnockoutFalse() {
        var hMap = board4p.getPiecePositionHashMap();
        Piece piece = pieces.get(0);
        piece.setIndex(12);
        pos = board4p.getPositions().get(firstPos+11);
        hMap.put(piece, pos);
        assertFalse(board4p.isKnockout(piece));
        assertThat(hMap.get(piece)).isEqualTo(board4p.getPositions().get(firstPos+11));
    }

    @Test
    public void testKnockoutPieceIfOccupied() throws NotFoundException {
        var hMap = board4p.getPiecePositionHashMap();

        // Initialize position and index for piece to knockout another piece
        Piece piece = pieces.get(0);
        piece.setIndex(12);
        pos = board4p.getPositions().get(firstPos+11);
        hMap.put(piece, pos);

        // Initialize position and index for piece to be knocked out
        Piece pieceToBeKnockedOut = pieces.get(4);
        pieceToBeKnockedOut.setIndex(2);
        Position secondPos = board4p.getPositions().get(firstPos+11);
        hMap.put(pieceToBeKnockedOut, secondPos);

        assertTrue(board4p.isKnockout(piece));
        board4p.knockOutPieceIfOccupied(piece);
        assertThat(hMap.get(piece)).isEqualTo(board4p.getPositions().get(firstPos+11));
    }

    @Test
    public void testKnockoutWithPiece() throws NotFoundException {
        var hMap = board4p.getPiecePositionHashMap();

        // Initialize position and index for piece to knockout another piece
        Piece piece = pieces.get(0);
        piece.setIndex(12);
        pos = board4p.getPositions().get(firstPos+11);
        hMap.put(piece, pos);

        // Initialize position and index for piece to be knocked out
        Piece pieceToBeKnockedOut = pieces.get(4);
        pieceToBeKnockedOut.setIndex(2);
        Position secondPos = board4p.getPositions().get(firstPos+11);
        hMap.put(pieceToBeKnockedOut, secondPos);

        assertThat(board4p.knockoutWithPiece(piece)).isEqualTo(pieceToBeKnockedOut);
        assertThat(pieceToBeKnockedOut.getIndex()).isEqualTo(0);
        assertThat(hMap.get(pieceToBeKnockedOut)).isEqualTo(board4p.getPositions().get(4));
    }

    @Test(expected = NotFoundException.class)
    public void testPieceAtPositionNotFoundException() throws NotFoundException {
        var hMap = board4p.getPiecePositionHashMap();
        Piece piece = pieces.get(0);
        piece.setIndex(12);
        pos = board4p.getPositions().get(firstPos+11);
        hMap.put(piece, pos);
        board4p.knockoutWithPiece(piece);
    }

    @Test(expected = NotFoundException.class)
    public void testIndexOfHomeNumberNotFoundException() throws NotFoundException {
        var hMap = board4p.getPiecePositionHashMap();

        // Initialize position and index for piece to knockout another piece
        Piece piece = pieces.get(0);
        piece.setIndex(12);
        pos = board4p.getPositions().get(firstPos+11);
        hMap.put(piece, pos);

        // Initialize position and index for piece to be knocked out and set its home number to a
        // value that will cause an exception
        Piece pieceToBeKnockedOut = pieces.get(4);
        pieceToBeKnockedOut.setIndex(2);
        Position secondPos = board4p.getPositions().get(firstPos+11);
        hMap.put(pieceToBeKnockedOut, secondPos);
        pieceToBeKnockedOut.setHomeNumber(-17);

        board4p.knockoutWithPiece(piece);
    }

    @Test
    public void testRemovePieceFromBoard() {
        var hMap = board4p.getPiecePositionHashMap();
        Piece piece = pieces.get(0);
        piece.setIndex(2);
        pos = board4p.getPositions().get(firstPos+1);
        hMap.put(piece, pos);

        assertTrue(hMap.containsKey(piece));
        board4p.removePieceFromBoard(piece);
        assertFalse(hMap.containsKey(piece));
    }

    @Test
    public void testGetLapLength() {
        assertThat(board4p.getLapLength()).isEqualTo(40);
    }

    @Test
    public void testGetFinishIndex() {
        assertThat(board4p.getFinishIndex()).isEqualTo(45);
    }

    @Test
    public void testGetFirstPositionIndexInLap() {
        assertThat(board4p.getFirstPositionIndexInLap()).isEqualTo(16);
    }

}
