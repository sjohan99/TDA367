package com.example.fiamedknuff.fragments.dialogfragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

/**
 * Responsibility: A DialogFragment container, designed for creating and hosting dialogs (popups).
 *
 * Used by: GameViewFragment
 * Uses: -
 *
 * Created by
 * @author Philip Winsnes
 */
public class PodiumDialogFragment extends DialogFragment {

    /**
     * Creates a Dialog to display as part of the DialogFragment.
     * The DialogFragment handles displaying the Dialog at appropriate
     * states in the fragment's lifecycle.
     *
     * @param savedInstanceState is the savedInstanceState
     * @return a new AlertDialog
     */
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        setCancelable(true);
        return new AlertDialog.Builder(requireContext())
                // TODO: 2021-10-15 Add more functionality
                .create();
    }

    public static String TAG = "PodiumDialogFragment";

}