package com.example.fiamedknuff.fragments;

import androidx.constraintlayout.widget.ConstraintLayout;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.fiamedknuff.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A class standardboardFragment that handles the view of the standardboard and its
 * pieces and positions.
 *
 * Created by
 * @author Emma Stålberg
 */

public class StandardboardFragment extends BoardFragment {
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

    List<ImageView> piecesImageViews;

    ConstraintLayout constraintLayout;

    protected void setView(LayoutInflater inflater, ViewGroup container) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_standardboard, container, false);
    }

    protected void setConstraintLayout() {
        constraintLayout = view.findViewById(R.id.sbConstraintLayout);
    }

    protected List<ImageView> getListOfPiecesImageViews() {
        return piecesImageViews;
    }

    protected List<ImageView> getListOfBoardPositions() {
        return boardPositions;
    }

    protected List<ImageView> getListOfHomePositions() {
        return homePositions;
    }

    protected ConstraintLayout getConstraintLayout() {
        return constraintLayout;
    }

    /**
     * Initiates the list piecesImageViews with all ImageViews of the pieces.
     * The first four ones are the pieces that belongs to player 1.
     * Then comes the pieces for player 2, and so on.
     */
    protected void initListOfAllPieces() {
        piecesImageViews = new ArrayList<>();
        piecesImageViews.add(view.findViewById(R.id.yellowpiece0));
        piecesImageViews.add(view.findViewById(R.id.yellowpiece1));
        piecesImageViews.add(view.findViewById(R.id.yellowpiece2));
        piecesImageViews.add(view.findViewById(R.id.yellowpiece3));
        piecesImageViews.add(view.findViewById(R.id.redpiece0));
        piecesImageViews.add(view.findViewById(R.id.redpiece1));
        piecesImageViews.add(view.findViewById(R.id.redpiece2));
        piecesImageViews.add(view.findViewById(R.id.redpiece3));
        piecesImageViews.add(view.findViewById(R.id.greenpiece0));
        piecesImageViews.add(view.findViewById(R.id.greenpiece1));
        piecesImageViews.add(view.findViewById(R.id.greenpiece2));
        piecesImageViews.add(view.findViewById(R.id.greenpiece3));
        piecesImageViews.add(view.findViewById(R.id.bluepiece0));
        piecesImageViews.add(view.findViewById(R.id.bluepiece1));
        piecesImageViews.add(view.findViewById(R.id.bluepiece2));
        piecesImageViews.add(view.findViewById(R.id.bluepiece3));
    }

    /**
     * Connects every position on the board to its equivalent ImageView
     */
    protected void connectBoardPositionsIds() {
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
     * Initiates the List with all positions in the homes.
     */
    protected void initListOfHomePositions() {
        homePositions = new ArrayList<>();


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
     * Initiates the List with all positions on the board.
     */
    protected void initListOfBoardPositions() {
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

}