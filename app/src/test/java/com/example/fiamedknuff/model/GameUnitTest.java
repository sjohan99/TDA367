package com.example.fiamedknuff.model;

import com.example.fiamedknuff.exceptions.NotFoundException;
import com.example.fiamedknuff.exceptions.NotImplementedException;

import org.apache.commons.lang3.SerializationUtils;
import org.junit.Before;
import org.junit.Test;
import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GameUnitTest {

    Game game;
    int playerCount;
    List<Player> players;
    int firstPos;

    @Before
    public void createGame() throws NotImplementedException {
        playerCount = 4;

        List<String> playerNames = new ArrayList<>();
        for (int i = 1; i <= playerCount; i++) {
            String s = Integer.toString(i);
            playerNames.add(s);
        }

        List<Color> colors = new ArrayList<>();
        colors.add(Color.YELLOW);
        colors.add(Color.RED);
        colors.add(Color.GREEN);
        colors.add(Color.BLUE);

        List<Boolean> selectedCPU = new ArrayList<>();
        for (int i = 0; i < playerCount; i++) {
            selectedCPU.add(false);
        }

        game = GameFactory.createNewGame(playerNames, colors, selectedCPU);
        players = game.getActivePlayers();
        firstPos = game.getBoard().getFirstPositionIndexInLap();
    }

    @Test
    public void testSerializing() {
        game.selectNextPlayer();
        byte[] data = SerializationUtils.serialize(game);
        Game g2 = SerializationUtils.deserialize(data);
        assertThat(g2.getCurrentPlayer().getName()).isEqualTo(game.getCurrentPlayer().getName());
    }

    @Test
    public void testRollDice() {
        for (int i = 0; i < 50; i++) {
            game.rollDice();
            assertThat(game.getDice().getRolledValue()).isBetween(0, 6);
        }
    }

    @Test
    public void testRollAndGetDiceValue() {
        for (int i = 0; i < 50; i++) {
            assertThat(game.rollAndGetDiceValue()).isBetween(0, 6);
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
    public void testSelectNextPlayer() {
        List<Player> activePlayers = players;
        activePlayers.subList(0, 3).clear();
        assertThat(activePlayers.size()).isEqualTo(1);
        game.selectNextPlayer();
        assertThat(game.getCurrentPlayerIndex()).isEqualTo(-1);
    }

    @Test
    public void testGetActivePlayers() {
        List<Player> activePlayers = game.getActivePlayers();
        for (int i = 0; i < activePlayers.size(); i++) {
            assertThat(players.get(i)).isEqualTo(activePlayers.get(i));
        }
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
    public void testRemovePieceIfFinishedFalse() throws NotFoundException {
        Player currentPlayer = players.get(0);
        Piece piece = currentPlayer.getPieces().get(0);
        for (int i = 0; i < game.getBoard().getLapLength(); i++) {
            piece.setIndex(i);
            game.move(piece);
            game.removePieceIfFinished(piece);
            game.removePlayerIfFinished();
        }
        assertThat(currentPlayer.getPieces().size()).isEqualTo(4);
    }

    @Test
    public void testRemoveFinishedPiece() throws NotFoundException {
        Player currentPlayer = players.get(0);
        Piece piece = currentPlayer.getPieces().get(0);
        piece.setIndex(game.getBoard().getFinishIndex() - 1);
        game.move(1, piece);
        game.removePieceIfFinished(piece);
        game.removePlayerIfFinished();
        assertThat(currentPlayer.getPieces().size()).isEqualTo(3);
    }

    @Test
    public void testRemoveSeveralFinishedPieces() throws NotFoundException {
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
    public void testFinishedPlayer() throws NotFoundException {
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
    public void testSeveralFinishedPlayer() throws NotFoundException {
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
    public void testMovePieceAndMoveBackwardsAfterMiddle() throws NotFoundException {
        var hMap = game.getBoard().getPiecePositionHashMap();

        Board board = game.getBoard();
        HashMap<Piece, Position> piecePositionHashMap = board.getPiecePositionHashMap();
        Piece piece = players.get(0).getPieces().get(0);
        piece.setIndex(board.getFinishIndex() - 1);
        Position pos = new Position(board.getFinishIndex() - 1);
        piecePositionHashMap.put(piece, pos);

        game.move(3, piece);
        assertThat(piece.getIndex()).isEqualTo(board.getFinishIndex() - 2);
        assertThat(hMap.get(piece).getPos()).isEqualTo(board.getFinishIndex() - 2);
    }

    @Test
    public void testIsKnockoutFalse() {
        var hMap = game.getBoard().getPiecePositionHashMap();

        Piece piece = players.get(0).getPieces().get(0);
        piece.setIndex(12);
        Position pos = game.getBoard().getPositions().get(firstPos+11);
        hMap.put(piece, pos);

        assertFalse(game.isKnockout(piece));
        assertThat(hMap.get(piece)).isEqualTo(game.getBoard().getPositions().get(firstPos+11));
    }

    @Test
    public void testIsKnockoutTrue() {
        var hMap = game.getBoard().getPiecePositionHashMap();

        // Initialize position and index for piece to knockout another piece
        Piece piece = players.get(0).getPieces().get(0);
        piece.setIndex(12);
        Position pos = game.getBoard().getPositions().get(firstPos+11);
        hMap.put(piece, pos);

        // Initialize position and index for piece to be knocked out
        Piece pieceToBeKnockedOut = players.get(1).getPieces().get(0);
        pieceToBeKnockedOut.setIndex(2);
        Position secondPos = game.getBoard().getPositions().get(firstPos+11);
        hMap.put(pieceToBeKnockedOut, secondPos);

        assertTrue(game.isKnockout(piece));
        assertThat(hMap.get(piece)).isEqualTo(game.getBoard().getPositions().get(firstPos+11));
    }

    @Test
    public void testKnockoutWithPiece() throws NotFoundException {
        var hMap = game.getBoard().getPiecePositionHashMap();

        // Initialize position and index for piece to knockout another piece
        Piece piece = players.get(0).getPieces().get(0);
        piece.setIndex(22);
        Position pos = game.getBoard().getPositions().get(firstPos+21);
        hMap.put(piece, pos);

        // Initialize position and index for piece to be knocked out
        Piece pieceToBeKnockedOut = players.get(1).getPieces().get(0);
        pieceToBeKnockedOut.setIndex(12);
        Position secondPos = game.getBoard().getPositions().get(firstPos+21);
        hMap.put(pieceToBeKnockedOut, secondPos);

        assertThat(game.knockoutWithPiece(piece)).isEqualTo(pieceToBeKnockedOut);
        assertThat(pieceToBeKnockedOut.getIndex()).isEqualTo(0);
        assertThat(hMap.get(piece)).isEqualTo(game.getBoard().getPositions().get(firstPos+21));
    }

    @Test(expected = NotFoundException.class)
    public void testKnockoutWithPieceException() throws NotFoundException {
        var hMap = game.getBoard().getPiecePositionHashMap();
        Piece piece = players.get(0).getPieces().get(0);
        piece.setIndex(22);
        Position pos = game.getBoard().getPositions().get(firstPos+21);
        hMap.put(piece, pos);
        game.getBoard().knockoutWithPiece(piece);
    }

    @Test
    public void testGetPlayerCount(){
        assertThat(game.getPlayerCount()).isEqualTo(players.size());
    }

    @Test
    public void testGetCurrentPlayerName() {
        for (int i = 1; i <= players.size(); i++) {
            assertThat(game.getCurrentPlayerName()).isEqualTo(Integer.toString(i));
            game.selectNextPlayer();
        }
    }

    @Test
    public void testGetPositions() {
        assertThat(game.getPositions().size()).isEqualTo(57 + 16);
    }

    @Test
    public void testGetPosition() {
        var hMap = game.getBoard().getPiecePositionHashMap();
        Piece piece = players.get(0).getPieces().get(0);
        piece.setIndex(22);
        Position pos = game.getBoard().getPositions().get(firstPos+21);
        hMap.put(piece, pos);
        assertThat(game.getPosition(piece)).isEqualTo(hMap.get(piece));
    }

    @Test
    public void testSetDiceIsUsed() {
        game.getDice().setIsUsed(false);
        game.setDiceIsUsed();
        assertTrue(game.getDice().getIsUsed());
    }

}
