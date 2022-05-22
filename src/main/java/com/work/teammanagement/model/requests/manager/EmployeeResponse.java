package com.work.teammanagement.model.requests.manager;

public class EmployeeResponse {
    public final boolean canArriveToday;
    public final String arrivalTime;

    public EmployeeResponse(boolean canArriveToday, String arrivalTime) {
        this.canArriveToday = canArriveToday;
        this.arrivalTime = arrivalTime;
    }

    @Override
    public String toString() {
        return "EmployeeResponse{" +
                "canArriveToday=" + canArriveToday +
                ", arrivalTime='" + arrivalTime + '\'' +
                '}';
    }
}
