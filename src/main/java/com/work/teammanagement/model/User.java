package com.work.teammanagement.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.work.teammanagement.model.serializing.UserRoleFromStrConverter;
import com.work.teammanagement.model.serializing.UserRoleToStrConverter;
import com.work.teammanagement.model.types.UserRole;

import static com.work.teammanagement.services.EncodingService.encodePassword;

public class User {
    private String username;
    private String passwordHash;
    @JsonSerialize(converter = UserRoleToStrConverter.class)
    @JsonDeserialize(converter = UserRoleFromStrConverter.class)
    private UserRole role;
    private String fullName;
    private String address;
    private String phone;


    // This empty constructor is needed for JSON (just like the unused getters)
    public User() {
    }

    public User(String username, String password, UserRole role, String fullName, String address, String phone) {
        this.username = username;
        this.passwordHash = encodePassword(username, password);
        this.role = role;
        this.fullName = fullName;
        this.address = address;
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public UserRole getRole() {
        return role;
    }

    public String getFullName() {
        return fullName;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
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

    public boolean equals(String username, String password, UserRole role) {
        if (!this.username.equals(username)) return false;
        if (!passwordHash.equals(encodePassword(username, password))) return false;
        return this.role == role;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", passwordHash='some_hash'" +
                ", role=" + role +
                ", fullName='" + fullName + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
