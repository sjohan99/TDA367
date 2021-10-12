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

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * UI controller for the game view layout.
 * @author Philip Winsnes
 */
public class GameViewFragment extends Fragment {

    private TextView player1Label, player2Label, player3Label, player4Label;
    private ArrayList<TextView> playerLabels = new ArrayList<>();

    private GameSideBarFragment sideBarFragment;
    private StandardboardFragment boardFragment;
    private DiceFragment diceFragment;

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    private GameViewModel gameViewModel;

    private ConstraintLayout gameViewConstraintLayout;
    private FrameLayout diceFrame;
    private FrameLayout boardFrame;

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
                if (player.getName().equals(player2Label.getText().toString())) {
                    ConstraintSet constraintSet = new ConstraintSet();
                    constraintSet.clone(gameViewConstraintLayout);
                    constraintSet.connect(
                            diceFrame.getId(), ConstraintSet.START, boardFrame.getId(), ConstraintSet.START);
                    constraintSet.connect(
                            diceFrame.getId(), ConstraintSet.END, boardFrame.getId(), ConstraintSet.END);
                    constraintSet.connect(
                            diceFrame.getId(), ConstraintSet.TOP, boardFrame.getId(), ConstraintSet.TOP);
                    constraintSet.connect(
                            diceFrame.getId(), ConstraintSet.BOTTOM, boardFrame.getId(), ConstraintSet.BOTTOM);
                    constraintSet.applyTo(gameViewConstraintLayout);
                    diceFrame.bringToFront();                }
            }
        });
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