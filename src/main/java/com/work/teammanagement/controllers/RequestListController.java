package com.work.teammanagement.controllers;

import com.work.teammanagement.Main;
import com.work.teammanagement.PopupWindow;
import com.work.teammanagement.exceptions.*;
import com.work.teammanagement.model.databases.EmployeeRequestsDB;
import com.work.teammanagement.model.requests.employee.EmployeeRequest;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.util.Callback;

public class RequestListController {
    static class XCell extends ListCell<EmployeeRequest> {
        HBox hbox = new HBox();
        Label label = new Label("(empty)");
        Pane pane = new Pane();
        Button button1 = new Button("Accept");
        Button button2 = new Button("Deny");
        EmployeeRequest lastItem;

        public XCell() {
            super();
            hbox.getChildren().addAll(label, pane, button1, button2);
            HBox.setHgrow(pane, Priority.ALWAYS);
            button1.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    System.out.println(lastItem + " : " + event);
                }
            });
            button2.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    System.out.println(lastItem + " : " + event);
                }
            });
        }

        @Override
        protected void updateItem(EmployeeRequest item, boolean empty) {
            super.updateItem(item, empty);
            setText(null);  // No text in label of super class
            if (empty) {
                lastItem = null;
                setGraphic(null);
            } else {
                lastItem = item;
                label.setText(item!=null ? item.toString() : "<null>");
                setGraphic(hbox);
            }
        }
    }

    public ListView<EmployeeRequest> requestsListView;
    @FXML
    private Button backButton;

    public void initialize() throws UserNotFoundException, UserNotLoggedInException, NotEnoughPrivilegesException, ManagerMismatchException, ManagerCannotHaveRequestsException, IOException {
        requestsListView.setCellFactory(new Callback<ListView<EmployeeRequest>, ListCell<EmployeeRequest>>() {
            @Override
            public ListCell<EmployeeRequest> call(ListView<EmployeeRequest> param) {
                return new XCell();
            }
        });

        ObservableList<EmployeeRequest> observableList = null;
        try {
            observableList = FXCollections.observableArrayList(EmployeeRequestsDB.getUserRequests());
        } catch (NoEmployeeRequestsException e) {
            PopupWindow.openPopup("request-list-error");
            Main.changeScene("menu");
        }
        requestsListView.setItems(observableList);
    }

    public void goBack(ActionEvent event) throws IOException {
        Main.changeScene("menu");
    }
}
