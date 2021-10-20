package com.example.fiamedknuff.exceptions;

/**
 * Responsibility: TODO
 *
 * Used by: BoardFragment, Board, Game, GameViewModel
 * Uses: TODO
 *
 * Created by
 * @author Johan Selin
 */

public class NotFoundException extends Exception {

    public NotFoundException(String errorMessage) {
        super(errorMessage);
    }

    public NotFoundException() {
        super();
    }

}
