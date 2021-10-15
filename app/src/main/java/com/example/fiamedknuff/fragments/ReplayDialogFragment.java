package com.example.fiamedknuff.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fiamedknuff.R;

/**
 * A DialogFragment, designed for creating and hosting dialogs (popups).
 * @author Philip Winsnes
 */
public class ReplayDialogFragment extends DialogFragment {

    /**
     * Creates a Dialog to display as part of the DialogFragment.
     * The DialogFragment handles displaying the Dialog at appropriate
     * states in the fragment's lifecycle.
     * @param savedInstanceState
     * @return
     */
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return new AlertDialog.Builder(requireContext())
                .setMessage(getString(R.string.app_name))
                .setPositiveButton(getString(R.string.popup_proceed_option), (dialog, which) -> {
                })
                .create();
    }

    public static String TAG = "PurchaseConfirmationDialog";

}