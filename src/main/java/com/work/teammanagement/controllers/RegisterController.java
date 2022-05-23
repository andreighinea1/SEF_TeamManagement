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

public class RegisterController {
    @FXML
    private Button goToLoginPageButton;
    @FXML
    private Button registerButton;
    @FXML
    private Button exitButton;
    @FXML
    private ChoiceBox <String> roleChoiceBox;
    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField fullNameTextField;
    @FXML
    private TextField addressTextField;
    @FXML
    private TextField phoneNoTextField;
    @FXML
    private PasswordField passwordPasswordField;

    private String[] roles = {"Manager", "Employee"};

    public RegisterController()
    {
    }

    public void initialize(URL arg0, ResourceBundle arg1)
    {
        roleChoiceBox.getItems().addAll(roles);
    }

    public void goToLoginPage(ActionEvent actionEvent) {
        Main.changeScene("login-page");
    }

    public void register(ActionEvent actionEvent)
    {
        Main.changeScene("main");
    }


}
