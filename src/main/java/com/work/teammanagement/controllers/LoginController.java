package com.work.teammanagement.controllers;

import com.work.teammanagement.Main;
import com.work.teammanagement.PopupWindow;
import com.work.teammanagement.exceptions.UserNotFoundException;
import com.work.teammanagement.exceptions.UsernameAlreadyExistsException;
import com.work.teammanagement.model.users.UserRole;
import com.work.teammanagement.services.LoginService;
import com.work.teammanagement.services.RegisterService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginController {
    @FXML
    private Button goToRegisterPageButton;
    @FXML
    private Button loginButton;
    @FXML
    private Button exitButton;
    @FXML
    private PasswordField passwordPasswordField;
    @FXML
    private TextField usernameTextField;
    @FXML
    private ChoiceBox<String> roleChoiceBox;
    private String[] roles = {"Employee", "Manager"};

    public void login(ActionEvent actionEvent) throws IOException {
        try {
            LoginService.loginUser(usernameTextField.getText(), passwordPasswordField.getText(), UserRole.valueOf(roleChoiceBox.getValue()));
        }  catch (UserNotFoundException e) {
            PopupWindow.openPopup("login-error");
        }
        Main.changeScene("menu");
    }

    public void goToRegisterPage(ActionEvent actionEvent) {
        Main.changeScene("register-page");
    }

    public void exitApplication(ActionEvent actionEvent) {
        javafx.application.Platform.exit();
    }

    public void initialize() {
        roleChoiceBox.getItems().addAll(roles);
    }
}
