package com.example.fiamedknuff.exceptions;

/**
 * Exception for when the client has issued a valid command but the feature is not yet implemented
 * @author Johan Selin
 */
public class NotImplementedException extends Exception {

    public NotImplementedException(String errorMessage) {
        super(errorMessage);
    }

    public NotImplementedException() {
        super();
    }
}
