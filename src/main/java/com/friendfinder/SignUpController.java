package com.friendfinder;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.InputMethodEvent;
import javafx.stage.Stage;

public class SignUpController implements Initializable {

    private Set<String> countrySet = new TreeSet<>();
    private Set<String> citySet = new TreeSet<>();

    private String emptyBlock = "Country";
    private String[] countries = {"United States", "Canada", "United Kingdom", "Russia"};
    private String[][] cities = {{"New York", "San Francisco"},
            {"Toronto", "Ottawa"}, {"London", "Edinburgh"}, {"Moscow", "Omsk"}};

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

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(signInScene);
        window.show();
    }

    @FXML
    void setCityBoxToEnable(ActionEvent event) {

        setCountries();

        boolean isMyComboBoxPressed = countryField.isManaged();

        if (!isMyComboBoxPressed) {
            checkIfCountryFieldIsEmpty();
        } else {
            cityField.getSelectionModel().clearSelection();
            cityField.getItems().clear();
            checkIfCountryFieldIsEmpty();
        }

    }

    @FXML
    void setCountryFieldWhenItsChanged(InputMethodEvent event) {

        cityField.setDisable(true);
        cityField.getSelectionModel().clearSelection();
        cityField.setValue(null);

        checkIfCountryFieldIsEmpty();
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

        cityField.setDisable(true);

        setCountries();
        countryField.getItems().add("Country");

        for (String country : countrySet) {
            countryField.getItems().add(country);
        }
    }

    private void setCountries() {

        for (int i = 0; i < countries.length; i++) {
            String country = countries[i];
            String[] citiesList = cities[i];

            for (String city : citiesList) {
                citySet.add(city);
            }

            countrySet.add(country);
        }
    }

    private void checkIfCountryFieldIsEmpty() {

        boolean isMyComboBoxEmpty = countryField.getSelectionModel().isEmpty();
        String comboBoxValue = countryField.getValue();

        if (!isMyComboBoxEmpty && !(comboBoxValue == emptyBlock)) {

            for (int i = 0; i < countries.length; i++) {
                if (countries[i] == countryField.getValue()) {

                    String[] cityList = cities[i];

                    for (String city : cityList) {
                        cityField.getItems().add(city);
                    }
                }
            }

            isMyComboBoxEmpty = false;
            cityField.setDisable(false);
        } else {
            cityField.setDisable(true);
            cityField.getSelectionModel().clearSelection();
            cityField.getItems().clear();
        }
    }
}
