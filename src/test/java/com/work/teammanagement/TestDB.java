package com.work.teammanagement;

import com.work.teammanagement.exceptions.*;
import com.work.teammanagement.model.databases.UsersDB;
import com.work.teammanagement.model.users.UserRole;
import com.work.teammanagement.model.databases.EmployeeRequestsDB;
import com.work.teammanagement.services.LoginService;
import com.work.teammanagement.services.RegisterService;

public class TestDB {
    public static void main(String[] args) {
        System.out.println("Test loading from DBs");

        UsersDB.loadUsersDB();
        UsersDB.print();
        UsersDB.unloadUsersDB();

        EmployeeRequestsDB.loadRequestsDB();
        EmployeeRequestsDB.print();
        EmployeeRequestsDB.unloadRequestsDB();


        System.out.println();
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
        } catch (UserNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            RegisterService.registerUser("employee3", "password5", UserRole.Employee, "NaN",
                    null, null, null);
            UsersDB.print();
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (UsernameAlreadyExistsException e) {
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
            EmployeeRequestsDB.loadRequestsDB();
            EmployeeRequestsDB.print();

            LoginService.loginUser("employee1", "password5", UserRole.Employee);
            EmployeeRequestsDB.addRequest("employee1", "request2", "manager1");
            EmployeeRequestsDB.addRequest("employee1", "request3", "manager1");

            EmployeeRequestsDB.print();

            EmployeeRequestsDB.addRequest("employee2", "request3", "manager1");
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
            assert e.getMessage().equals("{username: employee2} not found!");
        } catch (UserNotLoggedInException | UserCannotHaveRequestsException e) {
            throw new RuntimeException(e);
        }

        try {
            EmployeeRequestsDB.addRequest("manager1", "request1", "manager1");
        } catch (UserCannotHaveRequestsException e) {
            System.out.println(e.getMessage());
            assert e.getMessage().equals("User manager1 cannot have requests!");
        }catch (UserNotFoundException | UserNotLoggedInException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Ended test");
    }
}
