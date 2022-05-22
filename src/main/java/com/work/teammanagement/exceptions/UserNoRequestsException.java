package com.work.teammanagement.exceptions;

public class UserNoRequestsException extends Exception {
    public UserNoRequestsException(String username) {
        super(String.format("User %s doesn't have any requests!", username));
    }
}
