package com.work.teammanagement;

import com.work.teammanagement.exceptions.UserNotFoundException;
import com.work.teammanagement.exceptions.UsernameAlreadyExistsException;
import com.work.teammanagement.model.UsersDB;
import com.work.teammanagement.model.types.UserRole;
import com.work.teammanagement.services.LoginService;
import com.work.teammanagement.services.RegisterService;

public class TestUsersDB {
    public static void main(String[] args) {
        System.out.println("Test register");
        try {
            RegisterService.registerUser("manager1", "password1", UserRole.Manager,
                    "This is a name", "Some address", "088018805255081")
                    .addRequest("request1").addRequest("request2");
            RegisterService.registerUser("employee1", "password5", UserRole.Employee,
                            null, null, null)
                    .addRequest("request1");

            UsersDB.loadUsersDB();
            UsersDB.print();

            RegisterService.registerUser("manager1", "different password", UserRole.Manager,
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

            LoginService.loginUser("employee1", "password5", UserRole.Manager);
            LoggedInUser.print();
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Ended test");
    }
}
