package com.work.teammanagement.exceptions;

public class UserCannotHaveRequestsException extends Exception {
    public UserCannotHaveRequestsException() {
        super("User cannot have requests!");
    }

    public UserCannotHaveRequestsException(String username) {
        super(String.format("User %s cannot have requests!", username));
    }
}
