package com.work.teammanagement.exceptions;

import com.work.teammanagement.model.types.UserRole;

public class UserNotFoundException extends Exception {
    public UserNotFoundException(String username, UserRole role) {
        super(String.format("{username: %s, role: %s, password: '...'} not found!", username, role.toString()));
    }
}
