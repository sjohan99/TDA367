package com.example.fiamedknuff.ViewModels;

import android.os.CountDownTimer;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.fiamedknuff.model.Color;
import com.example.fiamedknuff.model.Player;

public class TestViewModel extends ViewModel {

    CountDownTimer timer;

    public MutableLiveData<Player> player_data = new MutableLiveData<>();

    private int counter = 0;

    Player player = new Player("TestName", Color.BLACK);

    public MutableLiveData<Boolean> finished = new MutableLiveData<>();

    private MutableLiveData<Integer> seconds = new MutableLiveData<Integer>();

    public void updatePlayer() {
        player.setName(player.getName() + counter++);
        player_data.setValue(player);
    }

    public void initPlayer() {
        player_data.setValue(player);
    }

    public LiveData<Integer> getSeconds() {
        return seconds;
    }

    public void startTimer() {
        timer = new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long l) {
                long timeLeft = l/1000;
                Integer s = (int) timeLeft;
                seconds.setValue(s);
            }

            @Override
            public void onFinish() {
                finished.setValue(true);
            }
        }.start();
    }

    public void stopTimer() {
        timer.cancel();
    }
}
