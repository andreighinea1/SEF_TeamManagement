package com.work.teammanagement.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class EmployeeListController {
    @FXML
    private Button exitButton;
    @FXML
    private ListView EmployeeListView;
    public void exitApplication(){
        javafx.application.Platform.exit();
    }

}
