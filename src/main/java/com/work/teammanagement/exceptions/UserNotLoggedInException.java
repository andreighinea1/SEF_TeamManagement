package com.work.teammanagement.exceptions;

public class UserNotLoggedInException extends Exception {
    public UserNotLoggedInException() {
        super("User not logged in!");
    }

    public UserNotLoggedInException(String username) {
        super(String.format("User not logged in as %s!", username));
    }
}
