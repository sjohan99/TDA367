package com.example.fiamedknuff.model;

import java.io.Serializable;

/**
 * A class representing a Piece in the game. Implements Serializable to handle data.
 *
 * Created by
 * @author Hanna Boquist
 */
public class Piece implements Serializable {

    private int index; // Amount of steps the piece has taken towards its goal
    private Color color; // The color of the piece
    private int homeNumber; // The index which represents where the pieces home is
    private int offset; // The offset which represents how many positions above zero the piece gets when leaving its home

    /**
     * Sole constructor for the class. Creates a piece of the color supplied.
     *
     * @param color the color the Piece is supposed to have.
     */
    Piece(Color color) {
        this.color = color;
        index = 0;      // index = 0 innebär att pjäsen sätts i boet
    }

    /**
     * Returns the piece's current index.
     *
     * @return the piece's current index.
     */
    public int getIndex() {
        return index;
    }

    /**
     * Sets the piece's index. Used for testing purposes only.
     *
     * @param index the index the piece will receive.
     */
    public void setIndex(int index) {
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
    public int getHomeNumber() {
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
    public void setOffset(int offset) {
        this.offset = offset;
    }

    /**
     * Returns the offset of the the piece.
     *
     * @return the offset of the the piece.
     */
    public int getOffset() {
        return this.offset;
    }
}
