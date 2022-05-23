package com.work.teammanagement.controllers;

import com.work.teammanagement.Main;
import com.work.teammanagement.PopupWindow;
import com.work.teammanagement.exceptions.*;
import com.work.teammanagement.model.databases.ManagerCallRequestsDB;
import com.work.teammanagement.services.ManagerCallRequestService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.control.CheckBox;

import java.io.IOException;

public class CallToOfficeController {
    @FXML
    private TextField usernameTextField;
    @FXML
    private Label messageLabel;
    @FXML
    private Label detailsLabel;
    @FXML
    private HBox actionParent;
    @FXML
    private Button callToOfficeCancelButton;
    @FXML
    private HBox okParent;
    @FXML
    private Button okButton;

    @FXML
    private CheckBox correctCheckBox;

    @FXML
    private Button cancelButton;


    public void cancelMethod(ActionEvent event) throws IOException {
        Main.changeScene("menu");
    }

    public void ok(ActionEvent event) throws IOException, UserNotLoggedInException, NotEmployeeException, NotEnoughPrivilegesException, NotManagerException {
        if(correctCheckBox.isSelected()) {
            try {
                ManagerCallRequestService.addRequest("Call to office", usernameTextField.getText());
                Main.changeScene("menu");
            } catch (UserNotFoundException ignored){
                PopupWindow.openPopup("register-error");
            }
        }
    }
}
