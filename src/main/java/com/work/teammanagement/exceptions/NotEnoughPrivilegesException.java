package com.work.teammanagement.exceptions;

public class NotEnoughPrivilegesException extends Exception {
    public NotEnoughPrivilegesException() {
        super("User doesn't have enough privileges!");
    }
}
