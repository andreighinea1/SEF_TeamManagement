package com.work.teammanagement.controllers;

import com.work.teammanagement.Main;
import com.work.teammanagement.PopupWindow;
import com.work.teammanagement.exceptions.NotManagerException;
import com.work.teammanagement.exceptions.UserNotFoundException;
import com.work.teammanagement.exceptions.UsernameAlreadyExistsException;
import com.work.teammanagement.model.databases.UsersDB;
import com.work.teammanagement.model.users.UserRole;
import com.work.teammanagement.services.RegisterService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import javax.swing.text.html.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegisterController {
    @FXML
    private Button goToLoginPageButton, registerButton, exitButton;
    @FXML
    private TextField usernameTextField, fullNameTextField, addressTextField, phoneNoTextField, managerUsernameTextField;
    @FXML
    private PasswordField passwordPasswordField;
    @FXML
    private ChoiceBox<String> roleChoiceBox;
    private String[] roles = {"Employee", "Manager"};

    public void goToLoginPage(ActionEvent event) throws IOException {
        Main.changeScene("login-page");
    }

    public void register(ActionEvent event) throws IOException {
        if(roleChoiceBox.getValue() == null){
            PopupWindow.openPopup("register-error");
            return;
        }

        try {
            UserRole userRole = UserRole.valueOf(roleChoiceBox.getValue());

            String managerUsername = null;
            if (userRole == UserRole.Employee) {
                managerUsername = managerUsernameTextField.getText();
                UsersDB.checkIsManager(managerUsername);
            }

            RegisterService.registerUser(usernameTextField.getText(), passwordPasswordField.getText(),
                    userRole, managerUsername,
                    fullNameTextField.getText(), addressTextField.getText(), phoneNoTextField.getText());

            Main.changeScene("login-page");
        } catch (UsernameAlreadyExistsException | UserNotFoundException | NotManagerException e) {
            PopupWindow.openPopup("register-error");
        }
    }

    public void exitApplication() {
        javafx.application.Platform.exit();
    }

    public void initialize() {
        roleChoiceBox.getItems().addAll(roles);
    }


}
