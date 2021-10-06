package com.example.fiamedknuff.model;

import com.example.fiamedknuff.NotImplementedException;

import java.util.ArrayList;
import java.util.List;

public class GameFactory {

    static public Game createNewGame(List<String> playerNames, Color[] colors, boolean[] CPUSelection) throws NotImplementedException {
        ArrayList<Player> players = new ArrayList<>();
        for (int i = 0; i < playerNames.size(); i++) {
            if (CPUSelection[i]) {
                players.add(new CPU(playerNames.get(i), colors[i]));
            } else {
                players.add(new Player(playerNames.get(i), colors[i]));
            }
        }
        return new Game(players);
    }

}
