package com.example.fiamedknuff.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.example.fiamedknuff.R;

/**
 * TODO
 * Responsibility: Creates the main activity.
 *
 * Used by: -
 * Uses: -
 *
 * Created by
 * @author Emma Stålberg, Hanna Boquist, Amanda Cyrén, Philip Winsnes, Johan Selin
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }

}
