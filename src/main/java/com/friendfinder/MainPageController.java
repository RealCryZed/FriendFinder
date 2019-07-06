package com.friendfinder;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;

import java.io.IOException;
import java.net.URL;
import java.util.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainPageController extends MovableApplication {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane mainPageAnchorPane;

    @FXML
    private JFXComboBox<Label> languageBox;

    @FXML
    private JFXButton myProfileButton;

    @FXML
    private ImageView myProfileImage;

    @FXML
    void setBackToSignInButton(ActionEvent event) throws IOException {

        Parent signInPage = FXMLLoader.load(getClass().getResource("signInPage.fxml"));
        Scene signInPageScene = new Scene(signInPage);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.close();
        window.setScene(signInPageScene);
        window.centerOnScreen();
        makeWindowMovable(signInPage, window);
        window.show();
    }

    @FXML
    void setLanguageComboBox(ActionEvent event) {

        ListCell<Label> buttonCell = new ListCell<>() {
            @Override
            protected void updateItem(Label label, boolean isEmpty) {
                super.updateItem(label, isEmpty);

                setText(label == null ? "" : label.getText());

            }
        };
        languageBox.setButtonCell(buttonCell);
    }

    @FXML
    void setMenuProfileButton(ActionEvent event) {

    }

    @FXML
    void setMyProfileButton(ActionEvent event) {

    }

    @FXML
    void initialize() {

    }
}
