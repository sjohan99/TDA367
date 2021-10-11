package com.example.fiamedknuff.model;

import com.example.fiamedknuff.NotImplementedException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Class responsible for tying together the different components of the game and using them to
 * simulate the game
 */
public class Game implements Serializable {

    private Board board;
    private List<Player> activePlayers;
    private int currentPlayerIndex = 0;
    private Dice dice;
    private List<Player> finishedPlayers = new ArrayList<>();

    /**
     * Creates a game
     *
     * @param players the players who will play the game
     * @throws NotImplementedException if an unsupported amount of players is given
     */
    public Game(List<Player> players) throws NotImplementedException {
        activePlayers = players;
        board = new Board(players.size(), getAllPlayerPieces());
        dice = new Dice();
    }

    /**
     * Get the board
     * @return board
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Get the current player
     *
     * @return the current player
     */
    public Player getCurrentPlayer() {
        return activePlayers.get(currentPlayerIndex);
    }

    /**
     * gets the dice
     *
     * @return the dice
     */
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
        } else {
            currentPlayerIndex++;
            if (currentPlayerIndex >= activePlayers.size()) {
                currentPlayerIndex = 0;
            }
        }
    }

    /**
     * For testing purposes only right now
     *
     * @return all active players
     */
    public List<Player> getActivePlayers() {
        return activePlayers;
    }

    /**
     * Get the current players index
     *
     * @return the current players index
     */
    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }

    /**
     * Get the current players pieces
     *
     * @param player specifies the current player
     * @return the current players pieces
     */
    private List<Piece> getPlayerPieces(Player player) {
        return player.getPieces();
    }

    /**
     * Get all the players pieces
     *
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
     *
     * @param player gets the movable pieces of this player
     * @return all the players movable pieces in a collection
     */
    public ArrayList<Piece> getMovablePieces(Player player) {
        return player.getMovablePieces(player.getPieces(), dice.getRolledValue());
    }

    /**
     * Mock method.
     * Get the current players movable pieces
     *
     * @param player      gets the movable pieces of this player
     * @param rolledValue the rolled value
     * @return all the players movable pieces in a collection
     */
    public ArrayList<Piece> getMovablePieces(Player player, int rolledValue) {
        return player.getMovablePieces(player.getPieces(), rolledValue);
    }

    /**
     * Rolls the dice
     */
    public void rollDice() {
        dice.rollDice();
    }

    public int getDiceValue() {
        return dice.getRolledValue();
    }

    /**
     * Getter for the dice's rolled value. Only used for testing purposes.
     * @return the dice's rolled value
     */
    public int rollAndGetDiceValue() {
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

    /**
     * Moves the piece according to diceValue
     * @param piece the piece to be moved
     * @throws Exception if a piece is to be knocked out but can't be found
     */
    public void move(Piece piece) throws Exception {
        int diceValue = dice.getRolledValue();
        if (pieceWillMovePastGoal(diceValue, piece)) {
            movePieceAndMoveBackwardsAfterMiddle(diceValue, piece);
        }
        else {
            movePieceNormally(diceValue, piece);
        }
        board.knockOutPieceIfOccupied(piece);
    }

    /**
     * Mock method.
     * Moves the piece according to diceValue
     * @param diceValue amount of steps to be taken
     * @param piece the piece to be moved
     * @throws Exception if a piece is to be knocked out but can't be found
     */
    public void move(int diceValue, Piece piece) throws Exception {
        if (pieceWillMovePastGoal(diceValue, piece)) {
            movePieceAndMoveBackwardsAfterMiddle(diceValue, piece);
        }
        else {
            movePieceNormally(diceValue, piece);
        }
        board.knockOutPieceIfOccupied(piece);
    }

    /**
     * Removes the given piece from the game if it is at the goal-index
     * @param piece the piece to be checked
     * @return True if the piece was removed, else False
     */
    public boolean removePieceIfFinished(Piece piece) {
        if (piece.getIndex() == board.getFinishIndex()) {
            removeFinishedPiece(piece);
            return true;
        }
        return false;
    }

    /**
     * Removes the current player from the game if it has no more active pieces
     * @return True if the player was removed, else False
     */
    public boolean removePlayerIfFinished() {
        if (isFinishedPlayer(getCurrentPlayer())) {
            finishedPlayer(getCurrentPlayer());
            return true;
        }
        return false;
    }

    private void movePieceNormally(int diceValue, Piece piece) throws Exception {
        for (int i = 0; i < diceValue; i++) {
            board.movePiece(piece);
        }
    }

    private boolean pieceWillMovePastGoal(int diceValue, Piece piece) {
        return piece.getIndex() + diceValue > board.getFinishIndex();
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

   /*
    //private move(Piece piece) {
        //board.move(piece);
   */

    /**
     * Returns the positions from the board.
     * @return the positions from the board.
     */
    public List<Position> getPositions () {
        return this.board.getPositions();
    }

    /**
     * Returns number of active players.
     * @return number of active players.
     */
    public int getPlayerCount() {
        return activePlayers.size();
    }

    /**
     * Returns the position of the piece given as a parameter.
     * @param piece is the piece from which you want to know the position
     * @return the position of the given piece
     */
    public Position getPosition(Piece piece) {
        return board.getPosition(piece);
    }
}
