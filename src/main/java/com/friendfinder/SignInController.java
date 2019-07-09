package com.friendfinder;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class SignInController extends MovableApplication {

    @FXML
    private AnchorPane signInAnchorPane;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXTextField usernameField;

    @FXML
    private JFXPasswordField passwordField;

    @FXML
    private JFXButton continueButton;

    @FXML
    private JFXButton signUpButton;

    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    /*
    *   --------------------> Getters and Setters <--------------------
     */



    /*
    *   --------------------> Other functions <--------------------
     */

    @FXML
    void setCloseButton(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void setContinueButton(ActionEvent event) throws SQLException{

        MainPageController mainPageController = new MainPageController();

        mainPageController.setUsrnm(usernameField.getText());
        mainPageController.setPswrd(passwordField.getText());
        mainPageController.setDefaultParamsForMyProfile(mainPageController.getUsrnm(), mainPageController.getPswrd());
        setToContinueMethod();
    }

    @FXML
    void setSignUpButton(ActionEvent event) throws IOException {

        Parent signUpPage = FXMLLoader.load(getClass().getResource("signUpPage.fxml"));
        Scene signUpScene = new Scene(signUpPage);

        Stage window = (Stage) signInAnchorPane.getScene().getWindow();

        window.setScene(signUpScene);
        makeWindowMovable(signUpPage, window);
        window.show();
    }

    @FXML
    void initialize() {
        checkForEnterPressed();
    }

    private static void infoBox(String infoMessage, String headerText, String title){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }

    private void checkForEnterPressed() {

        continueButton.setOnAction(e -> {
            try {
                setContinueButton(e);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        signInAnchorPane.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                continueButton.fire();
                e.consume();
            }
        });
    }

    private void setToContinueMethod() {

        String username = usernameField.getText().toString();
        String password = passwordField.getText().toString();

        ConnectionToDB connectionToDB = new ConnectionToDB();
        Connection connection = connectionToDB.getConnection();

        String sql = "SELECT username, password, email, phonenumber, " +
                "country, city FROM signupinfo WHERE username = ? and password = ?";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {

                Parent mainPage = FXMLLoader.load(getClass().getResource("mainPage.fxml"));
                Scene mainPageScene = new Scene(mainPage);

                Stage window = (Stage) signInAnchorPane.getScene().getWindow();

                window.close();
                window.setScene(mainPageScene);
                window.centerOnScreen();
                makeWindowMovable(mainPage, window);
                window.show();
            } else {
                infoBox("Please enter correct Username and Password", null, "Failed");
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
