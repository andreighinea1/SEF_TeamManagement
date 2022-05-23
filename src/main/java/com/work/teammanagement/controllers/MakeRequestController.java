package com.work.teammanagement.controllers;

import com.work.teammanagement.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MakeRequestController {
    @FXML
    private DatePicker startDatePicker,endDatePicker;
    @FXML
    private TextField reasonTextField;
    @FXML
    private ListView VacationListView;
    @FXML
    private ChoiceBox TypeRequestChoiceBox;
    private String[] requests = {"",""};
    @FXML
    private Button publicholidaysButton,cancelButton,sendButton;
    public void goToPublicHolidaysPage(ActionEvent event) throws IOException {
        Main.changeScene("public-holidays");
    }
    public void cancelfunction(){}
    public void sendinfo(){}
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TypeRequestChoiceBox.getItems().addAll(requests);
    }



}
