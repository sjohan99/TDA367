package com.example.fiamedknuff.model;

import java.util.ArrayList;
import java.util.Collection;

/**
 * A class Player that creates a player
 *
 * Created by
 * @author Amanda Cyrén
 */

public class Player {

    // A variable for the players name
    private String name;

    // A collection of the players pieces
    private final Collection<Piece> pieces;

    public Player(String name) {
        this.name = name;
        pieces = new ArrayList<Piece>(4);
    }

    /**
     * Sets the name of the player to incoming parameter name
     * @param name takes in a String as name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the name of the player
     * @return the players name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets a list of the players pieces
     * @return a list of pieces
     */
    public Collection<Piece> getPieces() {
        return pieces;
    }
}
