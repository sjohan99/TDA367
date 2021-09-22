package com.example.fiamedknuff.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Game {

    private Board board;
    private List<Player> activePlayers;
    private int currentPlayerIndex = 0;
    private Dice dice;
    private List<Player> finishedPlayers = new ArrayList<Player>();

    public Game(List<Player> players) {
        this.activePlayers = players;
        board = new Board();
        activePlayers = new ArrayList<Player>();
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

    private Collection<Piece> getPlayerPieces(Player player) {
        return player.getPieces();
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
