package com.example.fiamedknuff.exceptions;

/**
 * Responsibility: TODO
 *
 * Used by: GameSetupFragment, Board, Game, GameFactory, GameViewModel
 * Uses: TODO
 *
 * Created by
 * @author Johan Selin
 */

public class NotImplementedException extends Exception {

    public NotImplementedException(String errorMessage) {
        super(errorMessage);
    }

    public NotImplementedException() {
        super();
    }
}
