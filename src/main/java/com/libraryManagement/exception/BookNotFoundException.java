package com.libraryManagement.exception;

public class BookNotFoundException extends ResourceNotFoundException {

    private static final long serialVersionUID = 1L;

    public BookNotFoundException(String message) {
        super(message);
    }
}
