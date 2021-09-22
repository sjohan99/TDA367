package com.example.fiamedknuff.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * A class Player that creates a player
 *
 * Created by
 * @author Amanda Cyrén
 */

public class Player {

    // A variable for the players name
    private String name;

    // A list of the players pieces
    private final List<Piece> pieces;

    public Player(String name, Color color) {
        this.name = name;
        pieces = new ArrayList<>(4);
        for (int i = 0; i < 4; i++) {
            pieces.add(new Piece(color));
        }
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
    public List<Piece> getPieces() {
        return pieces;
    }

    /**
     * Finds out which pieces that are movable in the current move
     * @param pieces is the collection of pieces of the current player
     * @param roll is the value from the latest diceroll
     * @return which pieces that are able to move
     */
    Collection<Piece> getMovablePieces(Collection<Piece> pieces, int roll) {
        Collection<Piece> movablePieces = new ArrayList<>();

        for (Piece piece : pieces) {
            if (isMovable(piece, roll)) {
                movablePieces.add(piece);
            }
        }
        return movablePieces;

    }

    /**
     * Finds out if one piece is allowed to move or not
     * @param piece is one of the current players pieces
     * @param roll is the value from the latest diceroll
     * @return if the piece is movable or not
     */
    boolean isMovable (Piece piece, int roll) {
        if (piece.getIndex() == 0) {
            if (roll == 1 || roll == 6) {
                return true;
            }
        }
        int posP = piece.getIndex() + roll;
        for (Piece p : pieces) {
            if (posP == p.getIndex()) {
                return false;
            }
        }
        return true;
    }
}
