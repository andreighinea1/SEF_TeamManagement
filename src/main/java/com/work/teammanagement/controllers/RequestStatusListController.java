package com.work.teammanagement.controllers;

import com.work.teammanagement.Main;
import com.work.teammanagement.exceptions.*;
import com.work.teammanagement.model.databases.UsersDB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.io.IOException;

public class RequestStatusListController {
    public ListView<String> requestsStatusListView;
    @FXML
    private Button backButton;

    public void initialize() throws UserNotLoggedInException, NotLoggedInAsEmployeeException {
        requestsStatusListView.getItems().addAll(UsersDB.getEmployeeListForStatusList());
    }

    public void goBack(ActionEvent event) throws IOException {
        Main.changeScene("menu");
    }
}
