package com.example.fiamedknuff.model;

import com.example.fiamedknuff.NotImplementedException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Board {

    private List<Position> positions; // List of all positions on the board including home-positions
    private HashMap<Piece, Position> piecePositionHashMap; // Maps the pieces to their positions
    private final int[] numberOfPositions = {0, 0, 0, 57, 0, 0, 0}; // Number of positions for each board size

    public Board(int playerCount, List<Piece> pieces) throws NotImplementedException {
        if (playerCount == 4) {
            this.positions = createPositionsList(playerCount);
            this.piecePositionHashMap = initPiecePositionHashmap(pieces);
        }
        else {
            throw new NotImplementedException();
        }
    }

    /**
     * Generates the position list with Position-indices ranging from (-4 * playerCount) to (the amount of
     * positions on the board excluding home-positions - 1). playerCount = 4 would give Positions with
     * indices -16 to 56.
     * @param playerCount number of players playing.
     * @return list with Positions with indices (-4 * playerCount) to (numberOfPositions - 1).
     */
    private ArrayList<Position> createPositionsList(int playerCount) {
        int negativeIndices = playerCount * -4;
        int positiveIndices = numberOfPositions[playerCount - 1];
        ArrayList<Position> positionArrayList = new ArrayList<>();
        for (int i = negativeIndices; i < positiveIndices; i++) {
            positionArrayList.add(new Position(i));
        }
        return positionArrayList;
    }

    public List<Position> getPositions() {
        return positions;
    }

    private HashMap<Piece, Position> initPiecePositionHashmap(List<Piece> pieces) {
        HashMap<Piece, Position> piecePositionHashMap = new HashMap<>();
        int i = 0;
        for (Piece piece : pieces) {
            piecePositionHashMap.put(piece, positions.get(i++));
        }
        return piecePositionHashMap;
    }

    public HashMap<Piece, Position> getPiecePositionHashMap() {
        return piecePositionHashMap;
    }

    //Bara preliminärt för att få koden att kompilera, används i metoden knockout nedan
    Position homePos = new Position(0);

    /**
     * Moves Piece forward with the amount of the dice roll
     * @param roll is the value from the latest dice roll
     * @param piece is the piece to be moved
     */
    void movePiece(int roll, Piece piece) throws Exception {
        Position p;
        if (piece.isHome()) {
            p = new Position(10 + roll);    //ytterst preliminärt
        }
        else {
            p = new Position(piecePositionHashMap.get(piece).getPos() + roll);
        }

        piecePositionHashMap.remove(piece);
        piece.setIndex(piece.getIndex() + roll);

        if (isOccupied(p)) {
            knockout(p);
        }
        piecePositionHashMap.put(piece,p);
    }

    /**
     * Finds out which piece that are standing on a certain position
     * @param pos is the position to be checked
     * @return the piece at the incoming position
     * @throws Exception if the method is called incorrectly
     */
    Piece pieceAtPosition(Position pos) throws Exception {

        for (Piece piece : piecePositionHashMap.keySet()) {
            if (piecePositionHashMap.get(piece) == pos) {
                return piece;
            }
        }
        throw new Exception(); // -> skriv in rätt Exception här?
    }

    /**
     * Checks if a position is occupied with another piece
     * @param pos is the position to be checked
     * @return true if the position is occupied
     */
    boolean isOccupied(Position pos) {
        for (Position p: piecePositionHashMap.values()) {
            if (p.equals(pos)) {
                return true;
            }
        }
        return false;
    }


    int indexOfHomeNumber(Piece piece) throws Exception {
        for (Position p : positions) {
            if (p.equals(piece.getHomeNumber())) {
                return positions.indexOf(p);
            }
        }
        throw new Exception(); // -> skriv in rätt Exception här?
    }

    /**
     * Knocks out a piece at a position if another piece is moving to the same position
     * @param p is the position
     * @throws Exception if the method is called incorrectly
     */
    void knockout(Position p) throws Exception {
        Piece piece = pieceAtPosition(p);
        piecePositionHashMap.remove(piece);
        piece.setIndex(0);
        piecePositionHashMap.put(piece, positions.get(indexOfHomeNumber(piece)));

    }
}
