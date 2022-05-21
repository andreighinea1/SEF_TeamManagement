package com.work.teammanagement;

import com.work.teammanagement.exceptions.UsernameAlreadyExistsException;
import com.work.teammanagement.model.User;
import com.work.teammanagement.model.UsersDB;
import com.work.teammanagement.model.types.UserType;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    private static Scene scene;
    private static Main instance;

    @Override
    public void start(Stage primaryStage) throws IOException {
        try {
            UsersDB.addUser(new User("abc", "123456", UserType.Manager));
        } catch (UsernameAlreadyExistsException e) {
            throw new RuntimeException(e);
        }
        UsersDB.loadUsersDB();

//        instance = this;
//
//        primaryStage.setResizable(false);
//        primaryStage.setTitle("Team Management");
//
//        Parent root = FXMLLoader.load(getClass().getResource("login-view.fxml"));
//        scene = new Scene(root, 800, 600);
//        primaryStage.setScene(scene);
//        primaryStage.show();
    }

    public static void changeScene(String fxml) {
        try {
            scene.setRoot(FXMLLoader.load(instance.getClass().getResource(fxml)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}