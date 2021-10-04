package com.example.fiamedknuff.viewModels;

import com.example.fiamedknuff.NotImplementedException;
import com.example.fiamedknuff.model.Color;
import com.example.fiamedknuff.model.Game;
import com.example.fiamedknuff.model.GameFactory;
import com.example.fiamedknuff.model.Piece;
import com.example.fiamedknuff.model.Player;

import java.util.Collection;
import java.util.List;

/**
 * A class gameViewModel that ...
 *
 * Created by
 * @author Emma Stålberg
 */

public class GameViewModel {

    private Game game;
    private int playerCount;
    private Player currentPlayer;
    private int diceValue;
    private Collection<Piece> movablePieces;
    private Piece selectedPiece;
    private String[] playerNames;
    private Color[] colors;

    private void init(String[] playerNames, Color[] colors) throws NotImplementedException {
        // TODO game skall skapas av gamefactory, skickar med input från annan controllerklass (den
        //  som jobbar med spelinput inför ett spel)
        game = GameFactory.createNewGame(playerNames, colors);
    }

    /*public void play() throws Exception{
        while(game.getCurrentPlayerIndex() != -1) {
            // For a new round...
            currentPlayer = game.getCurrentPlayer();
            // TODO move dice to current player
            // TODO maybe not a while-loop, but we need to wait for player input to roll dice
            // while() {
                diceValue = game.rollDice();
            // }
            movablePieces = game.getMovablePieces(currentPlayer, diceValue);
            // TODO show result of rolled dice
            // while () {
                // TODO Wait for player input, and update variable selectedPiece
                // Check if selected piece is movable
                if (movablePieces.contains(selectedPiece)) {
                    game.move(diceValue, selectedPiece);
                    break;
                } else {
                    // TODO give feedback that it is not movable
                }
            // }

            // if you rolled a six, it´s your turn again
            // TODO if a player made a six and finished, it should be the next players turn...
            if (diceValue != 6) {
                game.selectNextPlayer();
            }
        }
    }*/

    //TODO
    //should have a parameter with the piece or index of piece or something ??
    // update so that it works for when a player has finished - or maybe it already does??
    public void pieceClicked() {
        // if the rolled dice is already used, we can´t move any piece before another roll has
        // been made
        if (!game.getDice().getIsUsed()) {
            // check if its movable
            List<Piece> movablePieces = game.getMovablePieces(game.getCurrentPlayer());
            // TODO how to compare a piece with the respective imageview?
            // if (movablePieces.contains the piece)
            if(true) {
                //move
                game.getDice().setIsUsed(true);
            }
            moveToNextPlayer();
        }
    }

    private void moveToNextPlayer() {
        game.selectNextPlayer();
        // check if game finished - finish
        // move dice to the next player
    }
}
