package com.example.fiamedknuff.model;

import com.example.fiamedknuff.exceptions.NotFoundException;
import com.example.fiamedknuff.exceptions.NotImplementedException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Class representing the board on which pieces move. Implements Serializable to handle data.
 *
 * Created by
 * @author Johan Selin
 */
public class Board implements Serializable {

    private List<Position> positions; // List of all positions on the board including home-positions
    private HashMap<Piece, Position> piecePositionHashMap; // Maps the pieces to their positions
    private final int[] numberOfPositions = {0, 57, 57, 57, 0, 0, 0}; // Number of positions for each board size
    private final int[] walkOutOffset = {0, 10, 10, 10, 0, 0, 0}; // Walk out offset-multiplier for the pieces for each board size
    private final int[] lapLength = {0, 40, 40, 40, 0, 0, 0}; // The amount of steps to walk around the path
    private final int[] finishIndex = {0, 45, 45, 45, 0, 0, 0}; // Index for last square where a piece finishes and disappears
    private final int playerCount; // amount of players

    /**
     * Creates a board according to the number of players and the pieces given. Assigns each piece
     * a home-position and offset as well as linking it to a position via the piecePositionHashMap.
     *
     * @param playerCount the number of players.
     * @param pieces all pieces to be involved in the game.
     * @throws NotImplementedException If given amount of players is not yet supported.
     */
    public Board(int playerCount, List<Piece> pieces) throws NotImplementedException {
        if (playerCount >= 2 && playerCount <= 4) {
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
     *
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

    /**
     * Gets the list of all positions.
     *
     * @return the list of all positions.
     */
    public List<Position> getPositions() {
        return positions;
    }

    /**
     * For testing purposes only.
     *
     * @param piece
     * @return
     */
    public Position getPositionOutsideHomeOf(Piece piece) {
        return piecePositionHashMap.get(piece);
    }

    /**
     * Returns the position of the piece given as a parameter.
     *
     * @param piece is the piece from which you want to know the position.
     * @return the position of the given piece.
     */
    public Position getPosition(Piece piece) {
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

    /**
     * Returns the hashmap linking the pieces to a position.
     *
     * @return the hashmap linking the pieces to a position.
     */
    public HashMap<Piece, Position> getPiecePositionHashMap() {
        return piecePositionHashMap;
    }

    /**
     * Get the lap length for the specific board.
     *
     * @return the lap length.
     */
    public int getLapLength() {
        return lapLength[playerCount - 1];
    }

    /**
     * Get the finish index for the specific board.
     *
     * @return the finish index.
     */
    public int getFinishIndex() {
        return finishIndex[playerCount - 1];
    }

    /**
     * Moves Piece step forward and increments its index. If the piece is home, about to go into the
     * middle path, or pass the lap line then the correct position will instead by calculated.
     *
     * @param piece the piece to be moved.
     */
    Position movePiece(Piece piece) {
        Position pos;
        if (piece.isHome()) {
            pos = getFirstPositionOf(piece);
        }
        else if (pieceWantsToGoInward(piece)) {
            pos = getFirstInwardPositionOf(piece);
        }
        else if (pieceAboutToLap(piece)) {
            pos = getFirstPositionInLap();
        }
        // FIXME: 2021-10-16 Only tested so it works for yellow so far, investigate
        else if (pieceOneStepBeforeGoal(piece)) {
            pos = getGoalPosition();
        }
        else { // Move as per usual
            pos = IncrementPositionOf(piece);
        }

        piecePositionHashMap.put(piece, pos); // Updates value of key
        piece.incrementIndex();
        return pos;
    }


    /**
     * Moves the piece in reverse order and decrements its index. The position will decrement by one in all cases except when
     * the piece is about to exit its middle path. In that case the piece will instead calculate the
     * position just before its middle path.
     *
     * @param piece the piece to be moved.
     */
    Position movePieceBackwards(Piece piece) {
        Position p;
        if (pieceAboutToExitMiddlePath(piece)) {
            p = getPieceLastPositionBeforeMiddlePath(piece);
        }
        // FIXME: 2021-10-16 Only tested so it works for yellow so far, investigate
        else if (piece.getIndex() == getFinishIndex()) {
            p = getPiecePositionBeforeGoal(piece);
        }
        else {
            p = decrementPositionOf(piece);
        }
        piecePositionHashMap.put(piece, p); // Updates value of key
        piece.decrementIndex();
        return p;
    }

    private boolean pieceOneStepBeforeGoal(Piece piece) {
        return piece.getIndex() == getFinishIndex() - 1;
    }

    private Position getPieceLastPositionBeforeMiddlePath(Piece piece) {
        Position p;
        if (piece.getOffset() > 0) {
            p = positions.get((4*playerCount) + piece.getOffset() - 1); // Start-position minus 1
        }
        else {
            p = positions.get((4 * playerCount) + lapLength[playerCount-1] - 1); // For player 1
        }
        return p;
    }

    private Position getPiecePositionBeforeGoal(Piece piece) {
        Position p = getPieceLastPositionBeforeMiddlePath(piece);
        p = positions.get(positions.indexOf(p) + 4);
        return p;
    }

    private boolean pieceAboutToExitMiddlePath(Piece piece) {
        return piece.getIndex() == lapLength[playerCount - 1] + 1;
    }

    private Position decrementPositionOf(Piece piece) {
        return positions.get((4 * playerCount) + piecePositionHashMap.get(piece).getPos() - 1);
    }

    private Position IncrementPositionOf(Piece piece) {
        return positions.get((4 * playerCount) + piecePositionHashMap.get(piece).getPos() + 1);
    }

    private Position getGoalPosition() {
        return positions.get(positions.size()-1);
    }

    private Position getFirstPositionInLap() {
        return positions.get(4*playerCount);
    }

    private Position getFirstInwardPositionOf(Piece piece) {
        Position p;
        int posIndex = lapLength[playerCount-1];
        posIndex += getPieceJumpAmount(piece);
        p = positions.get((4*playerCount)+posIndex);
        return p;
    }

    private Position getFirstPositionOf(Piece piece) {
        return positions.get((4*playerCount) + piece.getOffset());
    }

    private boolean pieceAboutToLap(Piece piece) {
        return piecePositionHashMap.get(piece) == positions.get((4 * playerCount) + lapLength[playerCount-1] - 1);
    }

    private boolean pieceWantsToGoInward(Piece piece) {
        return piece.getIndex() == lapLength[playerCount-1];
    }

    private int getPieceJumpAmount(Piece piece) {
        double absHomeNum = Math.abs(piece.getHomeNumber());
        double b = absHomeNum / 4;
        double c = Math.abs(b - playerCount);

        int indicator = (int) Math.floor(c);
        return 4 * indicator;
    }

    /**
     * Finds out which piece that are standing on a certain position
     * @param pos is the position to be checked
     * @return the piece at the incoming position
     * @throws NotFoundException if there's no piece at the given position
     */
    private Piece pieceAtPosition(Position pos) throws NotFoundException {
        for (Piece piece : piecePositionHashMap.keySet()) {
            if (piecePositionHashMap.get(piece) == pos) {
                return piece;
            }
        }
        throw new NotFoundException("No piece at given position");
    }

    /**
     * Checks if a position is occupied with another piece.
     *
     * @param pos is the position to be checked.
     * @return true if the position is occupied.
     */
    boolean isOccupied(Position pos) {
        for (Position p: piecePositionHashMap.values()) {
            if (p.equals(pos)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Finds the position corresponding to the piece's home number.
     * @param piece the piece whose home position to find
     * @return the home position of piece
     * @throws NotFoundException if a position couldn't be found
     */
    private int indexOfHomeNumber(Piece piece) throws NotFoundException {
        for (Position p : positions) {
            if (p.getPos() == (piece.getHomeNumber())) {
                return positions.indexOf(p);
            }
        }
        throw new NotFoundException("No position correlating to given piece's home number");
    }

    /**
     * Checks if the position of a piece is also occupied by another piece. If occupied the other
     * piece will get sent back to its home position.
     * @param piece the piece you want to check if it shares a position with another
     * @throws NotFoundException if the knocked out piece's home position couldn't be found
     */
    void knockOutPieceIfOccupied(Piece piece) throws NotFoundException {
        Position pos = piecePositionHashMap.get(piece);
        piecePositionHashMap.remove(piece);
        if (isOccupied(pos)) {
            knockout(pos);
        }
        piecePositionHashMap.put(piece, pos);
    }

    /**
     * Checks if the position of a piece is also occupied by another piece. If occupied,
     * the method returns true. Otherwise it returns false.
     * @param piece the piece you want to check if it shares a position with another
     * @return true if a piece should be knocked out, and false otherwise
     */
    boolean isKnockout(Piece piece) {
        boolean isKnockout = false;
        Position pos = piecePositionHashMap.get(piece);
        piecePositionHashMap.remove(piece);
        if (isOccupied(pos)) {
            isKnockout = true;
        }
        piecePositionHashMap.put(piece, pos);
        return isKnockout;
    }

    /**
     * Knocks out the piece that is standing on the same position as the piece which
     * is sent in as a parameter.
     * (The piece that is sent in as a parameter is removed from the hashmap while the
     * knockout is happening. This is because we don´t want the piece to knock out itself.
     * When the knockout is done, the piece is put back into the hashmap again.)
     * @param piece is the piece that is knocking out another piece
     * @return the piece that is knocked out
     * @throws NotFoundException if the method is called incorrectly
     */
    Piece knockoutWithPiece(Piece piece) throws NotFoundException {
        Position pos = piecePositionHashMap.get(piece);
        piecePositionHashMap.remove(piece);
        Piece knockedPiece = knockout(pos);
        piecePositionHashMap.put(piece, pos);
        return knockedPiece;
    }

    /**
     * Knocks out a piece at a position if another piece is moving to the same position
     * @param p is the position
     * @return the piece that is knocked out
     * @throws NotFoundException if a piece or position was not found
     */
    private Piece knockout(Position p) throws NotFoundException {
        Piece piece = pieceAtPosition(p);
        piecePositionHashMap.remove(piece);
        piece.resetIndex();
        piecePositionHashMap.put(piece, positions.get(indexOfHomeNumber(piece)));
        return piece;
    }

    /**
     * Removes a piece from the piecePositionHashMap.
     *
     * @param piece The piece to be removed.
     */
    void removePieceFromBoard(Piece piece) {
        piecePositionHashMap.remove(piece);
    }
}
