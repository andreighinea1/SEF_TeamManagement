package com.work.teammanagement.model.databases;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.work.teammanagement.exceptions.UserNotFoundException;
import com.work.teammanagement.exceptions.UsernameAlreadyExistsException;
import com.work.teammanagement.model.users.User;
import com.work.teammanagement.model.users.UserRole;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

public final class UsersDB {
    private static ArrayList<User> users = new ArrayList<>();
    private static final String dbName = "UsersDB.json";
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static boolean loaded = false;

    public static void addToDB(User user) throws UsernameAlreadyExistsException, UserNotFoundException {
        verifyUsernameDoesNotExist(user.getUsername());
        EmployeeRequestsDB.initForUser(user);
        users.add(user);
        saveUsersDB(); // TODO: Not perfect as it saves the whole DB, but it is what it is
    }

    public static void addToDB(String username, String password, UserRole role, String managerUsername, String fullName, String address,
                               String phone) throws UsernameAlreadyExistsException, UserNotFoundException {
        addToDB(new User(username, password, role, managerUsername, fullName, address, phone));
    }

    private static void verifyUsernameDoesNotExist(String username) throws UsernameAlreadyExistsException {
        for (User user : users) {
            if (username.equals(user.getUsername()))
                throw new UsernameAlreadyExistsException(username);
        }
    }

    public static void verifyUsernameExists(String username) throws UserNotFoundException {
        for (User user : users) {
            if (user.getUsername().equals(username))
                return;
        }
        throw new UserNotFoundException(username);
    }

    public static User findUser(String username, String password, UserRole role) throws UserNotFoundException {
        for (User user : users) {
            if (user.equals(username, password, role))
                return user;
        }
        throw new UserNotFoundException(username, role);
    }

    public static UserRole findUserRole(String username) throws UserNotFoundException {
        for (User user : users) {
            if (user.getUsername().equals(username))
                return user.getRole();
        }
        throw new UserNotFoundException(username);
    }


    public static void loadUsersDB() {
        System.out.println();
        try {
            users = objectMapper.readValue(Paths.get(dbName).toFile(), new TypeReference<>() {
            });
        loaded = true;
        } catch (FileNotFoundException ignored) {
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void unloadUsersDB() {
        users = new ArrayList<>();
        loaded = false;
    }

    public static void saveUsersDB() {
        System.out.printf("Saving DB for '%s'%n", dbName);
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(dbName), users);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public static boolean isLoaded() {
        return loaded;
    }


    public static void print() {
        System.out.print("UsersDB: [");
        for (User user : users) {
            System.out.println(user + ", ");
        }
        System.out.println("]");
    }
}
