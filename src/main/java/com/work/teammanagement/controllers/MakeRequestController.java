package com.work.teammanagement.controllers;

import com.work.teammanagement.Main;
import com.work.teammanagement.exceptions.ManagerCannotHaveRequestsException;
import com.work.teammanagement.exceptions.UserNotLoggedInException;
import com.work.teammanagement.services.LoggedInUser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MakeRequestController {
    @FXML
    private DatePicker startDatePicker, endDatePicker;
    @FXML
    private TextField reasonTextField;
    @FXML
    private ChoiceBox<String> typeRequestChoiceBox;
    private String[] requests = {"", ""};
    @FXML
    private Button publicHolidaysButton, cancelButton, sendButton;
    @FXML
    private Label availableVacationDaysLabel;

    public void goToPublicHolidaysPage(ActionEvent event) throws IOException {
        Main.changeScene("public-holidays");
    }

    public void cancel() {
        Main.changeScene("menu");
    }

    public void send() {
    }

    public void initialize() throws ManagerCannotHaveRequestsException, UserNotLoggedInException {
        availableVacationDaysLabel.setText("Available Vacations Days: " + LoggedInUser.getEmployeeAvailableHolidayDays());
        typeRequestChoiceBox.getItems().addAll(requests);
    }


}
