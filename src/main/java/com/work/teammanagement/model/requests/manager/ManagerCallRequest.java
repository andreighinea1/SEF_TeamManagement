package com.work.teammanagement.model.requests.manager;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.work.teammanagement.exceptions.NotManagerException;
import com.work.teammanagement.exceptions.UserNotFoundException;
import com.work.teammanagement.model.databases.UsersDB;
import com.work.teammanagement.model.requests.employee.serializing.ApprovalStatusFromStrConverter;
import com.work.teammanagement.model.requests.employee.serializing.ApprovalStatusToStrConverter;

public class ManagerCallRequest {
    private String requestTitle;
    private String managerUsername; // Manager to which the employee has to respond to
    private EmployeeResponse employeeResponse;


    // This empty constructor is needed for JSON
    public ManagerCallRequest() {
    }

    public ManagerCallRequest(String requestTitle, String managerUsername) throws UserNotFoundException, NotManagerException {
        UsersDB.checkIsManager(managerUsername);

        this.requestTitle = requestTitle;
        this.managerUsername = managerUsername;
        this.employeeResponse = null;
    }


    public String getRequestTitle() {
        return requestTitle;
    }

    public String getManagerUsername() {
        return managerUsername;
    }

    public EmployeeResponse getEmployeeResponse() {
        return employeeResponse;
    }

    public void updateEmployeeResponse(boolean canArriveToday, String arrivalTime) {
        employeeResponse = new EmployeeResponse(canArriveToday, arrivalTime);
    }

    @Override
    public String toString() {
        return "ManagerRequest{" +
                "requestTitle='" + requestTitle + '\'' +
                ", managerUsername='" + managerUsername + '\'' +
                ", employeeResponse=" + employeeResponse +
                '}';
    }
}
