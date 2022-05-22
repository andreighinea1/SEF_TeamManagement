package com.work.teammanagement.model.databases;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.work.teammanagement.LoggedInUser;
import com.work.teammanagement.exceptions.*;
import com.work.teammanagement.model.requests.employee.EmployeeRequest;
import com.work.teammanagement.model.requests.manager.ManagerRequest;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

public final class ManagerRequestsDB {
    private static HashMap<String, ArrayList<ManagerRequest>> usernameToRequests = new HashMap<>();
    private static final String dbName = "ManagerRequestsDB.json";
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static boolean loaded = false;

    public static void addRequest(String requestTitle, String calledEmployeeUsername) throws UserNotLoggedInException, NotEnoughPrivilegesException, UserNotFoundException, NotEmployeeException {
        String loggedInUsername = LoggedInUser.getManagerUsername();
        getUserRequestsForAdding(loggedInUsername).add(new ManagerRequest(requestTitle, calledEmployeeUsername));
        saveRequestsDB(); // TODO: Not perfect as it saves the whole DB, but it is what it is
    }

    @NotNull
    private static ArrayList<ManagerRequest> getUserRequestsForAdding(String loggedInUsername) throws UserNotFoundException {
        UsersDB.verifyUsernameExists(loggedInUsername);
        usernameToRequests.putIfAbsent(loggedInUsername, new ArrayList<>()); // IfAbsent because we are adding
        ArrayList<ManagerRequest> requests = usernameToRequests.get(loggedInUsername);
        if (requests == null)
            throw new RuntimeException("requests should not be null");
        return requests;
    }


    public static void loadRequestsDB() {
        try {
            HashMap<String, ArrayList<ManagerRequest>> temp = objectMapper.readValue(Paths.get(dbName).toFile(), new TypeReference<>() {
            });
            if (temp != null) {
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
        System.out.println("ManagerRequestsDB: " + usernameToRequests);
    }
}
