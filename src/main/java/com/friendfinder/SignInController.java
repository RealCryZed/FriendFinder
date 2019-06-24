package com.friendfinder;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class SignInController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXTextField usernameField;

    @FXML
    private JFXButton continueButton;

    @FXML
    private JFXTextField passwordField;

    @FXML
    private Label exitButton;

    @FXML
    private JFXButton signUpButton;

    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    @FXML
    void handleButtonAction(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    void setContinueButton(ActionEvent event) throws SQLException {

        String username = usernameField.getText().toString();
        String password = passwordField.getText().toString();

        ConnectionToDB connectionToDB = new ConnectionToDB();
        Connection connection = connectionToDB.getConnection();

        String sql = "SELECT * FROM signupinfo WHERE username = ? and password = ?";

        try{
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            if(!resultSet.next()){
                infoBox("Please enter correct Email and Password", null, "Failed");
            }else{
                infoBox("Login Successfull",null,"Success" );
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void infoBox(String infoMessage, String headerText, String title){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }

    @FXML
    void setSignUpButton(ActionEvent event) {

    }

    @FXML
    void initialize() {


    }
}
