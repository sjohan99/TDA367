package com.example.fiamedknuff;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * A class rulebookFragment that handles the GUI for the rule book
 *
 * Created by
 * @author Amanda Cyrén
 */

public class rulebookFragment extends Fragment implements View.OnClickListener {

    // A list that holds the images to be displayed in the rule book
    List<Integer> images;

    // A PageCurlView that creates a page curl effect when turning pages
    //PageCurlView pageCurlView;

    // An ImageButton for resuming to previous activity/fragment
    ImageButton imageButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_rule_book, container, false);

        // Button for resuming to previous activity/fragment
        imageButton = view.findViewById(R.id.arrow_back);
        imageButton.setOnClickListener(this);

        setPageCurlView(view);

        return view;
    }

    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.arrow_back) {
            // Return to previous activity/fragment
        }
    }

    private void setPageCurlView(View view) {
        // Sets up the PageCurlView
        //pageCurlView = (PageCurlView) view.findViewById(R.id.pagecurlView);

        // Initialize an list with images to display in the rule book
        images = new ArrayList<>();

        // Set up rules depending on language
        if (Locale.getDefault().getLanguage().equals("sv")) {
            languageSwedish();
        }
        else {
            languageEnglish();
        }

        // Adds the images to the PageCurlView and sets the speed of the page curl
        //pageCurlView.setCurlView(images);
        //pageCurlView.setCurlSpeed(600); // Set the speed in ms
    }

    private void languageSwedish() {
        images.add(R.drawable.rules_first_pages_sv);
        images.add(R.drawable.rules_second_pages_sv);
        images.add(R.drawable.rules_third_pages_sv);
    }

    // Rules in english not yet implemented
    private void languageEnglish() {
        images.add(R.drawable.pages);
        images.add(R.drawable.pages);
        images.add(R.drawable.pages);
    }

}
