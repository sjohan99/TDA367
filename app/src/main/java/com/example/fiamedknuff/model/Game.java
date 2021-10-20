package com.example.fiamedknuff.model;
import com.example.fiamedknuff.exceptions.NotFoundException;
import com.example.fiamedknuff.exceptions.NotImplementedException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Class responsible for tying together the different components of the game and using them to
 * simulate the game. Implements Serializable to handle data.
 *
 * Created by
 * @author Amanda Cyrén, Emma Stålberg, Hanna Boquist, Johan Selin, Philip Winsnes
 */
public class Game implements Serializable {

    private final Board board; // The board in the current game round
    private List<Player> activePlayers; // List of active players, an active player is a player who has one or several pieces left
    private int currentPlayerIndex = 0; // A variable to hold the index of the current player
    private final Dice dice; // The dice in the current game round
    private List<Player> finishedPlayers = new ArrayList<>(); // List of finished players, a finished player is a player who has no pieces left

    /**
     * Sole constructor for the class. Creates a game with an incoming list of players. All players
     * are added to the list of activePlayers from the start. A new Board corresponding to the
     * player count is initialized and a new dice is created.
     *
     * @param players is a list of the players who will play the game.
     * @throws NotImplementedException if an unsupported amount of players is given.
     */
    public Game(List<Player> players) throws NotImplementedException {
        activePlayers = players;
        board = new Board(players.size(), getAllPlayerPieces());
        dice = new Dice();
    }

    /**
     * Returns the board.
     *
     * @return the board.
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Returns the current player.
     *
     * @return the current player.
     */
    public Player getCurrentPlayer() {
        return activePlayers.get(currentPlayerIndex);
    }

    /**
     * Returns the current player's name.
     *
     * @return the current player's name
     */
    public String getCurrentPlayerName() {
        return getCurrentPlayer().getName();
    }

    /**
     * Returns the dice.
     *
     * @return the dice.
     */
    public Dice getDice() {
        return dice;
    }

    /**
     * Checks if there are more than one active player left in the game and if so, increments
     * the current player index to select next player.
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
     * Returns the list of active players.
     *
     * @return a list of all active players.
     */
    public List<Player> getActivePlayers() {
        return activePlayers;
    }

