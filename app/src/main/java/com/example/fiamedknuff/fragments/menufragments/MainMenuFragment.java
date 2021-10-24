package com.example.fiamedknuff.fragments.menufragments;

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

/**
 * Responsibility: A class MainMenuFragment that handles the GUI for the main menu.
 *
 * Used by: -
 * Uses: -
 *
 * Created by
 * @author Philip Winsnes, Hanna Boquist, Amanda Cyrén, Emma Stålberg, Johan Selin
 */
public class MainMenuFragment extends Fragment {

    private NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        navController = NavHostFragment.findNavController(this);

        View view = inflater.inflate(R.layout.fragment_main_menu, container, false);

        Button newGameBtn = view.findViewById(R.id.newGameBtn);
        Button leaderboardBtn = view.findViewById(R.id.leaderboardBtn);
        Button rulebookBtn = view.findViewById(R.id.rulebookBtn);

        newGameBtn.setOnClickListener(view1 -> navController.navigate(
                R.id.action_mainMenuFragment_to_gameSetupFragment,
                null,
                new NavOptions.Builder()
                        .setEnterAnim(android.R.animator.fade_in)
                        .setExitAnim(android.R.animator.fade_out)
                        .build()
        ));

        leaderboardBtn.setOnClickListener(view12 ->
                navController.navigate(R.id.action_mainMenuFragment_to_leaderboardFragment,
                null,
                new NavOptions.Builder()
                        .setEnterAnim(android.R.animator.fade_in)
                        .setExitAnim(android.R.animator.fade_out)
                        .build()
        ));

        rulebookBtn.setOnClickListener(view13 -> navController.navigate(
                R.id.action_mainMenuFragment_to_rulebookFragment,
                null,
                new NavOptions.Builder()
                        .setEnterAnim(android.R.animator.fade_in)
                        .setExitAnim(android.R.animator.fade_out)
                        .build()
        ));

        return view;
    }
}