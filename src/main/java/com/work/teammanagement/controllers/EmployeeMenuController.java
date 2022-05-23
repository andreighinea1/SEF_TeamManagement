package com.work.teammanagement.controllers;

import com.work.teammanagement.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class EmployeeMenuController {
    @FXML
    private Button logoutButton;

    @FXML
    private Button arrivalTimeButton;

    @FXML
    private Button makeRequestButton;

    @FXML
    private Button requestStatusButton;

    public void goToRequestStatusPage(ActionEvent event) throws IOException {
        Main.changeScene("request-status-page.fxml");
    }

    public void goToMakeRequestPage(ActionEvent event) throws IOException {
        Main.changeScene("make-request-page.fxml");
    }

    public void goToArrivalTimePage(ActionEvent event) throws IOException {
        Main.changeScene("arrival-time-page.fxml");
    }

    public void goToLogoutPage(ActionEvent event) throws IOException {
        Main.changeScene("logout-page.fxml");
    }

}