    /**
     * Returns the current players index. For testing purposes only.
     *
     * @return the current players index.
     */
    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }

    /**
     * Returns the current players pieces.
     *
     * @param player specifies the current player.
     * @return the current players pieces.
     */
    private List<Piece> getPlayerPieces(Player player) {
        return player.getPieces();
    }

    /**
     * Returns all the players pieces.
     *
     * @return all the players pieces in a list.
     */
    public List<Piece> getAllPlayerPieces() {
        List<Piece> pieces = new ArrayList<>();
        for (Player player : activePlayers) {
            pieces.addAll(getPlayerPieces(player));
        }
        return pieces;
    }

    /**
     * Returns the current players movable pieces.
     *
     * @param player gets the movable pieces of this player.
     * @return all the players movable pieces in a collection.
     */
    public ArrayList<Piece> getMovablePieces(Player player) {
        return player.getMovablePieces(player.getPieces(), dice.getRolledValue());
    }

    /**
     * Mock method, only used for testing purposes.
     * Returns the current players movable pieces.
     *
     * @param player      gets the movable pieces of this player.
     * @param rolledValue the rolled value.
     * @return all the players movable pieces in a collection.
     */
    public ArrayList<Piece> getMovablePieces(Player player, int rolledValue) {
        return player.getMovablePieces(player.getPieces(), rolledValue);
    }

    /**
     * Rolls the dice.
     */
    public void rollDice() {
        dice.rollDice();
    }

    /**
     * Rolls the dice and returns the rolled value. Only used for testing purposes.
     *
     * @return the dice's rolled value.
     */
    public int rollAndGetDiceValue() {
        return dice.rollDice();
    }

    private void finishedPlayer(Player player) {
        activePlayers.remove(player);
        finishedPlayers.add(player);
    }

    /**
     * Removes the given piece from the piece-position hashmap and the current player's piece list,
     * effectively removing it from the game.
     *
     * @param piece the piece to be removed.
     */
    private void removeFinishedPiece(Piece piece) {
        board.removePieceFromBoard(piece);
        getCurrentPlayer().removePiece(piece);
    }

    private boolean isFinishedPlayer(Player player) {
        return player.getPieces().size() == 0;
    }

    /**
     * Moves the piece according to diceValue.
     *
     * @param piece the piece to be moved.
     * @throws Exception if a piece is to be knocked out but can't be found.
     * @return a list of positions the piece has passed including where it ends.
     */
    // TODO: 2021-10-14 Separate behavior into calculating path and moving??
    public List<Position> move(Piece piece) throws NotFoundException {
        List<Position> positionPath;
        int diceValue = dice.getRolledValue();
        if (pieceWillMovePastGoal(diceValue, piece)) {
            positionPath = movePieceAndMoveBackwardsAfterMiddle(diceValue, piece);
        }
        else {
            positionPath = movePieceNormally(diceValue, piece);
        }
        return positionPath;
    }

    /**
     * Mock method, only used for testing purposes.
     * Moves the piece according to diceValue.
     *
     * @param diceValue amount of steps to be taken.
     * @param piece the piece to be moved.
     * @throws Exception if a piece is to be knocked out but can't be found.
     * @return returns a list of positions the piece has passed including where it ends.
     */
    // TODO: 2021-10-14 Separate behavior into calculating path and moving??
    public List<Position> move(int diceValue, Piece piece) throws NotFoundException {
        List<Position> positionPath;
        if (pieceWillMovePastGoal(diceValue, piece)) {
            positionPath = movePieceAndMoveBackwardsAfterMiddle(diceValue, piece);
        }
        else {
            positionPath = movePieceNormally(diceValue, piece);
        }
        board.knockOutPieceIfOccupied(piece);
        return positionPath;
    }

    /**
     * Checks if the position of a piece is also occupied by another piece. If occupied,
     * the method returns true. Otherwise it returns false.
     *
     * @param piece the piece you want to check if it shares a position with another.
     * @return true if a piece should be knocked out, and false otherwise.
     */
    public boolean isKnockout(Piece piece) {
        return board.isKnockout(piece);
    }

    /**
     * Knocks out the piece that is standing on the same position as the piece which
     * is sent in as a parameter.
     *
     * @param piece is the piece that is knocking out another piece.
     * @return the piece that is knocked out.
     * @throws NotFoundException if the method is called incorrectly.
     */
    public Piece knockoutWithPiece(Piece piece) throws NotFoundException {
        return board.knockoutWithPiece(piece);
    }

    /**
     * Removes the given piece from the game if it is at the goal-index.
     *
     * @param piece the piece to be checked.
     * @return true if the piece was removed, else false.
     */
    public boolean removePieceIfFinished(Piece piece) {
        if (piece.getIndex() == board.getFinishIndex()) {
            removeFinishedPiece(piece);
            return true;
        }
        return false;
    }

    /**
     * Removes the current player from the game if it has no more active pieces.
     *
     * @return true if the player was removed, else false.
     */
    public boolean removePlayerIfFinished() {
        if (isFinishedPlayer(getCurrentPlayer())) {
            finishedPlayer(getCurrentPlayer());
            return true;
        }
        return false;
    }

    private List<Position> movePieceNormally(int diceValue, Piece piece) {
        List<Position> positionPath = new ArrayList<>();
        for (int i = 0; i < diceValue; i++) {
            Position p = board.movePiece(piece);
            positionPath.add(p);
        }
        return positionPath;
    }

    private boolean pieceWillMovePastGoal(int diceValue, Piece piece) {
        return piece.getIndex() + diceValue > board.getFinishIndex();
    }

    private List<Position> movePieceAndMoveBackwardsAfterMiddle(int diceValue, Piece piece) {
        List<Position> positionPath = new ArrayList<>();
        Position p;
        int forwardSteps = board.getFinishIndex() - piece.getIndex();
        int backwardSteps = diceValue - forwardSteps;
        for (int i = forwardSteps; i > 0; i--) {
            p = board.movePiece(piece);
            positionPath.add(p);
        }
        for (int i = backwardSteps; i > 0; i--) {
            p = board.movePieceBackwards(piece);
            positionPath.add(p);
        }
        return positionPath;
    }

    /**
     * Returns the positions from the board.
     *
     * @return the positions from the board.
     */
    public List<Position> getPositions () {
        return this.board.getPositions();
    }

    /**
     * Returns number of active players.
     *
     * @return number of active players.
     */
    public int getPlayerCount() {
        return activePlayers.size();
    }

    /**
     * Returns the position of the piece given as a parameter.
     *
     * @param piece is the piece from which you want to know the position.
     * @return the position of the given piece.
     */
    public Position getPosition(Piece piece) {
        return board.getPosition(piece);
    }

    /**
     * Sets the dice to used.
     */
    public void setDiceIsUsed() {
        dice.setIsUsed(true);
    }

    /**
     * Returns whether the dice has been used
     * @return whether the dice has been used
     */
    public boolean getDiceIsUsed() {
        return dice.getIsUsed();
    }

    /**
     * Returns the last rolled number of the dice
     * @return the last rolled number of the dice
     */
    public int getDiceValue() {
        return dice.getRolledValue();
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
}
