package com.work.teammanagement.model.databases;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.work.teammanagement.LoggedInUser;
import com.work.teammanagement.exceptions.NotEnoughPrivilegesException;
import com.work.teammanagement.exceptions.ManagerCannotHaveRequestsException;
import com.work.teammanagement.exceptions.UserNotFoundException;
import com.work.teammanagement.exceptions.UserNotLoggedInException;
import com.work.teammanagement.model.EmployeeRequest;
import com.work.teammanagement.model.users.User;
import com.work.teammanagement.model.users.UserRole;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

public final class EmployeeRequestsDB {
    private static HashMap<String, ArrayList<EmployeeRequest>> usernameToRequests = new HashMap<>();
    private static final String dbName = "RequestsDB.json";
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static boolean loaded = false;

    public static void addRequest(String requestTitle, String managerUserName) throws UserNotFoundException, UserNotLoggedInException, ManagerCannotHaveRequestsException {
        String loggedInUsername = LoggedInUser.getEmployeeUsername();
        getUserRequestsForAdding(loggedInUsername).add(new EmployeeRequest(requestTitle, managerUserName));
        saveRequestsDB(); // TODO: Not perfect as it saves the whole DB, but it is what it is
    }

    @NotNull
    private static ArrayList<EmployeeRequest> getUserRequestsForAdding(String loggedInUsername) throws UserNotFoundException {
        UsersDB.verifyUsernameExists(loggedInUsername);
        ArrayList<EmployeeRequest> requests = usernameToRequests.get(loggedInUsername);
        if (requests == null)
            throw new RuntimeException("requests should not be null");
        return requests;
    }

    @NotNull
    public static ArrayList<EmployeeRequest> getOtherUserRequests(String otherUsername) throws UserNotFoundException, NotEnoughPrivilegesException, UserNotLoggedInException {
        UsersDB.verifyUsernameExists(otherUsername);
        LoggedInUser.checkLoggedInAsManager();
        ArrayList<EmployeeRequest> requests = usernameToRequests.get(otherUsername);
        if (requests == null)
            throw new RuntimeException("requests should not be null");
        return requests;
    }

    static void initForUser(User user) throws UserNotFoundException {
        if (user.getRole() == UserRole.Manager)
            return;

        UsersDB.verifyUsernameExists(user.getManagerUsername()); // Employees must have this one set
        usernameToRequests.put(user.getUsername(), new ArrayList<>());
    }


    public static void loadRequestsDB() {
        System.out.println();
        try {
            usernameToRequests = objectMapper.readValue(Paths.get(dbName).toFile(), new TypeReference<>() {
            });
            loaded = true;
        } catch (FileNotFoundException ignored) {
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void unloadRequestsDB() {
        usernameToRequests = new HashMap<>();
        loaded = false;
    }

    public static void saveRequestsDB() {
        System.out.printf("Saving DB for '%s'%n", dbName);
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(dbName), usernameToRequests);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public static boolean isLoaded() {
        return loaded;
    }

    public static void print() {
        System.out.println("EmployeeRequestsDB: " + usernameToRequests);
    }
}
