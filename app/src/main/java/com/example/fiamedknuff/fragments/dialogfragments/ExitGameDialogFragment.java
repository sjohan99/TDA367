package com.example.fiamedknuff.fragments.dialogfragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.fiamedknuff.R;

/**
 * Responsibility: A DialogFragment container, designed for creating and hosting dialogs (popups).
 *
 * Used by: GameSideBarFragment
 * Uses: -
 *
 * Created by
 * @author Philip Winsnes
 */
public class ExitGameDialogFragment extends DialogFragment {

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
        setCancelable(true);
        return new AlertDialog.Builder(requireContext())
                .setView(R.layout.fragment_exit_game_dialog)
                .create();
    }


    //TODO write javadoc
    public static String TAG = "ExitGameDialogFragment";

}