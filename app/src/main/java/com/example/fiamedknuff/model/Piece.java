package com.example.fiamedknuff.model;

public class Piece {

    public enum Color {
        BLUE, RED, GREEN, YELLOW, PINK, BLACK
    }

    private int index;
    private Color color;

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
}
