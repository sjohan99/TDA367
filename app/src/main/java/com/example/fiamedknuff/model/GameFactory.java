package com.example.fiamedknuff.model;

import com.example.fiamedknuff.NotImplementedException;

import java.util.ArrayList;

public class GameFactory {

    static public Game createNewGame(String[] playerNames, Color[] colors, boolean[] CPUSelection) throws NotImplementedException {
        ArrayList<Player> players = new ArrayList<>();
        for (int i = 0; i < playerNames.length; i++) {
            if (CPUSelection[i]) {
                players.add(new CPU(playerNames[i], colors[i]));
            } else {
                players.add(new Player(playerNames[i], colors[i]));
            }
        }
        return new Game(players);
    }

}
