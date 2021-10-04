package com.example.fiamedknuff.model;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.fiamedknuff.NotImplementedException;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class CPUUnitTest {

    Game game;
    Board board;
    CPU CPU;
    ArrayList<Player> players;

    @Before
    public void createGame() throws NotImplementedException {

        players = new ArrayList<>();
        players.add(new Player("1", Color.BLUE));
        players.add(new Player("2", Color.RED));
        players.add(new Player("3", Color.YELLOW));
        players.add(new Player("4", Color.GREEN));
        game = new Game(players);

        //board = game.board;
        CPU = new CPU("2", Color.RED, board);
    }

    @Test
    public void testMakeMoveKnockout() {
        HashMap<Piece, Position> piecePositionHashMap = board.getPiecePositionHashMap();
        Player player1 = players.get(0);
        Player cpu = players.get(1);

        // Set position for piece to be knockout
        Piece knockoutPiece = player1.getPieces().get(0);
        Position knockoutPos = new Position(10);
        piecePositionHashMap.put(knockoutPiece, knockoutPos);

        // Set position for CPU
        Piece cpuPiece = cpu.getPieces().get(0);
        Position cpuPos = new Position(8);
        piecePositionHashMap.put(cpuPiece, cpuPos);

        CPU.makeMove(2);
        assertThat(knockoutPiece.getIndex()).isEqualTo(0);

    }

}
