package com.work.teammanagement.model.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.work.teammanagement.model.serializing.ApprovedStatusFromStrConverter;
import com.work.teammanagement.model.serializing.ApprovedStatusToStrConverter;

public class EmployeeRequest {
    private String requestTitle;
    private String managerUserName;

    @JsonSerialize(converter = ApprovedStatusToStrConverter.class)
    @JsonDeserialize(converter = ApprovedStatusFromStrConverter.class)
    private ApprovalStatus approvalStatus;


    // This empty constructor is needed for JSON
    public EmployeeRequest() {
    }

    public EmployeeRequest(String requestTitle, String managerUserName) {
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
                "}\n";
    }
}
