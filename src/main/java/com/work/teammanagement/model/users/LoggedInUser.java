package com.work.teammanagement.model.users;

import com.work.teammanagement.exceptions.*;
import com.work.teammanagement.model.databases.UsersDB;

import java.util.Objects;

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

    public static void checkLoggedIn() throws UserNotLoggedInException {
        if (user == null)
            throw new UserNotLoggedInException();
    }

    public static void checkLoggedInAsManager() throws NotEnoughPrivilegesException, UserNotLoggedInException {
        checkLoggedIn();
        if (!user.isManager())
            throw new NotEnoughPrivilegesException(user.getUsername());
    }

    public static void checkLoggedInAsEmployee() throws ManagerCannotHaveRequestsException, UserNotLoggedInException {
        checkLoggedIn();
        if (!user.isEmployee())
            throw new ManagerCannotHaveRequestsException(user.getUsername());
    }

    // Check that the current logged-in user is the manager assigned to the provided employee username
    public static void checkAssignedManager(String employeeUsername) throws UserNotLoggedInException, UserNotFoundException, NotEnoughPrivilegesException, ManagerMismatchException {
        String managerUsername1 = getManagerUsername();
        String managerUsername2 = UsersDB.findAssignedManager(employeeUsername);
        if (!Objects.equals(managerUsername1, managerUsername2))
            throw new ManagerMismatchException(managerUsername1, managerUsername2);
    }


    public static String getUsername() throws UserNotLoggedInException {
        checkLoggedIn();
        return user.getUsername();
    }

    public static String getManagerUsername() throws NotEnoughPrivilegesException, UserNotLoggedInException {
        checkLoggedInAsManager();
        return user.getUsername();
    }

    public static String getEmployeeUsername() throws UserNotLoggedInException, ManagerCannotHaveRequestsException {
        checkLoggedInAsEmployee();
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
