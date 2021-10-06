package com.example.fiamedknuff.fragments;

import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.fiamedknuff.R;
import com.example.fiamedknuff.model.Piece;
import com.example.fiamedknuff.model.Position;
import com.example.fiamedknuff.viewModels.GameViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * A class standardboardFragment that ...
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
    List<ImageView> positions;
    HashMap<ImageView, Position> imageViewPositionHashMap;

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
                }
            }
        });
    }

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
        bluepiece0 = view.findViewById(R.id.bluepiece0);
        bluepiece1 = view.findViewById(R.id.bluepiece1);
        bluepiece2 = view.findViewById(R.id.bluepiece2);
        bluepiece3 = view.findViewById(R.id.bluepiece3);
        greenpiece0 = view.findViewById(R.id.greenpiece0);
        greenpiece1 = view.findViewById(R.id.greenpiece1);
        greenpiece2 = view.findViewById(R.id.greenpiece2);
        greenpiece3 = view.findViewById(R.id.greenpiece3);
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
                bluepiece0, bluepiece1, bluepiece2, bluepiece3)));
        piecesImageViews.addAll(new ArrayList<>(Arrays.asList(
                greenpiece0, greenpiece1, greenpiece2, greenpiece3)));
    }

    // TODO off by 1?
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

    // TODO off by 1?
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
        initListOfPositions();
        initPositionsHashmap();
    }

    // TODO off by 1?
    /**
     * Initiates the hashmap imageViewPositionHashMap. Gets the positions from gameViewModel
     * and connects them with the equivalent imageView.
     */
    private void initPositionsHashmap() {
        imageViewPositionHashMap = new HashMap<>();
        List<Position> positionsModel = gameViewModel.getPositions();
        for (int i = 0; i < positionsModel.size(); i++) {
            imageViewPositionHashMap.put(positions.get(i), positionsModel.get(i));
        }
    }

    /**
     * Connects every position to its equivalent ImageView.
     */
    private void connectPositionIds() {
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
     * Initiates the List with all positions.
     */
    private void initListOfPositions() {
        positions = new ArrayList<>();

        positions.addAll(new ArrayList<>(Arrays.asList
                (pos0, pos1, pos2, pos3, pos4, pos5, pos6, pos7, pos8, pos9, pos10)));
        positions.addAll(new ArrayList<>(Arrays.asList
                (pos11, pos12, pos13, pos14, pos15, pos16, pos17, pos18, pos19, pos20)));
        positions.addAll(new ArrayList<>(Arrays.asList
                (pos21, pos22, pos23, pos24, pos25, pos26, pos27, pos28, pos29, pos30)));
        positions.addAll(new ArrayList<>(Arrays.asList
                (pos31, pos32, pos33, pos34, pos35, pos36, pos37, pos38, pos39, pos40)));
        positions.addAll(new ArrayList<>(Arrays.asList
                (pos41, pos42, pos43, pos44, pos45, pos46, pos47, pos48, pos49, pos50)));
        positions.addAll(new ArrayList<>(Arrays.asList
                (pos51, pos52, pos53, pos54, pos55, pos56)));
    }

    /**
     * Adds OnClickListeners on all pieces. When a piece is clicked, the method pieceClicked in
     * gameViewModel should be called.
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

    //
    private void makeTurn(ImageView piece) {
        boolean isMoved = gameViewModel.move(imageViewPieceHashMap.get(piece));
        if (isMoved) {
            // move in view
            boolean playerIsFinished = removePieceAndPlayerIfFinished(piece);
            // select next player ... se to do document

        }
    }

    private boolean removePieceAndPlayerIfFinished(ImageView piece) {
        if (removePieceIfFinished(piece)) {
            return removePlayerIfFinished();
        }
        return false;
    }

    private boolean removePieceIfFinished(ImageView piece) {
        if (pieceIsFinished(piece)) {
            // remove piece from the view
            return true;
        }
        return false;
    }

    private boolean pieceIsFinished(ImageView piece) {
        return gameViewModel.removePieceIfFinished(imageViewPieceHashMap.get(piece));
    }

    private boolean removePlayerIfFinished() {
        if (gameViewModel.removePlayerIfFinished()) {
            // remove player from view
            return true;
        }
        return false;
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

    /*private void testingPieceMovement() {
    constraintLayout = view.findViewById(R.id.constraintLayout);

    yellowpiece1.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(constraintLayout);
            constraintSet.connect(yellowpiece1.getId(), ConstraintSet.START, pos10.getId(), ConstraintSet.START);
            constraintSet.connect(yellowpiece1.getId(), ConstraintSet.END, pos10.getId(), ConstraintSet.END);
            constraintSet.connect(yellowpiece1.getId(), ConstraintSet.TOP, pos10.getId(), ConstraintSet.TOP);
            constraintSet.connect(yellowpiece1.getId(), ConstraintSet.BOTTOM, pos10.getId(), ConstraintSet.BOTTOM);
            constraintSet.applyTo(constraintLayout);

            yellowpiece1.bringToFront();

        }
    });
    }*/

}