package com.work.teammanagement.services;

import com.work.teammanagement.LoggedInUser;
import com.work.teammanagement.exceptions.NotEnoughPrivilegesException;
import com.work.teammanagement.exceptions.UserNotFoundException;
import com.work.teammanagement.model.UsersDB;
import com.work.teammanagement.model.EmployeeRequest;
import com.work.teammanagement.model.users.User;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;

public final class EmployeeRequestsService {
    private static final HashMap<String, ArrayList<EmployeeRequest>> usernameToRequests = new HashMap<>();


    public static void addRequest(String username, String requestTitle, String managerUserName) throws UserNotFoundException, NotEnoughPrivilegesException {
        getUserRequests(username).add(new EmployeeRequest(requestTitle, managerUserName));
        UsersDB.saveUsersDB(); // TODO: Not perfect as it saves the whole DB, but it is what it is
    }

    @NotNull
    public static ArrayList<EmployeeRequest> getUserRequests(String username) throws UserNotFoundException, NotEnoughPrivilegesException {
        UsersDB.verifyUsernameExists(username);
        LoggedInUser.checkLoggedInAsManager();
        ArrayList<EmployeeRequest> requests = usernameToRequests.get(username);
        if (requests == null)
            throw new RuntimeException("requests should not be null");
        return requests;
    }

    public static void initForUsername(String username) {
        usernameToRequests.put(username, new ArrayList<>());
    }

    public static void print() {
        System.out.println(usernameToRequests);
    }
}
