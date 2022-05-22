package com.work.teammanagement.services;

import com.work.teammanagement.LoggedInUser;
import com.work.teammanagement.exceptions.UserNotFoundException;
import com.work.teammanagement.model.databases.UsersDB;
import com.work.teammanagement.model.users.UserRole;

public final class LoginService {
    public static void loginUser(String username, String password, UserRole role) throws UserNotFoundException {
        LoggedInUser.loginUser(UsersDB.findUser(username, password, role));
    }

    public static void logoutUser() {
        LoggedInUser.logoutUser();
    }
}
