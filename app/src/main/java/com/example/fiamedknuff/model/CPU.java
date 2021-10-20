package com.example.fiamedknuff.model;

import java.util.HashMap;
import java.util.List;

/**
 * A class CPU that creates a CPU object. A CPU is a subclass to Player and inherits all of Player's
 * attributes. A CPU also has some logic of its own which it uses to determine which move to make.
 *
 * Created by
 * @author Amanda Cyrén
 */
public class CPU extends Player {

    private Board board; // A CPU must have a board to access the game logic

    /**
     * Sole constructor for the class CPU which calls the superclass Player's constructor.
     *
     * @param name is the name of the CPU.
     * @param color is the CPU's color.
     * */
    public CPU(String name, Color color) {
        super(name, color);
    }

    /**
     * Sets the board to incoming parameter of board. If the board is already initialized,
     * do nothing. The board must be set for the CPU after a game has been created, since the
     * board is null otherwise.
     *
     * @param board the board to be set to CPU's board variable.
     */
    public void setBoard(Board board) {
        if (this.board == null) {
            this.board = board;
        }
    }

    /**
     * Decides which move a CPU wants to make by ranking different moves. Prioritizes to move a
     * piece which has the possibility to knockout another players piece, primarily if this piece
     * is at home. Otherwise going out with a piece off the board, then going out with a piece from
     * the home and lastly move a leading piece forward (a leading piece is the piece that has
     * moved the farthest on the board but not yet entered the middle path).
     *
     * @param roll is the value from the latest roll.
     * @return the piece to be moved.
     */
    public Piece choosePieceToMove(int roll) {
        List<Piece> movablePieces = getMovablePieces(getPieces(), roll);
        if (movablePieces.isEmpty()) {
           return null;
        }
        for (Piece piece : movablePieces) {
            if (isHomeAndCanKnockout(piece)) {
                return piece;
            }
        }
        for (Piece piece : movablePieces) {
            if (!piece.isHome() && piece.getIndex() < board.getLapLength() && isOnBoardAndCanKnockout(piece, roll)) {
                return piece;
            }
        }
        for (Piece piece : movablePieces) {
            if (piece.getIndex() + roll == board.getFinishIndex()) {
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

    // Return the leading piece, which is the piece that has moved the farthest on the board and not
    // yet entered the middle path. If all the pieces is in the middle path, return the first piece.
    private Piece leadingPiece(List<Piece> movablePieces) {
        Piece piece = movablePieces.get(0);
        int tmpIndex = piece.getIndex();
        for (Piece p : movablePieces) {
            // If piece is in the middle path, don't prioritize moving this piece
            if (p.getIndex() > board.getLapLength() + 1) {
                tmpIndex = 0;
            }
            else if (p.getIndex() > tmpIndex) {
                tmpIndex = p.getIndex();
                piece = p;
            }
        }
        return piece;
    }

    // If a piece is home, calculate the players first and sixth position from home to see if it
    // can knockout another piece at that position.
    private boolean isHomeAndCanKnockout(Piece piece) {
        Position firstPosOutOfHome = board.getFirstPositionOf(piece);
        int indexSixStepsOutOfHome = board.getFirstPositionIndexInLap() + board.getFirstPositionOf(piece).getPos() + 5;
        Position posSixStepsOutOfHome = board.getPositions().get(indexSixStepsOutOfHome);
        return piece.isHome() && (board.isOccupied(firstPosOutOfHome) || board.isOccupied(posSixStepsOutOfHome));
    }

    // If a piece is on board, calculate the new position from the dice roll and check if knockout
    // on another piece is possible.
    private boolean isOnBoardAndCanKnockout(Piece piece, int roll) {
        HashMap<Piece, Position> piecePositionHashMap = board.getPiecePositionHashMap();
        int indexNewPos = board.getFirstPositionIndexInLap() + piecePositionHashMap.get(piece).getPos() + roll;
        Position newPos = board.getPositions().get(indexNewPos); // IndexOutOfBoundException
        return board.isOccupied(newPos);
    }
}
