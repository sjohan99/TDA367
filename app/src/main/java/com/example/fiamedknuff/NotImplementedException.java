package com.example.fiamedknuff;

public class NotImplementedException extends Exception {

    public NotImplementedException(String errorMessage) {
        super(errorMessage);
    }

    public NotImplementedException() {
        super();
    }
}
