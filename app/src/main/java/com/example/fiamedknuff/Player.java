package com.example.fiamedknuff;

import java.util.ArrayList;

/**
 * A class Player that creates a player
 *
 * Created by
 * @author Amanda Cyrén
 */

public class Player {

    private final ArrayList<Piece> pieces;

    public Player() {
        this.pieces = new ArrayList<Piece>(4);
    }

    public ArrayList<Piece> getPieces() {
        return pieces;
    }
}
