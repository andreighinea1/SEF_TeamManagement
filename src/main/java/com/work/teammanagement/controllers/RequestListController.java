package com.work.teammanagement.controllers;

import com.work.teammanagement.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class RequestListController {
    @FXML
    private Button backButton;

    public void goToManagerMenuPage(ActionEvent event) throws IOException {
        Main.changeScene("menu-manager.fxml");
    }
}
