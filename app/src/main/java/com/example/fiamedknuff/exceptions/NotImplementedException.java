package com.example.fiamedknuff.exceptions;

/**
 * Responsibility: Exception for when the client has issued a valid command 
 *    but the feature is not yet implemented.
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
