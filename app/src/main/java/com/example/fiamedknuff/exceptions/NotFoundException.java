package com.example.fiamedknuff.exceptions;

public class NotFoundException extends Exception {

    public NotFoundException(String errorMessage) {
        super(errorMessage);
    }

    public NotFoundException() {
        super();
    }

}
