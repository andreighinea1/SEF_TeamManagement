package com.work.teammanagement;

import com.work.teammanagement.model.User;
import com.work.teammanagement.model.types.UserRole;

public final class LoggedInUser {
    private static User user;


    public static void loginUser(User user) {
        LoggedInUser.user = user;
    }

    public static void logoutUser() {
        LoggedInUser.user = null;
    }


    public static boolean isLoggedInAsManager() {
        return user != null && user.isManager();
    }

    public static boolean isLoggedInAsEmployee() {
        return user != null && user.isEmployee();
    }

    public static boolean isLoggedIn() {
        return user != null;
    }


    public static String getUsername() {
        return user.getUsername();
    }

    public static String getPasswordHash() {
        return user.getPasswordHash();
    }

    public static UserRole getRole() {
        return user.getRole();
    }

    public static String getFullName() {
        return user.getFullName();
    }

    public static String getAddress() {
        return user.getAddress();
    }

    public static String getPhone() {
        return user.getPhone();
    }

    public static void print() {
        System.out.println("Logged in user: " + user);
    }
}
