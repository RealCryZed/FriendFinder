package com.friendfinder;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class SignUpController implements Initializable {

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
    private JFXComboBox<String> countryField;

    @FXML
    private JFXComboBox<String> cityField;

    @FXML
    private JFXTextField emailField;

    @FXML
    private JFXTextField phoneNumField;

    @FXML
    void setBackToButton(ActionEvent event) throws IOException {

        Parent signInPage = FXMLLoader.load(getClass().getResource("signInPage.fxml"));
        Scene signInScene = new Scene(signInPage);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.setScene(signInScene);
        window.show();
    }

    @FXML
    public void setContinueButton(ActionEvent event) throws SQLException {

        ConnectionToDB connectionToDB = new ConnectionToDB();
        Connection connection = connectionToDB.getConnection();

        String sql = "INSERT INTO signupinfo (fname, lname, email, phonenumber, country, city, username, password) VALUES ('"
                + fnameField.getText() + "', '" + lnameField.getText() + "', '" + emailField.getText() + "', '"
                + phoneNumField.getText() + "', '" + countryField.getSelectionModel().getSelectedItem().toString() + "', '"
                + cityField.getSelectionModel().getSelectedItem().toString() + "', '"
                + usernameField.getText() + "', '" + passwordField.getText() + "'" + ");";

        Statement statement = connection.createStatement();
        statement.executeUpdate(sql);
    }

    @FXML
    public void initialize(URL url, ResourceBundle rb) {

        countryField.getItems().add("United States of America");
        countryField.getItems().add("Canada");
        countryField.getItems().add("United Kingdom");
        countryField.getItems().add("Others");

        cityField.getItems().add("New York");
        cityField.getItems().add("San Francisco");
        cityField.getItems().add("Toronto");
        cityField.getItems().add("London");
    }
}
