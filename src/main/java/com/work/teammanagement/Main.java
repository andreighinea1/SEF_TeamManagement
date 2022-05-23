package com.work.teammanagement;

import com.work.teammanagement.controllers.EmployeeListController;
import com.work.teammanagement.exceptions.UserNotFoundException;
import com.work.teammanagement.exceptions.UsernameAlreadyExistsException;
import com.work.teammanagement.model.databases.EmployeeRequestsDB;
import com.work.teammanagement.model.databases.ManagerCallRequestsDB;
import com.work.teammanagement.model.databases.UsersDB;
import com.work.teammanagement.model.users.UserRole;
import com.work.teammanagement.services.LoginService;
import com.work.teammanagement.services.PageSelector;
import com.work.teammanagement.services.RegisterService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    private static Stage primaryStage;
    private static Scene scene;

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Main.primaryStage = primaryStage;

        UsersDB.loadUsersDB();
        EmployeeRequestsDB.loadRequestsDB();
        ManagerCallRequestsDB.loadRequestsDB();

        primaryStage.setResizable(false);
        primaryStage.setTitle("Team Management");

//        try {
//            RegisterService.registerUser("test5", "test", UserRole.Manager, null, null, null, null);
//        } catch (UsernameAlreadyExistsException e) {
//
//        }
//        try {
//            LoginService.loginUser("test5", "test", UserRole.Manager);
//        } catch (UserNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//        Parent root = FXMLLoader.load(PageSelector.selectPage("list-of-employees-page"));

        Parent root = FXMLLoader.load(PageSelector.selectPage("login-page"));
        scene = new Scene(root, 581, 379);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void changeScene(String pageName) {
        try {
            Parent root = FXMLLoader.load(PageSelector.selectPage(pageName));
            scene.setRoot(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}