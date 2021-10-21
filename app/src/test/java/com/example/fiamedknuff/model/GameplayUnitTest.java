package com.example.fiamedknuff.model;

import com.example.fiamedknuff.exceptions.NotFoundException;
import com.example.fiamedknuff.exceptions.NotImplementedException;

import org.junit.Before;
import org.junit.Test;
import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;


public class GameplayUnitTest {

    Game game;
    Player currentPlayer;
    int pc = 4;
    List<Piece> currentPlayerPieces;

    @Before
    public void setup() throws NotImplementedException {
        ArrayList<String> names = new ArrayList<>();
        names.add("Player one");
        names.add("Player two");
        names.add("Player three");
        names.add("Player four");

        ArrayList<Color> playerColors = new ArrayList<>();
        playerColors.add(Color.YELLOW);
        playerColors.add(Color.RED);
        playerColors.add(Color.GREEN);
        playerColors.add(Color.BLUE);

        ArrayList<Boolean> isCPU = new ArrayList<>();
        isCPU.add(false);
        isCPU.add(false);
        isCPU.add(false);
        isCPU.add(false);

        game = GameFactory.createNewGame(names, playerColors, isCPU);
    }

    @Test
    public void testPlayerOneWalks() throws NotFoundException {
        int roll = 6;
        int moved = 0;
        var movablePieces = getPlayersMovablePieces(roll);
        var currentPiece = movablePieces.get(0);
        var pphm = game.getBoard().getPiecePositionHashMap();
        var positions = game.getBoard().getPositions();

        game.move(roll, currentPiece);
        moved += roll;
        assertThat(currentPiece.getIndex()).isEqualTo(moved);
        assertThat(pphm.get(currentPiece)).isEqualTo(positions.get((4*pc) + moved - 1));

        game.move(roll, currentPiece);
        moved += roll;
        assertThat(currentPiece.getIndex()).isEqualTo(moved);
        assertThat(pphm.get(currentPiece)).isEqualTo(positions.get((4*pc) + moved - 1));

        game.move(roll, currentPiece);
        moved += roll;
        assertThat(currentPiece.getIndex()).isEqualTo(moved);
        assertThat(pphm.get(currentPiece)).isEqualTo(positions.get((4*pc) + moved - 1));

        game.move(roll, currentPiece);
        moved += roll;
        assertThat(currentPiece.getIndex()).isEqualTo(moved);
        assertThat(pphm.get(currentPiece)).isEqualTo(positions.get((4*pc) + moved - 1));

        game.move(roll, currentPiece);
        moved += roll;
        assertThat(currentPiece.getIndex()).isEqualTo(moved);
        assertThat(pphm.get(currentPiece)).isEqualTo(positions.get((4*pc) + moved - 1));

        game.move(roll, currentPiece);
        moved += roll;
        assertThat(currentPiece.getIndex()).isEqualTo(moved);
        assertThat(pphm.get(currentPiece)).isEqualTo(positions.get((4*pc) + moved - 1));

        roll = 4;
        game.move(roll, currentPiece);
        moved += roll;
        assertThat(currentPiece.getIndex()).isEqualTo(moved);
        assertThat(pphm.get(currentPiece)).isEqualTo(positions.get((4*pc) + moved - 1));


        // Walk toward middle
        roll = 1;
        game.move(roll, currentPiece);
        moved += roll;
        assertThat(currentPiece.getIndex()).isEqualTo(moved);
        assertThat(pphm.get(currentPiece)).isEqualTo(positions.get((4*pc) + moved - 1));

        // One step before middle path
        roll = 3;
        game.move(roll, currentPiece);
        moved += roll;
        assertThat(currentPiece.getIndex()).isEqualTo(moved);
        assertThat(pphm.get(currentPiece)).isEqualTo(positions.get((4*pc) + moved - 1));

        // Walk out to entry before middle path
        roll = 6;
        game.move(roll, currentPiece);
        moved += 1;
        moved -= 5;
        assertThat(currentPiece.getIndex()).isEqualTo(moved);
        assertThat(pphm.get(currentPiece)).isEqualTo(positions.get((4*pc) + moved - 1));
        //printAllPieceLocations();
    }

