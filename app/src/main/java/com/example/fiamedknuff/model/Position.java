package com.example.fiamedknuff.model;

import java.io.Serializable;

/**
 * Responsibility: A class Position that creates a position. Implements Serializable to
 *  handle data.
 *
 * Used by: BoardFragment, Board, CPU, Game, GameViewModel
 * Uses: -
 *
 * Created by
 * @author Emma Stålberg
 */
public class Position implements Serializable {

    private final int pos; // Variable to hold the position

    /**
     * Sole constructor for the class. Creates a position which has a variable pos.
     *
     * @param x is the position.
     */
    public Position(int x) {
        pos = x;
    }

    /**
     * Returns the position.
     *
     * @return the position.
     */
    public int getPos() {
        return pos;
    }

}
