package com.work.teammanagement.model.types;

public class EmployeeRequest {
    private String requestTitle;

    // This empty constructor is needed for JSON
    public EmployeeRequest() {
    }

    public EmployeeRequest(String requestTitle) {
        this.requestTitle = requestTitle;
    }


    public String getRequestTitle() {
        return requestTitle;
    }

    @Override
    public String toString() {
        return requestTitle;
    }
}
