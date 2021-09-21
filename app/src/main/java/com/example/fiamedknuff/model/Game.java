package com.example.fiamedknuff.model;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private Board board;
    private List<Player> players;
    private int currentPlayerIndex = 0;

    public Game(int playerCount) {
        // board = new Board;
        players = new ArrayList<Player>();
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
/*
    private Collection<Piece> getMovablePieces(Player currentPlayer, int roll) {
        // return board.getMovablePieces(currentPlayer.getPieces(), roll);
    }

    //private move(Piece piece) {
        //board.move(piece);
   //}

 */

}