package com.work.teammanagement.requests;

import com.work.teammanagement.model.UsersDB;
import com.work.teammanagement.model.users.Employee;

import java.util.ArrayList;

public class EmployeeRequestsManager {

    public ArrayList<EmployeeRequest> getRequests() {
        return requests;
    }
    public Employee addRequest(String requestTitle, String managerUserName) {
        requests.add(new EmployeeRequest(requestTitle, managerUserName));
        UsersDB.saveUsersDB(); // TODO: Not perfect as it saves the whole DB, but it is what it is
        return this; // Why return? For testing purposes, to be able to chain addRequest("a").addRequest("b")...
    }
}
