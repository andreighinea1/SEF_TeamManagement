package com.work.teammanagement.model.databases;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.work.teammanagement.model.users.LoggedInUser;
import com.work.teammanagement.exceptions.*;
import com.work.teammanagement.model.requests.employee.EmployeeRequest;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

public final class EmployeeRequestsDB {
    private static HashMap<String, ArrayList<EmployeeRequest>> employeeUsernameToRequests = new HashMap<>();
    private static final String dbName = "EmployeeRequestsDB.json";
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static boolean loaded = false;

    public static void addRequest(String requestTitle, String requestText, String managerUserName) throws UserNotFoundException, UserNotLoggedInException, ManagerCannotHaveRequestsException, NotManagerException {
        getUserRequestsForLoggedInUser().add(new EmployeeRequest(requestTitle, requestText, managerUserName));
        saveRequestsDB(); // TODO: Not perfect as it saves the whole DB, but it is what it is
    }

    @NotNull
    private static ArrayList<EmployeeRequest> getUserRequestsForLoggedInUser() throws ManagerCannotHaveRequestsException, UserNotLoggedInException {
        String loggedInUsername = LoggedInUser.getEmployeeUsername();
        employeeUsernameToRequests.putIfAbsent(loggedInUsername, new ArrayList<>()); // IfAbsent because we are adding
        ArrayList<EmployeeRequest> requests = employeeUsernameToRequests.get(loggedInUsername);
        if (requests == null)
            throw new RuntimeException("requests should not be null");
        return requests;
    }

    @NotNull
    public static ArrayList<EmployeeRequest> getUserRequestsForManager(String otherUsername) throws UserNotFoundException, NotEnoughPrivilegesException, UserNotLoggedInException, ManagerMismatchException, NoEmployeeRequestsException {
        UsersDB.verifyUsernameExists(otherUsername);
        LoggedInUser.checkLoggedInAsManager();
        LoggedInUser.checkAssignedManager(otherUsername);

        ArrayList<EmployeeRequest> employeeRequests = employeeUsernameToRequests.get(otherUsername);
        if (employeeRequests == null)
            throw new NoEmployeeRequestsException(otherUsername);
        return employeeRequests;
    }


    public static void loadRequestsDB() {
        try {
            HashMap<String, ArrayList<EmployeeRequest>> temp = objectMapper.readValue(Paths.get(dbName).toFile(), new TypeReference<>() {
            });
            if (temp != null) {
                employeeUsernameToRequests = temp;
                loaded = true;
            }
        } catch (FileNotFoundException ignored) {
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void unloadRequestsDB() {
        employeeUsernameToRequests = new HashMap<>();
        loaded = false;
    }

    public static void saveRequestsDB() {
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(dbName), employeeUsernameToRequests);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public static boolean isLoaded() {
        return loaded;
    }

    public static void print() {
        System.out.println("EmployeeRequestsDB: " + employeeUsernameToRequests);
    }
}
