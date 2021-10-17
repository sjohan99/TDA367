package com.example.fiamedknuff.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * TODO write better comment
 * An abstract class BoardFragment. Superclass to our fragments of boards.
 *
 * Created by
 * @author Emma Stålberg
 */
public abstract class BoardFragment extends Fragment {

    View view;
    boolean alreadyInitialized;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        setView(inflater, container);
        setGameViewModel();
        setConstraintLayout();
        initPositions();
        initPieces();
        // TODO: 2021-10-13 Check if everything should be inside if-clause
        if (!alreadyInitialized) {
            initObservers();
            alreadyInitialized = true;
        }

        reInit();

        return view;
    }

    protected abstract void setView(LayoutInflater inflater, ViewGroup container);

    protected abstract void setGameViewModel();

    protected abstract void setConstraintLayout();

    protected abstract void initPositions();

    protected abstract void initPieces();

    protected abstract void initObservers();

    protected abstract void reInit();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        alreadyInitialized = false;
    }

}
