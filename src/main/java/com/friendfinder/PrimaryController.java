package com.friendfinder;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

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
    private ImageView upButton;

    @FXML
    private ImageView downButton;

    @FXML
    private ImageView upButton1;

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
    void initialize() {
        assert fnameField != null : "fx:id=\"fnameField\" was not injected: check your FXML file 'primary.fxml'.";
        assert usernameField != null : "fx:id=\"usernameField\" was not injected: check your FXML file 'primary.fxml'.";
        assert continueButton != null : "fx:id=\"continueButton\" was not injected: check your FXML file 'primary.fxml'.";
        assert upButton != null : "fx:id=\"upButton\" was not injected: check your FXML file 'primary.fxml'.";
        assert downButton != null : "fx:id=\"downButton\" was not injected: check your FXML file 'primary.fxml'.";
        assert upButton1 != null : "fx:id=\"upButton1\" was not injected: check your FXML file 'primary.fxml'.";
        assert lnameField != null : "fx:id=\"lnameField\" was not injected: check your FXML file 'primary.fxml'.";
        assert passwordField != null : "fx:id=\"passwordField\" was not injected: check your FXML file 'primary.fxml'.";
        assert countryField != null : "fx:id=\"countryField\" was not injected: check your FXML file 'primary.fxml'.";
        assert cityField != null : "fx:id=\"cityField\" was not injected: check your FXML file 'primary.fxml'.";
        assert emailField != null : "fx:id=\"emailField\" was not injected: check your FXML file 'primary.fxml'.";
        assert phoneNumField != null : "fx:id=\"phoneNumField\" was not injected: check your FXML file 'primary.fxml'.";

    }
}