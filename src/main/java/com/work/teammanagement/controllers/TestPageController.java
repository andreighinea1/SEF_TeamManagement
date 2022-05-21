package com.work.teammanagement.controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;

public class TestPageController {
    public Label welcomeText;

    public void onHelloButtonClick(ActionEvent actionEvent) {
        welcomeText.setText("Heeelllllllooooo!!");
    }
}
