package com.example.fiamedknuff.fragments.gamefragments;

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
import com.example.fiamedknuff.fragments.dialogfragments.ExitGameDialogFragment;
import com.example.fiamedknuff.fragments.dialogfragments.ReplayDialogFragment;

/**
 * Responsibility: UI controller for the side bar layout.
 *
 * Used by: GameViewFrament
 * Uses: ExitGameDialogFragment, ReplayDialogFragment
 *
 * Created by
 * @author Philip Winsnes
 */
public class GameSideBarFragment extends Fragment {

    private NavController navController;
    private Button homeBtn, settingsBtn, rulebookBtn, soundBtn, replayBtn;

    private MediaPlayer mediaPlayer;
    private boolean soundMuted;

    //TODO javadoc
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_game_side_bar, container, false);

        navController = NavHostFragment.findNavController(this);

        initButtons(view);
        specifyOnClickActions();

        initBackgroundMusic();

        return view;
    }

    private void initBackgroundMusic() {
        mediaPlayer = MediaPlayer.create(getActivity(), R.raw.music);
        mediaPlayer.start();
        mediaPlayer.setLooping(true);
    }

    private void specifyOnClickActions() {
        homeBtn.setOnClickListener(view -> new ExitGameDialogFragment().show(
                getChildFragmentManager(), ExitGameDialogFragment.TAG));

        settingsBtn.setOnClickListener(view -> {
            // TODO
        });

        rulebookBtn.setOnClickListener(view -> {
            // TODO
            navController.navigate(
                    R.id.action_gameView_to_RulebookFragment,
                    null,
                    new NavOptions.Builder()
                            .setEnterAnim(android.R.animator.fade_in)
                            .setExitAnim(android.R.animator.fade_out)
                            .build()
            );
        });

        soundBtn.setOnClickListener(view -> {
            if (soundMuted) {
                initBackgroundMusic();
                soundBtn.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_mute_btn, 0, 0);
            }
            else {
                mediaPlayer.stop();
                soundBtn.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_mute2_btn, 0, 0);
            }
            soundMuted = !soundMuted;
        });

        replayBtn.setOnClickListener(view -> new ReplayDialogFragment().show(
                getChildFragmentManager(), ReplayDialogFragment.TAG));

    }

    private void initButtons(View view) {
        homeBtn = view.findViewById(R.id.homeBtn);
        settingsBtn = view.findViewById(R.id.settingsBtn);
        rulebookBtn = view.findViewById(R.id.rulebookBtn2);
        soundBtn = view.findViewById(R.id.soundBtn);
        replayBtn = view.findViewById(R.id.replayBtn);
    }

}