package com.work.teammanagement.exceptions;

public class NoEmployeeRequestsException extends Exception {
    public NoEmployeeRequestsException(String username) {
        super(String.format("User %s doesn't have any employee requests!", username));
    }
}
