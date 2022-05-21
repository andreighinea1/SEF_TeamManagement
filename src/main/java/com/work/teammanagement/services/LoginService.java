package com.work.teammanagement.services;

import com.work.teammanagement.LoggedInUser;
import com.work.teammanagement.exceptions.UserNotFoundException;
import com.work.teammanagement.model.User;
import com.work.teammanagement.model.UsersDB;
import com.work.teammanagement.model.types.UserRole;

public final class LoginService {
    public static void loginUser(String username, String password, UserRole role) throws UserNotFoundException {
        User user = UsersDB.findUser(username, password, role);
        LoggedInUser.loginUser(user);
    }

    public static void logoutUser(){
        LoggedInUser.logoutUser();
    }
}
