package com.work.teammanagement.exceptions;

import com.work.teammanagement.model.users.UserRole;

public class UserNotFoundException extends Exception {
    public UserNotFoundException(String username, UserRole role) {
        super(String.format("{username: %s, role: %s, password: '...'} not found!", username, role.toString()));
    }

    public UserNotFoundException(String username) {
        super(String.format("{username: %s} not found!", username));
    }
}
