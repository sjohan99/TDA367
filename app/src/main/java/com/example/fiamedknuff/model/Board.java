package com.example.fiamedknuff.model;

import com.example.fiamedknuff.NotImplementedException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Board implements Serializable {

    private List<Position> positions; // List of all positions on the board including home-positions
    private HashMap<Piece, Position> piecePositionHashMap; // Maps the pieces to their positions
    private final int[] numberOfPositions = {0, 0, 0, 57, 0, 0, 0}; // Number of positions for each board size
    private final int[] walkOutOffset = {0, 0, 0, 10, 0, 0, 0};
    private final int[] lapLength = {0, 0, 0, 39, 0, 0, 0};
    private final int playerCount;

    public Board(int playerCount, List<Piece> pieces) throws NotImplementedException {
        if (playerCount == 4) {
            this.playerCount = playerCount;
            this.positions = createPositionsList();
            this.piecePositionHashMap = initPiecePositionHashmap(pieces);
            assignPieceOffsets(pieces);
        }
        else {
            throw new NotImplementedException();
        }
    }

    /**
     * Generates the position list with Position-indices ranging from (-4 * playerCount) to (the amount of
     * positions on the board excluding home-positions - 1). playerCount = 4 would give Positions with
     * indices -16 to 56.
     * @return list with Positions with indices (-4 * playerCount) to (numberOfPositions - 1).
     */
    private ArrayList<Position> createPositionsList() {
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

    /**
     * For testing purposes only as of now
     * @param piece
     * @return
     */
    public Position getPositionOutsideHomeOf(Piece piece) {
        return piecePositionHashMap.get(piece);
    }

    private void assignPieceOffsets(List<Piece> pieces) {
        int multiplier = -1;
        for (int i = 0; i < pieces.size(); i++) {
            if (i % 4 == 0) {
                multiplier++;
            }
            pieces.get(i).setOffset(multiplier * walkOutOffset[playerCount-1]);
        }
    }

    private HashMap<Piece, Position> initPiecePositionHashmap(List<Piece> pieces) {
        HashMap<Piece, Position> piecePositionHashMap = new HashMap<>();
        int i = 0;
        for (Piece piece : pieces) {
            piecePositionHashMap.put(piece, positions.get(i));
            piece.setHomeNumber(positions.get(i).getPos());
            i++;
        }
        return piecePositionHashMap;
    }

    public HashMap<Piece, Position> getPiecePositionHashMap() {
        return piecePositionHashMap;
    }

    /**
     * Moves Piece forward one step
     * @param piece is the piece to be moved
     */
    void movePiece(Piece piece) throws Exception {
        Position p;
        if (piece.isHome()) {
            p = getFirstPositionOf(piece);
        }
        else if (pieceWantsToGoInward(piece)) {
            p = getFirstInwardPositionOf(piece);
        }
        else if (pieceAboutToLap(piece)) {
            p = getFirstPositionInLap();
        }
        else { // Move as per usual
            p = IncrementPositionOf(piece);
        }

        piecePositionHashMap.put(piece, p); // Updates value of key
        piece.setIndex(piece.getIndex() + 1);
    }

    private Position IncrementPositionOf(Piece piece) {
        return positions.get((4 * playerCount) + piecePositionHashMap.get(piece).getPos() + 1);
    }

    private Position getFirstPositionInLap() {
        return positions.get(4*playerCount);
    }

    private Position getFirstInwardPositionOf(Piece piece) {
        Position p;
        int posIndex = (4*playerCount) + piecePositionHashMap.get(piece).getPos();
        posIndex = getPieceJumpAmount(piece, posIndex);
        p = positions.get(posIndex);
        return p;
    }

    private Position getFirstPositionOf(Piece piece) {
        return positions.get((4*playerCount) + piece.getOffset());
    }

    private boolean pieceAboutToLap(Piece piece) {
        return piecePositionHashMap.get(piece) == positions.get((4 * playerCount) + lapLength[playerCount-1]);
    }

    private boolean pieceWantsToGoInward(Piece piece) {
        return piece.getIndex() == lapLength[playerCount-1];
    }

    private int getPieceJumpAmount(Piece piece, int posIndex) {
        double indicator = Math.abs(piece.getHomeNumber()) / 4;
        indicator = Math.ceil(indicator);
        int jump = (int) ((indicator * 4) - 4);
        posIndex += jump;
        return posIndex;
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
        // TODO: 2021-10-01 More specific exception
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
            if (p.getPos() == (piece.getHomeNumber())) {
                return positions.indexOf(p);
            }
        }
        // TODO: 2021-10-01 More specific exception
        throw new Exception(); // -> skriv in rätt Exception här?
    }

    void knockOutPieceIfOccupied(Piece piece) throws Exception {
        Position pos = piecePositionHashMap.get(piece);
        piecePositionHashMap.remove(piece);
        if (isOccupied(pos)) {
            knockout(pos);
        }
        piecePositionHashMap.put(piece, pos);
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

    /**
     * Removes a piece from the piecePositionHashMap
     * @param piece The piece to be removed
     */
    void removePieceFromBoard(Piece piece) {
        piecePositionHashMap.remove(piece);
    }
}
