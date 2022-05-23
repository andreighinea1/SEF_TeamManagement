package com.work.teammanagement.controllers;

import com.work.teammanagement.exceptions.NotEnoughPrivilegesException;
import com.work.teammanagement.exceptions.UserNotLoggedInException;
import com.work.teammanagement.model.databases.UsersDB;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class EmployeeListController {
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
}
