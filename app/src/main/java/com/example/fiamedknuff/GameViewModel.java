package com.example.fiamedknuff;

import com.example.fiamedknuff.model.Game;
import com.example.fiamedknuff.model.Player;

/**
 * A class gameViewModel that ...
 *
 * Created by
 * @author Emma Stålberg
 */

public class GameViewModel {

    Game game;
    int playerCount;
    Player currentPlayer;
    int diceValue;

    private void init() {
        playerCount = 4; //TODO senare, input

        game = new Game(playerCount);
    }

    public void play() {
        while() {
            // For a new round…
            currentPlayer = game.getCurrentPlayer();

            // roll dice
            // Get player pieces for current player - unnecessary??
            // check and get which pieces are movable
            // show result of rolled dice
            while () {
                // Wait for player input
                // Check if selected piece is movable
                // If it´s movable, move selected piece
                // break
                // Else, give feedback that it is not movable
            }
            // Check if the player won/finished
            // if it won/finished - call method playerFinished
            // Select next player
        }
        // TODO skriv in villkor för while-satsen att när det inte är någon spelar kvar så bryt
    }

}
