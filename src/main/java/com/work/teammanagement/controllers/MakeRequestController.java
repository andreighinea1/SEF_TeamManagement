package com.work.teammanagement.controllers;

import com.work.teammanagement.Main;
import com.work.teammanagement.PopupWindow;
import com.work.teammanagement.exceptions.NotLoggedInAsEmployeeException;
import com.work.teammanagement.exceptions.NotManagerException;
import com.work.teammanagement.exceptions.UserNotFoundException;
import com.work.teammanagement.exceptions.UserNotLoggedInException;
import com.work.teammanagement.model.databases.UsersDB;
import com.work.teammanagement.services.EmployeeRequestService;
import com.work.teammanagement.services.LoggedInUser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class MakeRequestController {
    @FXML
    private DatePicker startDatePicker, endDatePicker;
    @FXML
    private TextField reasonTextField;
    @FXML
    private ChoiceBox<String> typeRequestChoiceBox;
    private String[] requests = {"Plan Holiday", "Work From Home", "Short Time Absence"};
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

    public void send() throws UserNotFoundException, IOException, NotLoggedInAsEmployeeException, UserNotLoggedInException, NotManagerException {
        String managerUsername = UsersDB.findAssignedManager(LoggedInUser.getEmployeeUsername());

        if (typeRequestChoiceBox.getValue().equals("Plan Holiday")) {
            if (convertDate(startDatePicker).equals(""))
                EmployeeRequestService.planHoliday(convertDate(startDatePicker), convertDate(startDatePicker), reasonTextField.getText(), managerUsername);
            else if (convertDate(endDatePicker).equals(""))
                EmployeeRequestService.planHoliday(convertDate(endDatePicker), convertDate(endDatePicker), reasonTextField.getText(), managerUsername);
            else
                EmployeeRequestService.planHoliday(convertDate(startDatePicker), convertDate(endDatePicker), reasonTextField.getText(), managerUsername);
        } else if (typeRequestChoiceBox.getValue().equals("Work From Home")) {

            if (convertDate(startDatePicker).equals(""))
                EmployeeRequestService.requestWorkFromHome(convertDate(startDatePicker), convertDate(startDatePicker), reasonTextField.getText(), managerUsername);
            else if (convertDate(endDatePicker).equals(""))
                EmployeeRequestService.requestWorkFromHome(convertDate(endDatePicker), convertDate(endDatePicker), reasonTextField.getText(), managerUsername);
            else
                EmployeeRequestService.requestWorkFromHome(convertDate(startDatePicker), convertDate(endDatePicker), reasonTextField.getText(), managerUsername);
        } else if (typeRequestChoiceBox.getValue().equals("Short Time Absence")) {
            EmployeeRequestService.requestShortTimeAbsence(convertDate(startDatePicker), reasonTextField.getText(), managerUsername);
        } else {
            PopupWindow.openPopup("no-option-selected-error");
            return;
        }
        Main.changeScene("menu");
    }

    public void initialize() throws NotLoggedInAsEmployeeException, UserNotLoggedInException {
        availableVacationDaysLabel.setText("Available Vacations Days: " + LoggedInUser.getEmployeeAvailableHolidayDays());
        typeRequestChoiceBox.getItems().addAll(requests);
    }

    private String convertDate(DatePicker dp) {
        if (dp.getValue() == null)
            return "";
        return dp.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
