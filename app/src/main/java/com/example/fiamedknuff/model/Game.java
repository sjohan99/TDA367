package com.example.fiamedknuff.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Game {

    private Board board;
    private List<Player> players;
    private int currentPlayerIndex = 0;
    private Dice dice;

    public Game(int playerCount) {
        // board = new Board;
        players = new ArrayList<Player>();
        dice = new Dice();
    }

    public Game(int playerCount, int maxValueDice) {
        // board = new Board;
        players = new ArrayList<Player>();
        dice = new Dice(maxValueDice);
    }

    public Player getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }

    private void selectNextPlayer() {
        currentPlayerIndex++;
        if (currentPlayerIndex >= players.size()) {
            currentPlayerIndex = 0;
        }
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

    public void move(Piece piece, int diceValue) {
        // move the piece
        // check for knockout
        // check for if finished
    }

 /*
    //private move(Piece piece) {
        //board.move(piece);
   //}

 */

}
