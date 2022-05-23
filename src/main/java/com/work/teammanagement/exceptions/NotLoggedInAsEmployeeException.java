package com.work.teammanagement.exceptions;

public class NotLoggedInAsEmployeeException extends Exception {
    public NotLoggedInAsEmployeeException() {
        super("Not logged in as employee!");
    }

    public NotLoggedInAsEmployeeException(String username) {
        super(String.format("Not logged in as employee! (logged in as %s)", username));
    }
}
