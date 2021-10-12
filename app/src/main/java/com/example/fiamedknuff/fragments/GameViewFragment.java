package com.example.fiamedknuff.fragments;

import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.fiamedknuff.R;
import com.example.fiamedknuff.model.Player;
import com.example.fiamedknuff.viewModels.GameViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * UI controller for the game view layout.
 * @author Philip Winsnes
 */
public class GameViewFragment extends Fragment {

    private TextView player1Label, player2Label, player3Label, player4Label;
    private List<TextView> playerLabels = new ArrayList<>();

    private GameSideBarFragment sideBarFragment;
    private StandardboardFragment boardFragment;
    private DiceFragment diceFragment;

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    private GameViewModel gameViewModel;

    private ConstraintLayout gameViewConstraintLayout;
    private FrameLayout diceFrame;
    private FrameLayout boardFrame;

    private HashMap<String, ConstraintSet> playerDiceConstraintsHashMap;
    private ConstraintSet constraintSetDiceToPlayer1;
    private ConstraintSet constraintSetDiceToPlayer2;
    private ConstraintSet constraintSetDiceToPlayer3;
    private ConstraintSet constraintSetDiceToPlayer4;
    private List<ConstraintSet> constraintSets;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        gameViewModel = new ViewModelProvider(getActivity()).get(GameViewModel.class);
        View view = inflater.inflate(R.layout.fragment_game_view, container, false);
        gameViewConstraintLayout = view.findViewById(R.id.gameViewConstraintLayout);

        initLabels(view);
        populatePlayerLabelList();
        setLabelNames();
        initFrames(view);
        initFragments();
        createConstraintSetsDiceToPlayers();
        createListOfConstraintSets();
        initPlayerDiceConstraintsHashMap();
        initObservers();

        showAllFragments();

