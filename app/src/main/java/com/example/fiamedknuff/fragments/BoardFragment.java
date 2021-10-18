package com.example.fiamedknuff.fragments;

import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.fiamedknuff.viewModels.GameViewModel;

import java.util.List;

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
    ImageView latestClickedPiece;
    GameViewModel gameViewModel;

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

    private void setGameViewModel() {
        gameViewModel = new ViewModelProvider(getActivity()).get(GameViewModel.class);
    }
    protected abstract void setConstraintLayout();

    protected abstract List<ImageView> getListOfPiecesImageViews();

    /**
     * Initiates the pieces by connecting the pieces ids, initiates the list of the pieces,
     * initiates the hashmap with the pieces, makes the inactive pieces invisible and adds
     * onClickListeners to the pieces.
     */
    protected void initPieces() {
        connectPiecesIds();
        initListOfAllPieces();
        initPiecesHashmap();
        makeInactivePiecesInvisible();
        addPiecesOnClickListeners();
    }

    /**
     * Connects the pieces id:s with its equivalent imageview.
     */
    protected abstract void connectPiecesIds();

    /**
     * Initiates the list piecesImageViews with all ImageViews of the pieces.
     */
    protected abstract void initListOfAllPieces();

    /**
     * Initiates the hashmap imageViewPieceHashMap. Gets the active pieces from gameViewModel
     * and connects them with the equivalent imageView.
     */
    protected abstract void initPiecesHashmap();

    /**
     * The pieces that should be visible are connected in the imageViewPieceHashMap. The
     * rest of the pieces in the list piecesImageViews should be invisible, and that is
     * what happens in this method.
     */
    protected abstract void makeInactivePiecesInvisible();

    /**
     * Adds OnClickListeners on all pieces. When a piece is clicked, the method pieceClicked
     * should be called.
     */
    protected void addPiecesOnClickListeners() {
        for (ImageView piece : getListOfPiecesImageViews()) {
            piece.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void onClick(View view) {
                    pieceClicked(piece);
                }
            });
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    protected void pieceClicked(ImageView piece) {
        setPiecesClickable(false);
        latestClickedPiece = piece;
        gameViewModel.move(imageViewPieceHashMap.get(piece));
        setPiecesClickable(true);
    }

    /**
     * Either sets all the pieces to clickable, or to non-clickable, depending on the param
     * @param isClickable is true if the pieces should be set to clickable, and
     *                    false if the pieces should be set to non-clickable
     */
    private void setPiecesClickable(Boolean isClickable) {
        for (ImageView piece : getListOfPiecesImageViews()) {
            piece.setClickable(isClickable);
        }
    }

    /**
     * Should move the piece in the model, through the viewmodel.
     * @param piece
     */
    protected abstract void moveInModel(ImageView piece);

    /**
     * Initiates the positions.
     */
    protected void initPositions() {
        connectPositionIds();
        initListsOfPositions();
        initPositionsHashmap();
    }

    protected abstract void connectPositionIds();

    protected abstract void initListsOfPositions();

    protected abstract void initPositionsHashmap();

    protected abstract void initObservers();

    protected abstract void reInit();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        alreadyInitialized = false;
    }

}
