package com.example.fiamedknuff;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

/**
 * A class rulebookFragment that handles the GUI for the rule book
 *
 * Created by
 * @author Amanda Cyrén
 */

public class rulebookFragment extends Fragment {

    // A list that holds the images to be displayed in the rule book
    List<Integer> images;

    // A PageCurlView that creates a page curl effect when turning pages
    //PageCurlView pageCurlView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_rule_book, container, false);

        // Sets up the PageCurlView
        //pageCurlView = (PageCurlView) view.findViewById(R.id.pagecurlView);

        // Initialize an list with images to display in the rule book
        images = new ArrayList<>();
        images.add(R.drawable.pages); // First Image
        images.add(R.drawable.pages); // Second Image
        images.add(R.drawable.pages); // Third Image

        // Adds the images to the PageCurlView and sets the speed of the page curl
        //pageCurlView.setCurlView(images);
        //pageCurlView.setCurlSpeed(600); // Set the speed in ms

        return view;
    }

}
