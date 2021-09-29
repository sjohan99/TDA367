package com.example.fiamedknuff.model;

import java.io.Serializable;

public class Piece implements Serializable {

    private int index;
    private Color color;
    private int homeNumber;

    public Piece(Color color) {
        this.color = color;
        index = 0;      // index = 0 innebär att pjäsen sätts i boet
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    boolean isHome () {
        return index == 0;
    }

    public int getHomeNumber() {
        return homeNumber;
    }
}
