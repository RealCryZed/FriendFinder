package com.friendfinder;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class PrimaryController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXTextField fnameField;

    @FXML
    private JFXTextField usernameField;

    @FXML
    private JFXButton continueButton;

    @FXML
    private JFXTextField lnameField;

    @FXML
    private JFXTextField passwordField;

    @FXML
    private JFXComboBox<?> countryField;

    @FXML
    private JFXComboBox<?> cityField;

    @FXML
    private JFXTextField emailField;

    @FXML
    private JFXTextField phoneNumField;

    @FXML
    private Label exitButton;

    @FXML
    void handleButtonAction(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    void initialize(URL url, ResourceBundle rb) {


    }
}
