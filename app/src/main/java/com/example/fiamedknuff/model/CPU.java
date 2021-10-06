package com.example.fiamedknuff.model;

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
    private Board board;

    /**
     * Constructor for the class CPU which calls the superclass Players constructor and
     * couples the variable board with the incoming parameter board
     * */
    public CPU(String name, Color color) {
        super(name, color);
    }

    /**
     * Set the board to incoming parameter of board, if board is already initialized,
     * do nothing
     * @param board is the specific board to be...?
     */
    public void setBoard(Board board) {
        if (this.board == null) {
            this.board = board;
        }
    }

    /**
     * Decides which move a CPU wants to make by ranking different moves
     * @param roll is the value from the latest roll
     * @return the piece to be moved
     */
    public Piece choosePieceToMove(int roll) {
        List<Piece> movablePieces = getMovablePieces(getPieces(), roll);
        HashMap<Piece, Position> piecePositionHashMap = board.getPiecePositionHashMap();
        Position pos;
        if (movablePieces.size() == 0) {
           return null;
        }
        for (Piece piece : movablePieces) {
            pos = new Position(piecePositionHashMap.get(piece).getPos() + roll);
            if (board.isOccupied(pos)) {
                return piece;
            }
        }
        for (Piece piece : movablePieces) {
            if (piece.getIndex() + roll == 45) {
                return piece;
            }
        }
        for (Piece piece : movablePieces) {
            if (piece.isHome()) {
                return piece;
            }
        }
        return leadingPiece(movablePieces);
    }

    private Piece leadingPiece(List<Piece> movablePieces) {
        Piece piece = movablePieces.get(0);
        int tmpIndex = piece.getIndex();
        for (Piece p : movablePieces) {
            if (p.getIndex() > tmpIndex) {
                tmpIndex = p.getIndex();
                piece = p;
            }
        }
        return piece;
    }

    /*
    * Ta reda på vilka drag som är möjliga, som en vanlig Player -> getMovablePieces
    * Rangordning på möjliga drag:
    * 1. Om isOccupied -> knockput
    * 2. Gå ut med en pjäs
    * 3. Gå ut från bo med pjäs -> 1a eller 6a
    * 4. Inget speciellt, bara gå antal steg på tärningen framåt. Ta första bästa. (Slumpa mellan dessa om det finns fler?)
    *
    * Välja vilken pjäs -> Flytta den
    *
    * Överlag: Kan inte flytta -> Gå vidare till nästa
     */
}
