package com.example.fiamedknuff;

import com.example.fiamedknuff.model.Color;
import com.example.fiamedknuff.model.Game;
import com.example.fiamedknuff.model.GameFactory;
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
    Color[] colors;

    private void init(String[] playerNames, Color[] colors) {
        // TODO game skall skapas av gamefactory, skickar med input från annan controllerklass (den
        //  som jobbar med spelinput inför ett spel)
        game = GameFactory.createNewGame(playerNames, colors);
    }

    public void play() {
        while(game.getCurrentPlayerIndex() != -1) {
            // For a new round...
            currentPlayer = game.getCurrentPlayer();
            // TODO move dice to current player
            // TODO maybe not a while-loop, but we need to wait for player input to roll dice
            while() {
                diceValue = game.rollDice();
            }
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
            game.selectNextPlayer();
        }
    }

}
