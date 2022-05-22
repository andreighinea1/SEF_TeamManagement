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
            RegisterService.registerUser("manager1", "password1", UserRole.Manager,
                    "this should be reset", "This is a name", "Some address", "088018805255081");
            RegisterService.registerUser("employee1", "password5", UserRole.Employee,
                    "manager1", null, null, null);
            RegisterService.registerUser("manager2", "123", UserRole.Manager,
                    null, null, null, null);
            RegisterService.registerUser("employee2", "password5", UserRole.Employee,
                    "manager2", null, null, null);


            // FAIL
            RegisterService.registerUser("manager1", "different password", UserRole.Manager, "",
                    null, null, null);
        } catch (UsernameAlreadyExistsException e) {
            System.out.println(e.getMessage());
        }

        System.out.println();
        System.out.println("Test login");
        try {
            LoginService.loginUser("manager1", "password1", UserRole.Manager);
            LoggedInUser.print();

            LoginService.loginUser("employee1", "password5", UserRole.Employee);
            LoggedInUser.print();


            // FAIL
            LoginService.loginUser("employee1", "password5", UserRole.Manager);
            LoggedInUser.print();
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
        }

        System.out.println();
        System.out.println("Test Add Requests");
        try {
            UsersDB.loadUsersDB();
            EmployeeRequestsDB.loadRequestsDB();
            EmployeeRequestsDB.print();

            LoginService.loginUser("employee1", "password5", UserRole.Employee);
            EmployeeRequestsDB.addRequest("request2", "manager1");
            EmployeeRequestsDB.addRequest("request3", "manager1");

            LoginService.loginUser("employee2", "password5", UserRole.Employee);
            EmployeeRequestsDB.addRequest("request_employee2_1", "manager2");

            EmployeeRequestsDB.print();


            // FAIL
            LoginService.loginUser("employee3", "ceva", UserRole.Employee); // here is exp
            EmployeeRequestsDB.addRequest("request3", "manager1");
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
            assert e.getMessage().equals("{username: employee3} not found!");
        } catch (UserNotLoggedInException | ManagerCannotHaveRequestsException e) {
            throw new RuntimeException(e);
        }
        try {
            // FAIL
            LoginService.loginUser("manager1", "password1", UserRole.Manager);
            EmployeeRequestsDB.addRequest("request1", "manager1");
        } catch (ManagerCannotHaveRequestsException e) {
            System.out.println(e.getMessage());
            assert e.getMessage().equals("User manager1 cannot have requests!");
        } catch (UserNotFoundException | UserNotLoggedInException e) {
            throw new RuntimeException(e);
        }

        System.out.println();
        System.out.println("Test Get Requests");
        try {
            UsersDB.loadUsersDB();
            EmployeeRequestsDB.loadRequestsDB();

            LoginService.loginUser("manager1", "password1", UserRole.Manager);
            System.out.println(EmployeeRequestsDB.getUserRequestsForManager("employee1"));

            LoginService.loginUser("manager2", "123", UserRole.Manager);
            System.out.println(EmployeeRequestsDB.getUserRequestsForManager("employee2"));


            // FAIL
            System.out.println(EmployeeRequestsDB.getUserRequestsForManager("employee1"));
        } catch (UserNotFoundException | NotEnoughPrivilegesException | UserNotLoggedInException |
                 UserNoRequestsException e) {
            throw new RuntimeException(e);
        } catch (ManagerMismatchException e) {
            System.out.println(e.getMessage());
        }
        try {
            // FAIL
            LoginService.loginUser("manager1", "password1", UserRole.Manager);
            System.out.println(EmployeeRequestsDB.getUserRequestsForManager("employee3"));
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (UserNotLoggedInException | ManagerMismatchException | NotEnoughPrivilegesException |
                 UserNoRequestsException e) {
            throw new RuntimeException(e);
        }

        System.out.println();
        System.out.println("Ended Test");
    }
}
