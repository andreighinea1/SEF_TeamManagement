package com.work.teammanagement.requests;

public class EmployeeRequest {
    private String requestTitle;
    private String managerUserName;

    // This empty constructor is needed for JSON
    public EmployeeRequest() {
    }

    public EmployeeRequest(String requestTitle, String managerUserName) {
        this.requestTitle = requestTitle;
        this.managerUserName = managerUserName;
    }


    public String getRequestTitle() {
        return requestTitle;
    }

    @Override
    public String toString() {
        return requestTitle;
    }
}
