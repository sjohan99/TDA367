package com.example.fiamedknuff.fragments;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import com.example.fiamedknuff.R;
import com.example.fiamedknuff.model.Piece;
import com.example.fiamedknuff.viewModels.GameViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DiceFragment extends Fragment {

    View view;
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
        for (Map.Entry<Piece, ImageView> entry : getCurrentPlayersMovablePiecesImageViews().entrySet()) {
            // TODO: Change to something fancy
            entry.getValue().setBackgroundColor(R.drawable.background); // Highlight the movable piece
        }
    }

    /**
     * Removes the background of all piece's ImageView.
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void unMarkAllPieces() {
        imageViewPieceHashMap.forEach((imageView, piece) -> {
            imageView.setBackgroundColor(0); // Remove the background.
        });
    }

    /**
     * TODO Make more slim
     * Method used to help identify ImageViews for affecting pliancy on the current player's pieces.
     * @return Returns a HashMap with the current player's piece's ImageViews.
     */
    public HashMap<Piece, ImageView> getCurrentPlayersMovablePiecesImageViews() {
        // For each of the player's movable pieces, iterate through all pieces in the HashMap
        // and find the corresponding ImageView that is connected to the movable piece.
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

}
