package com.example.fiamedknuff.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * A class CPU that creates a CPU object and couples computer player logic with a player
 *
 * Created by
 * @author Amanda Cyrén
 */
public class CPU extends Player {

    /**
     * Constructor for the class CPU which calls the superclass Players constructor
     * */
    public CPU(String name, Color color) {
        super(name, color);
    }

    void makeMove(int roll) {
        Collection<Piece> movablePieces = new ArrayList<>();
        movablePieces = getMovablePieces(getPieces(), roll);
        //... HashMap?
    }

    /*
    * Ta reda på vilka drag som är möjliga, som en vanlig Player -> getMovablePieces
    * Rangordning på möjliga drag:
    * 1. Om isOccupied -> knockput
    * 2. Gå ut med en pjäs
    * 3. Gå ut från bo med pjäs -> 1a eller 6a
    * 4. Inget speciellt, bara gå antal steg på tärningen framåt. Slumpa mellan dessa om det finns fler?
    *
    * Välja vilken pjäs -> Flytta den
    *
    * Överlag: Kan inte flytta -> Gå vidare till nästa
     */
}
