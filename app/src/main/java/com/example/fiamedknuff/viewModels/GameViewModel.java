package com.example.fiamedknuff.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

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

public class GameViewModel extends ViewModel {

    private Game game;
    private int playerCount;
    private Player currentPlayer;
    private int diceValue;
    private Collection<Piece> movablePieces;
    private Piece selectedPiece;
    private List<String> playerNames;
    private Color[] colors;

    public void init(List<String> playerNames, Color[] colors, boolean[] selectedCPU) throws NotImplementedException {
        // TODO game skall skapas av gamefactory, skickar med input från annan controllerklass (den
        //  som jobbar med spelinput inför ett spel)
        // this.playerNames.setValue(playerNames);
        this.playerNames = playerNames;
        game = GameFactory.createNewGame(playerNames, colors, selectedCPU);
    }

    public void play() throws Exception{
        while (game.getCurrentPlayerIndex() != -1) {
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
    }
    
    // TODO update so that it works for when a player has finished with the new method

    /**
     * TODO
     * @param clickedPiece is the clicked piece
     */
    
    public void pieceClicked(Piece clickedPiece) {
        // if the rolled dice is already used, we can´t move any piece before another roll has
        // been made
        if (!game.getDice().getIsUsed()) {
            List<Piece> movablePieces = game.getMovablePieces(game.getCurrentPlayer());
            //checks if the clicked piece is movable
            if(movablePieces.contains(clickedPiece)) {
                move(clickedPiece);
                checkIfRemoved(clickedPiece); // TODO change! see above
            }
            moveToNextPlayer();
        }
    }

    /**
     * TODO - should be erased and replaced
     * Checks if the piece is removed from the board in the model. If so, the piece
     * should be removed from the view as well.
     * @param piece is the piece that should be checked.
     */

    private void checkIfRemoved(Piece piece) {
        if (!game.getAllPlayerPieces().contains(piece)) {
            // remove piece from view
        }
    }

    /**
     * Moves the piece and tells the dice it has been used.
     * @param piece is the piece that should be moved
     */

    private void move(Piece piece) {
        try {
            game.move(piece);
            // TODO move piece in the view!! (maybe new method... is coming)
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // TODO

    private void moveToNextPlayer() {
        game.selectNextPlayer();
        // check if game finished - finish
        // move dice to the next player
        game.getDice().setIsUsed(true);

    public LiveData<String> getPlayerName(int index) {
        MutableLiveData<String> data = new MutableLiveData<>();
        data.setValue(playerNames.get(index));
        return data;
    }

    /**
     * If the dice is ready to be rolled (i.e. is used), the dice is rolled.
     * @return the value of the rolled dice (if it´s ready to be rolled), or -1 if
     * it´s not ready to be rolled.
     */

    public int rollDice() {
        
        // if the dice is used, you may roll again

        if (game.getDice().getIsUsed()) {
            game.rollDice();
            return game.getDice().getRolledValue();
        }
        return -1;
    }

    /**
     * Returns all player pieces from game.
     * @return all player pieces
     */

    public List<Piece> getPieces() {
        return game.getAllPlayerPieces();
    }

    /**
     * Returns the positions from the board.
     * @return the positions from the board.
     */

    public List<Position> getPositions() {
        return game.getPositions();
    }
}

    public LiveData<String> getPlayerName(int index) {
        MutableLiveData<String> data = new MutableLiveData<>();
        data.setValue(playerNames.get(index));
        return data;
    }
}
