package com.luv2code.cruddemo.rest;

public class StudentNotFoundException extends RuntimeException {

    // define constructor from parent class

    public StudentNotFoundException(String message) {
        super(message);
    }

    public StudentNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public StudentNotFoundException(Throwable cause) {
        super(cause);
    }
}
