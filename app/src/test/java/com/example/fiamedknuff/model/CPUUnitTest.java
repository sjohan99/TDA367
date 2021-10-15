package com.example.fiamedknuff.model;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.fiamedknuff.exceptions.NotImplementedException;

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
        CPU = new CPU("2", Color.RED);

        players = new ArrayList<>();
        players.add(new Player("1", Color.BLUE));
        players.add(CPU);
        players.add(new Player("3", Color.YELLOW));
        players.add(new Player("4", Color.GREEN));
        game = new Game(players);

        board = game.getBoard();
        CPU.setBoard(board);
    }

    @Test
    public void testChoosePieceToMoveKnockout() {
        HashMap<Piece, Position> piecePositionHashMap = board.getPiecePositionHashMap();
        Player player1 = players.get(0);

        // Set position for piece to be knockout
        Piece knockoutPiece = player1.getPieces().get(0);
        Position knockoutPos = new Position(10);
        piecePositionHashMap.put(knockoutPiece, knockoutPos);

        // Set position for first CPU-piece
        Piece cpuFirstPiece = CPU.getPieces().get(0);
        cpuFirstPiece.setIndex(8);
        Position cpuFirstPos = new Position(17);
        piecePositionHashMap.put(cpuFirstPiece, cpuFirstPos);

        // Set position for second CPU-piece
        Piece cpuSecondPiece = CPU.getPieces().get(1);
        cpuSecondPiece.setIndex(4);
        Position cpuSecondPos = new Position(13);
        piecePositionHashMap.put(cpuSecondPiece, cpuSecondPos);

        assertThat(CPU.choosePieceToMove(2)).isEqualTo(cpuFirstPiece);
    }

   @Test
   public void testChoosePieceToMoveFinishPiece() {
       HashMap<Piece, Position> piecePositionHashMap = board.getPiecePositionHashMap();

       Player cpu = players.get(1);

       // Set position for a CPU-piece
       Piece cpuFirstPiece = CPU.getPieces().get(0);
       cpuFirstPiece.setIndex(4);
       Position cpuFirstPos = new Position(13);
       piecePositionHashMap.put(cpuFirstPiece, cpuFirstPos);

       // Set position for a second cpu-piece
       Piece cpuSecondPiece = cpu.getPieces().get(1);
       cpuSecondPiece.setIndex(21);
       Position cpuSecondPos= new Position(30);
       piecePositionHashMap.put(cpuSecondPiece, cpuSecondPos);

       // Set position for third CPU-piece
       Piece cpuThirdPiece = CPU.getPieces().get(2);
       cpuThirdPiece.setIndex(board.getFinishIndex() - 2);
       Position cpuToBeFinishPos = new Position(52);
       piecePositionHashMap.put(cpuThirdPiece, cpuToBeFinishPos);

       assertThat(CPU.choosePieceToMove(2)).isEqualTo(cpuThirdPiece);
   }

   @Test
    public void testChoosePieceToMoveOutOfHome() {
       HashMap<Piece, Position> piecePositionHashMap = board.getPiecePositionHashMap();

       Player cpu = players.get(1);

       // Set position for a CPU-piece
       Piece cpuFirstPiece = CPU.getPieces().get(0);
       cpuFirstPiece.setIndex(4);
       Position cpuFirstPos = new Position(13);
       piecePositionHashMap.put(cpuFirstPiece, cpuFirstPos);

       // Set position for a second cpu-piece
       Piece cpuSecondPiece = cpu.getPieces().get(1);
       cpuSecondPiece.setIndex(21);
       Position cpuSecondPos= new Position(30);
       piecePositionHashMap.put(cpuSecondPiece, cpuSecondPos);

       // Set position for third CPU-piece
       Piece cpuThirdPiece = CPU.getPieces().get(2);
       cpuThirdPiece.setIndex(34);
       Position cpuThirdPos = new Position(43);
       piecePositionHashMap.put(cpuThirdPiece, cpuThirdPos);

       // Set position for fourth CPU-piece
       Piece cpuFourthPiece = CPU.getPieces().get(3);

       assertThat(CPU.choosePieceToMove(1)).isEqualTo(cpuFourthPiece);
   }

    @Test
    public void testChooseLeadingPieceToMove() {
        HashMap<Piece, Position> piecePositionHashMap = board.getPiecePositionHashMap();

        Player cpu = players.get(1);

        // Set position for first CPU-piece
        Piece cpuFirstPiece = CPU.getPieces().get(0);
        cpuFirstPiece.setIndex(4);
        Position cpuFirstPos = new Position(13);
        piecePositionHashMap.put(cpuFirstPiece, cpuFirstPos);

        // Set position for second cpu-piece (leading piece)
        Piece cpuSecondPiece = cpu.getPieces().get(1);
        cpuSecondPiece.setIndex(21);
        Position cpuSecondPos= new Position(30);
        piecePositionHashMap.put(cpuSecondPiece, cpuSecondPos);

        // Set position for third CPU-piece
        Piece cpuThirdPiece = CPU.getPieces().get(2);
        cpuThirdPiece.setIndex(43);
        Position cpuThirdPos = new Position(52);
        piecePositionHashMap.put(cpuThirdPiece, cpuThirdPos);

        assertThat(CPU.choosePieceToMove(4)).isEqualTo(cpuSecondPiece);
    }

    @Test
    public void testChoosePieceToMoveInHomePath() {
        Player cpu = players.get(1);

        // Set position for a CPU-piece
        Piece cpuFirstPiece = CPU.getPieces().get(0);
        cpuFirstPiece.setIndex(41);

        // Set position for a second cpu-piece
        Piece cpuSecondPiece = cpu.getPieces().get(1);
        cpuSecondPiece.setIndex(42);

        assertThat(CPU.choosePieceToMove(2)).isEqualTo(cpuFirstPiece);
    }

    @Test
    public void testChoosePieceToMoveNoMovablePieces() {
        Player cpu = players.get(1);
        assertThat(CPU.choosePieceToMove(3)).isEqualTo(null);
    }

}
