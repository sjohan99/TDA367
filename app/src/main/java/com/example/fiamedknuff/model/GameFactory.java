package com.example.fiamedknuff.model;

import java.util.ArrayList;

public class GameFactory {

    static private ArrayList<Player> players;

    static public Game createNewGame(String[] playerNames, Color[] colors) {
        players = new ArrayList<>();
        // TODO: Check valid amount
        for (int i = 0; i < playerNames.length; i++) {
            players.add(new Player(playerNames[i], colors[i]));
        }
        return new Game(players);
    }

}
