package com.example.fiamedknuff.fragments;

import android.os.Bundle;
import static android.content.ContentValues.TAG;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.fiamedknuff.R;

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

    ImageView yellowpiece1;
    ConstraintLayout constraintLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_standardboard, container, false);

        initPositions();
        initPieces();
        testingPieceMovement();

        return view;
    }

    private void initPieces() {
        yellowpiece1 = view.findViewById(R.id.yellowpiece1);

    }


    private void testingPieceMovement() {
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
    }

    private void initPositions() {
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
        pos39 = view.findViewById(R.id.pos28);
        pos0 = view.findViewById(R.id.pos40);
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
        pos39 = view.findViewById(R.id.pos28);
        pos0 = view.findViewById(R.id.pos40);
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

}