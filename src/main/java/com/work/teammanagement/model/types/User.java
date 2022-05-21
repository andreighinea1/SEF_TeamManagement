package com.work.teammanagement.model.types;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.work.teammanagement.model.UsersDB;
import com.work.teammanagement.model.serializing.UserRoleFromStrConverter;
import com.work.teammanagement.model.serializing.UserRoleToStrConverter;

import java.util.ArrayList;

import static com.work.teammanagement.services.Encoding.encodePassword;

public class User {
    private String username;
    private String passwordHash;
    @JsonSerialize(converter = UserRoleToStrConverter.class)
    @JsonDeserialize(converter = UserRoleFromStrConverter.class)
    private UserRole role;
    private String fullName;
    private String address;
    private String phone;
    private ArrayList<EmployeeRequest> requests;


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
        this.requests = new ArrayList<>();
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

    public ArrayList<EmployeeRequest> getRequests() {
        return requests;
    }

    @JsonIgnore
    public User addRequest(String requestTitle) {
        requests.add(new EmployeeRequest(requestTitle));
        UsersDB.saveUsersDB(); // TODO: Not perfect as it saves the whole DB, but it is what it is
        return this; // Why return? For testing purposes, to be able to chain addRequest("a").addRequest("b")...
    }

    @JsonIgnore
    public boolean isManager() {
        return role == UserRole.Manager;
    }

    @JsonIgnore
    public boolean isEmployee() {
        return role == UserRole.Employee;
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
                ", requests=" + requests +
                '}';
    }
}
