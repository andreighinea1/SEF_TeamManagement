package com.work.teammanagement.model.databases;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.work.teammanagement.LoggedInUser;
import com.work.teammanagement.exceptions.*;
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
        usernameToRequests.putIfAbsent(loggedInUsername, new ArrayList<>()); // IfAbsent because we are adding
        ArrayList<EmployeeRequest> requests = usernameToRequests.get(loggedInUsername);
        if (requests == null)
            throw new RuntimeException("requests should not be null");
        return requests;
    }

    @NotNull
    public static ArrayList<EmployeeRequest> getUserRequestsForManager(String otherUsername) throws UserNotFoundException, NotEnoughPrivilegesException, UserNotLoggedInException, ManagerMismatchException, UserNoRequestsException {
        UsersDB.verifyUsernameExists(otherUsername);
        LoggedInUser.checkLoggedInAsManager();
        LoggedInUser.checkAssignedManager(otherUsername);

        ArrayList<EmployeeRequest> requests = usernameToRequests.get(otherUsername);
        if (requests == null)
            throw new UserNoRequestsException(otherUsername);
        return requests;
    }


    public static void loadRequestsDB() {
        try {
            HashMap<String, ArrayList<EmployeeRequest>> temp = objectMapper.readValue(Paths.get(dbName).toFile(), new TypeReference<>() {
            });
            if(temp != null) {
                usernameToRequests = temp;
                loaded = true;
            }
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
