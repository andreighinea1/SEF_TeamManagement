package com.work.teammanagement.controllers;

import com.work.teammanagement.Main;
import com.work.teammanagement.exceptions.NotEnoughPrivilegesException;
import com.work.teammanagement.exceptions.UserNotLoggedInException;
import com.work.teammanagement.model.databases.UsersDB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class EmployeeListController {
    public Button backButton;
    @FXML
    private ListView<String> employeeListView;
    @FXML
    private Button exitButton;

    public void exitApplication() {
        javafx.application.Platform.exit();
    }

    public void initialize() throws UserNotLoggedInException, NotEnoughPrivilegesException {
        employeeListView.getItems().addAll(UsersDB.getEmployeeListForList());
    }

    public void back(ActionEvent actionEvent) {
        Main.changeScene("menu");
    }
}
