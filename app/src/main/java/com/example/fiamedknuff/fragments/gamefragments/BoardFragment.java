package com.example.fiamedknuff.fragments.gamefragments;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import com.example.fiamedknuff.R;
import com.example.fiamedknuff.exceptions.NotFoundException;
import com.example.fiamedknuff.model.Piece;
import com.example.fiamedknuff.model.Position;
import com.example.fiamedknuff.viewModels.GameViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Responsibility: An abstract class BoardFragment. Superclass to fragments of boards.
 *  Holds the common behavior for all boardfragments. Implements the methods
 *  that all boardfragments have. Some methods are declared abstract and should be
 *  implemented by the specific boardfragments that extends this class.
 *
 * Used by: StandardBoardFragment TODO correct?
 * Uses: GameViewModel, NotFoundException, Piece, Position
 *
 * Created by
 * @author Emma Stålberg, Amanda Cyrén, Hanna Boquist
 */
public abstract class BoardFragment extends Fragment {

    View view;
    boolean alreadyInitialized;
    ImageView latestClickedPiece;
    GameViewModel gameViewModel;
    HashMap<ImageView, Piece> imageViewPieceHashMap;
    HashMap<Position, ImageView> imageViewPositionHashMap;

    //TODO javadoc
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
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

    protected abstract List<ImageView> getListOfBoardPositions();

    protected abstract List<ImageView> getListOfHomePositions();

    protected abstract ConstraintLayout getConstraintLayout();

    /**
     * Initiates the pieces by connecting the pieces ids, initiates the list of the pieces,
     * initiates the hashmap with the pieces, makes the inactive pieces invisible and adds
     * onClickListeners to the pieces.
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    protected void initPieces() {
        initListOfAllPieces();
        initPiecesHashmap();
        makeInactivePiecesInvisible();
        addPiecesOnClickListeners();
    }

    /**
     * Initiates the list piecesImageViews with all ImageViews of the pieces.
     * The first four ones are the pieces that belongs to player 1.
     * Then comes the pieces for player 2, and so on.
     */
    protected abstract void initListOfAllPieces();

    /**
     * Initiates the hashmap imageViewPieceHashMap. Gets the active pieces from gameViewModel
     * and connects them with the equivalent imageView.
     */
    private void initPiecesHashmap() {
        imageViewPieceHashMap = new HashMap<>();
        List<Piece> activePieces = gameViewModel.getPieces();
        for (int i = 0; i < activePieces.size(); i++) {
            imageViewPieceHashMap.put(getListOfPiecesImageViews().get(i), activePieces.get(i));
        }
    }

    /**
     * The pieces that should be visible are connected in the imageViewPieceHashMap. The
     * rest of the pieces in the list piecesImageViews should be invisible, and that is
     * what happens in this method.
     */
    private void makeInactivePiecesInvisible() {
        for (int i = imageViewPieceHashMap.size(); i < getListOfPiecesImageViews().size(); i++) {
            getListOfPiecesImageViews().get(i).setVisibility(View.INVISIBLE);
        }
    }

    /**
     * Adds OnClickListeners on all pieces. When a piece is clicked, the method pieceClicked
     * should be called.
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    protected void addPiecesOnClickListeners() {
        for (ImageView piece : getListOfPiecesImageViews()) {
            piece.setOnClickListener(view -> pieceClicked(piece));
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
     * Initiates the positions.
     */
    protected void initPositions() {
        initListsOfPositions();
        initPositionsHashmap();
    }

    /**
     * Initiates the lists for the positions in the homes and on the board.
     */
    private void initListsOfPositions() {
        initListOfBoardPositions();
        initListOfHomePositions();
    }

    /**
     * Initiates the List with all positions on the board.
     */
    protected abstract void initListOfBoardPositions();

    /**
     * Initiates the List with all positions in the homes.
     */
    protected abstract void initListOfHomePositions();

    /**
     * Initiates the hashmap imageViewPositionHashMap. Gets the positions from gameViewModel
     * and connects them with the equivalent imageView. The first ones are for the positions
     * in the homes.
     */
    private void initPositionsHashmap() {
        imageViewPositionHashMap = new HashMap<>();
        List<Position> positionsInModel = gameViewModel.getPositions();
        int nrOfHomePositions = gameViewModel.getPlayerCount() * 4;
        for (int i = 0; i < nrOfHomePositions; i++) {
            imageViewPositionHashMap.put(positionsInModel.get(i), getListOfHomePositions().get(i));
        }

        for (int i = nrOfHomePositions; i < positionsInModel.size(); i++) {
            imageViewPositionHashMap.put(
                    positionsInModel.get(i), getListOfBoardPositions().get(i - nrOfHomePositions));
        }
    }

