package com.work.teammanagement.services;

import com.work.teammanagement.exceptions.ManagerCannotHaveRequestsException;
import com.work.teammanagement.exceptions.NotManagerException;
import com.work.teammanagement.exceptions.UserNotFoundException;
import com.work.teammanagement.exceptions.UserNotLoggedInException;
import com.work.teammanagement.model.databases.EmployeeRequestsDB;

public final class EmployeeRequestService {
    public static void planHoliday(String startDate, String endDate, String requestText, String managerUserName) throws UserNotFoundException, ManagerCannotHaveRequestsException, UserNotLoggedInException, NotManagerException {
        EmployeeRequestsDB.addRequest("Vacation: " + startDate + "-" + endDate, requestText, managerUserName);
    }

    public static void requestWorkFromHome(String startDate, String endDate, String requestText, String managerUserName) throws UserNotFoundException, ManagerCannotHaveRequestsException, UserNotLoggedInException, NotManagerException {
        EmployeeRequestsDB.addRequest("Work from home: " + startDate + "-" + endDate, requestText, managerUserName);
    }

    public static void requestShortTimeAbsence(String date, String requestText, String managerUserName) throws UserNotFoundException, ManagerCannotHaveRequestsException, UserNotLoggedInException, NotManagerException {
        EmployeeRequestsDB.addRequest("Short time absence: " + date, requestText, managerUserName);
    }
}
