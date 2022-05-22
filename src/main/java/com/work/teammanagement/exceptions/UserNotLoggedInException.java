package com.work.teammanagement.exceptions;

public class UserNotLoggedInException extends Exception {
    public UserNotLoggedInException() {
        super("User not logged in!");
    }
}
