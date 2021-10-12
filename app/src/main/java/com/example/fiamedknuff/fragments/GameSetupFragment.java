package com.example.fiamedknuff.fragments;

import android.os.Build;
import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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

// TODO: 2021-10-11 Disable being able to choose only one player
public class GameSetupFragment extends Fragment {

    private NavController navController;
    private Button createGameBtn;
    private Spinner playerAmountSpinner;
    private EditText player1Name, player2Name, player3Name, player4Name;
    private LinearLayout verticalLayout;
    private CheckBox CPUCheckBox1, CPUCheckBox2, CPUCheckBox3, CPUCheckBox4;
    private GameViewModel gameViewModel;
    ArrayList<EditText> players = new ArrayList<>();
    ArrayList<CheckBox> CPUCheckBoxes = new ArrayList<>();
    ArrayList<Color> colors = new ArrayList<>();
    private int selectedPlayerCount = 4;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        navController = NavHostFragment.findNavController(this);

        gameViewModel = new ViewModelProvider(getActivity()).get(GameViewModel.class);

        View view = inflater.inflate(R.layout.fragment_game_setup, container, false);

        initWidgets(view);
        populateSpinner();
        populatePlayersList();
        populateCPUCheckBoxList();
        populateColors();

        initCreateGameButton();

        initPlayerAmountSpinner();

        return view;
    }

    private void initCreateGameButton() {
        createGameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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

    private void initPlayerAmountSpinner() {
        playerAmountSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Object item = parent.getItemAtPosition(position);
                selectedPlayerCount = Integer.parseInt(item.toString());
                for (int i = 0; i < selectedPlayerCount; i++) {
                    players.get(i).setVisibility(View.VISIBLE);
                    CPUCheckBoxes.get(i).setVisibility(View.VISIBLE);
                }
                for (int j = selectedPlayerCount; j < players.size(); j++) {
                    players.get(j).setVisibility(View.INVISIBLE);
                    CPUCheckBoxes.get(j).setVisibility(View.INVISIBLE);
                }
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void populatePlayersList() {
        players.add(player1Name);
        players.add(player2Name);
        players.add(player3Name);
        players.add(player4Name);
    }

    private void populateCPUCheckBoxList() {
        CPUCheckBoxes.add(CPUCheckBox1);
        CPUCheckBoxes.add(CPUCheckBox2);
        CPUCheckBoxes.add(CPUCheckBox3);
        CPUCheckBoxes.add(CPUCheckBox4);
    }

    private void populateColors() {
        colors.add(Color.YELLOW);
        colors.add(Color.RED);
        colors.add(Color.GREEN);
        colors.add(Color.BLUE);
        colors.add(Color.BLACK);
        colors.add(Color.PINK);
    }

    private List<Color> getColors() {
        // TODO
        List<Color> selectedColors = new ArrayList<>();
        for (int i = 0; i < selectedPlayerCount; i++) {
            colors.add(colors.get(i));
        }
        return colors;
    }

    private List<Boolean> getSelectedCPU() {
        // FIXME: 2021-10-11 Fixa
        ArrayList<Boolean> isCPU = new ArrayList<>();
        for (int i = 0; i < selectedPlayerCount; i++) {
            isCPU.add(CPUCheckBoxes.get(i).isSelected());
        }
        return isCPU;
    }

    private List<String> getPlayerNames() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < selectedPlayerCount; i++) {
            list.add(players.get(i).getText().toString());
        }
//        list.add(player1Name.getText().toString());
//        list.add(player2Name.getText().toString());
//        list.add(player3Name.getText().toString());
//        list.add(player4Name.getText().toString());
        return list;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
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

    private void populateSpinner() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), R.layout.spinner_item, getResources().getStringArray(R.array.spinner_player_amount));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        playerAmountSpinner.setAdapter(adapter);
    }


}
