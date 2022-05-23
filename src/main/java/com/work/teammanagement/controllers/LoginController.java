package com.work.teammanagement.controllers;

import com.work.teammanagement.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController {
    @FXML
    private Button exitButton;
    @FXML
    private Button goToRegisterPageButton;
    @FXML
    private Button loginButton;
    @FXML
    private ChoiceBox <String> roleChoiceBox;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordPasswordField;

    private String[] roles = {"Manager", "Employee"};

    public LoginController() {
    }

    public void initialize(URL arg0, ResourceBundle arg1)
    {
        roleChoiceBox.getItems().addAll(roles);
    }

    public void exitApplication() {
        javafx.application.Platform.exit();
    }

    public void goToRegisterPage(ActionEvent actionEvent) {
        Main.changeScene("register-page");
    }

    public void login(ActionEvent actionEvent) {
        Main.changeScene("main");
    }
}
