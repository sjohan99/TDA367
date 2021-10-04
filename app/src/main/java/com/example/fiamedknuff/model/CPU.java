package com.example.fiamedknuff.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * A class CPU that creates a CPU object and couples computer player logic with a player
 *
 * Created by
 * @author Amanda Cyrén
 */
public class CPU extends Player {

    // A CPU must have a board to access the game logic
    private final Board board;

    /**
     * Constructor for the class CPU which calls the superclass Players constructor and
     * couples the variable board with the incoming parameter board
     * */
    public CPU(String name, Color color, Board board) {
        super(name, color);
        this.board = board;
    }

    Piece makeMove(int roll) throws Exception {
        Collection<Piece> movablePieces = getMovablePieces(getPieces(), roll);
        HashMap<Piece, Position> piecePositionHashMap = board.getPiecePositionHashMap();
        Position pos;
        for (Piece piece : movablePieces) {
            pos = new Position(piecePositionHashMap.get(piece).getPos() + roll);
            if (board.isOccupied(pos)) {
                return piece;
            }
        }
        for (Piece piece : movablePieces) {
            pos = new Position(piecePositionHashMap.get(piece).getPos() + roll);
            if (piece.getIndex() + roll == 45) {
                return piece;
            }
        }
        return null;
        //... HashMap?
    }

    /*
    * Ta reda på vilka drag som är möjliga, som en vanlig Player -> getMovablePieces
    * Rangordning på möjliga drag:
    * 1. Om isOccupied -> knockput
    * 2. Gå ut med en pjäs
    * 3. Gå ut från bo med pjäs -> 1a eller 6a
    * 4. Inget speciellt, bara gå antal steg på tärningen framåt. Slumpa mellan dessa om det finns fler?
    *
    * Välja vilken pjäs -> Flytta den
    *
    * Överlag: Kan inte flytta -> Gå vidare till nästa
     */
}
