package com.example.fiamedknuff.model;


import com.example.fiamedknuff.NotImplementedException;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GameplayUnitTest {

    static Game game;
    Player currentPlayer;
    List<Piece> currentPlayerPieces;

    @BeforeClass
    public static void setup() throws NotImplementedException {
        String[] names = {
                "Player one",
                "Player two",
                "Player three",
                "Player four"};

        Color[] playerColors = {
                Color.YELLOW,
                Color.RED,
                Color.GREEN,
                Color.BLUE};

        game = GameFactory.createNewGame(names, playerColors);
    }

    @Test
    public void moveFirstPieceWithDiceRoll1() throws Exception {

        // Test that all pieces are movable when home and then move one piece and check that it moved
        int diceRoll = 1;
        var movablePieces = getPlayersMovablePieces(diceRoll);
        assertThat(movablePieces.size()).isEqualTo(getPlayersPieces().size());
        assertThat(movablePieces.get(0).getIndex()).isEqualTo(0);

        game.move(diceRoll, movablePieces.get(0));
        assertThat(movablePieces.get(0).getIndex()).isEqualTo(1);
        var ppHashMap = game.getBoard().getPiecePositionHashMap();
        assertThat(ppHashMap.get(movablePieces.get(0)).getPos()).isEqualTo(game.getBoard().getPositions().get(15+1).getPos());


        // Test if there's only one movable piece when 3 are home and one is at the first position
        diceRoll = 1;
        movablePieces = getPlayersMovablePieces(diceRoll);
        assertThat(movablePieces.size()).isEqualTo(1);


        // Test that a player can move a piece out of home with a 6 and that the piece gets index
        // 6 after being moved
        game.selectNextPlayer();
        diceRoll = 6;
        movablePieces = getPlayersMovablePieces(diceRoll);
        assertThat(movablePieces.size()).isEqualTo(getPlayersPieces().size());
        game.move(diceRoll, movablePieces.get(0));
        assertThat(movablePieces.get(0).getIndex()).isEqualTo(6);
        assertThat(ppHashMap.get(movablePieces.get(0)).getPos()).isEqualTo(game.getBoard().getPositions().get(10+15+6).getPos());

        // Test that only the already moved piece is movable, since the pieces at home will
        // otherwise end up at the same position
        movablePieces = getPlayersMovablePieces(diceRoll);
        assertThat(movablePieces.size()).isEqualTo(1);


        //
        game.selectNextPlayer();
        diceRoll = 6;
        movablePieces = getPlayersMovablePieces(diceRoll);
        game.move(diceRoll, movablePieces.get(1));


        // Test if piece laps correctly and knocks out player one's piece at position 0.
        game.selectNextPlayer();
        movablePieces = getPlayersMovablePieces(diceRoll);
        game.move(diceRoll, movablePieces.get(0));
        game.move(5, movablePieces.get(0));
        Piece currentPiece = movablePieces.get(0);
        assertThat(game.getBoard().getPiecePositionHashMap().get(currentPiece).getPos()).isEqualTo(0);
        game.selectNextPlayer();
        assertThat(getPlayersPieces().get(0).getIndex()).isEqualTo(0);
        Piece pieceThatShouldBeKnockedOut = getPlayersPieces().get(0);
        assertThat(game.getBoard().getPiecePositionHashMap().get(pieceThatShouldBeKnockedOut).getPos()).isEqualTo(pieceThatShouldBeKnockedOut.getHomeNumber());


        // TODO: 2021-10-04 Add asserts below 
        game.selectNextPlayer();
        game.selectNextPlayer();
        movablePieces = getPlayersMovablePieces(6);
        game.move(6, movablePieces.get(0));
        game.move(6, movablePieces.get(0));
        game.move(6, movablePieces.get(0));
        game.move(6, movablePieces.get(0));
        game.move(6, movablePieces.get(0));
        game.move(6, movablePieces.get(0));


        game.selectNextPlayer();
        movablePieces = getPlayersMovablePieces(6);
        game.move(6, movablePieces.get(0));
        game.move(6, movablePieces.get(0));
        game.move(6, movablePieces.get(0));
        game.move(6, movablePieces.get(0));
        game.move(6, movablePieces.get(0));
        printAllPieceLocations();
        game.move(6, movablePieces.get(0));
        game.move(1, movablePieces.get(0));
        game.move(6, movablePieces.get(0));

        printAllPieceLocations();

    }


    // Helper methods below
    private ArrayList<Piece> getPlayersMovablePieces(int roll) {
        return game.getMovablePieces(game.getCurrentPlayer(), roll);
    }

    private List<Piece> getPlayersPieces() {
        return game.getCurrentPlayer().getPieces();
    }

    private void printAllPieceLocations() {
        String p = "";
        int x = 0;
        for (Player player : game.getActivePlayers()) {
            p = game.getActivePlayers().get(x).getName();
            x++;
            for (Piece piece : player.getPieces()) {
                System.out.println(p + ": " + "Index: " + piece.getIndex() + ", Position: " + game.getBoard().getPositionOutsideHomeOf(piece).getPos() + ", Home: " + piece.getHomeNumber());
            }
        }
    }


    // TODO: 2021-09-29 Remove everything below or find how to not test everything in one method.
    // Current problem is that the data is not persistent, inconsistent and seemingly not called
    // in deterministic order. Hard to test a series of events with those problems.

    public void initTestAttributes() {
        currentPlayer = game.getCurrentPlayer();
        currentPlayerPieces = game.getCurrentPlayer().getPieces();
    }

    public void testIfOnlyOneMovablePieceWithRoll1() {
        var diceRoll = 1;
        var movablePieces = game.getMovablePieces(currentPlayer, diceRoll);
        assertThat(movablePieces.size()).isEqualTo(1);
    }

    public void moveSecondPieceWithDiceRoll6() throws Exception {
        game.selectNextPlayer();
        int diceRoll = 6;
        var movablePieces = game.getMovablePieces(currentPlayer, diceRoll);
        assertThat(movablePieces.size()).isEqualTo(currentPlayerPieces.size());
        game.move(diceRoll, movablePieces.get(0));
        assertThat(movablePieces.get(0).getIndex()).isEqualTo(6);
    }

    public void t2() {
        System.out.println(currentPlayer.getName());
    }

}
