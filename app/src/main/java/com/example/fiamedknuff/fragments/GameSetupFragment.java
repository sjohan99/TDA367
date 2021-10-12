package com.example.fiamedknuff.fragments;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.fragment.NavHostFragment;

import com.example.fiamedknuff.NotImplementedException;
import com.example.fiamedknuff.R;
import com.example.fiamedknuff.viewModels.GameViewModel;
import com.example.fiamedknuff.model.Color;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GameSetupFragment extends Fragment {

    private NavController navController;
    private Button createGameBtn;
    private Spinner playerAmountSpinner;
    private EditText player1Name, player2Name, player3Name, player4Name;
    private LinearLayout verticalLayout;
    private CheckBox CPUCheckBox1, CPUCheckBox2, CPUCheckBox3, CPUCheckBox4;
    private GameViewModel gameViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        navController = NavHostFragment.findNavController(this);

        gameViewModel = new ViewModelProvider(getActivity()).get(GameViewModel.class);

        View view = inflater.inflate(R.layout.fragment_game_setup, container, false);

        initWidgets(view);
        populateSpinner();

        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void initTypoForEditTexts() {
        EditText[] editTexts = new EditText[] {
                player1Name,
                player2Name,
                player3Name,
                player4Name
        };

        for (EditText playerNameLabel : editTexts) {
            playerNameLabel.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean b) {

                    // TODO Add type controll
                    getPlayerNames().stream().distinct().forEach(System.out::println);

                }

            });
        }


    }

    private Color[] getColors() {
        // TODO
        return new Color[] {Color.YELLOW, Color.RED, Color.GREEN, Color.BLUE};
    }

    private boolean[] getSelectedCPU() {
        return new boolean[] {
                CPUCheckBox1.isSelected(),
                CPUCheckBox2.isSelected(),
                CPUCheckBox3.isSelected(),
                CPUCheckBox4.isSelected()
        };
    }

    private List<String> getPlayerNames() {
        List<String> list = new ArrayList<>();
        list.add(player1Name.getText().toString());
        list.add(player2Name.getText().toString());
        list.add(player3Name.getText().toString());
        list.add(player4Name.getText().toString());
        return list;
    }

    private void initWidgets(View view) {
        playerAmountSpinner = view.findViewById(R.id.playerAmountSpinner);
        createGameBtn = view.findViewById(R.id.createGameBtn);
        // EditTexts
        player1Name = view.findViewById(R.id.player1Name);
        player2Name = view.findViewById(R.id.player2Name);
        player3Name = view.findViewById(R.id.player3Name);
        player4Name = view.findViewById(R.id.player4Name);
        // CheckBoxes
        CPUCheckBox1 = view.findViewById(R.id.CPUCheckBox1);
        CPUCheckBox2 = view.findViewById(R.id.CPUCheckBox2);
        CPUCheckBox3 = view.findViewById(R.id.CPUCheckBox3);
        CPUCheckBox4 = view.findViewById(R.id.CPUCheckBox4);

        initTypoForEditTexts();

        createGameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String playerCount = playerAmountSpinner.getSelectedItem().toString();

                // GameFactory.createNewGame(getPlayerNames(), getColors(), getSelectedCPU());
                try {
                    gameViewModel.init(getPlayerNames(), getColors(), getSelectedCPU());
                } catch (NotImplementedException e) {
                    e.printStackTrace();
                }
                // TODO Get more parameter inputs
                navController.navigate(R.id.action_gameSetupFragment_to_gameView, null, new NavOptions.Builder()
                        .setEnterAnim(android.R.animator.fade_in)
                        .setExitAnim(android.R.animator.fade_out)
                        .build());
            }
        });


    }

    private void populateSpinner() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), R.layout.spinner_item, getResources().getStringArray(R.array.spinner_player_amount));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        playerAmountSpinner.setAdapter(adapter);
    }


}
