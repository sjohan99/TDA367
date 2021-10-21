package com.example.fiamedknuff.model;

import java.io.Serializable;

/**
 * The class Position creates a position. Implements Serializable to handle data.
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
    Position(int x) {
        pos = x;
    }

    /**
     * Returns the position.
     *
     * @return the position.
     */
    int getPos() {
        return pos;
    }

}
