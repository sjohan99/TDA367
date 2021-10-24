package com.example.fiamedknuff.fragments.gamefragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fiamedknuff.R;
import com.example.fiamedknuff.fragments.dialogfragments.PodiumDialogFragment;
import com.example.fiamedknuff.viewmodels.GameViewModel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


/**
 * Responsibility: UI controller for the game view layout.
 *
 * Used by: -
 * Uses: PodiumDialogFragment, Player, GameViewModel
 *
 * Created by
 * @author Philip Winsnes, Emma Stålberg, Johan Selin
 */
public class GameViewFragment extends Fragment {

    private boolean alreadyInitialized;
    private TextView player1Label, player2Label, player3Label, player4Label;
    private final List<TextView> playerLabels = new ArrayList<>();

    private GameSideBarFragment sideBarFragment;
    private BoardFragment boardFragment;
    private DiceFragment diceFragment;

    private GameViewModel gameViewModel;

    private ConstraintLayout gameViewConstraintLayout;
    private FrameLayout diceFrame;

    private ImageView spacePlayer1Dice;
    private ImageView spacePlayer2Dice;
    private ImageView spacePlayer3Dice;
    private ImageView spacePlayer4Dice;
    private Map<String, ImageView> playerToDicespaceHashMap;
    private List<ImageView> diceSpaces;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        System.out.println("GMF onCreateView");
        gameViewModel = new ViewModelProvider(requireActivity()).get(GameViewModel.class);
        View view = inflater.inflate(R.layout.fragment_game_view, container, false);
        gameViewConstraintLayout = view.findViewById(R.id.gameViewConstraintLayout);

        if (!alreadyInitialized) {
            initLabels(view);
            initFrames(view);
            initDiceSpaces(view);
            populatePlayerLabelList();
            setLabelNames();
            initFragments();
            initDiceSpacesList();
            initPlayerToDicespaceHashMap();
            initObservers();
            alreadyInitialized = true;
            showAllFragments();
        }

        reInitDice();

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //gameViewModel = new ViewModelProvider(getActivity()).get(GameViewModel.class);
        alreadyInitialized = false;
    }

    private void initFrames(View view) {
        diceFrame = view.findViewById(R.id.diceFrame);
    }

    private void initObservers() {
        gameViewModel.currentPlayer.observe(requireActivity(), player -> {
            ImageView target = playerToDicespaceHashMap.get(player.getName());
            moveDice(Objects.requireNonNull(target));
        });
    }

    private void reInitDice() {
        ImageView target = playerToDicespaceHashMap.get(gameViewModel.getCurrentPlayerName());
        moveDice(Objects.requireNonNull(target));
    }

    private void moveDice(ImageView target) {
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(gameViewConstraintLayout);
        constraintSet.connect(
                diceFrame.getId(), ConstraintSet.START, target.getId(), ConstraintSet.START);
        constraintSet.connect(
                diceFrame.getId(), ConstraintSet.END, target.getId(), ConstraintSet.END);
        constraintSet.connect(
                diceFrame.getId(), ConstraintSet.TOP, target.getId(), ConstraintSet.TOP);
        constraintSet.connect(
                diceFrame.getId(), ConstraintSet.BOTTOM, target.getId(), ConstraintSet.BOTTOM);
        constraintSet.applyTo(gameViewConstraintLayout);

        diceFrame.bringToFront();
    }

    private void initDiceSpaces(View view) {
        spacePlayer1Dice = view.findViewById(R.id.spacePlayer1Dice);
        spacePlayer2Dice = view.findViewById(R.id.spacePlayer2Dice);
        spacePlayer3Dice = view.findViewById(R.id.spacePlayer3Dice);
        spacePlayer4Dice = view.findViewById(R.id.spacePlayer4Dice);
    }

    private void initDiceSpacesList() {
        diceSpaces = new ArrayList<>();
        diceSpaces.add(spacePlayer1Dice);
        diceSpaces.add(spacePlayer2Dice);
        diceSpaces.add(spacePlayer3Dice);
        diceSpaces.add(spacePlayer4Dice);
    }

    private void initPlayerToDicespaceHashMap() {
        playerToDicespaceHashMap = new HashMap<>();
        for (int i = 0; i < playerLabels.size(); i++) {
            playerToDicespaceHashMap.put(playerLabels.get(i).getText().toString(), diceSpaces.get(i));
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
        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(frameLayoutId, fragment);
        fragmentTransaction.commit();
    }

    /**
     * Displays a podium dialog by showing a ReplayDialogFragment.
     * @param players is the list of players in a winning order
     */
    public void showPodiumDialog(List<String> players) {
        // TODO: 2021-10-15 Do something with the list
        new PodiumDialogFragment().show(getChildFragmentManager(), PodiumDialogFragment.TAG);
    }

}