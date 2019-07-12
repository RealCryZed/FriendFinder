package com.friendfinder;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.Date;
import java.util.ResourceBundle;
import org.apache.log4j.*;

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

    private static final Logger logger = Logger.getLogger(SignInController.class.getName());
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

                Date date = new Date();
                logger.info("--------------------");
                logger.info(date.toString());
                logger.info(username);
                logger.info(password);

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

        PropertyConfigurator.configure("src/main/java/log4j.properties");
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
}
