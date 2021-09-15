package com.example.fiamedknuff;

import java.util.ArrayList;

/**
 * A class Player that creates a player
 *
 * Created by
 * @author Amanda Cyrén
 */

public class Player {

    private String name;
    private final ArrayList<Piece> pieces;

    public Player(String name) {
        this.name = name;
        this.pieces = new ArrayList<Piece>(4);
    }

    /**
     * Sets the name of the player to incoming parameter name
     * @param name takes in a String as name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns a list of the players pieces
     * @return a list of pieces
     */
    public ArrayList<Piece> getPieces() {
        return pieces;
    }
}
