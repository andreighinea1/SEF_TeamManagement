package com.work.teammanagement.services;

import com.work.teammanagement.exceptions.UserNotFoundException;
import com.work.teammanagement.exceptions.UsernameAlreadyExistsException;
import com.work.teammanagement.model.databases.UsersDB;
import com.work.teammanagement.model.users.UserRole;

public final class RegisterService {
    public static void registerUser(String username, String password, UserRole role, String managerUsername,
                                    String fullName, String address, String phone) throws UsernameAlreadyExistsException, UserNotFoundException {
        UsersDB.addToDB(username, password, role, managerUsername, fullName, address, phone);
    }
}
