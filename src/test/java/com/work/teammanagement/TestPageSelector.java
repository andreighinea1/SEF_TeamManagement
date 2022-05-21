package com.work.teammanagement;

import com.work.teammanagement.exceptions.UserNotFoundException;
import com.work.teammanagement.model.UsersDB;
import com.work.teammanagement.model.users.UserRole;
import com.work.teammanagement.services.LoginService;
import com.work.teammanagement.services.PageSelector;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

import static javafx.application.Application.launch;

public class TestPageSelector extends Application {
    private static Scene scene;

    @Override
    public void start(Stage primaryStage) throws IOException {
        UsersDB.loadUsersDB();

        primaryStage.setResizable(false);
        primaryStage.setTitle("TEST");

        Parent root = FXMLLoader.load(PageSelector.selectPage(TestPageSelector.class, "test-page"));
        scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();

        startTest();
    }

    public static void changeScene(String pageName) {
        try {
            Parent root = FXMLLoader.load(PageSelector.selectPage(TestPageSelector.class, pageName));
            scene.setRoot(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static PauseTransition pauseTransition;

    public static void startTest() {
        System.out.println();
        System.out.println("Test page selection");
        pauseTransition = new PauseTransition(Duration.seconds(1));

        pauseTransition.setOnFinished(evt -> testScene1());
        pauseTransition.playFromStart();
    }

    private static void testScene1() {
        pauseTransition.stop();
        LoginService.logoutUser();
        TestPageSelector.changeScene("test-page");

        pauseTransition.setOnFinished(evt -> testScene2());
        pauseTransition.playFromStart();
    }

    private static void testScene2() {
        try {
            pauseTransition.stop();
            LoginService.loginUser("manager1", "password1", UserRole.Manager);
            TestPageSelector.changeScene("test-page-afterlogin");

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
            TestPageSelector.changeScene("test-page-afterlogin");
        } catch (UserNotFoundException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Ended test");
    }

    public static void main(String[] args) {
        TestUsersDB.main(args);
        launch(args);
    }
}