    @Test
    public void testPlayerTwoWalks() throws NotFoundException {
        game.selectNextPlayer();
        int roll = 6;
        int moved = 0;
        var movablePieces = getPlayersMovablePieces(roll);
        var currentPiece = movablePieces.get(0);
        var pphm = game.getBoard().getPiecePositionHashMap();
        var positions = game.getBoard().getPositions();

        game.move(roll, currentPiece);
        moved += roll;
        assertThat(currentPiece.getIndex()).isEqualTo(moved);
        assertThat(pphm.get(currentPiece)).isEqualTo(positions.get((4*pc) + moved - 1 + currentPiece.getOffset()));


        game.move(roll, currentPiece);
        moved += roll;
        assertThat(currentPiece.getIndex()).isEqualTo(moved);
        assertThat(pphm.get(currentPiece)).isEqualTo(positions.get((4*pc) + moved - 1 + currentPiece.getOffset()));

        game.move(roll, currentPiece);
        moved += roll;
        assertThat(currentPiece.getIndex()).isEqualTo(moved);
        assertThat(pphm.get(currentPiece)).isEqualTo(positions.get((4*pc) + moved - 1 + currentPiece.getOffset()));

        game.move(roll, currentPiece);
        moved += roll;
        assertThat(currentPiece.getIndex()).isEqualTo(moved);
        assertThat(pphm.get(currentPiece)).isEqualTo(positions.get((4*pc) + moved - 1 + currentPiece.getOffset()));

        game.move(roll, currentPiece);
        moved += roll;
        assertThat(currentPiece.getIndex()).isEqualTo(moved);
        assertThat(pphm.get(currentPiece)).isEqualTo(positions.get((4*pc) + moved - 1 + currentPiece.getOffset()));

        game.move(roll, currentPiece);
        moved += roll;
        int pos = moved + -40 + currentPiece.getOffset();
        System.out.println(moved - 1 - 40);
        assertThat(currentPiece.getIndex()).isEqualTo(moved);
        assertThat(pphm.get(currentPiece)).isEqualTo(positions.get((4*pc) + pos - 1));

        roll = 4;
        game.move(roll, currentPiece);
        moved += roll;
        pos += roll;
        assertThat(currentPiece.getIndex()).isEqualTo(moved);
        assertThat(pphm.get(currentPiece)).isEqualTo(positions.get((4*pc) + pos - 1));

        // Walk toward middle path
        roll = 1;
        game.move(roll, currentPiece);
        moved += roll;
        pos = 44;
        assertThat(currentPiece.getIndex()).isEqualTo(moved);
        assertThat(pphm.get(currentPiece)).isEqualTo(positions.get((4*pc) + pos));

        // One step before middle path
        roll = 3;
        game.move(roll, currentPiece);
        moved += roll;
        pos += roll;
        assertThat(currentPiece.getIndex()).isEqualTo(moved);
        assertThat(pphm.get(currentPiece)).isEqualTo(positions.get((4*pc) + pos));

        // Walk out to entry before middle path
        roll = 6;
        game.move(roll, currentPiece);
        moved += 1;
        moved -= 5;
        pos = 9;
        assertThat(currentPiece.getIndex()).isEqualTo(moved);
        assertThat(pphm.get(currentPiece)).isEqualTo(positions.get((4*pc) + pos));

        //printAllPieceLocations();
    }








    // Helper methods


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
                System.out.println(p + ": " + "Index: " + piece.getIndex() + ", Position: " + game.getBoard().getPosition(piece).getPos() + ", Home: " + piece.getHomeNumber());
            }
        }
    }

}
