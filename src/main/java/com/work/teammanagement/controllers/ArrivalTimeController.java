package com.work.teammanagement.controllers;

import com.work.teammanagement.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class ArrivalTimeController {

    @FXML
    private Label messageLabel;
    @FXML
    private Label detailsLabel;
    @FXML
    private HBox actionParent;
    @FXML
    private HBox okParent;
    @FXML
    private TextField arrivalTimeTextField;

    @FXML
    private Button actionButton;

    @FXML
    private Button cancelButton;

    @FXML
    private Button okButton;

    public void cantArriveTodayMethod(ActionEvent event) throws IOException {
    }

    public void cancelMethod(ActionEvent event) throws IOException {
    }

    public void okMethod(ActionEvent event) throws IOException {
    }
}
