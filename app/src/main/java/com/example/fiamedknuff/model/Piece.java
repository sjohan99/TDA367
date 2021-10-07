package com.example.fiamedknuff.model;

import java.io.Serializable;

/**
 * A class representing a Piece in the game
 *
 * Created by
 * TODO add author
 * @author Unknown
 */
public class Piece implements Serializable {

    private int index; // Amount of steps the piece has taken towards its goal
    private Color color; // The color of the piece
    private int homeNumber; // The index which represents where the pieces home is
    private int offset; // The offset which represents how many positions above zero the piece gets when leaving its home

    /**
     * Creates a piece of the color supplied
     * @param color the color the Piece is supposed to have
     */
    public Piece(Color color) {
        this.color = color;
        index = 0;      // index = 0 innebär att pjäsen sätts i boet
    }

    /**
     * Returns the piece's current index
     * @return the piece's current index
     */
    public int getIndex() {
        return index;
    }

    /**
     * Only used for testing purposes
     * Sets the piece's index
     * @param index the index the piece will receive
     */
    public void setIndex(int index) {
        this.index = index;
    }

    /**
     * Increases the piece's index by 1
     */
    void incrementIndex() {
        this.index += 1;
    }

    /**
     * decreases the piece's index by 1
     */
    void decrementIndex() {
        this.index -= 1;
    }

    /**
     * Sets the piece's index to zero
     */
    void resetIndex() {
        this.index = 0;
    }

    /**
     * gets whether the piece is at home
     * @return whether the piece is at home
     */
    boolean isHome () {
        return index == 0;
    }

    /**
     * gets the piece's home number
     * @return the piece's home number
     */
    public int getHomeNumber() {
        return homeNumber;
    }

    /**
     * Sets the piece's home number
     * @param homeNumber the homenumber the piece is supposed to have
     */
    void setHomeNumber(int homeNumber) {
        this.homeNumber = homeNumber;
    }

    /**
     * Sets the piece's offset
     * @param offset the offset the piece should have
     */
    public void setOffset(int offset) {
        this.offset = offset;
    }

    /**
     * Gets the piece's offset
     * @return the piece's offset
     */
    public int getOffset() {
        return this.offset;
    }
}
