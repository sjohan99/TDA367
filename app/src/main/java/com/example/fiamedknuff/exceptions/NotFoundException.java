package com.example.fiamedknuff.exceptions;

/**
 * Exception for when iterating an iterative searching for a certain value but it cannot be found
 * @author Johan Selin
 */
public class NotFoundException extends Exception {

    /**
     * Creates a NotFoundException with a message to the client
     * @param errorMessage Specify what couldn't be found
     */
    public NotFoundException(String errorMessage) {
        super(errorMessage);
    }

    public NotFoundException() {
        super();
    }

}
