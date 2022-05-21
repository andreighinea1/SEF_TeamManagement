package com.work.teammanagement.services;

import com.work.teammanagement.exceptions.UsernameAlreadyExistsException;
import com.work.teammanagement.model.UsersDB;
import com.work.teammanagement.model.types.User;
import com.work.teammanagement.model.types.UserRole;

public final class RegisterService {
    public static User registerUser(String username, String password, UserRole role, String fullName, String address,
                                    String phone) throws UsernameAlreadyExistsException {
        return UsersDB.addUser(username, password, role, fullName, address, phone);
    }
}
