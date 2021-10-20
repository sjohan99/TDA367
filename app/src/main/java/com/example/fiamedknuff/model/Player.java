package com.example.fiamedknuff.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * A class Player that creates a player. A player has a name and a list of pieces.
 * Implements Serializable to handle data.
 *
 * Created by
 * @author Amanda Cyrén
 */
public class Player implements Serializable {

    private String name; // A variable for the players name
    private final List<Piece> pieces; // A list of the players pieces

    /**
     * Sole constructor for the class. Creates a Player which has a name, color and a list of pieces.
     *
     * @param name is the name of the player.
     * @param color is the player's color.
     */
    public Player(String name, Color color) {
        this.name = name;
        pieces = new ArrayList<>(4);
        for (int i = 0; i < 4; i++) {
            pieces.add(new Piece(color));
        }
    }

    /**
     * Sets the name of the player.
     *
     * @param name the name of the player.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the name of the player.
     *
     * @return the name of the player.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns a list of the player's pieces.
     *
     * @return a list of the player's pieces.
     */
    public List<Piece> getPieces() {
        return pieces;
    }

    /**
     * Returns a list of the player's pieces which are able to make a move in the current round.
     *
     * @param pieces is the list of pieces for the current player.
     * @param roll is the value from the latest dice roll.
     * @return the list of pieces which are able to make a move.
     */
    ArrayList<Piece> getMovablePieces(Collection<Piece> pieces, int roll) {
        ArrayList<Piece> movablePieces = new ArrayList<>();
        for (Piece piece : pieces) {
            if (isMovable(piece, roll)) {
                movablePieces.add(piece);
            }
        }
        return movablePieces;
    }

    /**
     * Checks if a piece is allowed to make a move or not.
     *
     * @param piece is the piece to be checked.
     * @param roll is the value from the latest dice roll.
     * @return true if the piece is able to make a move, otherwise false.
     */
    boolean isMovable (Piece piece, int roll) {
        if (targetPositionOccupiedBySelf(piece.getIndex() + roll)) {
            return false;
        }
        if (piece.isHome()) {
            return roll == 1 || roll == 6;
        }
        return true;
    }

    /**
     * Checks if the given index is already occupied by one of this player's pieces.
     *
     * @param targetIndex is the index to be checked.
     * @return true if the position is occupied by self, otherwise false.
     */
    private boolean targetPositionOccupiedBySelf(int targetIndex) {
        for (Piece piece : pieces) {
            if (targetIndex == piece.getIndex()) {
                return true;
            }
        }
        return false;
    }


    /**
     * Removes a specific piece from the player's list of pieces.
     *
     * @param piece the piece to be removed.
     */
    void removePiece(Piece piece) {
        pieces.remove(piece);
    }

}
