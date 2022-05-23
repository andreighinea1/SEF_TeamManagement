package com.work.teammanagement.controllers;

import com.work.teammanagement.Main;
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
        Main.changeScene("list-of-request-page.fxml");
    }

    public void goToCallToOfficePage(ActionEvent event) throws IOException {
        Main.changeScene("call-to-office-page.fxml");
    }

    public void goToListOfEmployeesPage(ActionEvent event) throws IOException {
        Main.changeScene("list-of-employees-page.fxml");
    }

    public void goToLogoutPage(ActionEvent event) throws IOException {
        Main.changeScene("logout-page.fxml");
    }
}
