package com.work.teammanagement.exceptions;

public class NoManagerCallRequestsException extends Exception {
    public NoManagerCallRequestsException() {
        super("User has no manager call requests!");
    }

    public NoManagerCallRequestsException(String username) {
        super(String.format("User %s has no manager call requests", username));
    }
}
