package com.example.fiamedknuff.model;

import java.io.Serializable;

public class Piece implements Serializable {

    private int index;
    private Color color;
    private int homeNumber;
    private int offset;

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

    public void setHomeNumber(int homeNumber) {
        this.homeNumber = homeNumber;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getOffset() {
        return this.offset;
    }
}
