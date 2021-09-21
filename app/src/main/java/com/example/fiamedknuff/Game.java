package com.example.fiamedknuff;

import java.util.ArrayList;
import java.util.Collection;
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