        return view;
    }

    private void initFrames(View view) {
        diceFrame = view.findViewById(R.id.diceFrame);
        boardFrame = view.findViewById(R.id.boardFrame);
    }

    private void initObservers() {
        gameViewModel.currentPlayer.observe(getActivity(), new Observer<>() {
            @Override
            public void onChanged(Player player) {
                // TODO move diceframe to current player

                ConstraintSet constraintSet = playerDiceConstraintsHashMap.get(player.getName());
                constraintSet.applyTo(gameViewConstraintLayout);
                diceFrame.bringToFront();
            }
        });
    }

    private void createConstraintSetsDiceToPlayers() {
        createConstraintSetDicePlayer1();
        createConstraintSetDicePlayer2();
        createConstraintSetDicePlayer3();
        createConstraintSetDicePlayer4();
    }

    private void createConstraintSetDicePlayer1() {
        constraintSetDiceToPlayer1 = new ConstraintSet();
        constraintSetDiceToPlayer1.clone(gameViewConstraintLayout);
        constraintSetDiceToPlayer1.connect(
                diceFrame.getId(), ConstraintSet.END, boardFrame.getId(), ConstraintSet.START);
        constraintSetDiceToPlayer1.connect(
                diceFrame.getId(), ConstraintSet.TOP, player1Label.getId(), ConstraintSet.BOTTOM);
        constraintSetDiceToPlayer1.connect(
                diceFrame.getId(), ConstraintSet.START, diceFrame.getId(), ConstraintSet.START);
        constraintSetDiceToPlayer1.connect(
                diceFrame.getId(), ConstraintSet.BOTTOM, diceFrame.getId(), ConstraintSet.BOTTOM);
        constraintSetDiceToPlayer1.applyTo(gameViewConstraintLayout);
    }

    private void createConstraintSetDicePlayer2() {
        constraintSetDiceToPlayer2 = new ConstraintSet();
        constraintSetDiceToPlayer2.clone(gameViewConstraintLayout);
        constraintSetDiceToPlayer2.connect(
                diceFrame.getId(), ConstraintSet.START, boardFrame.getId(), ConstraintSet.END);
        constraintSetDiceToPlayer2.connect(
                diceFrame.getId(), ConstraintSet.TOP, player2Label.getId(), ConstraintSet.BOTTOM);
        constraintSetDiceToPlayer2.connect(
                diceFrame.getId(), ConstraintSet.END, diceFrame.getId(), ConstraintSet.END);
        constraintSetDiceToPlayer2.connect(
                diceFrame.getId(), ConstraintSet.BOTTOM, diceFrame.getId(), ConstraintSet.BOTTOM);
        constraintSetDiceToPlayer2.applyTo(gameViewConstraintLayout);
    }

    private void createConstraintSetDicePlayer3() {
        constraintSetDiceToPlayer3 = new ConstraintSet();
        constraintSetDiceToPlayer3.clone(gameViewConstraintLayout);
        constraintSetDiceToPlayer3.connect(
                diceFrame.getId(), ConstraintSet.START, boardFrame.getId(), ConstraintSet.END);
        constraintSetDiceToPlayer3.connect(
                diceFrame.getId(), ConstraintSet.BOTTOM, player3Label.getId(), ConstraintSet.TOP);
        constraintSetDiceToPlayer3.connect(
                diceFrame.getId(), ConstraintSet.END, diceFrame.getId(), ConstraintSet.END);
        constraintSetDiceToPlayer3.connect(
                diceFrame.getId(), ConstraintSet.TOP, diceFrame.getId(), ConstraintSet.TOP);
        constraintSetDiceToPlayer3.applyTo(gameViewConstraintLayout);
    }

    private void createConstraintSetDicePlayer4() {
        constraintSetDiceToPlayer4 = new ConstraintSet();
        constraintSetDiceToPlayer4.clone(gameViewConstraintLayout);
        constraintSetDiceToPlayer4.connect(
                diceFrame.getId(), ConstraintSet.END, boardFrame.getId(), ConstraintSet.START);
        constraintSetDiceToPlayer4.connect(
                diceFrame.getId(), ConstraintSet.BOTTOM, player4Label.getId(), ConstraintSet.TOP);
        constraintSetDiceToPlayer4.connect(
                diceFrame.getId(), ConstraintSet.START, diceFrame.getId(), ConstraintSet.START);
        constraintSetDiceToPlayer4.connect(
                diceFrame.getId(), ConstraintSet.TOP, diceFrame.getId(), ConstraintSet.TOP);
        constraintSetDiceToPlayer4.applyTo(gameViewConstraintLayout);
    }

    private void createListOfConstraintSets() {
        constraintSets = new ArrayList<>();
        constraintSets.add(constraintSetDiceToPlayer1);
        constraintSets.add(constraintSetDiceToPlayer2);
        constraintSets.add(constraintSetDiceToPlayer3);
        constraintSets.add(constraintSetDiceToPlayer4);
    }

    private void initPlayerDiceConstraintsHashMap() {
        playerDiceConstraintsHashMap = new HashMap<>();
        for (int i = 0; i < playerLabels.size(); i++) {
            playerDiceConstraintsHashMap.put(playerLabels.get(i).getText().toString(), constraintSets.get(i));
        }
    }

    private void showAllFragments() {
        showFragment(R.id.boardFrame, boardFragment);
        showFragment(R.id.sideBarFrame, sideBarFragment);
        showFragment(R.id.diceFrame, diceFragment);
    }

    private void initLabels(View view) {
        player1Label = view.findViewById(R.id.player1Label);
        player2Label = view.findViewById(R.id.player2Label);
        player3Label = view.findViewById(R.id.player3Label);
        player4Label = view.findViewById(R.id.player4Label);
    }

    private void setLabelNames() {
        int players = gameViewModel.getPlayerCount();
        for (TextView label : playerLabels) {
            label.setVisibility(View.INVISIBLE);
        }
        for (int i = 0; i < players; i++) {
            playerLabels.get(i).setText(gameViewModel.getPlayerName(i).getValue());
            playerLabels.get(i).setVisibility(View.VISIBLE);
        }
    }

    private void populatePlayerLabelList() {
        playerLabels.add(player1Label);
        playerLabels.add(player2Label);
        playerLabels.add(player3Label);
        playerLabels.add(player4Label);
    }

    private void initFragments() {
        sideBarFragment = new GameSideBarFragment();
        boardFragment = new StandardboardFragment();
        diceFragment = new DiceFragment();
    }

    private <T extends Fragment> void showFragment(int frameLayoutId, T fragment) {
        fragmentManager = getChildFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(frameLayoutId, fragment);
        fragmentTransaction.commit();
    }

}