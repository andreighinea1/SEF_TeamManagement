package com.work.teammanagement;

import com.work.teammanagement.model.User;
import com.work.teammanagement.model.types.UserRole;

public final class LoggedInUser {
    private static User user;

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        LoggedInUser.user = user;
    }

    public String getUsername() {
        return user.getUsername();
    }

    public String getPasswordHash() {
        return user.getPasswordHash();
    }

    public UserRole getRole() {
        return user.getRole();
    }

    public String getFullName() {
        return user.getFullName();
    }

    public String getAddress() {
        return user.getAddress();
    }

    public String getPhone() {
        return user.getPhone();
    }

    public static void print() {
        System.out.println("Logged in user: " + user);
    }
}
