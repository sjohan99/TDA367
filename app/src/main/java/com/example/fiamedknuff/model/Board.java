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

    boolean isOccupied(Position pos) {
        for (Position p: piecePositionHashMap.values()) {
            if (p.equals(pos)) {
                return true;
            }
        }
        return false;
    }


}
