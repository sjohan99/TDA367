package com.example.fiamedknuff.fragments;

import android.graphics.drawable.Drawable;
import android.media.Image;
import android.media.MediaPlayer;
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
 * UI controller for the side bar layout.
 * @author Philip Winsnes
 */

public class GameSideBarFragment extends Fragment {

    private NavController navController;
    private Button homeBtn, settingsBtn, rulebookBtn, soundBtn, replayBtn;

    private MediaPlayer mediaPlayer;
    private boolean soundMuted;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        navController = NavHostFragment.findNavController(this);

        View view = inflater.inflate(R.layout.fragment_game_side_bar, container, false);

        initButtons(view);
        specifyOnClickActions();

        initBackgroundMusic();

        return view;
    }

    private void initBackgroundMusic() {
        mediaPlayer = MediaPlayer.create(getActivity(), R.raw.music);
        mediaPlayer.start();
    }

    private void specifyOnClickActions() {
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO
            }
        });

        settingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO
            }
        });

        rulebookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO
                navController.navigate(
                        R.id.action_gameView_to_RulebookFragment,
                        null,
                        new NavOptions.Builder()
                                .setEnterAnim(android.R.animator.fade_in)
                                .setExitAnim(android.R.animator.fade_out)
                                .build()
                );
            }
        });

        soundBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (soundMuted) {
                    soundBtn.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_mute_btn, 0, 0);
                }
                else { soundBtn.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_mute2_btn, 0, 0);
                }
                soundMuted = !soundMuted;
            }
        });

        replayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO
            }
        });
    }

    private void initButtons(View view) {
        homeBtn = view.findViewById(R.id.homeBtn);
        settingsBtn = view.findViewById(R.id.settingsBtn);
        rulebookBtn = view.findViewById(R.id.rulebookBtn2);
        soundBtn = view.findViewById(R.id.soundBtn);
        replayBtn = view.findViewById(R.id.replayBtn);
    }
}