package com.example.fiamedknuff.model;

import android.content.res.Resources;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Board {

    private List<Position> positions;
    private HashMap<Piece, Position> piecePositionHashMap;
    private final int[] numberOfPositions = {0, 0, 0, 57, 0, 0, 0};

    public Board(int playerCount, List<Piece> pieces) {
        if (playerCount == 4) {
            this.positions = createPositionsList(playerCount);
            this.piecePositionHashMap = initPiecePositionHashmap(pieces);
        }

    }

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
    void movePiece(int roll, Piece piece) {
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

    Piece pieceAtposition(Position pos) {
        for (Piece piece : piecePositionHashMap.keySet()) {
            if (piecePositionHashMap.get(piece) == pos) {
                return piece;
            }
        }
        throw new Resources.NotFoundException();
        // kommer aldrig att hända om isOccupied anropas innan
    }

    boolean isOccupied(Position pos) {
        for (Position p: piecePositionHashMap.values()) {
            if (p.equals(pos)) {
                return true;
            }
        }
        return false;
    }

    void knockout(Position p) {
        Piece piece = pieceAtposition(p);
        piecePositionHashMap.remove(piece);
        piece.setIndex(0);
        piecePositionHashMap.put(piece,homePos);  // homePos = "0"

    }


}
