package com.example.fiamedknuff.fragments.gamefragments;

import androidx.constraintlayout.widget.ConstraintLayout;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.fiamedknuff.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A class standardboardFragment that handles the view of the standardboard and its
 * pieces and positions.
 *
 * Created by
 * @author Emma Stålberg
 */

public class StandardboardFragment extends BoardFragment {
    List<ImageView> boardPositions;
    List<ImageView> homePositions;
    List<ImageView> piecesImageViews;

    ConstraintLayout constraintLayout;

    @Override
    protected void setView(LayoutInflater inflater, ViewGroup container) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_standardboard, container, false);
    }

    @Override
    protected void setConstraintLayout() {
        constraintLayout = view.findViewById(R.id.sbConstraintLayout);
    }

    @Override
    protected List<ImageView> getListOfPiecesImageViews() {
        return piecesImageViews;
    }

    @Override
    protected List<ImageView> getListOfBoardPositions() {
        return boardPositions;
    }

    @Override
    protected List<ImageView> getListOfHomePositions() {
        return homePositions;
    }

    @Override
    protected ConstraintLayout getConstraintLayout() {
        return constraintLayout;
    }

    /**
     * Initiates the list piecesImageViews with all ImageViews of the pieces.
     * The first four ones are the pieces that belongs to player 1.
     * Then comes the pieces for player 2, and so on.
     */
    @Override
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
     * Initiates the List with all positions in the homes.
     * The first four ones belongs to player 1.
     * Then comes the home positions for player 2, and so on.
     */
    @Override
    protected void initListOfHomePositions() {
        homePositions = new ArrayList<>();

        homePositions.add(view.findViewById(R.id.yellowHomepos0));
        homePositions.add(view.findViewById(R.id.yellowHomepos1));
        homePositions.add(view.findViewById(R.id.yellowHomepos2));
        homePositions.add(view.findViewById(R.id.yellowHomepos3));

        homePositions.add(view.findViewById(R.id.redHomepos0));
        homePositions.add(view.findViewById(R.id.redHomepos1));
        homePositions.add(view.findViewById(R.id.redHomepos2));
        homePositions.add(view.findViewById(R.id.redHomepos3));

        homePositions.add(view.findViewById(R.id.greenHomepos0));
        homePositions.add(view.findViewById(R.id.greenHomepos1));
        homePositions.add(view.findViewById(R.id.greenHomepos2));
        homePositions.add(view.findViewById(R.id.greenHomepos3));

        homePositions.add(view.findViewById(R.id.blueHomepos0));
        homePositions.add(view.findViewById(R.id.blueHomepos1));
        homePositions.add(view.findViewById(R.id.blueHomepos2));
        homePositions.add(view.findViewById(R.id.blueHomepos3));
    }

    /**
     * Initiates the List with all positions on the board.
     */
    @Override
    protected void initListOfBoardPositions() {
        boardPositions = new ArrayList<>();

        boardPositions.add(view.findViewById(R.id.pos0));
        boardPositions.add(view.findViewById(R.id.pos1));
        boardPositions.add(view.findViewById(R.id.pos2));
        boardPositions.add(view.findViewById(R.id.pos3));
        boardPositions.add(view.findViewById(R.id.pos4));
        boardPositions.add(view.findViewById(R.id.pos5));
        boardPositions.add(view.findViewById(R.id.pos6));
        boardPositions.add(view.findViewById(R.id.pos7));
        boardPositions.add(view.findViewById(R.id.pos8));
        boardPositions.add(view.findViewById(R.id.pos9));
        boardPositions.add(view.findViewById(R.id.pos10));
        boardPositions.add(view.findViewById(R.id.pos11));
        boardPositions.add(view.findViewById(R.id.pos12));
        boardPositions.add(view.findViewById(R.id.pos13));
        boardPositions.add(view.findViewById(R.id.pos14));
        boardPositions.add(view.findViewById(R.id.pos15));
        boardPositions.add(view.findViewById(R.id.pos16));
        boardPositions.add(view.findViewById(R.id.pos17));
        boardPositions.add(view.findViewById(R.id.pos18));
        boardPositions.add(view.findViewById(R.id.pos19));
        boardPositions.add(view.findViewById(R.id.pos20));
        boardPositions.add(view.findViewById(R.id.pos21));
        boardPositions.add(view.findViewById(R.id.pos22));
        boardPositions.add(view.findViewById(R.id.pos23));
        boardPositions.add(view.findViewById(R.id.pos24));
        boardPositions.add(view.findViewById(R.id.pos25));
        boardPositions.add(view.findViewById(R.id.pos26));
        boardPositions.add(view.findViewById(R.id.pos27));
        boardPositions.add(view.findViewById(R.id.pos28));
        boardPositions.add(view.findViewById(R.id.pos29));
        boardPositions.add(view.findViewById(R.id.pos30));
        boardPositions.add(view.findViewById(R.id.pos31));
        boardPositions.add(view.findViewById(R.id.pos32));
        boardPositions.add(view.findViewById(R.id.pos33));
        boardPositions.add(view.findViewById(R.id.pos34));
        boardPositions.add(view.findViewById(R.id.pos35));
        boardPositions.add(view.findViewById(R.id.pos36));
        boardPositions.add(view.findViewById(R.id.pos37));
        boardPositions.add(view.findViewById(R.id.pos38));
        boardPositions.add(view.findViewById(R.id.pos39));
        boardPositions.add(view.findViewById(R.id.pos40));
        boardPositions.add(view.findViewById(R.id.pos41));
        boardPositions.add(view.findViewById(R.id.pos42));
        boardPositions.add(view.findViewById(R.id.pos43));
        boardPositions.add(view.findViewById(R.id.pos44));
        boardPositions.add(view.findViewById(R.id.pos45));
        boardPositions.add(view.findViewById(R.id.pos46));
        boardPositions.add(view.findViewById(R.id.pos47));
        boardPositions.add(view.findViewById(R.id.pos48));
        boardPositions.add(view.findViewById(R.id.pos49));
        boardPositions.add(view.findViewById(R.id.pos50));
        boardPositions.add(view.findViewById(R.id.pos51));
        boardPositions.add(view.findViewById(R.id.pos52));
        boardPositions.add(view.findViewById(R.id.pos53));
        boardPositions.add(view.findViewById(R.id.pos54));
        boardPositions.add(view.findViewById(R.id.pos55));
        boardPositions.add(view.findViewById(R.id.pos56));
    }

}