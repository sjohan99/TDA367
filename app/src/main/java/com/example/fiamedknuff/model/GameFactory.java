package com.example.fiamedknuff.model;

import com.example.fiamedknuff.NotImplementedException;

import java.util.ArrayList;

public class GameFactory {

    static public Game createNewGame(String[] playerNames, Color[] colors) throws NotImplementedException {
        ArrayList<Player> players = new ArrayList<>();
        // TODO: Check valid amount
        for (int i = 0; i < playerNames.length; i++) {
            players.add(new Player(playerNames[i], colors[i]));
        }
        return new Game(players);
    }

}
