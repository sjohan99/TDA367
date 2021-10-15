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
     * @param name The name of the CPU
     * @param color The color you want the player's pieces to have
     * */
    public CPU(String name, Color color) {
        super(name, color);
    }

    /**
     * Set the board to incoming parameter of board, if board is already initialized,
     * do nothing
     * @param board is the specific board to be set to CPU's board variable
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
            Position firstPosOutOfHome = board.getFirstPositionOf(piece);
            int sixStepsOutOfHome = board.getFirstPositionIndexInLap() + board.getFirstPositionOf(piece).getPos() + 5;
            int test = board.getFirstPositionIndexInLap();
            Position posSixStepsOutOfHome = board.getPositions().get(sixStepsOutOfHome);
            if (piece.isHome() && (board.isOccupied(firstPosOutOfHome) || board.isOccupied(posSixStepsOutOfHome))) {
                return piece;
            }
        }
        for (Piece piece : movablePieces) {
            pos = new Position(piecePositionHashMap.get(piece).getPos() + roll);
            if (board.isOccupied(pos)) {
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

}
