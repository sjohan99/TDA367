package com.example.fiamedknuff.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.fiamedknuff.R;
import com.example.fiamedknuff.viewModels.GameViewModel;

/**
 * UI controller for the game view layout.
 * @author Philip Winsnes
 */
public class GameViewFragment extends Fragment {

    private TextView player1Label, player2Label, player3Label, player4Label;

    private GameSideBarFragment sideBarFragment;
    private StandardboardFragment boardFragment;

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    private GameViewModel gameViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        gameViewModel = new ViewModelProvider(getActivity()).get(GameViewModel.class);
        View view = inflater.inflate(R.layout.fragment_game_view, container, false);


        initLabels(view);
        initFragments();

        showFragment(R.id.boardFrame, boardFragment);
        showFragment(R.id.sideBarFrame, sideBarFragment);

        return view;
    }

    private void initLabels(View view) {
        player1Label = view.findViewById(R.id.player1Label);
        player2Label = view.findViewById(R.id.player2Label);
        player3Label = view.findViewById(R.id.player3Label);
        player4Label = view.findViewById(R.id.player4Label);

        player1Label.setText(gameViewModel.getPlayerName(0).getValue());
        player2Label.setText(gameViewModel.getPlayerName(1).getValue());
        player3Label.setText(gameViewModel.getPlayerName(2).getValue());
        player4Label.setText(gameViewModel.getPlayerName(3).getValue());
    }

    private void initFragments() {
        sideBarFragment = new GameSideBarFragment();
        boardFragment = new StandardboardFragment();
    }

    private <T extends Fragment> void showFragment(int frameLayoutId, T fragment) {
        fragmentManager = getChildFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(frameLayoutId, fragment);
        fragmentTransaction.commit();
    }

}