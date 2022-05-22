package com.work.teammanagement.model.databases;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.work.teammanagement.model.users.LoggedInUser;
import com.work.teammanagement.exceptions.*;
import com.work.teammanagement.model.requests.manager.ManagerCallRequest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;

public final class ManagerCallRequestsDB {
    private static HashMap<String, ManagerCallRequest> employeeUsernameToRequests = new HashMap<>();
    private static final String dbName = "ManagerCallRequestsDB.json";
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static boolean loaded = false;

    // Manager adds the request
    public static void addRequest(String requestTitle, String calledEmployeeUsername) throws UserNotLoggedInException, NotEnoughPrivilegesException, UserNotFoundException, NotEmployeeException, NotManagerException {
        String loggedInManagerUsername = LoggedInUser.getManagerUsername();
        UsersDB.checkIsEmployee(calledEmployeeUsername);
        employeeUsernameToRequests.put(calledEmployeeUsername, new ManagerCallRequest(requestTitle, loggedInManagerUsername));
        saveRequestsDB(); // TODO: Not perfect as it saves the whole DB, but it is what it is
    }

    // Employee responds to the request
    public static void respondToRequest(boolean canArriveToday, String arrivalTime) throws ManagerCannotHaveRequestsException, UserNotLoggedInException, NoManagerCallRequestsException {
        getRequestForLoggedInEmployee().updateEmployeeResponse(canArriveToday, arrivalTime);
        saveRequestsDB(); // TODO: Not perfect as it saves the whole DB, but it is what it is
    }

    private static ManagerCallRequest getRequestForLoggedInEmployee() throws NoManagerCallRequestsException, ManagerCannotHaveRequestsException, UserNotLoggedInException {
        String loggedInEmployeeUsername = LoggedInUser.getEmployeeUsername();
        ManagerCallRequest managerCallRequest = employeeUsernameToRequests.get(loggedInEmployeeUsername);
        if (managerCallRequest == null)
            throw new NoManagerCallRequestsException(loggedInEmployeeUsername);
        return managerCallRequest;
    }

    // Manager acknowledges the response (removed from DB)
    public static void acknowledgeResponse(String employeeUsername) throws UserNotLoggedInException, NotEnoughPrivilegesException {
        LoggedInUser.checkLoggedInAsManager();
        employeeUsernameToRequests.remove(employeeUsername);
        saveRequestsDB(); // TODO: Not perfect as it saves the whole DB, but it is what it is
    }


    public static void loadRequestsDB() {
        try {
            HashMap<String, ManagerCallRequest> temp = objectMapper.readValue(Paths.get(dbName).toFile(), new TypeReference<>() {
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
        System.out.println("ManagerCallRequestsDB: " + employeeUsernameToRequests);
    }
}
