package com.example.fiamedknuff.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.example.fiamedknuff.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import app.num.numandroidpagecurleffect.PageCurlView;

/**
 * A class rulebookFragment that handles the GUI for the rule book
 *
 * Created by
 * @author Amanda Cyrén
 */

public class rulebookFragment extends Fragment {

    View view;

    ImageView rulebookTitle;

    // A PageCurlView that creates a page curl effect when turning pages
    PageCurlView pageCurlView;

    // A list that holds the images to be displayed in the rule book
    List<Integer> images;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_rule_book, container, false);

        setPageCurlView(view);

        return view;
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void setPageCurlView(View view) {
        pageCurlView = view.findViewById(R.id.pagecurlView);

        rulebookTitle = view.findViewById(R.id.rule_book_title);

        // Initialize an list with images to display in the rule book
        images = new ArrayList<>();

        // Set up rules and title depending on language
        if (Locale.getDefault().getLanguage().equals("sv")) {
            languageSwedish();
            rulebookTitle.setImageDrawable(getResources().getDrawable(R.drawable.rule_book_title_sv));
        }
        else {
            languageEnglish();
            rulebookTitle.setImageDrawable(getResources().getDrawable(R.drawable.rule_book_title_en));
        }

        // Adds the images to the PageCurlView and sets the speed of the page curl
        pageCurlView.setCurlView(images);
        pageCurlView.setCurlSpeed(600); // Set the speed in ms
    }

    private void languageSwedish() {
        images.add(R.drawable.rules_first_pages_sv);
        images.add(R.drawable.rules_second_pages_sv);
        images.add(R.drawable.rules_third_pages_sv);
    }

    // TODO add rules in english
    private void languageEnglish() {
        images.add(R.drawable.pages);
        images.add(R.drawable.pages);
        images.add(R.drawable.pages);
    }

}