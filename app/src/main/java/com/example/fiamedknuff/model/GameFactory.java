package com.example.fiamedknuff.model;

import com.example.fiamedknuff.exceptions.NotImplementedException;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for instantiating new games of different kinds.
 */
public class GameFactory {

    /**
     * Creates a game according to given arguments. The arrays need to correspond 1 to 1.
     *
     * Example: To create a player with name = p1, color = YELLOW and who is not a CPU then the
     * first value of each array has to be (in order)
     * playerNames[0] = "p1"
     * colors[0] = Color.BLACK
     * CPUSelection[0] = false
     *
     * @param playerNames The name of each player in corresponding order.
     * @param colors the color of the player's pieces in corresponding order.
     * @param CPUSelection whether the player is a CPU or not in corresponding order.
     * @return the created game in accordance with the parameters.
     * @throws NotImplementedException if an unsupported amount of players is supplied.
     */
    static public Game createNewGame(List<String> playerNames, List<Color> colors, List<Boolean> CPUSelection) throws NotImplementedException {
        ArrayList<Player> players = new ArrayList<>();
        for (int i = 0; i < playerNames.size(); i++) {
            if (CPUSelection.get(i)) {
                players.add(new CPU(playerNames.get(i), colors.get(i)));
            } else {
                players.add(new Player(playerNames.get(i), colors.get(i)));
            }
        }
        return new Game(players);
    }

}
