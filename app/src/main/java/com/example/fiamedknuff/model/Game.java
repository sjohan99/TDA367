package com.example.fiamedknuff.model;

import com.example.fiamedknuff.NotImplementedException;

import java.io.Serializable;
import java.util.ArrayList;
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

    public Dice getDice() {
        return dice;
    }

    /**
     * Checks if there are more than one active player left in the game and if so, increments
     * the current player index to select next player
     */
    public void selectNextPlayer() {
        if (activePlayers.size() <= 1) {
            // currentPlayerIndex is set to -1 when there is one or less players left
            currentPlayerIndex = -1;
        }
        else {
            currentPlayerIndex++;
            if (currentPlayerIndex >= activePlayers.size()) {
                currentPlayerIndex = 0;
            }
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
    public List<Piece> getAllPlayerPieces() {
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
    public ArrayList<Piece> getMovablePieces(Player player) {
        return player.getMovablePieces(player.getPieces(), dice.getRolledValue());
    }

    /**
     * Rolls the dice
     */
    public void rollDice() {
        dice.rollDice();
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
        if (pieceWillMovePastGoal(diceValue, piece)) {
            movePieceAndMoveBackwardsAfterMiddle(diceValue, piece);
        }
        else {
            movePieceNormally(diceValue, piece);
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

    private void movePieceNormally(int diceValue, Piece piece) throws Exception {
        for (int i = 0; i < diceValue; i++) {
            board.movePiece(piece);
        }
    }

    private boolean pieceWillMovePastGoal(int diceValue, Piece piece) {
        return piece.getIndex() + diceValue > 45;
    }

    private void movePieceAndMoveBackwardsAfterMiddle(int diceValue, Piece piece) throws Exception {
        int forwardSteps = 45 - piece.getIndex();
        int backwardSteps = diceValue - forwardSteps;
        for (int i = forwardSteps; i > 0; i--) {
            board.movePiece(piece);
        }
        for (int i = backwardSteps; i > 0; i--) {
            board.movePieceBackwards(piece);
        }
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
