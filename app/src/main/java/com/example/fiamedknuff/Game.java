package com.example.fiamedknuff;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Game {

    private Board board;
    private Dice dice;
    private List<Player> players;
    private int currentPlayerIndex = 0;

    public Game game(int playerCount) {
        board = new Board;
        dice = new Dice;
        players = new ArrayList<Player>();
    }


            // For a new round…
            // roll dice in the dice class. Returns roll.
            // Get player pieces for current player
            // Wait for player input
            // Move selected player piece
            // ...
            // Select new player




    private Player getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }

    private void selectNextPlayer() {
        currentPlayerIndex++;
    }

    private int rollDice() {
        return dice.roll();
    }

    private Collection<Piece> getMovablePieces(Player currentPlayer, int roll) {
        return board.getMovablePieces(currentPlayer.getPieces(), roll);
    }

    private move(Piece piece) {
        board.move(piece);
    }

}