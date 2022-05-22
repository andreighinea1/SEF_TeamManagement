package com.work.teammanagement.exceptions;

public class NotManagerException extends Exception {
    public NotManagerException() {
        super("User is not a manager!");
    }

    public NotManagerException(String username) {
        super(String.format("User %s is not a manager!", username));
    }
}
