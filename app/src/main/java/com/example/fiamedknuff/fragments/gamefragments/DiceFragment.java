package com.example.fiamedknuff.fragments.gamefragments;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.fiamedknuff.R;
import com.example.fiamedknuff.viewModels.GameViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Responsibility: A class DiceFragment that handles the dice visually.
 *
 * Used by: GameViewFragment
 * Uses: GameViewModel
 *
 * Created by
 * @author Emma Stålberg, Amanda Cyrén, Hanna Boquist
 */
public class DiceFragment extends Fragment {

    View view;
    GameViewModel gameViewModel;

    ImageView diceImage;
    List<Integer> diceImages;
    private boolean alreadyInitialized;
    private Animation rotationAnimation;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_dice, container, false);

        gameViewModel = new ViewModelProvider(getActivity()).get(GameViewModel.class);

        if (!alreadyInitialized) {
            initDiceImages();
            initObservers();
            alreadyInitialized = true;
        }

        initDice();
        reInitDiceImageValue();
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        alreadyInitialized = false;
    }

    private void reInitDiceImageValue() {
        setDiceImage(gameViewModel.getDiceValue());
    }

    private void initDice() {
        diceImage = view.findViewById(R.id.diceImage);
        diceImage.setOnClickListener(view -> rollDice());
        initDiceAnimationListener();
    }

    private void initObservers() {
        gameViewModel.CPUDiceRoll.observe(getActivity(), aBoolean -> {
            if (aBoolean) {
                rollDice();
            }
        });
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

    private void rollDice() {
        int rolledValue = gameViewModel.rollDice();

        // If the dice is rolled, the dice should be rotated and
        // the method diceRolled should be called.
        if (rolledValue != -1) {
            rotateDice(rolledValue);
        }
    }

    private void initDiceAnimationListener() {
        rotationAnimation = AnimationUtils.loadAnimation(requireActivity().getApplicationContext(), R.anim.rotate);
        rotationAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                gameViewModel.diceRolled();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    /**
     * Method that rotates the dice and sets the new rolled value.
     * @param rolledValue is the rolled value
     */
    private void rotateDice(int rolledValue) {
        diceImage.startAnimation(rotationAnimation); // animate the roll of the dice
        setDiceImage(rolledValue); // sets the rolled value
    }

    private void setDiceImage(int roll) {
        try {
            diceImage.setImageResource(diceImages.get(roll - 1));
        } catch (IndexOutOfBoundsException e) {
            // FIXME: 2021-10-16 This error gets triggered when starting the game and possibly other times as well, investigate
            Log.d(TAG, "setDiceImage: encountered error: \n" + Arrays.toString(e.getStackTrace()));
            System.out.println("setDiceImage: encountered error: \n" + Arrays.toString(e.getStackTrace()));
            diceImage.setImageResource(diceImages.get(0));
        }
    }
}
