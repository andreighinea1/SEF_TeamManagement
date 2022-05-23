package com.work.teammanagement.controllers;

import com.work.teammanagement.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;


public class RequestsStatusListController {
    @FXML
    private Button backButton;

    public void back(ActionEvent actionEvent) {
        Main.changeScene("menu");
    }
}
