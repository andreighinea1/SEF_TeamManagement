package com.work.teammanagement.exceptions;

public class NotEnoughPrivilegesException extends Exception {
    public NotEnoughPrivilegesException() {
        super("User doesn't have enough privileges!");
    }

    public NotEnoughPrivilegesException(String username) {
        super(String.format("User %s doesn't have enough privileges!", username));
    }
}
