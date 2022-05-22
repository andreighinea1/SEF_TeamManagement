package com.work.teammanagement;

import com.work.teammanagement.exceptions.NotEnoughPrivilegesException;
import com.work.teammanagement.exceptions.UserNotFoundException;
import com.work.teammanagement.exceptions.UserNotLoggedInException;
import com.work.teammanagement.exceptions.UsernameAlreadyExistsException;
import com.work.teammanagement.model.UsersDB;
import com.work.teammanagement.model.users.UserRole;
import com.work.teammanagement.services.EmployeeRequestsService;
import com.work.teammanagement.services.LoginService;
import com.work.teammanagement.services.RegisterService;

public class TestUsersDB {
    public static void main(String[] args) {
        System.out.println("Test register");
        try {
            RegisterService.registerUser("manager1", "password1", UserRole.Manager, "this should be reset",
                    "This is a name", "Some address", "088018805255081");
            RegisterService.registerUser("employee1", "password5", UserRole.Employee, "manager1",
                    null, null, null);

            RegisterService.registerUser("manager1", "different password", UserRole.Manager, "",
                    null, null, null);

            UsersDB.loadUsersDB();
            UsersDB.print();
        } catch (UsernameAlreadyExistsException e) {
            System.out.println(e.getMessage());
        }
        try {
            EmployeeRequestsService.addRequest("employee2", "request3", "manager1");
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (NotEnoughPrivilegesException e) {
            throw new RuntimeException(e);
        }

        System.out.println();
        System.out.println("Test login");
        try {
            LoginService.loginUser("manager1", "password1", UserRole.Manager);
            LoggedInUser.print();

            LoginService.loginUser("employee1", "password5", UserRole.Employee);
            LoggedInUser.print();

            LoginService.loginUser("employee1", "password5", UserRole.Manager);
            LoggedInUser.print();
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
        }

        System.out.println();
        System.out.println("Test Requests");
        try {
            UsersDB.loadUsersDB();
            LoginService.loginUser("manager1", "password1", UserRole.Manager);

            EmployeeRequestsService.addRequest("manager1", "request1", "manager1");
            EmployeeRequestsService.addRequest("manager1", "request2", "manager1");
            EmployeeRequestsService.addRequest("employee1", "request3", "manager1");

            EmployeeRequestsService.print();
        } catch (UserNotFoundException | NotEnoughPrivilegesException e) {
            throw new RuntimeException(e);
        }


        System.out.println("Ended test");
    }
}
