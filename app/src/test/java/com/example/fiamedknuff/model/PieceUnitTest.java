package com.example.fiamedknuff.model;

import org.junit.Before;
import org.junit.Test;
import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;

public class PieceUnitTest {

    Piece piece;

    @Before
    public void createPiece() {
        piece = new Piece(Color.RED);
    }

    @Test
    public void testIndex() {
        assertThat(piece.getIndex()).isEqualTo(0);
        for (int i = 0; i < 10; i++) {
            piece.setIndex(i);
            assertThat(piece.getIndex()).isEqualTo(i);
        }
    }

    @Test
    public void testIncrementIndex() {
        assertThat(piece.getIndex()).isEqualTo(0);
        for (int i = 0; i < 10; i++) {
            piece.incrementIndex();
            assertThat(piece.getIndex()).isEqualTo(i+1);
        }
    }

    @Test
    public void testDecrementIndex() {
        piece.setIndex(10);
        assertThat(piece.getIndex()).isEqualTo(10);
        for (int i = 10; i >= 0; i--) {
            piece.decrementIndex();
            assertThat(piece.getIndex()).isEqualTo(i-1);
        }
    }

    @Test
    public void testResetIndex() {
        piece.setIndex(10);
        assertThat(piece.getIndex()).isEqualTo(10);
        piece.resetIndex();
        assertThat(piece.getIndex()).isEqualTo(0);
    }

    @Test
    public void testisHome() {
        piece.setIndex(10);
        assertFalse(piece.isHome());
        piece.resetIndex();
        assertTrue(piece.isHome());
    }

    @Test
    public void testHomeNumber() {
        assertThat(piece.getHomeNumber()).isEqualTo(0);
        piece.setHomeNumber(10);
        assertThat(piece.getHomeNumber()).isEqualTo(10);
    }

    @Test
    public void testOffset() {
        assertThat(piece.getOffset()).isEqualTo(0);
        piece.setOffset(10);
        assertThat(piece.getOffset()).isEqualTo(10);
    }

}
