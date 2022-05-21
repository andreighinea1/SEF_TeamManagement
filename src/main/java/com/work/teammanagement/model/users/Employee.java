package com.work.teammanagement.model.users;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.work.teammanagement.model.UsersDB;
import com.work.teammanagement.requests.EmployeeRequest;

import java.util.ArrayList;

public class Employee extends User {
    private String managerUserName;
    private ArrayList<EmployeeRequest> requests;


    // This empty constructor is needed for JSON (just like the unused getters)
    public Employee() {
    }

    public Employee(String username, String password, UserRole role,
                    String fullName, String address, String phone,
                    String managerUserName) {
        super(username, password, role, fullName, address, phone);
        this.managerUserName = managerUserName;
    }


    public ArrayList<EmployeeRequest> getRequests() {
        return requests;
    }

    @JsonIgnore
    public Employee addRequest(String requestTitle) {
        requests.add(new EmployeeRequest(requestTitle, managerUserName));
        UsersDB.saveUsersDB(); // TODO: Not perfect as it saves the whole DB, but it is what it is
        return this; // Why return? For testing purposes, to be able to chain addRequest("a").addRequest("b")...
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + getUsername() + '\'' +
                ", passwordHash='some_hash'" +
                ", role=" + getRole() +
                ", fullName='" + getFullName() + '\'' +
                ", address='" + getAddress() + '\'' +
                ", phone='" + getPhone() + '\'' +
                ", requests=" + requests +
                '}';
    }
}
