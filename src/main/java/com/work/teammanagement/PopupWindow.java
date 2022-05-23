package com.work.teammanagement;

import com.work.teammanagement.model.databases.UsersDB;
import com.work.teammanagement.services.PageSelector;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.*;

import java.io.IOException;

public class PopupWindow {
    private static final double popupWidth = 800;
    private static final double popupHeight = 600;

    public static void openPopup(String popupPageName) throws IOException {
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(Main.getPrimaryStage());

        Parent root = FXMLLoader.load(PageSelector.selectPage(popupPageName));
        Scene dialogScene = new Scene(root, popupWidth, popupHeight);
        dialog.setScene(dialogScene);
        dialog.show();
    }
}