package com.work.teammanagement;

import com.work.teammanagement.exceptions.*;
import com.work.teammanagement.model.databases.ManagerCallRequestsDB;
import com.work.teammanagement.model.databases.UsersDB;
import com.work.teammanagement.services.LoggedInUser;
import com.work.teammanagement.model.users.UserRole;
import com.work.teammanagement.model.databases.EmployeeRequestsDB;
import com.work.teammanagement.services.LoginService;
import com.work.teammanagement.services.RegisterService;

public class TestDB {
    private static void loadDBTest() {
        System.out.println("Test loading from DBs");

        UsersDB.loadUsersDB();
        UsersDB.print();
        UsersDB.unloadUsersDB();

        EmployeeRequestsDB.loadRequestsDB();
        EmployeeRequestsDB.print();
        EmployeeRequestsDB.unloadRequestsDB();

        ManagerCallRequestsDB.loadRequestsDB();
        ManagerCallRequestsDB.print();
        ManagerCallRequestsDB.unloadRequestsDB();


        UsersDB.loadUsersDB();
        EmployeeRequestsDB.loadRequestsDB();
        ManagerCallRequestsDB.loadRequestsDB();

        UsersDB.print();
        EmployeeRequestsDB.print();
        ManagerCallRequestsDB.print();

        UsersDB.unloadUsersDB();
        EmployeeRequestsDB.unloadRequestsDB();
        ManagerCallRequestsDB.unloadRequestsDB();
    }

    private static void registerTest() {
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
    }

