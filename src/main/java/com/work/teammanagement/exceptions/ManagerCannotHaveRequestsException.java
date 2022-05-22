package com.work.teammanagement.exceptions;

public class ManagerCannotHaveRequestsException extends Exception {
    public ManagerCannotHaveRequestsException() {
        super("Managers cannot have requests!");
    }

    public ManagerCannotHaveRequestsException(String username) {
        super(String.format("Managers cannot have requests! (logged in as %s)", username));
    }
}
