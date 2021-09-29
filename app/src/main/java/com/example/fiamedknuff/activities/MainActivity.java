package com.example.fiamedknuff.activities;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.example.fiamedknuff.R;
import com.example.fiamedknuff.ViewModels.TestViewModel;
import com.example.fiamedknuff.model.Player;

public class MainActivity extends AppCompatActivity {

    TextView tv;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_viewmodel);

        tv = findViewById(R.id.textV);
        button = findViewById(R.id.button);
        ViewModelProvider.Factory factory = new ViewModelProvider.NewInstanceFactory();
        TestViewModel viewModel = new ViewModelProvider(this, factory).get(TestViewModel.class);

        viewModel.startTimer();
        viewModel.initPlayer();

        viewModel.getSeconds().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                tv.setText(integer.toString());
                Log.d(TAG, "onChanged: " + integer);
            }
        });

        viewModel.finished.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    Toast.makeText(MainActivity.this, "Finished!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        viewModel.player_data.observe(this, new Observer<Player>() {
            @Override
            public void onChanged(Player player) {
                tv.setText(player.getName());
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.updatePlayer();
            }
        });

    }
}