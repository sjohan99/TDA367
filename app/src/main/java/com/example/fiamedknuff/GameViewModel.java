package com.example.fiamedknuff;

import com.example.fiamedknuff.model.Game;
import com.example.fiamedknuff.model.Piece;
import com.example.fiamedknuff.model.Player;

import java.util.Collection;

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
    Collection<Piece> movablePieces;
    Piece selectedPiece;
    String[] playerNames;
    Piece.Color[] colors;

    private void init() {

        // TODO game skall skapas av gamefactory, skickar med input från annan controllerklass (den
        //  som jobbar med spelinput inför ett spel)
        game = GameFactory.createNewGame(playerNames, colors);

    }

    public void play() {
        while() {
            // For a new round...
            currentPlayer = game.getCurrentPlayer();
            diceValue = game.rollDice();
            // TODO Get player pieces for current player - unnecessary??
            movablePieces = game.getMovablePieces(currentPlayer, diceValue);
            // TODO show result of rolled dice
            while () {
                // TODO Wait for player input, and update variable selectedPiece
                // Check if selected piece is movable
                if (movablePieces.contains(selectedPiece)) {
                    game.move(selectedPiece, diceValue);
                    break;
                } else {
                    // TODO give feedback that it is not movable
                }
            }

            // Select next player
        }
        // TODO skriv in villkor för while-satsen att när det inte är någon spelar kvar så bryt
    }

}
