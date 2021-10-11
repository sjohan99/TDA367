package com.example.fiamedknuff.model;

import com.example.fiamedknuff.NotImplementedException;

import org.apache.commons.lang3.SerializationUtils;
import org.junit.Before;
import org.junit.Test;
import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
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
            assertThat(game.rollAndGetDiceValue()).isBetween(0, 6);
        }
    }

    @Test
    public void testGetActivePlayers() {
        List<Player> activePlayers = game.getActivePlayers();
        for (int i = 0; i < activePlayers.size(); i++) {
            assertThat(players.get(i)).isEqualTo(activePlayers.get(i));
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
        piece.setIndex(game.getBoard().getFinishIndex() - 1);
        game.move(1, piece);
        game.removePieceIfFinished(piece);
        game.removePlayerIfFinished();
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
        piece.setIndex(game.getBoard().getFinishIndex() - 1);
        game.move(1, piece);
        game.removePieceIfFinished(piece);
        game.removePlayerIfFinished();
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
        piece.setIndex(game.getBoard().getFinishIndex() - 1);
        game.move(1, piece);
        game.removePieceIfFinished(piece);
        game.removePlayerIfFinished();
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
        piece.setIndex(game.getBoard().getFinishIndex() - 5);
        game.move(5, piece);
        game.removePieceIfFinished(piece);
        game.removePlayerIfFinished();

        // Remove all pieces except for one for current player
        for (int i = 0; i < 3; i++) {
            piece = currentPlayer.getPieces().get(0);
            currentPlayer.removePiece(piece);
        }
        // Go out with last piece for current player
        piece = currentPlayer.getPieces().get(0);
        piece.setIndex(game.getBoard().getFinishIndex() - 6);
        game.move(6, piece);
        game.removePieceIfFinished(piece);
        game.removePlayerIfFinished();
        assertThat(activePlayers.size()).isEqualTo(2);
    }

    @Test
    public void testSelectNextPlayer() {
        List<Player> activePlayers = players;
        activePlayers.subList(0, 3).clear();
        assertThat(activePlayers.size()).isEqualTo(1);
        game.selectNextPlayer();
        assertThat(game.getCurrentPlayerIndex()).isEqualTo(-1);
    }


    @Test
    public void testGetMovablePiecesTwoPieces() {
        Player player = players.get(0);
        List<Piece> pieces = player.getPieces();
        pieces.get(0).setIndex(1);
        pieces.get(1).setIndex(11);
        for (int i = 2; i < 6; i++) {
            assertEquals(2, game.getMovablePieces(player, i).size());
        }
    }

    @Test
    public void testGetMovablePiecesAllPieces() {
        Player player = players.get(0);
        List<Piece> pieces = player.getPieces();
        pieces.get(0).setIndex(1);
        pieces.get(1).setIndex(11);
        assertEquals(2, game.getMovablePieces(player, 1).size());
        assertEquals(4, game.getMovablePieces(player, 6).size());
    }

    @Test
    public void testMovePieceAndMoveBackwardsAfterMiddle() throws Exception {
        Board board = game.getBoard();
        HashMap<Piece, Position> piecePositionHashMap = board.getPiecePositionHashMap();
        Piece piece = players.get(0).getPieces().get(0);
        piece.setIndex(board.getFinishIndex() - 1);
        Position pos = new Position(board.getFinishIndex() - 1);
        piecePositionHashMap.put(piece, pos);

        game.move(3, piece);
        assertThat(piece.getIndex()).isEqualTo(board.getFinishIndex() - 2);
        assertThat(piecePositionHashMap.get(piece).getPos()).isEqualTo(board.getFinishIndex() - 2);
    }
}
