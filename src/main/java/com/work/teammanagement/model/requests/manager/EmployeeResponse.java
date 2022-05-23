package com.work.teammanagement.model.requests.manager;

public class EmployeeResponse {
    private boolean canArriveToday;
    private String arrivalTime;

    public EmployeeResponse(){}

    public EmployeeResponse(boolean canArriveToday, String arrivalTime) {
        this.canArriveToday = canArriveToday;
        this.arrivalTime = arrivalTime;
    }


    public boolean isCanArriveToday() {
        return canArriveToday;
    }

    public void setCanArriveToday(boolean canArriveToday) {
        this.canArriveToday = canArriveToday;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
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
