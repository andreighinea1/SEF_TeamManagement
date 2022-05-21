package com.work.teammanagement.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.work.teammanagement.exceptions.UserNotFoundException;
import com.work.teammanagement.exceptions.UsernameAlreadyExistsException;
import com.work.teammanagement.model.types.User;
import com.work.teammanagement.model.types.UserRole;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

public final class UsersDB {
    private static ArrayList<User> users = new ArrayList<>();
    private static final String dbName = "UsersDB.json";


    public static User addUser(User newUser) throws UsernameAlreadyExistsException {
        checkUsernameExists(newUser.getUsername());
        users.add(newUser);
        saveUsersDB(); // TODO: Not perfect as it saves the whole DB, but it is what it is

        return newUser;
    }

    public static User addUser(String username, String password, UserRole role, String fullName, String address,
                               String phone) throws UsernameAlreadyExistsException {
        return addUser(new User(username, password, role, fullName, address, phone));
    }

    private static void checkUsernameExists(String username) throws UsernameAlreadyExistsException {
        for (User user : users) {
            if (username.equals(user.getUsername()))
                throw new UsernameAlreadyExistsException(username);
        }
    }

    public static User findUser(String username, String password, UserRole role) throws UserNotFoundException {
        for (User user : users) {
            if (user.equals(username, password, role))
                return user;
        }
        throw new UserNotFoundException(username, role);
    }


    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static void loadUsersDB() {
        try {
            users = objectMapper.readValue(Paths.get(dbName).toFile(), new TypeReference<>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public static void saveUsersDB() {
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(dbName), users);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public static void print() {
        System.out.print("[");
        for (User user : users) {
            System.out.println(user + ", ");
        }
        System.out.println("]");
    }
}
