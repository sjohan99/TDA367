package com.example.fiamedknuff.viewModels;

import android.widget.ImageView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.fiamedknuff.NotImplementedException;
import com.example.fiamedknuff.model.CPU;
import com.example.fiamedknuff.model.Color;
import com.example.fiamedknuff.model.Game;
import com.example.fiamedknuff.model.GameFactory;
import com.example.fiamedknuff.model.Piece;
import com.example.fiamedknuff.model.Player;
import com.example.fiamedknuff.model.Position;

import java.util.Collection;
import java.util.List;

/**
 * A class responsible for communicating the model to whatever view is interested.
 *
 * Created by
 * @author Emma Stålberg
 */

public class GameViewModel extends ViewModel {

    private Game game;
    private int playerCount;
    private int diceValue;
    private Collection<Piece> movablePieces;
    private Piece selectedPiece;
    private List<String> playerNames;
    private Color[] colors;

    public MutableLiveData<Boolean> isMoved = new MutableLiveData<>();
    public MutableLiveData<Player> currentPlayer = new MutableLiveData<>();

    public void init(List<String> playerNames, Color[] colors, boolean[] selectedCPU) throws NotImplementedException {
        this.playerNames = playerNames;
        game = GameFactory.createNewGame(playerNames, colors, selectedCPU);

        // For each CPU in the player list, set the board
        for (Player player: game.getActivePlayers()) {
            if (player.getClass() == CPU.class) {
                ((CPU) player).setBoard(game.getBoard());
            }
        }
    }

    /**
     * Moves the clicked piece.
     * @param clickedPiece is the clicked piece
     */
    public void move(Piece clickedPiece) {
        // if the rolled dice is already used, we can´t move any piece before another roll has
        // been made
        if (!game.getDice().getIsUsed()) {
            List<Piece> movablePieces = game.getMovablePieces(game.getCurrentPlayer());
            //checks if the clicked piece is movable
            if(movablePieces.contains(clickedPiece)) {
                movePiece(clickedPiece);
            }
        }
    }

    /**
     * Moves the piece. Sets the value of the mutable livedata "isMoved" to true.
     * @param piece is the piece that should be moved
     */
    private void movePiece(Piece piece) {
        try {
            game.move(piece);
            isMoved.setValue(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Removes the piece from the model if it is finished.
     * @param piece is the piece that might be finished
     * @return true if the piece is finished, false if it is not
     */
    public boolean removePieceIfFinished(Piece piece) {
        return game.removePieceIfFinished(piece);
    }

    /**
     * Removes the current player from the model if it is finished.
     * @return true if the player is finished, and false otherwise.
     */
    public boolean removePlayerIfFinished() {
        return game.removePlayerIfFinished();
    }

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
  
    /**
     * Returns the dicevalue.
     * @return the dicevalue.
     */
    public int getDiceValue() {
        return game.getDice().getRolledValue();
    }

    /**
     * Selects the next player in the model. Sets the value of the variable currentPlayer
     * to the current player.
     */
    public void selectNextPlayer() {
        game.selectNextPlayer();
        currentPlayer.setValue(game.getCurrentPlayer());
    }

    /**
     * Sets the dice to used.
     */
    public void diceIsUsed() {
        game.getDice().setIsUsed(true);
    }

    /**
     * Returns number of active players in the game.
     * @return number of active players.
     */
    public int getPlayerCount() {
        return game.getPlayerCount();
    }

    /**
     * Returns the position of the piece given as a parameter.
     * @param piece is the piece from which you want to know the position
     * @return the position of the given piece
     */
    public Position getPosition(Piece piece) {
        return game.getPosition(piece);
    }

    /**
     * Checks if the current player can move any of their pieces with the rolled
     * dicevalue.
     * @return true if the current player can move any piece, and false otherwise
     */
    public boolean isPossibleToUseDicevalue() {
        List<Piece> movablePieces = game.getMovablePieces(game.getCurrentPlayer());
        return movablePieces.size() != 0;
    }

    /**
     * If a player rolls a six and is not finished, it is their turn again. Otherwise,
     * it is the next player´s turn.
     * @param playerIsFinished is true if the player is finished, otherwise false.
     * @return true if it is the next player´s turn, otherwise false.
     */
    public boolean isNextPlayer(boolean playerIsFinished) {
        return !((getDiceValue() == 6) && !playerIsFinished);
    }

    public LiveData<List<Piece>> getMovablePiecesForCurrentPlayer() {
        MutableLiveData<List<Piece>> data = new MutableLiveData<>();
        data.setValue(game.getMovablePieces(game.getCurrentPlayer()));
        return data;
    }
}
