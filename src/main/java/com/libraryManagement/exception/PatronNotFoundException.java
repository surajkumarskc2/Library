package com.libraryManagement.exception;

public class PatronNotFoundException extends ResourceNotFoundException {

    private static final long serialVersionUID = 1L;

    public PatronNotFoundException(String message) {
        super(message);
    }
}
