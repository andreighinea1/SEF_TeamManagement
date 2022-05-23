package com.work.teammanagement.controllers;

import com.work.teammanagement.Main;
import com.work.teammanagement.services.LoginService;
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
        Main.changeScene("list-of-requests-page");
    }

    public void goToMakeRequestPage(ActionEvent event) throws IOException {
        Main.changeScene("make-request-page");
    }

    public void goToArrivalTimePage(ActionEvent event) throws IOException {
        Main.changeScene("arrival-time-page");
    }

    public void logout(ActionEvent event) throws IOException {
        LoginService.logoutUser();
    }

}
