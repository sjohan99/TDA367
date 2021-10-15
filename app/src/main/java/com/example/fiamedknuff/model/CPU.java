package com.example.fiamedknuff.model;

import java.util.HashMap;
import java.util.List;

/**
 * A class CPU that creates a CPU object and couples computer player logic with a player.
 *
 * Created by
 * @author Amanda Cyrén
 */
public class CPU extends Player {

    private Board board; // A CPU must have a board to access the game logic

    /**
     * Constructor for the class CPU which calls the superclass Players constructor and
     * couples the variable board with the incoming parameter board.
     *
     * @param name The name of the CPU.
     * @param color The color you want the player's pieces to have.
     * */
    public CPU(String name, Color color) {
        super(name, color);
    }

    /**
     * Set the board to incoming parameter of board, if board is already initialized,
     * do nothing. The Board must be set for the CPU after a Game has been created, since the
     * Board is null otherwise.
     *
     * @param board is the specific board to be set to CPU's board variable.
     */
    public void setBoard(Board board) {
        if (this.board == null) {
            this.board = board;
        }
    }

    /**
     * Decides which move a CPU wants to make by ranking different moves. Prioritizes knockout
     * of another players piece. Otherwise going out with a piece off the board, then going out
     * with a piece from the home and lastly move a leading piece forward (a leading piece is the
     * piece that has moved the farthest on the board but not yet entered the home-path).
     *
     * @param roll is the value from the latest roll.
     * @return the piece to be moved.
     */
    public Piece choosePieceToMove(int roll) {
        List<Piece> movablePieces = getMovablePieces(getPieces(), roll);
        if (movablePieces.size() == 0) {
           return null;
        }
        for (Piece piece : movablePieces) {
            if (isHomeAndCanKnockout(piece)) {
                return piece;
            }
        }
        for (Piece piece : movablePieces) {
            if (!piece.isHome() && isOnBoardAndCanKnockout(piece, roll)) {
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

    // Return the leading piece, which is the piece that has moved the farthest on the board and
    // not yet entered the home path. If all the pieces is in the home path, return the first piece
    private Piece leadingPiece(List<Piece> movablePieces) {
        Piece piece = movablePieces.get(0);
        int tmpIndex = piece.getIndex();
        for (Piece p : movablePieces) {
            // If piece is in home path, don't prioritize moving that piece
            if (p.getIndex() > board.getLapLength() + 1) {
                continue;
            }
            if (p.getIndex() > tmpIndex) {
                tmpIndex = p.getIndex();
                piece = p;
            }
        }
        return piece;
    }

    // If a piece is home, calculate the players first and sixth position from home to see if it can knockout another piece at that position.
    private boolean isHomeAndCanKnockout(Piece piece) {
        Position firstPosOutOfHome = board.getFirstPositionOf(piece);
        int indexSixStepsOutOfHome = board.getFirstPositionIndexInLap() + board.getFirstPositionOf(piece).getPos() + 5;
        Position posSixStepsOutOfHome = board.getPositions().get(indexSixStepsOutOfHome);
        return piece.isHome() && (board.isOccupied(firstPosOutOfHome) || board.isOccupied(posSixStepsOutOfHome));
    }

    // If a piece is on board, calculate the new position from the dice roll and check if knockout on another piece is possible.
    private boolean isOnBoardAndCanKnockout(Piece piece, int roll) {
        HashMap<Piece, Position> piecePositionHashMap = board.getPiecePositionHashMap();
        int indexNewPos = board.getFirstPositionIndexInLap() + piecePositionHashMap.get(piece).getPos() + roll;
        Position newPos = board.getPositions().get(indexNewPos);
        return board.isOccupied(newPos);
    }
}
