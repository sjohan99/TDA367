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
     * Constructor that creates a position.
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
