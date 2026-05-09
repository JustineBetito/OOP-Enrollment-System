package org.example.exception;

public class SectionFullException extends Exception {

    /**
     * Constructor that accepts an error message
     * @param message The error message describing why the section is full
     */
    public SectionFullException(String message) {
        super(message);
    }
}