    private static void loginTest() {
        System.out.println();
        System.out.println("Test login");
        try {
            LoginService.loginUser("manager1", "password1", UserRole.Manager);
            LoggedInUser.print();

            LoginService.loginUser("employee1", "password5", UserRole.Employee);
            LoggedInUser.print();

            LoginService.loginUser("employee2", "password5", UserRole.Employee);
            LoggedInUser.print();


            // FAIL
            LoginService.loginUser("employee1", "password5", UserRole.Manager);
            LoggedInUser.print();
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void addEmployeeRequests() {
        System.out.println();
        System.out.println("Test Add Employee Requests");
        try {
            UsersDB.loadUsersDB();
            EmployeeRequestsDB.unloadRequestsDB();

            LoginService.loginUser("employee1", "password5", UserRole.Employee);
            EmployeeRequestsDB.addRequest("request2", "Some message", "manager1");
            EmployeeRequestsDB.addRequest("request3", "Some message 2", "manager1");

            LoginService.loginUser("employee2", "password5", UserRole.Employee);
            EmployeeRequestsDB.addRequest("request_employee2_1", "Some other message", "manager2");

            EmployeeRequestsDB.print();


            // FAIL
            LoginService.loginUser("employee3", "ceva", UserRole.Employee);
            EmployeeRequestsDB.addRequest("request3", "FAIL", "manager1");
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
            assert e.getMessage().equals("{username: employee3} not found!");
        } catch (UserNotLoggedInException | NotLoggedInAsEmployeeException | NotManagerException e) {
            throw new RuntimeException(e);
        }
        try {
            // FAIL
            LoginService.loginUser("manager1", "password1", UserRole.Manager);
            EmployeeRequestsDB.addRequest("request1", "FAIL", "manager1");
        } catch (NotLoggedInAsEmployeeException e) {
            System.out.println(e.getMessage());
            assert e.getMessage().equals("User manager1 cannot have requests!");
        } catch (UserNotFoundException | UserNotLoggedInException | NotManagerException e) {
            throw new RuntimeException(e);
        }
    }

    private static void getEmployeeRequests() {
        System.out.println();
        System.out.println("Test Get Employee Requests");
        try {
            UsersDB.loadUsersDB();
            EmployeeRequestsDB.loadRequestsDB();

            LoginService.loginUser("manager1", "password1", UserRole.Manager);
            System.out.println(EmployeeRequestsDB.getUserRequests("employee1"));

            LoginService.loginUser("manager2", "123", UserRole.Manager);
            System.out.println(EmployeeRequestsDB.getUserRequests("employee2"));


            // FAIL
            System.out.println(EmployeeRequestsDB.getUserRequests("employee1"));
        } catch (UserNotFoundException | NotEnoughPrivilegesException | UserNotLoggedInException |
                 NoEmployeeRequestsException e) {
            throw new RuntimeException(e);
        } catch (ManagerMismatchException e) {
            System.out.println(e.getMessage());
        } catch (NotLoggedInAsEmployeeException e) {
            throw new RuntimeException(e);
        } catch (NotEmployeeException e) {
            throw new RuntimeException(e);
        }
        try {
            // FAIL
            LoginService.loginUser("manager1", "password1", UserRole.Manager);
            System.out.println(EmployeeRequestsDB.getUserRequests("employee3"));
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (UserNotLoggedInException | ManagerMismatchException | NotEnoughPrivilegesException |
                 NoEmployeeRequestsException e) {
            throw new RuntimeException(e);
        } catch (NotLoggedInAsEmployeeException e) {
            throw new RuntimeException(e);
        } catch (NotEmployeeException e) {
            throw new RuntimeException(e);
        }
    }

    private static void addManagerCallRequests() {
        System.out.println();
        System.out.println("Test Add Manager Requests");
        System.out.println("---------- addRequest");
        try {
            UsersDB.loadUsersDB();
            ManagerCallRequestsDB.unloadRequestsDB();

            LoginService.loginUser("manager1", "password1", UserRole.Manager);
            ManagerCallRequestsDB.addRequest("request1", "employee1");
            ManagerCallRequestsDB.addRequest("request1", "employee2");

            ManagerCallRequestsDB.print();

            // FAIL
            ManagerCallRequestsDB.addRequest("request2", "manager1");
        } catch (NotEmployeeException e) {
            System.out.println(e.getMessage());
        } catch (UserNotLoggedInException | NotEnoughPrivilegesException | UserNotFoundException |
                 NotManagerException e) {
            throw new RuntimeException(e);
        }

        System.out.println("---------- respondToRequest");
        try {
            LoginService.loginUser("employee2", "password5", UserRole.Employee);
            ManagerCallRequestsDB.respondToRequest(false, "NOPE");

            LoginService.loginUser("employee1", "password5", UserRole.Employee);
            ManagerCallRequestsDB.respondToRequest(true, "in a few hours");
            ManagerCallRequestsDB.print();

            // FAIL
            ManagerCallRequestsDB.respondToRequest(true, "in a few hours");
        } catch (NotLoggedInAsEmployeeException | UserNotLoggedInException |
                 UserNotFoundException e) {
            throw new RuntimeException(e);
        } catch (NoManagerCallRequestsException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("---------- acknowledgeResponse");
        try {
            LoginService.loginUser("manager1", "password1", UserRole.Manager);
            ManagerCallRequestsDB.acknowledgeResponse("employee1");
            ManagerCallRequestsDB.print();

            // FAIL
            LoginService.loginUser("employee1", "password5", UserRole.Employee);
            ManagerCallRequestsDB.acknowledgeResponse("employee1");
        } catch (UserNotLoggedInException | UserNotFoundException e) {
            throw new RuntimeException(e);
        } catch (NotEnoughPrivilegesException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("---------- addRequest");
        try {
            // FAIL
            LoginService.loginUser("employee1", "password5", UserRole.Employee);
            ManagerCallRequestsDB.addRequest("request3", "employee2");
        } catch (NotEnoughPrivilegesException e) {
            System.out.println(e.getMessage());
        } catch (UserNotFoundException | UserNotLoggedInException | NotEmployeeException | NotManagerException e) {
            throw new RuntimeException(e);
        }
    }

    private static void getManagerRequests() {
//        System.out.println();
//        System.out.println("Test Get Employee Requests");
//        try {
//            UsersDB.loadUsersDB();
//            ManagerCallRequestsDB.loadRequestsDB();
//
//            LoginService.loginUser("manager1", "password1", UserRole.Manager);
//            System.out.println(ManagerCallRequestsDB.getUserRequestsForManager("employee1"));
//
//            LoginService.loginUser("manager2", "123", UserRole.Manager);
//            System.out.println(ManagerCallRequestsDB.getUserRequestsForManager("employee2"));
//
//
//            // FAIL
//            System.out.println(ManagerCallRequestsDB.getUserRequestsForManager("employee1"));
//        } catch (UserNotFoundException | NotEnoughPrivilegesException | UserNotLoggedInException |
//                 UserNoRequestsException e) {
//            throw new RuntimeException(e);
//        } catch (ManagerMismatchException e) {
//            System.out.println(e.getMessage());
//        }
//        try {
//            // FAIL
//            LoginService.loginUser("manager1", "password1", UserRole.Manager);
//            System.out.println(ManagerCallRequestsDB.getUserRequestsForManager("employee3"));
//        } catch (UserNotFoundException e) {
//            System.out.println(e.getMessage());
//        } catch (UserNotLoggedInException | ManagerMismatchException | NotEnoughPrivilegesException |
//                 UserNoRequestsException e) {
//            throw new RuntimeException(e);
//        }
    }

    public static void main(String[] args) {
        loadDBTest();
        registerTest();
        loginTest();

        addEmployeeRequests();
        getEmployeeRequests();

        addManagerCallRequests();
        getManagerRequests();


        System.out.println();
        System.out.println("Ended Test");
    }
}
