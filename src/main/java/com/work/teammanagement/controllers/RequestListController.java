package com.work.teammanagement.controllers;

import com.work.teammanagement.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.io.IOException;

public class RequestListController {
    public ListView requestsListView;
    @FXML
    private Button backButton;

    public void goBack(ActionEvent event) throws IOException {
        Main.changeScene("menu");
    }
}
