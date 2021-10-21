package com.example.fiamedknuff.exceptions;

/**
 * Responsibility: Exception for when iterating an iterative searching for a 
 *      certain value but it cannot be found.
 *
 * Used by: BoardFragment, Board, Game, GameViewModel
 * Uses: Exception
 *
 * Created by
 * @author Johan Selin
 */
public class NotFoundException extends Exception {

    /**
     * Creates a NotFoundException with a message to the client
     * @param errorMessage Specify what couldn't be found
     */
    public NotFoundException(String errorMessage) {
        super(errorMessage);
    }

    public NotFoundException() {
        super();
    }

}
