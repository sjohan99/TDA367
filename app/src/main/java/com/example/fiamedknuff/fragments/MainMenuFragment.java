package com.example.fiamedknuff.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.fiamedknuff.R;

public class MainMenuFragment extends Fragment {

    NavController navController;
    Button newGameBtn, leaderboardBtn, rulebookBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        navController = NavHostFragment.findNavController(this);

        View view = inflater.inflate(R.layout.fragment_main_menu, container, false);

        newGameBtn = view.findViewById(R.id.newGameBtn);
        leaderboardBtn = view.findViewById(R.id.leaderboardBtn);
        rulebookBtn = view.findViewById(R.id.rulebookBtn);

        newGameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO

                navController.navigate(
                        R.id.action_mainMenuFragment_to_standardboardFragment,
                        null,
                        new NavOptions.Builder()
                                .setEnterAnim(android.R.animator.fade_in)
                                .setExitAnim(android.R.animator.fade_out)
                                .build()
                );

            }
        });

        leaderboardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_mainMenuFragment_to_leaderboardFragment,
                        null,
                        new NavOptions.Builder()
                                .setEnterAnim(android.R.animator.fade_in)
                                .setExitAnim(android.R.animator.fade_out)
                                .build()
                );
            }
        });

        rulebookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(
                        R.id.action_mainMenuFragment_to_rulebookFragment,
                        null,
                        new NavOptions.Builder()
                                .setEnterAnim(android.R.animator.fade_in)
                                .setExitAnim(android.R.animator.fade_out)
                                .build()
                );
            }
        });

        return view;
    }
}