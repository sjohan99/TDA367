package com.example.fiamedknuff.model;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.fiamedknuff.exceptions.NotImplementedException;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CPUUnitTest {

    Game game;
    Board board;
    CPU CPU;
    int playerCount;
    List<Player> players;

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
        selectedCPU.add(false);
        selectedCPU.add(true);
        selectedCPU.add(false);
        selectedCPU.add(false);

        game = GameFactory.createNewGame(playerNames, colors, selectedCPU);
        players = game.getActivePlayers();

        board = game.getBoard();
        CPU = (CPU) players.get(1);
        CPU.setBoard(board);
    }

    @Test
    public void testChoosePieceToMovePieceInHomeKnockout() {
        HashMap<Piece, Position> piecePositionHashMap = board.getPiecePositionHashMap();
        Player player1 = players.get(0);

        // Set position for piece to be knockout
        Piece knockoutPiece = player1.getPieces().get(0);
        knockoutPiece.setIndex(16);
        Position knockoutPos = board.getPositions().get(16+15);
        piecePositionHashMap.put(knockoutPiece, knockoutPos);

        // Set position for anther piece
        Piece secondPiece = player1.getPieces().get(1);
        secondPiece.setIndex(23);
        Position secondPos = board.getPositions().get(16+22);
        piecePositionHashMap.put(secondPiece, secondPos);

        // Set position for first CPU-piece
        Piece cpuFirstPiece = CPU.getPieces().get(0);
        cpuFirstPiece.setIndex(7);
        Position cpuFirstPos = board.getPositions().get(16+16);
        piecePositionHashMap.put(cpuFirstPiece, cpuFirstPos);

        // Set position for first CPU-piece
        Piece cpuPieceToKnockout = CPU.getPieces().get(1);

        assertThat(CPU.choosePieceToMove(6)).isEqualTo(cpuPieceToKnockout);
    }

    @Test
    public void testChoosePieceToMoveKnockout() {
        HashMap<Piece, Position> piecePositionHashMap = board.getPiecePositionHashMap();
        Player player1 = players.get(0);

        // Set position for piece to be knockout
        Piece knockoutPiece = player1.getPieces().get(0);
        knockoutPiece.setIndex(16);
        Position knockoutPos = board.getPositions().get(16+15);
        piecePositionHashMap.put(knockoutPiece, knockoutPos);

        // Set position for first CPU-piece
        Piece cpuFirstPiece = CPU.getPieces().get(0);
        cpuFirstPiece.setIndex(8);
        Position cpuFirstPos = board.getPositions().get(16+17);
        piecePositionHashMap.put(cpuFirstPiece, cpuFirstPos);

        // Set position for second CPU-piece
        Piece cpuSecondPiece = CPU.getPieces().get(1);
        cpuSecondPiece.setIndex(4);
        Position cpuSecondPos = board.getPositions().get(16+13);
        piecePositionHashMap.put(cpuSecondPiece, cpuSecondPos);

        assertThat(CPU.choosePieceToMove(2)).isEqualTo(cpuSecondPiece);
    }

   @Test
   public void testChoosePieceToMoveFinishPiece() {
       HashMap<Piece, Position> piecePositionHashMap = board.getPiecePositionHashMap();

       // Set position for a CPU-piece
       Piece cpuFirstPiece = CPU.getPieces().get(0);
       cpuFirstPiece.setIndex(4);
       Position cpuFirstPos = board.getPositions().get(16+13);
       piecePositionHashMap.put(cpuFirstPiece, cpuFirstPos);

       // Set position for a second cpu-piece
       Piece cpuSecondPiece = CPU.getPieces().get(1);
       cpuSecondPiece.setIndex(21);
       Position cpuSecondPos= board.getPositions().get(16+30);
       piecePositionHashMap.put(cpuSecondPiece, cpuSecondPos);

       // Set position for third CPU-piece
       Piece cpuThirdPiece = CPU.getPieces().get(2);
       cpuThirdPiece.setIndex(board.getFinishIndex() - 2);
       Position cpuToBeFinishPos = board.getPositions().get(16+52);
       piecePositionHashMap.put(cpuThirdPiece, cpuToBeFinishPos);

       assertThat(CPU.choosePieceToMove(2)).isEqualTo(cpuThirdPiece);
   }

   @Test
    public void testChoosePieceToMoveOutOfHome() {
       HashMap<Piece, Position> piecePositionHashMap = board.getPiecePositionHashMap();
       // Set position for a CPU-piece
       Piece cpuFirstPiece = CPU.getPieces().get(0);
       cpuFirstPiece.setIndex(4);
       Position cpuFirstPos = board.getPositions().get(16+13);
       piecePositionHashMap.put(cpuFirstPiece, cpuFirstPos);

       // Set position for a second cpu-piece
       Piece cpuSecondPiece = CPU.getPieces().get(1);
       cpuSecondPiece.setIndex(21);
       Position cpuSecondPos= board.getPositions().get(16+30);
       piecePositionHashMap.put(cpuSecondPiece, cpuSecondPos);

       // Set position for third CPU-piece
       Piece cpuThirdPiece = CPU.getPieces().get(2);
       cpuThirdPiece.setIndex(34);
       Position cpuThirdPos = board.getPositions().get(16+43);
       piecePositionHashMap.put(cpuThirdPiece, cpuThirdPos);

       // Set position for fourth CPU-piece
       Piece cpuFourthPiece = CPU.getPieces().get(3);

       assertThat(CPU.choosePieceToMove(1)).isEqualTo(cpuFourthPiece);
   }

    @Test
    public void testChooseLeadingPieceToMove() {
        HashMap<Piece, Position> piecePositionHashMap = board.getPiecePositionHashMap();
        // Set position for first CPU-piece
        Piece cpuFirstPiece = CPU.getPieces().get(0);
        cpuFirstPiece.setIndex(4);
        Position cpuFirstPos = board.getPositions().get(16+13);
        piecePositionHashMap.put(cpuFirstPiece, cpuFirstPos);

        // Set position for second cpu-piece (leading piece)
        Piece cpuSecondPiece = CPU.getPieces().get(1);
        cpuSecondPiece.setIndex(21);
        Position cpuSecondPos= board.getPositions().get(16+30);
        piecePositionHashMap.put(cpuSecondPiece, cpuSecondPos);

        // Set position for third CPU-piece, (in middle path)
        Piece cpuThirdPiece = CPU.getPieces().get(2);
        cpuThirdPiece.setIndex(43);
        Position cpuThirdPos = board.getPositions().get(16+52);
        piecePositionHashMap.put(cpuThirdPiece, cpuThirdPos);

        assertThat(CPU.choosePieceToMove(4)).isEqualTo(cpuSecondPiece);
    }

    @Test
    public void testChoosePieceToMoveInMiddlePath() {
        // Set position for a CPU-piece
        Piece cpuFirstPiece = CPU.getPieces().get(0);
        cpuFirstPiece.setIndex(41);

        // Set position for a second cpu-piece
        Piece cpuSecondPiece = CPU.getPieces().get(1);
        cpuSecondPiece.setIndex(42);

        assertThat(CPU.choosePieceToMove(2)).isEqualTo(cpuFirstPiece);
    }

    @Test
    public void testChoosePieceToMoveNoMovablePieces() {
        assertThat(CPU.choosePieceToMove(3)).isEqualTo(null);
    }
}
