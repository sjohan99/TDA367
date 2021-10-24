package com.example.fiamedknuff.model;

import java.io.Serializable;

/**
 * Responsibility: A class Piece representing a Piece in the game. Implements Serializable
 *  to handle data.
 *
 * Used by: BoardFragment, Board, CPU, Game, Player, GameViewModel
 * Uses: Color
 *
 * Created by
 * @author Hanna Boquist, Amanda Cyrén, Emma Stålberg, Johan Selin, Philip Winsnes
 */
public class Piece implements Serializable {

    // Amount of steps the piece has taken towards its goal
    private int index;

    // The color of the piece
    private final Color color;

    // The index which represents where the pieces home is
    private int homeNumber;

    // The offset which represents how many positions above zero the piece gets when leaving its home
    private int offset;

    /**
     * Sole constructor for the class. Creates a piece of the color supplied.
     *
     * @param color the color the Piece is supposed to have.
     */
    Piece(Color color) {
        this.color = color;
        index = 0; // The piece will start from its home
    }

    /**
     * Returns the piece's current index.
     *
     * @return the piece's current index.
     */
    int getIndex() {
        return index;
    }

    /**
     * Sets the piece's index. Used for testing purposes only.
     *
     * @param index the index the piece will receive.
     */
    void setIndex(int index) {
        this.index = index;
    }

    /**
     * Increases the piece's index by 1.
     */
    void incrementIndex() {
        this.index += 1;
    }

    /**
     * Decreases the piece's index by 1.
     */
    void decrementIndex() {
        this.index -= 1;
    }

    /**
     * Sets the piece's index to zero.
     */
    void resetIndex() {
        this.index = 0;
    }

    /**
     * Returns if the piece is at home or not.
     *
     * @return true if the piece is at home, otherwise false.
     */
    boolean isHome () {
        return index == 0;
    }

    /**
     * Returns the home number of the piece
     *
     * @return the home number of the piece.
     */
    int getHomeNumber() {
        return homeNumber;
    }

    /**
     * Sets the home number of the piece
     *
     * @param homeNumber the home number of the piece.
     */
    void setHomeNumber(int homeNumber) {
        this.homeNumber = homeNumber;
    }

    /**
     * Sets the offset of the the piece.
     *
     * @param offset the offset of the the piece.
     */
    void setOffset(int offset) {
        this.offset = offset;
    }

    /**
     * Returns the offset of the the piece.
     *
     * @return the offset of the the piece.
     */
    int getOffset() {
        return this.offset;
    }
}