    /**
     * Initiates the observers for variables movingPath, movesArePossibleToMake
     * currentPlayer and knockedPiece.
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void initObservers() {

        initMovingPathObserver();
        initMovesArePossibleToMakeObserver();
        initCurrentPlayerObserver();
        initKnockedPieceObserver();

    }

    /**
     * Observes the variable movingPath in GameViewModel, which is set to a position path
     * when a piece is moved in the model.
     * Unmarks all pieces and calls for method isMoved which handles the logic for when
     * a piece is moved.
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void initMovingPathObserver() {
        gameViewModel.movingPath.observe(getActivity(), movingPath -> {
            if (movingPath.size() != 0) {
                unMarkAllPieces();
                isMoved(movingPath);
            }
        });
    }

    /**
     * Observes the variable movesArePossibleToMake, which is set to true when the rolled
     * dicevalue is able to use.
     */
    private void initMovesArePossibleToMakeObserver() {
        gameViewModel.movesArePossibleToMake.observe(getActivity(), aBoolean -> {
            if (aBoolean) {
                markMovablePieces();
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void initCurrentPlayerObserver() {
        gameViewModel.currentPlayer.observe(getActivity(), player -> {
            if (gameViewModel.isCPU(player)) {
                gameViewModel.CPUDiceRoll(true);
                Piece piece = gameViewModel.getPieceForCPUMove();
                if (piece != null) {
                    ImageView pieceImageView = null;
                    try {
                        pieceImageView = getPieceImageView(piece);
                    } catch (NotFoundException e) {
                        e.printStackTrace();
                    }
                    pieceClicked(pieceImageView);
                }
            }
        });
    }

    private void initKnockedPieceObserver() {
        gameViewModel.knockedPiece.observe(getActivity(), piece -> {
            Position target = gameViewModel.getPosition(piece);
            List<Position> movingPath = new ArrayList<>();
            movingPath.add(target);
            try {
                move(getPieceImageView(piece), movingPath);
            } catch (NotFoundException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Clears the animations of all pieces imageviews.
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void unMarkAllPieces() {
        imageViewPieceHashMap.forEach((imageView, piece) -> {
            imageView.clearAnimation();
        });
    }

    private synchronized void isMoved(List<Position> movingPath) {
        move(latestClickedPiece, movingPath);
        boolean playerIsFinished = removePieceAndPlayerIfFinished(latestClickedPiece);
        if (gameViewModel.isNextPlayer(playerIsFinished)) {
            gameViewModel.selectNextPlayer();
        }
        // TODO check if game is finished --> finish...
        gameViewModel.setDiceIsUsed();
    }

    /**
     * Should move the piece in the view to the positions sent in as a parameter.
     * This positions is usually the positions that the piece has moved through in the model,
     * and it stops on the piece´s target position.
     * @param piece is the piece that should be moved
     * @param targets is the positions that the piece should be moved to
     */
    private synchronized void move(ImageView piece, List<Position> targets) {
        Runnable walk = new Runnable() {
            @Override
            public void run() {
                for (Position target : targets) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            moveImageView(piece, imageViewPositionHashMap.get(target));
                        }
                    });
                    try {
                        Thread.sleep(175);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        Thread t = new Thread(walk);
        t.start();
    }

    /**
     * Makes the first parameter, movingImageView, have the same constraints as the second
     * parameter, target. I.e. it moves the "movingImageView" to the same place as the "target".
     * @param movingImageView is the ImageView that should be moved
     * @param target is the place where the movingImageView should be moved to
     */
    private void moveImageView(ImageView movingImageView, ImageView target) {
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(getConstraintLayout());
        constraintSet.connect(
                movingImageView.getId(), ConstraintSet.START, target.getId(), ConstraintSet.START);
        constraintSet.connect(
                movingImageView.getId(), ConstraintSet.END, target.getId(), ConstraintSet.END);
        constraintSet.connect(
                movingImageView.getId(), ConstraintSet.TOP, target.getId(), ConstraintSet.TOP);
        constraintSet.connect(
                movingImageView.getId(), ConstraintSet.BOTTOM, target.getId(), ConstraintSet.BOTTOM);
        constraintSet.applyTo(getConstraintLayout());

        movingImageView.bringToFront();
    }

    /**
     * Checks if either the piece or the player is finished. If they are, they are removed
     * from the board.
     * @param piece is the piece that should be checked
     * @return true if the player has finished, and false otherwise
     */
    private boolean removePieceAndPlayerIfFinished(ImageView piece) {
        if (removePieceIfFinished(piece)) {
            return removePlayerIfFinished();
        }
        return false;
    }

    /**
     * If the selected piece is finished, it is removed in the model and also in the view (it is
     * made invisible).
     * @param piece is the piece that should be checked
     * @return true if the piece is finished, and false otherwise
     */
    private boolean removePieceIfFinished(ImageView piece) {
        if (pieceIsFinished(piece)) {
            animateFinishedPiece(piece);
            piece.setVisibility(View.INVISIBLE);
            return true;
        }
        return false;
    }

    private void animateFinishedPiece(ImageView piece) {
        Animation rotate = AnimationUtils.loadAnimation(
                requireActivity().getApplicationContext(), R.anim.rotate);
        Animation fadeout = AnimationUtils.loadAnimation(
                requireActivity().getApplicationContext(), R.anim.fadeout);

        int duration = 750;
        rotate.setDuration(duration);

        AnimationSet animation = new AnimationSet(false);
        animation.addAnimation(rotate);
        animation.addAnimation(fadeout);
        piece.setAnimation(animation);

        piece.animate();
    }

    /**
     * Checks if the current player is finished.
     * @return true if the player is finished, false otherwise.
     */
    private boolean removePlayerIfFinished() {
        return gameViewModel.removePlayerIfFinished();
    }

    /**
     * Checks if piece is finished.
     * @param piece is the piece that is checked
     * @return true if the piece is finished, false otherwise.
     */
    private boolean pieceIsFinished(ImageView piece) {
        return gameViewModel.removePieceIfFinished(imageViewPieceHashMap.get(piece));
    }

    /**
     * Gets the current player's movable pieces marked on the GUI.
     */
    private void markMovablePieces() {
        for (Map.Entry<Piece, ImageView> entry : getCurrentPlayersMovablePiecesImageViews().entrySet()) {
            Animation anim = AnimationUtils.loadAnimation(requireActivity().getApplicationContext(), R.anim.bounce);
            entry.getValue().startAnimation(anim);
        }
    }

    /**
     * TODO Make more slim
     * Method used to help identify ImageViews for affecting pliancy on the current player's pieces.
     * @return Returns a HashMap with the current player's piece's ImageViews.
     */
    private HashMap<Piece, ImageView> getCurrentPlayersMovablePiecesImageViews() {
        // For each of the player's movable pieces, iterate through all pieces in the HashMap
        // and find the corresponding ImageView that is connected to the movable piece.
        HashMap<Piece, ImageView> map = new HashMap<>();
        LiveData<List<Piece>> movablePieces = gameViewModel.getMovablePiecesForCurrentPlayer();
        for (Piece piece : movablePieces.getValue()) {
            for (Map.Entry<ImageView, Piece> entry : imageViewPieceHashMap.entrySet()) {
                if (piece.toString().equals(entry.getValue().toString())) {
                    map.put(entry.getValue(), entry.getKey());
                }
            }
        }
        return map;
    }

    private ImageView getPieceImageView(Piece piece) throws NotFoundException {
        for (Map.Entry<ImageView, Piece> entry : imageViewPieceHashMap.entrySet()) {
            if (piece.toString().equals(entry.getValue().toString())) {
                return entry.getKey();
            }
        }
        throw new NotFoundException("No ImageView found for given piece");
    }

    protected void reInit() {
        reInitPieces();
        if (!gameViewModel.isDiceUsed()) {
            markMovablePieces();
        }
    }

    private void reInitPieces() {
        for (int i = 0; i < gameViewModel.getPlayerCount() * 4; i++) {
            ImageView piece = getListOfPiecesImageViews().get(i);
            Position target = gameViewModel.getPosition(imageViewPieceHashMap.get(piece));
            moveImageView(piece, imageViewPositionHashMap.get(target));
        }
    }

    //TODO javadoc
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        alreadyInitialized = false;
    }

}
