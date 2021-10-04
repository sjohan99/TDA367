package com.example.fiamedknuff.model;

import java.io.Serializable;

/**
 * The class Position creates a position.
 *
 * Created by
 * @author Emma Stålberg
 */

public class Position implements Serializable {

    private final int pos;

    public Position(int x) {
        pos = x;
    }

    /**
     * Returns the position.
     * @return the position
     */
    public int getPos() {
        return pos;
    }

}
