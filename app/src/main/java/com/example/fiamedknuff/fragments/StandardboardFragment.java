package com.example.fiamedknuff.fragments;

import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.fiamedknuff.R;
import com.example.fiamedknuff.viewModels.GameViewModel;
import com.example.fiamedknuff.model.Piece;
import com.example.fiamedknuff.model.Position;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * A class standardboardFragment that handles the view of the standardboard and its
 * pieces, positions and dice.
 *
 * Created by
 * @author Emma Stålberg
 */

public class StandardboardFragment extends Fragment {
    View view;
    ImageView pos0, pos1, pos2, pos3, pos4, pos5, pos6, pos7, pos8, pos9, pos10;
    ImageView pos11, pos12, pos13, pos14, pos15, pos16, pos17, pos18, pos19, pos20;
    ImageView pos21, pos22, pos23, pos24, pos25, pos26, pos27, pos28, pos29, pos30;
    ImageView pos31, pos32, pos33, pos34, pos35, pos36, pos37, pos38, pos39, pos40;
    ImageView pos41, pos42, pos43, pos44, pos45, pos46, pos47, pos48, pos49, pos50;
    ImageView pos51, pos52, pos53, pos54, pos55, pos56;
    ImageView yellowHomePos0, yellowHomePos1, yellowHomePos2, yellowHomePos3;
    ImageView redHomePos0, redHomePos1, redHomePos2, redHomePos3;
    ImageView greenHomePos0, greenHomePos1, greenHomePos2, greenHomePos3;
    ImageView blueHomePos0, blueHomePos1, blueHomePos2, blueHomePos3;
    List<ImageView> boardPositions;
    List<ImageView> homePositions;
    HashMap<Position, ImageView> imageViewPositionHashMap;

    ImageView yellowpiece0, yellowpiece1, yellowpiece2, yellowpiece3;
    ImageView redpiece0, redpiece1, redpiece2, redpiece3;
    ImageView bluepiece0, bluepiece1, bluepiece2, bluepiece3;
    ImageView greenpiece0, greenpiece1, greenpiece2, greenpiece3;
    HashMap<ImageView, Piece> imageViewPieceHashMap;
    List<ImageView> piecesImageViews;

    ConstraintLayout constraintLayout;
    GameViewModel gameViewModel;

