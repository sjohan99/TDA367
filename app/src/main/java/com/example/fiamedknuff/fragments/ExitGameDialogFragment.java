package com.example.fiamedknuff.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.fragment.NavHostFragment;

import com.example.fiamedknuff.R;

/**
 * A DialogFragment container, designed for creating and hosting dialogs (popups).
 * For more information: <a href="https://developer.android.com/guide/fragments/dialogs"> Displaying dialogs with DialogFragment </a>
 * @author Philip Winsnes
 */
public class ExitGameDialogFragment extends DialogFragment {

    private NavController navController;

    /**
     * Creates a Dialog to display as part of the DialogFragment.
     * The DialogFragment handles displaying the Dialog at appropriate
     * states in the fragment's lifecycle.
     * @param savedInstanceState is the savedInstanceState
     * @return a new AlertDialog
     */
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        navController = NavHostFragment.findNavController(this);
        setCancelable(true);
        return new AlertDialog.Builder(requireContext())
                .setMessage(getString(R.string.exit_game_popup_message))
                .setPositiveButton(getString(R.string.popup_cancel_option), (dialog, which) -> {})
                .setNeutralButton(getString(R.string.popup_proceed_option), this::onClick)
                .create();
    }

    public static String TAG = "ExitGameDialogFragment";

    private void onClick(DialogInterface dialog, int which) {
        // TODO: 2021-10-15 Add more return logic if necessary
        navController.navigate(R.id.action_gameView_to_mainMenuFragment,
                null,
                new NavOptions.Builder()
            .setEnterAnim(android.R.animator.fade_in)
            .setExitAnim(android.R.animator.fade_out)
            .build()
        );
    }

}