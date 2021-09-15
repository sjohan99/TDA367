package com.example.fiamedknuff;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Game {

    private Dice dice;
    private List<Player> players;
    private int currentPlayerIndex = 0;

    public Game game(int playerCount) {
        dice = new Dice;
        players = new ArrayList<Player>();
    }

    public static void main(String[] args) {
        while (true) {
            // For a new round…
            // roll dice in the dice class. Returns roll.

            // Get player pieces for current player
            Collection<Pieces> playerPieces = getCurrentPlayer().getPlayerPieces();
            getMovablePieces(Collection pieces, int roll);
            // ...
            // Select new player
        }
    }



    private Player getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }

    private int rollDice() {
        dice.roll();
    }


}
