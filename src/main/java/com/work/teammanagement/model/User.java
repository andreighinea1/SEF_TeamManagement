package com.work.teammanagement.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.work.teammanagement.model.serializing.UserTypeFromStrConverter;
import com.work.teammanagement.model.serializing.UserTypeToStrConverter;
import com.work.teammanagement.model.types.UserType;

import static com.work.teammanagement.services.EncodingService.encodePassword;

public class User {
    private String username;
    private String passwordHash;
    @JsonSerialize(converter = UserTypeToStrConverter.class)
    @JsonDeserialize(converter = UserTypeFromStrConverter.class)
    private UserType role;


    // This empty constructor is needed for JSON (just like the unused getters)
    public User() {
    }

    public User(String username, String password, UserType role) {
        this.username = username;
        this.passwordHash = encodePassword(username, password);
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public UserType getRole() {
        return role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!username.equals(user.username)) return false;
        if (!passwordHash.equals(user.passwordHash)) return false;
        return role.equals(user.role);
    }

    public boolean equals(String username, String password, UserType role) {
        if (!this.username.equals(username)) return false;
        if (!passwordHash.equals(encodePassword(username, password))) return false;
        return this.role == role;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", passwordHash='" + passwordHash + '\'' +
                ", role=" + role +
                '}';
    }
}
