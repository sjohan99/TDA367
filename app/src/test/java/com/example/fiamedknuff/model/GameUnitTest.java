package com.example.fiamedknuff.model;

import com.example.fiamedknuff.NotImplementedException;

import org.apache.commons.lang3.SerializationUtils;
import org.junit.Before;
import org.junit.Test;
import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

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

    @Test
    public void testRemoveSeveralFinishedPieces() throws Exception {
        Player currentPlayer = players.get(0);

        // Remove all pieces except for one
        for (int i = 0; i < 3; i++) {
            Piece piece = currentPlayer.getPieces().get(0);
            currentPlayer.removePiece(piece);
        }
        Piece piece = currentPlayer.getPieces().get(0);
        piece.setIndex(44);
        game.move(1, piece);
        assertThat(currentPlayer.getPieces().size()).isEqualTo(0);
    }

    @Test
    public void testFinishedPlayer() throws Exception {
        List<Player> activePlayers = players;
        Player currentPlayer = players.get(0);
        Piece piece;

        // Remove all pieces except for one
        for (int i = 0; i < 3; i++) {
            piece = currentPlayer.getPieces().get(0);
            currentPlayer.removePiece(piece);
        }
        // Go out with last piece for current player
        piece = currentPlayer.getPieces().get(0);
        piece.setIndex(44);
        game.move(1, piece);
        assertThat(activePlayers.size()).isEqualTo(3);
    }

    @Test
    public void testSeveralFinishedPlayer() throws Exception {
        List<Player> activePlayers = players;
        Player finishedPlayer = players.get(0);
        Player currentPlayer = players.get(1);
        Piece piece;

        // Remove all pieces except for one for finished player
        for (int i = 0; i < 3; i++) {
            piece = finishedPlayer.getPieces().get(0);
            finishedPlayer.removePiece(piece);
        }
        // Go out with last piece for finished player
        piece = finishedPlayer.getPieces().get(0);
        piece.setIndex(40);
        game.move(5, piece);

        // Remove all pieces except for one for current player
        for (int i = 0; i < 3; i++) {
            piece = currentPlayer.getPieces().get(0);
            currentPlayer.removePiece(piece);
        }
        // Go out with last piece for current player
        piece = currentPlayer.getPieces().get(0);
        piece.setIndex(39);
        game.move(6, piece);
        assertThat(activePlayers.size()).isEqualTo(2);
    }


}
