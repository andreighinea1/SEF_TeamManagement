package com.work.teammanagement.model.requests.manager;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.work.teammanagement.exceptions.NotEmployeeException;
import com.work.teammanagement.exceptions.UserNotFoundException;
import com.work.teammanagement.model.databases.UsersDB;
import com.work.teammanagement.model.requests.employee.serializing.ApprovalStatusFromStrConverter;
import com.work.teammanagement.model.requests.employee.serializing.ApprovalStatusToStrConverter;

public class ManagerRequest {
    private String requestTitle;
    private String calledEmployeeUsername;

    @JsonSerialize(converter = ApprovalStatusToStrConverter.class)
    @JsonDeserialize(converter = ApprovalStatusFromStrConverter.class)
    private EmployeeResponse employeeResponse;


    // This empty constructor is needed for JSON
    public ManagerRequest() {
    }

    public ManagerRequest(String requestTitle, String calledEmployeeUsername) throws UserNotFoundException, NotEmployeeException {
        UsersDB.checkIsEmployee(calledEmployeeUsername);

        this.requestTitle = requestTitle;
        this.calledEmployeeUsername = calledEmployeeUsername;
        this.employeeResponse = null;
    }


    public String getRequestTitle() {
        return requestTitle;
    }

    public String getCalledEmployeeUsername() {
        return calledEmployeeUsername;
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
                ", calledEmployeeUsername='" + calledEmployeeUsername + '\'' +
                ", employeeResponse=" + employeeResponse +
                '}';
    }
}
