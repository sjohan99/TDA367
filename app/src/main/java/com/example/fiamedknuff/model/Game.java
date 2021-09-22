package com.example.fiamedknuff.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Game {

    private Board board;
    private List<Player> activePlayers;
    private int currentPlayerIndex = 0;
    private Dice dice;
    private List<Player> finishedPlayers = new ArrayList<>();

    public Game(List<Player> players) {
        board = new Board(players.size(), getAllPlayerPieces());
        this.activePlayers = players;
        dice = new Dice();
    }
  
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

    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }

    private List<Piece> getPlayerPieces(Player player) {
        return player.getPieces();
    }

    private List<Piece> getAllPlayerPieces() {
        List<Piece> pieces = new ArrayList<>();
        for (Player player : activePlayers) {
            pieces.addAll(getPlayerPieces(player));
        }
        return pieces;
    }

    public Collection<Piece> getMovablePieces(Player player, int rolledValue) {
        //return board.getMovablePieces(player.getPieces(), rolledValue);
        return null;
    }

    public int rollDice() {
        return dice.rollDice();
    }

    private void finishedPlayer(Player player) {
        activePlayers.remove(player);
        finishedPlayers.add(player);
    }

    public void move(Piece piece, int diceValue) {
        // move the piece
        // check for knockout
        // check if finished piece - hide
        // check if player is finished - call finishedPlayer
    }

   /*
    //private move(Piece piece) {
        //board.move(piece);
   */

}
