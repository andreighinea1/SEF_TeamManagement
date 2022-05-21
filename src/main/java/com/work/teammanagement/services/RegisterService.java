package com.work.teammanagement.services;

import com.work.teammanagement.exceptions.UsernameAlreadyExistsException;
import com.work.teammanagement.model.User;
import com.work.teammanagement.model.UsersDB;
import com.work.teammanagement.model.types.UserType;

public class RegisterService {
    public static void registerUser(String username, String password, UserType role) throws UsernameAlreadyExistsException {
        UsersDB.addUser(new User(username, password, role));
    }
}
