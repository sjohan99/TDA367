package com.example.fiamedknuff.model;

import com.example.fiamedknuff.NotImplementedException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Game implements Serializable {

    private Board board;
    private List<Player> activePlayers;
    private int currentPlayerIndex = 0;
    private Dice dice;
    private List<Player> finishedPlayers = new ArrayList<>();

    public Game(List<Player> players) throws NotImplementedException {
        activePlayers = players;
        board = new Board(players.size(), getAllPlayerPieces());
        dice = new Dice();
    }

    /**
     * Get the current player
     * @return the current player
     */
    public Player getCurrentPlayer() {
        return activePlayers.get(currentPlayerIndex);
    }

    public void selectNextPlayer() {
        if (activePlayers.size() == 0) {
            // currentPlayerIndex is set to -1 when there is no active players left
            currentPlayerIndex = -1;
        }
        currentPlayerIndex++;
        if (currentPlayerIndex >= activePlayers.size()) {
            currentPlayerIndex = 0;
        }
    }

    /**
     * For testing purposes only right now
     * @return all active players
     */
    public List<Player> getActivePlayers() {
        return activePlayers;
    }

    /**
     * Get the current players index
     * @return the current players index
     */
    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }

    /**
     * Get the current players pieces
     * @param player specifies the current player
     * @return the current players pieces
     */
    private List<Piece> getPlayerPieces(Player player) {
        return player.getPieces();
    }

    /**
     * Get all the players pieces
     * @return all the players pieces in a list
     */
    private List<Piece> getAllPlayerPieces() {
        List<Piece> pieces = new ArrayList<>();
        for (Player player : activePlayers) {
            pieces.addAll(getPlayerPieces(player));
        }
        return pieces;
    }

    /**
     * Get the current players movable pieces
     * @return all the players movable pieces in a collection
     */
    public ArrayList<Piece> getMovablePieces(Player player, int rolledValue) {
        return player.getMovablePieces(player.getPieces(), rolledValue);
    }

    /**
     * Rolls the dice
     * @return the value of the rolled dice
     */
    public int rollDice() {
        return dice.rollDice();
    }

    private void finishedPlayer(Player player) {
        activePlayers.remove(player);
        finishedPlayers.add(player);
    }


    /**
     * Removes the given piece from the piece-position hashmap and the current player's
     * piece list, effectively removing it from the game
     * @param piece The piece to be removed
     */
    private void removeFinishedPiece(Piece piece) {
        board.removePieceFromBoard(piece);
        getCurrentPlayer().removePiece(piece);
    }

    private boolean isFinishedPlayer(Player player) {
        return player.getPieces().size() == 0;
    }

    public void move(int diceValue, Piece piece) throws Exception {
        for (int i = 0; i < diceValue; i++) {
            board.movePiece(piece);
        }
        board.knockOutPieceIfOccupied(piece);
        if (piece.getIndex() == 45) {
            removeFinishedPiece(piece);
        }
        if (isFinishedPlayer(getCurrentPlayer())) {
            finishedPlayer(getCurrentPlayer());
        }

        // move the piece
        // check for knockout
        // check if finished piece - "hide"
        // check if player is finished - call finishedPlayer
    }

    /**
     * For testing purposes only currently
     * @return
     */
    Board getBoard() {
        return this.board;
    }

   /*
    //private move(Piece piece) {
        //board.move(piece);
   */

}
