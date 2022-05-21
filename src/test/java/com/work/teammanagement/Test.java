package com.work.teammanagement;

import com.work.teammanagement.services.PageSelector;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Test extends Application {
    private static Scene scene;

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setResizable(false);
        primaryStage.setTitle("TEST");

        Parent root = FXMLLoader.load(PageSelector.selectPage(Test.class, "test-page"));
        scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();

        TestUsersDB.testDB();
    }

    public static void changeScene(String pageName) {
        try {
            Parent root = FXMLLoader.load(PageSelector.selectPage(Test.class, pageName));
            scene.setRoot(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}