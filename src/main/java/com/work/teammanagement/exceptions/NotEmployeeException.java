package com.work.teammanagement.exceptions;

public class NotEmployeeException extends Exception {
    public NotEmployeeException() {
        super("User is not an employee!");
    }

    public NotEmployeeException(String username) {
        super(String.format("User %s is not an employee!", username));
    }
}
