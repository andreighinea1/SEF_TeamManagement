package com.work.teammanagement;

import com.work.teammanagement.exceptions.NotEnoughPrivilegesException;
import com.work.teammanagement.exceptions.UserNotFoundException;
import com.work.teammanagement.exceptions.UserNotLoggedInException;
import com.work.teammanagement.model.EmployeeRequest;
import com.work.teammanagement.model.users.User;
import com.work.teammanagement.model.users.UserRole;
import com.work.teammanagement.model.databases.EmployeeRequestsDB;

import java.util.ArrayList;

public final class LoggedInUser {
    private static User user;


    public static void loginUser(User user) {
        LoggedInUser.user = user;
    }

    public static void logoutUser() {
        LoggedInUser.user = null;
    }


    public static boolean isLoggedInAsManager() {
        return isLoggedIn() && user.isManager();
    }

    public static boolean isLoggedInAsEmployee() {
        return isLoggedIn() && user.isEmployee();
    }

    public static boolean isLoggedIn() {
        return user != null;
    }

    public static void checkLoggedIn(String username) throws UserNotLoggedInException {
        checkLoggedIn();
        if(!user.getUsername().equals(username))
            throw new UserNotLoggedInException(username);
    }
    public static void checkLoggedIn() throws UserNotLoggedInException {
        if (user == null)
            throw new UserNotLoggedInException();
    }

    public static void checkLoggedInAsManager() throws NotEnoughPrivilegesException {

        if (!isLoggedInAsManager())
            throw new NotEnoughPrivilegesException();
    }


    public static String getUsername() throws UserNotLoggedInException {
        checkLoggedIn();
        return user.getUsername();
    }

    public static String getPasswordHash() throws UserNotLoggedInException {
        checkLoggedIn();
        return user.getPasswordHash();
    }

    public static UserRole getRole() throws UserNotLoggedInException {
        checkLoggedIn();
        return user.getRole();
    }

    public static String getFullName() throws UserNotLoggedInException {
        checkLoggedIn();
        return user.getFullName();
    }

    public static String getAddress() throws UserNotLoggedInException {
        checkLoggedIn();
        return user.getAddress();
    }

    public static String getPhone() throws UserNotLoggedInException {
        checkLoggedIn();
        return user.getPhone();
    }

    public static void print() {
        System.out.println("Logged in user: " + user);
    }
}