    ImageView diceImage;
    List<Integer> diceImages;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_standardboard, container, false);

        gameViewModel = new ViewModelProvider(getActivity()).get(GameViewModel.class);

        constraintLayout = view.findViewById(R.id.constraintLayout);

        initPositions();
        initPieces();
        initDice();

        return view;
    }

    private void initDice() {
        diceImage = view.findViewById(R.id.diceImage);
        initDiceImages();

        diceImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int rolledValue = gameViewModel.rollDice();
                if (rolledValue != -1) {
                    rotateDice(rolledValue);

                    // If the rolled value is not possible to use, i.e. the player can´t move
                    // any of their pieces with that value, the dice should be moved to the
                    // next player in the view and the dice should be set to used. Also, the next
                    // player should be selected.
                    if (!gameViewModel.isPossibleToUseDicevalue()) {
                        gameViewModel.selectNextPlayer();
                        moveDice();
                        gameViewModel.diceIsUsed();
                    } else {
                        // The player can make a turn and the player's pieces will be highlighted.
                        markMovablePieces();
                    }
                }
            }
        });
    }

    /**
     * Gets the current player's movable pieces marked on the GUI.
     */
    private void markMovablePieces() {
        // For each of the player's movable pieces, iterate through all pieces in the HashMap
        // and find the corresponding ImageView that is connected to the movable piece.
        // When found the piece gets highlighted.
        LiveData<List<Piece>> movablePieces = gameViewModel.getMovablePiecesForCurrentPlayer();
        for (Piece piece : movablePieces.getValue()) {
            for (Map.Entry<ImageView, Piece> entry : imageViewPieceHashMap.entrySet()) {
                if (piece.toString().equals(entry.getValue().toString())) {
                    // TODO: Change to something fancy
                    entry.getKey().setBackgroundColor(R.drawable.background); // Highlight the movable piece
                }
            }
        }

    }

    /**
     * Method used to help identify ImageViews for affecting pliancy on the current player's pieces.
     * @return Returns a HashMap with the current player's piece's ImageViews.
     */
    public HashMap<Piece, ImageView> getCurrentPlayersMovablePiecesImageViews() {
        HashMap<Piece, ImageView> map = new HashMap<>();
        LiveData<List<Piece>> movablePieces = gameViewModel.getMovablePiecesForCurrentPlayer();
        for (Piece piece : movablePieces.getValue()) {
            for (Map.Entry<ImageView, Piece> entry : imageViewPieceHashMap.entrySet()) {
                if (piece.toString().equals(entry.getValue().toString())) {
                    map.put(entry.getValue(), entry.getKey());
                }
            }
        }
        return map;
    }

    private void moveDice() {
        // move dice in view, not implemented yet
        // 1. check current player.
        //2. move the dice to that player
    }

    /**
     * Initiates the diceimages and puts them in a list "diceImages".
     */
    private void initDiceImages() {
        diceImages = new ArrayList<>();
        diceImages.add(R.drawable.dice1);
        diceImages.add(R.drawable.dice2);
        diceImages.add(R.drawable.dice3);
        diceImages.add(R.drawable.dice4);
        diceImages.add(R.drawable.dice5);
        diceImages.add(R.drawable.dice6);
    }

    /**
     * Method that rotates the dice and sets the new rolled value.
     * @param rolledValue is the rolled value
     */
    private void rotateDice(int rolledValue) {
        Animation anim = AnimationUtils.loadAnimation(requireActivity().getApplicationContext(), R.anim.rotate);
        diceImage.startAnimation(anim); // animate the roll of the dice
        diceImage.setImageResource(diceImages.get(rolledValue - 1)); // sets the rolled value
    }

    /**
     * Initiates the pieces by connecting the pieces ids, initiates the list of the pieces,
     * initiates the hashmap with the pieces, makes the inactive pieces invisible and adds
     * onClickListeners to the pieces.
     */
    private void initPieces() {
        connectPiecesIds();
        initListOfAllPieces();
        initPiecesHashmap();
        makeInactivePiecesInvisible();
        addPiecesOnClickListeners();
    }

    /**
     * Connects the pieces id:s with its equivalent imageview.
     */
    private void connectPiecesIds() {
        yellowpiece0 = view.findViewById(R.id.yellowpiece0);
        yellowpiece1 = view.findViewById(R.id.yellowpiece1);
        yellowpiece2 = view.findViewById(R.id.yellowpiece2);
        yellowpiece3 = view.findViewById(R.id.yellowpiece3);
        redpiece0 = view.findViewById(R.id.redpiece0);
        redpiece1 = view.findViewById(R.id.redpiece1);
        redpiece2 = view.findViewById(R.id.redpiece2);
        redpiece3 = view.findViewById(R.id.redpiece3);
        greenpiece0 = view.findViewById(R.id.greenpiece0);
        greenpiece1 = view.findViewById(R.id.greenpiece1);
        greenpiece2 = view.findViewById(R.id.greenpiece2);
        greenpiece3 = view.findViewById(R.id.greenpiece3);
        bluepiece0 = view.findViewById(R.id.bluepiece0);
        bluepiece1 = view.findViewById(R.id.bluepiece1);
        bluepiece2 = view.findViewById(R.id.bluepiece2);
        bluepiece3 = view.findViewById(R.id.bluepiece3);
    }

    /**
     * Initiates the list piecesImageViews with all ImageViews of the pieces.
     */
    private void initListOfAllPieces() {
        piecesImageViews = new ArrayList<>();
        piecesImageViews.addAll(new ArrayList<>(Arrays.asList(
                yellowpiece0, yellowpiece1, yellowpiece2, yellowpiece3)));
        piecesImageViews.addAll(new ArrayList<>(Arrays.asList(
                redpiece0, redpiece1, redpiece2, redpiece3)));
        piecesImageViews.addAll(new ArrayList<>(Arrays.asList(
                greenpiece0, greenpiece1, greenpiece2, greenpiece3)));
        piecesImageViews.addAll(new ArrayList<>(Arrays.asList(
                bluepiece0, bluepiece1, bluepiece2, bluepiece3)));
    }

    /**
     * Initiates the hashmap imageViewPieceHashMap. Gets the active pieces from gameViewModel
     * and connects them with the equivalent imageView.
     */
    private void initPiecesHashmap() {
        imageViewPieceHashMap = new HashMap<>();
        List<Piece> activePieces = gameViewModel.getPieces();
        for (int i = 0; i < activePieces.size(); i++) {
            imageViewPieceHashMap.put(piecesImageViews.get(i), activePieces.get(i));
        }
    }

    /**
     * The pieces that should be visible are connected in the imageViewPieceHashMap. The
     * rest of the pieces in the list piecesImageViews should be invisible, and that is
     * what happens in this method.
     */
    private void makeInactivePiecesInvisible() {
        for (int i = imageViewPieceHashMap.size(); i < piecesImageViews.size(); i++) {
            piecesImageViews.get(i).setVisibility(View.INVISIBLE);
        }
    }

    /**
     * Calls the methods connectPositionIds and initListOfPositions.
     */
    private void initPositions() {
        connectPositionIds();
        initListsOfPositions();
        initPositionsHashmap();
    }

    /**
     * Connects every position to its equivalent ImageView.
     */
    private void connectPositionIds() {
        connectBoardPositionsIds();
        connectHomePositionsIds();
    }

    /**
     * Connects every position on the board to its equivalent ImageView
     */
    private void connectBoardPositionsIds() {
        pos0 = view.findViewById(R.id.pos0);
        pos1 = view.findViewById(R.id.pos1);
        pos2 = view.findViewById(R.id.pos2);
        pos3 = view.findViewById(R.id.pos3);
        pos4 = view.findViewById(R.id.pos4);
        pos5 = view.findViewById(R.id.pos5);
        pos6 = view.findViewById(R.id.pos6);
        pos7 = view.findViewById(R.id.pos7);
        pos8 = view.findViewById(R.id.pos8);
        pos9 = view.findViewById(R.id.pos9);
        pos10 = view.findViewById(R.id.pos10);
        pos11 = view.findViewById(R.id.pos11);
        pos12 = view.findViewById(R.id.pos12);
        pos13 = view.findViewById(R.id.pos13);
        pos14 = view.findViewById(R.id.pos14);
        pos15 = view.findViewById(R.id.pos15);
        pos16 = view.findViewById(R.id.pos16);
        pos17 = view.findViewById(R.id.pos17);
        pos18 = view.findViewById(R.id.pos18);
        pos19 = view.findViewById(R.id.pos19);
        pos20 = view.findViewById(R.id.pos20);
        pos21 = view.findViewById(R.id.pos21);
        pos22 = view.findViewById(R.id.pos22);
        pos23 = view.findViewById(R.id.pos23);
        pos24 = view.findViewById(R.id.pos24);
        pos25 = view.findViewById(R.id.pos25);
        pos26 = view.findViewById(R.id.pos26);
        pos27 = view.findViewById(R.id.pos27);
        pos28 = view.findViewById(R.id.pos28);
        pos29 = view.findViewById(R.id.pos29);
        pos30 = view.findViewById(R.id.pos30);
        pos31 = view.findViewById(R.id.pos31);
        pos32 = view.findViewById(R.id.pos32);
        pos33 = view.findViewById(R.id.pos33);
        pos34 = view.findViewById(R.id.pos34);
        pos35 = view.findViewById(R.id.pos35);
        pos36 = view.findViewById(R.id.pos36);
        pos37 = view.findViewById(R.id.pos37);
        pos38 = view.findViewById(R.id.pos38);
        pos39 = view.findViewById(R.id.pos39);
        pos40 = view.findViewById(R.id.pos40);
        pos41 = view.findViewById(R.id.pos41);
        pos42 = view.findViewById(R.id.pos42);
        pos43 = view.findViewById(R.id.pos43);
        pos44 = view.findViewById(R.id.pos44);
        pos45 = view.findViewById(R.id.pos45);
        pos46 = view.findViewById(R.id.pos46);
        pos47 = view.findViewById(R.id.pos47);
        pos48 = view.findViewById(R.id.pos48);
        pos49 = view.findViewById(R.id.pos49);
        pos50 = view.findViewById(R.id.pos50);
        pos51 = view.findViewById(R.id.pos51);
        pos52 = view.findViewById(R.id.pos52);
        pos53 = view.findViewById(R.id.pos53);
        pos54 = view.findViewById(R.id.pos54);
        pos55 = view.findViewById(R.id.pos55);
        pos56 = view.findViewById(R.id.pos56);
    }

    /**
     * Connects every position in the homes to its equivalent ImageView.
     */
    private void connectHomePositionsIds() {
        yellowHomePos0 = view.findViewById(R.id.yellowHomepos0);
        yellowHomePos1 = view.findViewById(R.id.yellowHomepos1);
        yellowHomePos2 = view.findViewById(R.id.yellowHomepos2);
        yellowHomePos3 = view.findViewById(R.id.yellowHomepos3);
        redHomePos0 = view.findViewById(R.id.redHomepos0);
        redHomePos1 = view.findViewById(R.id.redHomepos1);
        redHomePos2 = view.findViewById(R.id.redHomepos2);
        redHomePos3 = view.findViewById(R.id.redHomepos3);
        greenHomePos0 = view.findViewById(R.id.greenHomepos0);
        greenHomePos1 = view.findViewById(R.id.greenHomepos1);
        greenHomePos2 = view.findViewById(R.id.greenHomepos2);
        greenHomePos3 = view.findViewById(R.id.greenHomepos3);
        blueHomePos0 = view.findViewById(R.id.blueHomepos0);
        blueHomePos1 = view.findViewById(R.id.blueHomepos1);
        blueHomePos2 = view.findViewById(R.id.blueHomepos2);
        blueHomePos3 = view.findViewById(R.id.blueHomepos3);
    }

    /**
     * Initiates the lists for the positions in the homes and on the board.
     */
    private void initListsOfPositions() {
        initListOfBoardPositions();
        initListOfHomePositions();
    }

    /**
     * Initiates the List with all positions in the homes.
     */
    private void initListOfHomePositions() {
        homePositions = new ArrayList<>();

        homePositions.addAll(new ArrayList<>(Arrays.asList(
                yellowHomePos0, yellowHomePos1, yellowHomePos2, yellowHomePos3)));
        homePositions.addAll(new ArrayList<>(Arrays.asList(
                redHomePos0, redHomePos1, redHomePos2, redHomePos3)));
        homePositions.addAll(new ArrayList<>(Arrays.asList(
                greenHomePos0, greenHomePos1, greenHomePos2, greenHomePos3)));
        homePositions.addAll(new ArrayList<>(Arrays.asList(
                blueHomePos0, blueHomePos1, blueHomePos2, blueHomePos3)));
    }

    /**
     * Initiates the List with all positions on the board.
     */
    private void initListOfBoardPositions() {
        boardPositions = new ArrayList<>();

        boardPositions.addAll(new ArrayList<>(Arrays.asList
                (pos0, pos1, pos2, pos3, pos4, pos5, pos6, pos7, pos8, pos9, pos10)));
        boardPositions.addAll(new ArrayList<>(Arrays.asList
                (pos11, pos12, pos13, pos14, pos15, pos16, pos17, pos18, pos19, pos20)));
        boardPositions.addAll(new ArrayList<>(Arrays.asList
                (pos21, pos22, pos23, pos24, pos25, pos26, pos27, pos28, pos29, pos30)));
        boardPositions.addAll(new ArrayList<>(Arrays.asList
                (pos31, pos32, pos33, pos34, pos35, pos36, pos37, pos38, pos39, pos40)));
        boardPositions.addAll(new ArrayList<>(Arrays.asList
                (pos41, pos42, pos43, pos44, pos45, pos46, pos47, pos48, pos49, pos50)));
        boardPositions.addAll(new ArrayList<>(Arrays.asList
                (pos51, pos52, pos53, pos54, pos55, pos56)));
    }

    /**
     * Initiates the hashmap imageViewPositionHashMap. Gets the positions from gameViewModel
     * and connects them with the equivalent imageView. The first ones are for the positions
     * in the homes.
     */
    private void initPositionsHashmap() {
        imageViewPositionHashMap = new HashMap<>();
        List<Position> positionsModel = gameViewModel.getPositions();
        int nrOfHomePositions = gameViewModel.getPlayerCount() * 4;
        for (int i = 0; i < nrOfHomePositions; i++) {
            imageViewPositionHashMap.put(positionsModel.get(i), homePositions.get(i));
        }

        for (int i = nrOfHomePositions; i < positionsModel.size(); i++) {
            imageViewPositionHashMap.put(positionsModel.get(i), boardPositions.get(i - nrOfHomePositions));
        }
    }

    /**
     * Adds OnClickListeners on all pieces. When a piece is clicked, the method makeTurn
     * should be called. The pieces should be non-clickable when the method makeTurn is called.
     */
    private void addPiecesOnClickListeners() {
        for (ImageView piece : piecesImageViews) {
            piece.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setPiecesClickable(false);
                    makeTurn(piece);
                    setPiecesClickable(true);
                }
            });
        }
    }

    // TODO - some of the logic which is going to be implemented is right now just comments
    //  or not written here at all
    /**
     * Makes a turn with the parameter piece. First, it is moved in the model (if possible). If
     * it is not movable, the method is done here. Otherwise, the piece is moved in the view.
     * If the piece is finished it is removed from the model and view. If a player rolls a six
     * and is not finished, it is their turn again. Otherwise, the next player is selected.
     * If the game is finished, another method should be called here (not implemented yet).
     * If not, the dice in the view is moved to the next player and the dice´s value is
     * set to used.
     * @param piece is the piece which is about to move
     */
    private void makeTurn(ImageView piece) {
        boolean isMoved = gameViewModel.move(imageViewPieceHashMap.get(piece));
        if (isMoved) {
            move(piece);
            boolean playerIsFinished = removePieceAndPlayerIfFinished(piece);
            if (isNextPlayer(playerIsFinished)) {
                gameViewModel.selectNextPlayer();
                moveDice();
            }
            // check if game is finished --> finish...
            gameViewModel.diceIsUsed();
        }
    }

    /**
     * If a player rolls a six and is not finished, it is their turn again. Otherwise,
     * it is the next player´s turn.
     * @param playerIsFinished is true if the player is finished, otherwise false.
     * @return true if it is the next player´s turn, otherwise false.
     */
    private boolean isNextPlayer(boolean playerIsFinished) {
        return !((gameViewModel.getDiceValue() == 6) && !playerIsFinished);
    }

    /**
     * Should move the piece in the view (implementation not completed yet).
     * @param piece is the piece that should be moved.
     */
    private void move(ImageView piece) {
        //move piece in view, implementation not completed yet
        Position target = gameViewModel.getPosition(imageViewPieceHashMap.get(piece));
        moveImageView(piece, imageViewPositionHashMap.get(target));
    }

    /**
     * Checks if either the piece or the player is finished. If they are, they are removed
     * from the board.
     * @param piece is the piece that should be checked
     * @return true if the player has finished, and false otherwise
     */
    private boolean removePieceAndPlayerIfFinished(ImageView piece) {
        if (removePieceIfFinished(piece)) {
            return removePlayerIfFinished();
        }
        return false;
    }

    /**
     * If the selected piece is finished, it is removed in the model and also in the view (it is
     * made invisible).
     * @param piece is the piece that should be checked
     * @return true if the piece is finished, and false otherwise
     */
    private boolean removePieceIfFinished(ImageView piece) {
        if (pieceIsFinished(piece)) {
            piece.setVisibility(View.INVISIBLE);
            return true;
        }
        return false;
    }

    /**
     * Checks if piece is finished.
     * @param piece is the piece that is checked
     * @return true if the piece is finished, false otherwise.
     */
    private boolean pieceIsFinished(ImageView piece) {
        return gameViewModel.removePieceIfFinished(imageViewPieceHashMap.get(piece));
    }

    /**
     * Checks if the current player is finished.
     * @return true if the player is finished, false otherwise.
     */
    private boolean removePlayerIfFinished() {
        return gameViewModel.removePlayerIfFinished();
    }

    /**
     * Either sets all the pieces to clickable, or to non-clickable, depending on the param
     * @param isClickable is true if the pieces should be set to clickable, and
     *                    false if the pieces should be set to non-clickable
     */
    private void setPiecesClickable(Boolean isClickable) {
        for (ImageView piece : piecesImageViews) {
            piece.setClickable(isClickable);
        }
    }

    /**
     * Makes the first parameter, movingImageView, have the same constraints as the second
     * parameter, target. I.e. it moves the "movingImageView" to the same place as the "target".
     * @param movingImageView is the ImageView that should be moved
     * @param target is the place where the movingImageView should be moved to
     */
    private void moveImageView(ImageView movingImageView, ImageView target) {
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(constraintLayout);
        constraintSet.connect(
                movingImageView.getId(), ConstraintSet.START, target.getId(), ConstraintSet.START);
        constraintSet.connect(
                movingImageView.getId(), ConstraintSet.END, target.getId(), ConstraintSet.END);
        constraintSet.connect(
                movingImageView.getId(), ConstraintSet.TOP, target.getId(), ConstraintSet.TOP);
        constraintSet.connect(
                movingImageView.getId(), ConstraintSet.BOTTOM, target.getId(), ConstraintSet.BOTTOM);
        constraintSet.applyTo(constraintLayout);

        if (piecesImageViews.contains(movingImageView)) {
            //TODO a piece should have a margin in the bottom to make it look more real
        }

        movingImageView.bringToFront();
    }

}