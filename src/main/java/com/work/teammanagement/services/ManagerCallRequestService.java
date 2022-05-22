package com.work.teammanagement.services;

import com.work.teammanagement.exceptions.*;
import com.work.teammanagement.model.databases.ManagerCallRequestsDB;

public final class ManagerCallRequestService {
    // Manager adds the request
    public static void addRequest(String requestTitle, String calledEmployeeUsername) throws UserNotFoundException, UserNotLoggedInException, NotEmployeeException, NotEnoughPrivilegesException, NotManagerException {
        ManagerCallRequestsDB.addRequest(requestTitle, calledEmployeeUsername);
    }

    // Employee responds to the request
    public static void respondToRequest(boolean canArriveToday, String arrivalTime) throws ManagerCannotHaveRequestsException, UserNotLoggedInException, NoManagerCallRequestsException {
        ManagerCallRequestsDB.respondToRequest(canArriveToday, arrivalTime);
    }

    // Manager acknowledges the response (removed from DB)
    public static void acknowledgeResponse(String employeeUsername) throws UserNotLoggedInException, NotEnoughPrivilegesException {
        ManagerCallRequestsDB.acknowledgeResponse(employeeUsername);
    }
}
