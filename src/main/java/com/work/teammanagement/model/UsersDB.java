package com.work.teammanagement.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.work.teammanagement.exceptions.UsernameAlreadyExistsException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

public final class UsersDB {
    private static ArrayList<User> users = new ArrayList<>();
    private static final String dbName = "UsersDB.json";


    public static void addUser(User newUser) throws UsernameAlreadyExistsException {
        checkUserDoesNotAlreadyExist(newUser.getUsername());
        users.add(newUser);
        saveUsersDB(); // Not perfect as it saves the whole DB, but it is what it is
    }

    private static void checkUserDoesNotAlreadyExist(String username) throws UsernameAlreadyExistsException {
        for (User user : users) {
            if (username.equals(user.getUsername()))
                throw new UsernameAlreadyExistsException(username);
        }
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

    private static void saveUsersDB() {
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(dbName), users);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public static void print() {
        System.out.println(users.toString());
    }
}
