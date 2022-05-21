package com.work.teammanagement;

import com.work.teammanagement.model.UsersDB;
import com.work.teammanagement.services.PageSelector;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    private static Scene scene;

    @Override
    public void start(Stage primaryStage) throws IOException {
        UsersDB.loadUsersDB();

        primaryStage.setResizable(false);
        primaryStage.setTitle("Team Management");

//        Parent root = FXMLLoader.load(getClass().getResource("login-view"));
        Parent root = FXMLLoader.load(PageSelector.selectPage("test-page"));
        scene = new Scene(root, 800, 600);
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