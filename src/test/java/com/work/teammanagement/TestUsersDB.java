package com.work.teammanagement;

import com.work.teammanagement.exceptions.UserNotFoundException;
import com.work.teammanagement.exceptions.UsernameAlreadyExistsException;
import com.work.teammanagement.model.UsersDB;
import com.work.teammanagement.model.types.UserRole;
import com.work.teammanagement.services.LoginService;
import com.work.teammanagement.services.RegisterService;
import javafx.animation.PauseTransition;
import javafx.util.Duration;

public class TestUsersDB {
    private static PauseTransition pauseTransition;

    public static void testDB() {
        System.out.println("Test register");
        try {
            RegisterService.registerUser("manager1", "password1", UserRole.Manager, "This is a name", "Some address", "088018805255081");
            RegisterService.registerUser("employee1", "password5", UserRole.Employee, null, null, null);
            UsersDB.loadUsersDB();
            UsersDB.print();
            RegisterService.registerUser("manager1", "different password", UserRole.Manager, null, null, null);
        } catch (UsernameAlreadyExistsException e) {
            System.out.println(e);
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
            System.out.println(e);
        }

        System.out.println();
        System.out.println("Test page selection");
        {
            pauseTransition = new PauseTransition(Duration.seconds(1));

            pauseTransition.setOnFinished(evt -> testScene1());
            pauseTransition.playFromStart();
        }
    }

    private static void testScene1() {
        pauseTransition.stop();
        LoginService.logoutUser();
        Test.changeScene("test-page");

        pauseTransition.setOnFinished(evt -> testScene2());
        pauseTransition.playFromStart();
    }

    private static void testScene2() {
        try {
            pauseTransition.stop();
            LoginService.loginUser("manager1", "password1", UserRole.Manager);
            Test.changeScene("test-page-afterlogin");

            pauseTransition.setOnFinished(evt -> testScene3());
            pauseTransition.playFromStart();
        } catch (UserNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static void testScene3() {
        try {
            pauseTransition.stop();
            LoginService.loginUser("employee1", "password5", UserRole.Employee);
            Test.changeScene("test-page-afterlogin");
        } catch (UserNotFoundException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Ended test");
    }
}
