package com.example.fiamedknuff.model;

import com.example.fiamedknuff.NotImplementedException;

import org.apache.commons.lang3.SerializationUtils;
import org.junit.Before;
import org.junit.Test;
import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;

public class GameUnitTest {

    Game game;
    ArrayList<Player> players;

    @Before
    public void createGame() throws NotImplementedException {
        players = new ArrayList<>();
        players.add(new Player("1", Color.BLUE));
        players.add(new Player("2", Color.RED));
        players.add(new Player("3", Color.YELLOW));
        players.add(new Player("4", Color.GREEN));
        game = new Game(players);
    }

    @Test
    public void testDiceRoll() {
        for (int i = 0; i < 50; i++) {
            assertThat(game.rollDice()).isBetween(0, 6);
        }
    }

    @Test
    public void testCurrentPlayer() {
        for (int i = 0; i < 25; i++) {
            assertThat(game.getCurrentPlayer()).isEqualTo(players.get(i % players.size()));
            game.selectNextPlayer();
        }
        for (int i = 0; i < 25; i++) {
            assertThat(game.getCurrentPlayer()).isEqualTo(players.get(game.getCurrentPlayerIndex()));
            game.selectNextPlayer();
        }
    }

    @Test
    public void testSerializing() {
        game.selectNextPlayer();
        byte[] data = SerializationUtils.serialize(game);
        Game g2 = SerializationUtils.deserialize(data);
        assertThat(g2.getCurrentPlayer().getName()).isEqualTo(game.getCurrentPlayer().getName());
    }

    @Test
    public void testRemoveFinishedPiece() throws Exception {
        Player currentPlayer = players.get(0);
        Piece piece = currentPlayer.getPieces().get(0);
        piece.setIndex(44);
        game.move(1, piece);
        assertThat(currentPlayer.getPieces().size()).isEqualTo(3);
    }

}
