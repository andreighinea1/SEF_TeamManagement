package com.work.teammanagement.controllers;

import com.work.teammanagement.Main;
import com.work.teammanagement.services.LoginService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class ManagerMenuController {
    @FXML
    private Button listOfRequestsButton;

    @FXML
    private Button callToOfficeButton;

    @FXML
    private Button logoutButton;

    @FXML
    private Button listOfEmployeesButton;

    public void goToListOfRequestsPage(ActionEvent event) throws IOException {
        Main.changeScene("list-of-requests-page");
    }

    public void goToCallToOfficePage(ActionEvent event) throws IOException {
        Main.changeScene("call-to-office-page");
    }

    public void goToListOfEmployeesPage(ActionEvent event) throws IOException {
        Main.changeScene("list-of-employees-page");
    }

    public void logout(ActionEvent event) throws IOException {
        LoginService.logoutUser();
    }
}
