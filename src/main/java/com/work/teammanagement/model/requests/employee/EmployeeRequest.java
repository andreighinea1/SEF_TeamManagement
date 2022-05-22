package com.work.teammanagement.model.requests.employee;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.work.teammanagement.exceptions.NotEnoughPrivilegesException;
import com.work.teammanagement.exceptions.NotManagerException;
import com.work.teammanagement.exceptions.UserNotFoundException;
import com.work.teammanagement.model.databases.UsersDB;
import com.work.teammanagement.model.requests.employee.serializing.ApprovalStatusFromStrConverter;
import com.work.teammanagement.model.requests.employee.serializing.ApprovalStatusToStrConverter;

public class EmployeeRequest {
    private String requestTitle;
    private String managerUserName;

    @JsonSerialize(converter = ApprovalStatusToStrConverter.class)
    @JsonDeserialize(converter = ApprovalStatusFromStrConverter.class)
    private ApprovalStatus approvalStatus;


    // This empty constructor is needed for JSON
    public EmployeeRequest() {
    }

    public EmployeeRequest(String requestTitle, String managerUserName) throws UserNotFoundException, NotManagerException {
        UsersDB.checkIsManager(managerUserName);

        this.requestTitle = requestTitle;
        this.managerUserName = managerUserName;
        this.approvalStatus = ApprovalStatus.Pending;
    }


    public String getRequestTitle() {
        return requestTitle;
    }

    public String getManagerUserName() {
        return managerUserName;
    }


    public ApprovalStatus getApprovalStatus() {
        return approvalStatus;
    }

    public void approve() {
        approvalStatus = ApprovalStatus.Approved;
    }

    public void reject() {
        approvalStatus = ApprovalStatus.Rejected;
    }


    @Override
    public String toString() {
        return "EmployeeRequest{" +
                "requestTitle='" + requestTitle + '\'' +
                ", managerUserName='" + managerUserName + '\'' +
                ", approvalStatus=" + approvalStatus +
                "}";
    }
}
